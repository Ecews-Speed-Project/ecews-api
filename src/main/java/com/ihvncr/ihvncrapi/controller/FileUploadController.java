package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.payload.response.FIleBatchResponse;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.service.ArtLineListDataExport;
import com.ihvncr.ihvncrapi.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FileUploadController {
    private final UploadService uploadService;

    @GetMapping("/get-uploads")
    public @ResponseBody FIleBatchResponse getAllUploads(@RequestParam(value = "page") int pageNo, @RequestParam(value = "per_page") int pageSize) {
        return uploadService.getAllUploads(pageNo, pageSize);
    }

    @PostMapping("zipped-file-upload")
    public ResponseEntity<MessageResponse> uploadZippedFile(@RequestPart MultipartFile file) throws IOException {
        return uploadService.uploadZippedFile(file, String.valueOf(System.currentTimeMillis()));
    }

    @PostMapping("patient-upload")
    public ResponseEntity<?> uploadPatientsFile(@RequestPart MultipartFile file, @RequestParam(value = "casemanager") Long casemanager) throws IOException {
        return uploadService.uploadFile(file, casemanager);
    }


    @GetMapping("/excel-export")
    public ModelAndView exportToExcel(@RequestParam String datimCode, @RequestParam String quarter) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new ArtLineListDataExport());
        List<ArtLinelist> linelists = uploadService.getAllArtLineList(datimCode, quarter);
        mav.addObject("list", linelists);
        return mav;
    }
}
