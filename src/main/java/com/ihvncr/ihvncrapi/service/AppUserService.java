package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.ihvncr.ihvncrapi.exception.UserNotFoundException;
import com.ihvncr.ihvncrapi.interfaces.IAppUser;
import com.ihvncr.ihvncrapi.model.*;
import com.ihvncr.ihvncrapi.model.dto.AppUserData;
import com.ihvncr.ihvncrapi.payload.request.AppUserRequest;
import com.ihvncr.ihvncrapi.payload.request.PatientRequestDto;
import com.ihvncr.ihvncrapi.payload.response.AppuserResponse;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.repository.*;
import com.ihvncr.ihvncrapi.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.getCurrentQuarterCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService implements IAppUser {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;
    private final CaseManagerUserRepository caseManagerUserRepository;
    private final ArtLineListRepository artLineListRepository;
    private final PatientService patientService;
    private  PatientInfo patientInfo;
    private  PatientRequestDto patientRequestDto;
    private  final DemographicRepository demographicRepository;

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;

    public ResponseEntity<MessageResponse> create(AppUserRequest request) {
        if(request.getUserType().equals("patient" ) ){
            if (request.getArtNumber() == null) {
                    return ResponseEntity
                            .status(201)
                            .body(new MessageResponse("ART number Cannot be empty!"));
            }
           return createPatients(request);
        }else{
            return createCaseManagers(request);
        }
    }
    public ResponseEntity<MessageResponse> createPatients(AppUserRequest request) {
        String quarter = "FY23Q4";
        ArtLinelist artLinelist =  null;
        AppUser mobileExisit = appUserRepository.findByMobile(request.getMobile()).orElse(null);
        AppUser emailExists = appUserRepository.findByEmail(request.getEmail()).orElse(null);
        AppUser patient = appUserRepository.findByPatientIdentifier(request.getArtNumber()).orElse(null);
        if(mobileExisit !=null){
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("mobile already taken!"));
        }

        if(emailExists !=null){
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("email already taken!"));
        }

        try {
            artLinelist = artLineListRepository.
                    findArtLinelistByPatientUniqueIdAndQuarter(request.getArtNumber(),quarter)
                    .stream().findFirst().get();
            if(artLinelist == null) {
                return ResponseEntity
                        .status(201)
                        .body(new MessageResponse("No patient with this ART number"));
            }else{
                if(patient == null) {
                    patient = new AppUser();
                }
                patient.setMobile(request.getMobile());
                patient.setFirstName(request.getFirstName());
                patient.setLastName(request.getLastName());
                patient.setEmail(request.getEmail());

                patient.setIsPatient(Boolean.TRUE);
                patient.setPatientIdentifier(request.getArtNumber());
                patient.setPatientUniqueId(artLinelist.getPatientUuid());
                patient.setPassword(getEncodedPassword(request.getPassword()));
                patient.setQrId(request.getQrId());
                patient.setUserType(request.getUserType());
                appUserRepository.save(patient);
                return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
            }
        }catch (Exception e){
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("No patient with this ART number"));
        }

    }
    public ResponseEntity<MessageResponse> createCaseManagers(AppUserRequest request) {
        AppUser mobileExist = appUserRepository.findByMobile(request.getMobile()).orElse(null);
        AppUser emailExists = appUserRepository.findByEmail(request.getEmail()).orElse(null);

        if(mobileExist !=null){
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("mobile already taken!"));
        }

        if(emailExists !=null){
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("email already taken!"));
        }

        AppUser  appUser  = new AppUser();
        appUser.setMobile(request.getMobile());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setEmail(request.getEmail());
        appUser.setIsPatient(Boolean.FALSE);
        appUser.setPassword(getEncodedPassword("Pass@1234#"));
        appUser.setUserType(request.getUserType());
        appUser.setPassIsDefault(Boolean.TRUE);
        appUserRepository.save(appUser);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public AppUser update(AppUserRequest request) {
        AppUser user = appUserRepository.findById(request.getId()).orElse(null);
        if (user != null) {
            user.setMobile(request.getMobile());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(getEncodedPassword(request.getPassword()));
            return appUserRepository.save(user);
        } else
            throw new UserNotFoundException("User not found");
    }
    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public ResponseEntity<?> getUserByEmailAndPassword(String mobile, String password) {
       // String quarter = getCurrentQuarterCode(LocalDate.now());
        String quarter = "FY23Q4";
        try {
            AppUserData appUserData = new AppUserData();
            ArtLinelist artLinelist = null;
            patientRequestDto = new PatientRequestDto();
            AppUser appUser = appUserRepository.findByMobile(mobile).orElse(null);
            if (appUser == null) {
                return ResponseEntity
                        .status(201)
                        .body(new MessageResponse("Invalid mobile or password"));
            }

            appUserData.setId(appUser.getId());
            appUserData.setEmail(appUser.getEmail());
            appUserData.setMobile(appUser.getMobile());
            appUserData.setFirstName(appUser.getFirstName());
            appUserData.setLastName(appUser.getLastName());
            appUserData.setPatientIdentifier(appUser.getPatientIdentifier());
            appUserData.setPatientUniqueId(appUser.getPatientUniqueId());
            appUserData.setUserType(appUser.getUserType());
            appUserData.setCohort(appUser.getNameOfChort());
            Long totalPatients = appUserRepository.findAppUserByMasterId(appUser.getId()).stream().count();
            appUserData.setTotalPatient(totalPatients);

            if (appUser.getPatientIdentifier() != null && appUser.getIsPatient() == true) {
                try {
                    artLinelist = artLineListRepository.
                            findArtLinelistByPatientUniqueIdAndQuarter(appUser.getPatientIdentifier(), quarter)
                            .stream().findFirst().get();

                } catch (Exception e) {
                    return ResponseEntity
                            .status(201)
                            .body(new MessageResponse("No patient with this ART number"));
                }


                appUserData.setCurrentRegimen(artLinelist.getCurrentRegimen());
                appUserData.setCurrentViralLoad(artLinelist.getCurrentViralLoad());
                appUserData.setCurrentViralLoadDate(artLinelist.getViralLoadEncounterDate());
                appUserData.setLastPickUpdate(artLinelist.getLastPickupDate());
                appUserData.setDaysOfRefill(artLinelist.getDaysOfArvRefil());
                appUserData.setStatus(artLinelist.getCurrentArtStatus());
                LocalDate returnDate = artLinelist.getLastPickupDate().plusDays(28 + artLinelist.getDaysOfArvRefil().intValue());
                appUserData.setNextAppointment(returnDate);
                appUserData.setPatient(true);
            }
            return ResponseEntity
                    .ok()
                    .body(appUserData);
        }catch(Exception e) {
            return ResponseEntity
                    .status(201)
                    .body(new MessageResponse("Invalid entry"));
        }
    }

    @Override
    public List<AppUser> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<AppUser> pagedResult = appUserRepository.findAll(paging);
        return pagedResult.toList();
    }

    public AppuserResponse getAllUsers(int pageNo, int pageSize, String type) {
        AppuserResponse appuserResponse = new AppuserResponse();

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<AppUser> pageUsers = appUserRepository.findByUserType(type, paging);


        List<AppUser> data = appUserRepository.findAll();
        long totalRows = appUserRepository.count();

        long totalCaseManagers= data.stream()
                .filter(c -> c.getUserType().equals("casemanager"))
                .count();
        long totalPatients= data.stream()
                .filter(c -> c.getUserType()=="patient")
                .count();
        appuserResponse.setTotalUsers(totalRows);
        appuserResponse.setTotalCaseManagers(totalCaseManagers);
        appuserResponse.setTotalPatients(totalPatients);
        appuserResponse.setTotalMappedPatients(0);
        appuserResponse.setTotalUnmappedPatients(totalRows - appuserResponse.getTotalMappedPatients());
        appuserResponse.setUsers(pageUsers);
        return appuserResponse;
    }

    public ResponseEntity<?> getUser(Long userId) {
        Optional<AppUser>  appUser  =   appUserRepository.findById(userId);
        if(appUser.isPresent()){
          return   ResponseEntity
                    .ok()
                    .body(appUser);
        }else {
            return ResponseEntity
                    .ok()
                    .body("invalid User");
        }
    }

    public ResponseEntity<?> getUserByPatientIdentifier(String PatientId, String privateKey) {
        if(privateKey.equals("smartlytics#$@") ) {
            List<Demographics> artLinelist = demographicRepository.
                    findDemographicsByPepid(PatientId);
            if (artLinelist != null) {
                return ResponseEntity
                        .ok()
                        .body(artLinelist);
            } else {
                return ResponseEntity
                        .ok()
                        .body("invalid User");
            }
        }else{
            return ResponseEntity
                    .ok()
                    .body("invalid key");
        }
    }

    public List <AppUser> getUsersByCaseManagers(Long caseManagersId) {
       List <AppUser> pateintsList = appUserRepository.findAppUserByMasterId(caseManagersId);
       return pateintsList;
    }

}
