package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.payload.request.AnnouncementsRequest;
import com.ihvncr.ihvncrapi.payload.request.AppUserRequest;
import com.ihvncr.ihvncrapi.payload.request.AppointmentRescheduleRequest;
import com.ihvncr.ihvncrapi.payload.request.DemographicRequest;
import com.ihvncr.ihvncrapi.payload.response.AppuserResponse;
import com.ihvncr.ihvncrapi.service.AnnouncementService;
import com.ihvncr.ihvncrapi.service.AppUserService;
import com.ihvncr.ihvncrapi.service.AppointmentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final AppointmentScheduleService appointmentScheduleService;

    private final AnnouncementService announcementService;

    @GetMapping("/app-users")
    public @ResponseBody AppuserResponse getAllUsers(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "page") int pageNo,
            @RequestParam(value = "per_page") int pageSize
    ) {
        return appUserService.getAllUsers(pageNo, pageSize,type);
    }

    @GetMapping("/app-users-by-id")
    public @ResponseBody ResponseEntity<?> getUsers(@RequestParam(value = "id") Long id) {
        return appUserService.getUser(id);
    }

    @PostMapping("/decrpt_demographics")
    public @ResponseBody ResponseEntity<?> getUserByPatientIdentifier(
            @RequestBody DemographicRequest demographicRequest
    ) {
        return appUserService.getUserByPatientIdentifier(demographicRequest.getPatientId(),demographicRequest.getPrivateKey());
    }

    @GetMapping("/app-users-by-casemanagers")
    public @ResponseBody ResponseEntity<?> getUsersByCasemanagers(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(appUserService.getUsersByCaseManagers(id));
    }

    @PostMapping({"/register-app-user"})
    public ResponseEntity<?> createNewUser(@RequestBody AppUserRequest request) {
        return appUserService.create(request);
    }

    @PostMapping({"/app-user-login"})
    public ResponseEntity<?> appLogin(@RequestBody AppUserRequest request) {
        return appUserService.getUserByEmailAndPassword(request.getMobile(),request.getPassword());
    }

    @GetMapping({"/get-appointments-by-case-manager"})
    public ResponseEntity<?> getAppointmentsByCasemanager(  @RequestParam("id") Long caseManagerId) {
        return ResponseEntity.ok(appointmentScheduleService.getAppointments(caseManagerId));
    }

    @PostMapping({"/reschedule-appointment"})
    public ResponseEntity<?> rescheduleAppointment(@RequestBody AppointmentRescheduleRequest request) {
        return appointmentScheduleService.create(request);
    }


    @PostMapping({"/create-announcements"})
    public String announcements(
                                @RequestParam("title") String title,
                                @RequestParam("message") String message,
                                @RequestParam("announcementsType") String announcementsType,
                                @RequestParam("state") String state,
                                @RequestParam("createdDate") String createdDate,
                                @RequestParam("expireDate") String expireDate,
                                @RequestParam("file") MultipartFile multipartFile)
            throws ExecutionException,
            InterruptedException {
        AnnouncementsRequest request =  new AnnouncementsRequest();
        request.setTitle(title);
        request.setMessage(message);
        request.setAnnouncementsType(announcementsType);
        request.setState(state);
        request.setCreatedDate(createdDate);
        request.setExpireDate(expireDate);
        return announcementService.createAnnouncement(request,multipartFile);
    }

    @GetMapping({"/get-announcements"})
    public ResponseEntity<?> getAnnouncements() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(
                announcementService.getAnnouncement(), HttpStatus.OK);
    }

    @RequestMapping("/update-announcements")
    public ResponseEntity<?> updateUser(@RequestBody AnnouncementsRequest request) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(announcementService.updateAnnouncement(request));
    }

    @PostMapping("/profile/pic")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        return announcementService.upload(multipartFile);
    }
}
