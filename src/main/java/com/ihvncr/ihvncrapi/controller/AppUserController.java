package com.ihvncr.ihvncrapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihvncr.ihvncrapi.model.BiometricInfo;
import com.ihvncr.ihvncrapi.payload.request.AnnouncementsRequest;
import com.ihvncr.ihvncrapi.payload.request.AppUserRequest;
import com.ihvncr.ihvncrapi.payload.request.AppointmentRescheduleRequest;
import com.ihvncr.ihvncrapi.payload.request.DemographicRequest;
import com.ihvncr.ihvncrapi.payload.response.AppuserResponse;
import com.ihvncr.ihvncrapi.payload.response.GeneralResponse;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.service.AnnouncementService;
import com.ihvncr.ihvncrapi.service.AppUserService;
import com.ihvncr.ihvncrapi.service.AppointmentScheduleService;
import com.ihvncr.ihvncrapi.utils.GeneralMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final AppointmentScheduleService appointmentScheduleService;
    ObjectMapper mapper;
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

    @GetMapping("/app-users-by-casemanager")
    public @ResponseBody ResponseEntity<?> getUsersByCasemanagers(@RequestParam(value = "id") Long id) {
        GeneralResponse generalResponse =  new GeneralResponse();
        if(appUserService.getUsersByCaseManagers(id).size() > 0){
            generalResponse.setMessage("success");
            generalResponse.setData(appUserService.getUsersByCaseManagers(id));
            return ResponseEntity.ok(generalResponse);
        }else{
            generalResponse.setMessage("Patients not Found");
            return ResponseEntity.status(201).body(generalResponse);
        }

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
        GeneralResponse generalResponse =  new GeneralResponse();
        generalResponse.setMessage("success");
        generalResponse.setData(appointmentScheduleService.getAppointments(caseManagerId));
        return ResponseEntity.ok(generalResponse);
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


    @GetMapping("/pbs")
    public void getPatientPBSInfo() throws UnirestException {

        RestTemplate restTemplate = new RestTemplate();
        String PBS_RECAPTURE_URL = "05a8cf33-aae0-4e02-8ea3-02cfc833807a";


      /*  BiometricInfo[] forObject =
                restTemplate.getForEntity(PBS_RECAPTURE_URL, BiometricInfo[].class).getBody();
*/
      //  JSONObject appsResponse = Unirest.get(PBS_RECAPTURE_URL).asJson().getBody().getObject();


        String response = Unirest.get("http://localhost:2018/api/FingerPrint/CheckForPreviousCapture")
                .header("Content-Type", "application/json").queryString("PatientUUID", PBS_RECAPTURE_URL).asString().getBody();


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        BiometricInfo[] n =  gson.fromJson(response, BiometricInfo[].class);


        System.out.println(n.length);
     /*   Gson gson = new Gson();
        ResponseEntity<String> pbsInfo = GeneralMapper.requestPbsInfo("05a8cf33-aae0-4e02-8ea3-02cfc833807a");
        if (pbsInfo != null && pbsInfo.getStatusCodeValue() == 200 && pbsInfo.getBody() != null) {
*/
           // Map<String, String> re =  new HashMap<>();
            //re.put("pbs", pbsInfo.getBody());

            //	BiometricInfo[] langs = mapper.readValue(pbsInfo.getBody(), BiometricInfo[].class);
            //List<BiometricInfo> langList = new ArrayList(Arrays.asList(langs));
           // String jsond = new Gson().toJson(pbsInfo.getBody());
          //  JSONObject outerObject = new JSONObject(jsond);
          //  JSONArray jsonArray = outerObject.getJSONArray("pbs");
           // System.out.println(jsonArray.length());

          /*  for (int i = 0, size = jsonArray.length(); i < size; i++) {
                JSONObject objectInArray = jsonArray.getJSONObject(i);

                // "...and get thier component and thier value."
                String[] elementNames = JSONObject.getNames(objectInArray);
                System.out.printf("%d ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
                for (String elementName : elementNames) {
                    String value = objectInArray.getString(elementName);
                    System.out.printf("name=%s, value=%s\n", elementName, value);
                }
                System.out.println();
            }*/




    }

}
