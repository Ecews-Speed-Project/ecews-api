package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.interfaces.IFileBatch;
import com.ihvncr.ihvncrapi.model.*;
import com.ihvncr.ihvncrapi.payload.response.FIleBatchResponse;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.repository.ArtLineListRepository;
import com.ihvncr.ihvncrapi.repository.CaseManagerUserRepository;
import com.ihvncr.ihvncrapi.repository.FacilityRepository;
import com.ihvncr.ihvncrapi.repository.FileBatchRepository;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import com.ihvncr.ihvncrapi.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import static com.ihvncr.ihvncrapi.enums.FileBatchStatus.QUEUED;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService implements IFileBatch {
    private final FileBatchRepository fileBatchRepository;
    private final ArtLineListRepository artLineListRepository;
    private final FacilityRepository facilityRepository;
    private  final CaseManagerUserRepository caseManagerUserRepository;
    private final UserUtil userUtil;
    private static final String zippedFolder = "ZippedFilesFolder";
    private static final String desktopPath;
    private static final String folderPath;
    private static final String os = System.getProperty("os.name").toLowerCase();

    static {
        if (os.contains("windows")) {
            desktopPath = System.getenv("USERPROFILE") + "\\Desktop\\";
        } else {
            desktopPath = System.getProperty("user.home") + "/Desktop/";
        }
        folderPath = desktopPath + zippedFolder;
        File folder = new File(desktopPath);

        File dir = new File(folder, zippedFolder);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Unable to create directory " + dir.getAbsolutePath());
        }
    }


    public FIleBatchResponse getAllUploads(int pageNo, int pageSize) {
        User user = userUtil.getLoggedInUser();
        FIleBatchResponse fIleBatchResponse = new FIleBatchResponse();


        //Get upload files stats
        List<FileBatch> fileBatchList = findPaginated(pageNo, pageSize);
        //Get uploaded files data
//        List<FileBatch>  fileBatchStats = findPaginated(CommonUtils.STARTING_PAGE_NUMBER, totalRows);

        if (user.getRole().stream().anyMatch(role -> role.getId() == CommonUtils.STATE_ADMIN)) {
            long totalRows = fileBatchRepository.countByUser(user);
            fIleBatchResponse.setTotalRows(totalRows);
            fIleBatchResponse.setUploaded(totalRows);
            fIleBatchResponse.setProcessing(fileBatchRepository.countByUserAndStatus(user, CommonUtils.UPLOADED_PROCESSING));
            fIleBatchResponse.setProcessed(fileBatchRepository.countByUserAndStatus(user, CommonUtils.UPLOADED_PROCESSED));
            fIleBatchResponse.setQueued(fileBatchRepository.countByUserAndStatus(user, CommonUtils.UPLOADED_QUEUED));
            fIleBatchResponse.setFailed(fileBatchRepository.countByUserAndStatus(user, CommonUtils.UPLOADED_FAILED));
        } else {
            long totalRows = fileBatchRepository.count();
            fIleBatchResponse.setTotalRows(totalRows);
            fIleBatchResponse.setUploaded(totalRows);
            fIleBatchResponse.setProcessing(fileBatchRepository.countByStatus(CommonUtils.UPLOADED_PROCESSING));
            fIleBatchResponse.setProcessed(fileBatchRepository.countByStatus(CommonUtils.UPLOADED_PROCESSED));
            fIleBatchResponse.setQueued(fileBatchRepository.countByStatus(CommonUtils.UPLOADED_QUEUED));
            fIleBatchResponse.setFailed(fileBatchRepository.countByStatus(CommonUtils.UPLOADED_FAILED));
        }
//        fIleBatchResponse.setUploaded(fileBatchStats.size());
//        fIleBatchResponse.setProcessing(fileBatchStats.stream()
//                .filter(i->i.getStatus().equals(CommonUtils.UPLOADED_PROCESSING))
//                .count());
//        fIleBatchResponse.setProcessed(fileBatchStats.stream()
//                .filter(i->i.getStatus().equals(CommonUtils.UPLOADED_PROCESSED))
//                .count());
//        fIleBatchResponse.setQueued(fileBatchStats.stream()
//                .filter(i->i.getStatus().equals( CommonUtils.UPLOADED_QUEUED))
//                .count());
//        fIleBatchResponse.setFailed(fileBatchStats.stream()
//                .filter(fileBatch -> fileBatch.getStatus().equals(CommonUtils.UPLOADED_FAILED))
//                .count());
        fIleBatchResponse.setFileBatch(fileBatchList);
        return fIleBatchResponse;
    }

    @Override
    public List<FileBatch> findPaginated(int pageNo, int pageSize) {

        List<FileBatch> fileBatches;
        User user = userUtil.getLoggedInUser();
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (user.getRole().stream().anyMatch(role -> role.getId() == CommonUtils.ADMIN_ROLE || role.getId() == CommonUtils.MANDE)) {
            fileBatches = fileBatchRepository.findAllByOrderByUploadDateDesc(paging).toList();
        } else
            fileBatches = fileBatchRepository.findAllByUserOrderByUploadDateDesc(user, paging).toList();
        return fileBatches;
    }

    public ResponseEntity<MessageResponse> uploadZippedFile(MultipartFile zippedFile, String batchNumber)
            throws IOException {
        if (zippedFile != null) {
            User user = userUtil.getLoggedInUser();

            String originalFileName = zippedFile.getOriginalFilename();
            String fileName = batchNumber + "_" + originalFileName;
            Facility facility = null;
            if (originalFileName != null) {
                facility = getFacility(originalFileName);
            }

            File file = convertMultipartToFile(zippedFile, fileName);
            if (file != null) {
                if (checkIfUploadedFileIsZipped(file)) {
                    saveFileBatch(batchNumber, user, file, facility);
                    return new ResponseEntity<>(new MessageResponse("Upload successful"), OK);
                } else {
                    log.error("File is not zipped");
                    throw new ZipException("File is not zipped");
                }
            }
        }
        throw new IOException("File is null");
    }

    public ResponseEntity<MessageResponse> uploadFile(MultipartFile zippedFile, Long casemanager) {
        try {
            if (zippedFile != null) {
                User user = userUtil.getLoggedInUser();

                String originalFileName = zippedFile.getOriginalFilename();
                String fileName = casemanager + "_" + originalFileName;
                Workbook workbook = null;

                workbook = new XSSFWorkbook(zippedFile.getInputStream());


                Sheet sheet = workbook.getSheet("Data");
                Iterator<Row> rows = sheet.iterator();
                List<CaseManagerUser> caseManagerUsers = new ArrayList<CaseManagerUser>();
                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();

                    // skip header
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }

                    Iterator<Cell> cellsInRow = currentRow.iterator();

                    CaseManagerUser caseManagerUser = new CaseManagerUser();
                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();

                        switch (cellIdx) {
                            case 0:
                                caseManagerUser.setCaseManagerId(casemanager);
                                caseManagerUser.setArtNumber(currentCell.getStringCellValue());
                                break;

                      /*  case 1:
                            tutorial.setTitle(currentCell.getStringCellValue());
                            break;

                        case 2:
                            tutorial.setDescription(currentCell.getStringCellValue());
                            break;

                        case 3:
                            tutorial.setPublished(currentCell.getBooleanCellValue());
                            break;*/

                            default:
                                break;
                        }

                        cellIdx++;
                    }

                    caseManagerUsers.add(caseManagerUser);
                }
                caseManagerUserRepository.saveAll(caseManagerUsers);
                workbook.close();
            }
            return ResponseEntity.ok(new MessageResponse("User mapped successfully!"));
        } catch (IOException e) {
            return ResponseEntity.ok(new MessageResponse("failed"));
        }

    }

    private Facility getFacility(String fileName) {
        String datimCode = fileName.split("_")[1];
        return facilityRepository.findByDatimCode(datimCode)
                .orElseThrow(() -> new NoSuchElementException("Invalid datim code"));
    }

    private void saveFileBatch(String batchNumber, User user, File path, Facility facility) {
        FileBatch fileBatch = FileBatch.builder()
                .batchNumber(batchNumber)
                .zipFileName(path.getAbsolutePath())
                .user(user)
                .uploadDate(LocalDateTime.now())
                .status(QUEUED.name())
                .facility(facility)
                .build();
        fileBatchRepository.save(fileBatch);
    }

    private boolean checkIfUploadedFileIsZipped(File file) throws IOException {
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File convertedFile;
        try (FileOutputStream fileOutputStream = new FileOutputStream(folderPath + "/" + fileName)) {
            convertedFile = new File(folderPath + "/" + fileName);
            fileOutputStream.write(multipartFile.getBytes());
        }
        return convertedFile;
    }

    public List<ArtLinelist> getAllArtLineList(String datimCode, String quarter) {
        List<ArtLinelist> linelists = null;
        try {
            linelists = artLineListRepository.findArtLinelistsByDatimCodeAndQuarter(datimCode, quarter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linelists;
    }
}
