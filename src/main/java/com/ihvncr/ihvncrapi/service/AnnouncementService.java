package com.ihvncr.ihvncrapi.service;

import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.ihvncr.ihvncrapi.IhvncrApiApplication;
import com.ihvncr.ihvncrapi.payload.request.AnnouncementsRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.dateToLong;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.stringToLocalDate;

@Service
public class AnnouncementService {
    public String createAnnouncement(AnnouncementsRequest announcements,MultipartFile multipartFile) throws ExecutionException, InterruptedException {
        LocalDateTime dateCreated = stringToLocalDate(announcements.getCreatedDate());
        LocalDateTime  expireDate = stringToLocalDate(announcements.getExpireDate());

        announcements.setExpireDate(String.valueOf(dateToLong(expireDate)));
        announcements.setCreatedDate(String.valueOf(dateToLong(dateCreated)));
        String imageUrl = upload(multipartFile);
        if(imageUrl == "failed"){
            return "Unavaliable network";
        }
        announcements.setImage(upload(multipartFile));
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("announcement").document().set(announcements);
        return  collectionApiFuture.get().getUpdateTime().toString();
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("ecews-db.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        ClassLoader classLoader = IhvncrApiApplication.class.getClassLoader();
        File googleFile = new File(Objects.requireNonNull(classLoader.getResource("ecews-db.json")).getFile());
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(googleFile.getAbsolutePath()));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/ecews-db.appspot.com/o/%s?alt=media"
                , URLEncoder.encode(fileName, "UTF-8"));
    }

    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();
            return TEMP_URL;                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public List<AnnouncementsRequest> getAnnouncement() throws ExecutionException, InterruptedException {

        List<AnnouncementsRequest> announcementsRequestList =  new ArrayList<>();
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> collectionApiFuture = dbFireStore.collection("announcement").get();
        List<QueryDocumentSnapshot> documents = collectionApiFuture.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            AnnouncementsRequest announcementsRequest =  document.toObject(AnnouncementsRequest.class);
            announcementsRequest.setId(document.getId());
            announcementsRequestList.add(announcementsRequest);
        }
        return announcementsRequestList;
    }

    public String updateAnnouncement(AnnouncementsRequest announcements) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("announcement").document(announcements.getId()).set(announcements);
        return  collectionApiFuture.get().getUpdateTime().toString();
    }
}
