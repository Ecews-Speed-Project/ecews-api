package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.model.*;
import com.ihvncr.ihvncrapi.mongo.model.Container;
import com.ihvncr.ihvncrapi.mongo.model.EncounterType;
import com.ihvncr.ihvncrapi.mongo.model.ObsType;
import com.ihvncr.ihvncrapi.payload.request.PatientRequestDto;
import com.ihvncr.ihvncrapi.payload.request.PatientSignupDto;
import com.ihvncr.ihvncrapi.repository.ArtLineListRepository;
import com.ihvncr.ihvncrapi.repository.mongo_repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.checkTreatmentGroup;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.findDifference;
import static com.ihvncr.ihvncrapi.utils.ConstantsUtils.*;
import static com.ihvncr.ihvncrapi.utils.HelperFunctions.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final ContainerRepository containerRepository;
    private final ArtLineListRepository artLineListRepository;

    private PatientInfo patientInfo;
    public PatientInfo getPatientByIdentifier(PatientRequestDto patientRequestDto) {
        String quarter = "FY23Q4";
        try {
            patientInfo = new PatientInfo();


            List<ArtLinelist> artLineList = artLineListRepository.findArtLinelistByPatientUniqueId(patientRequestDto.getPatientId());
            if (artLineList.size() > 0) {
                String ID = artLineList.stream().findFirst().get().getPatientUuid();

                // Container patients = containerRepository.findByMessageDataPatientIdentifiersIdentifier(patientSignupDto.getPatientId());
                Optional<Container> patients = containerRepository.findById(ID);
                if (patients.isPresent()) {
                    Container patient = patients.get();
                    String PatientId = patient.getMessageData().getPatientIdentifiers()
                            .stream().filter(x -> x.getIdentifierType() == PEPFAR_IDENTIFIER).findFirst().get().getIdentifier();

                    List<ObsType> obsTypeList = patient.getMessageData().getObs();

                    patientInfo.setGender(patient.getMessageData().getDemographics().getGender());
                    patientInfo.setFacilityName(patient.getMessageHeader().getFacilityName());
                    patientInfo.setFacilityCode(patient.getMessageData().getDemographics().getDatimId());
                    patientInfo.setPhoneNumber(patient.getMessageData().getDemographics().getPhoneNumber());
                    //patientInfo.(patient.getMessageData().getDemographics().getBirthdate());
                    patientInfo.setPepfarId(PatientId);
                    patientInfo.setPatientUniqueId(PatientId + "_" + patientInfo.getFacilityCode());

                    List<EncounterType> encounterTypeList = patient.getMessageData().getEncounters();

                    getEncounter(
                            encounterTypeList,
                            obsTypeList,
                            patient,
                            patientInfo);
                    return patientInfo;

                } else {
                    return null;
                }
            } else {
                return null;
            }
        }catch (Exception exception){
            return null;
        }
    }

    public EcewsPatientResponse signUp(PatientSignupDto patientSignupDto) {

        String quarter =  "FY23Q4";
        try {
            EcewsPatientResponse ecewsPatientResponse = new EcewsPatientResponse();
            PatientInfo patientInfo = new PatientInfo();
            List<EcewsIdentifierInfo> identifiers = new ArrayList<>();
            EcewsDemographicInfo demographicInfo = new EcewsDemographicInfo();
            EcewsFacilityInfo facilityInfo = new EcewsFacilityInfo();


            String ID = artLineListRepository.
                    findArtLinelistByPatientUniqueIdAndQuarter(patientSignupDto.getPatientId(), quarter)
                    .stream().findFirst().get().getPatientUuid();
            ;
            // Container patients = containerRepository.findByMessageDataPatientIdentifiersIdentifier(patientSignupDto.getPatientId());
            Optional<Container> patients = containerRepository.findById(ID);
            if (patients.isPresent()) {
                Container patient = patients.get();
                String PatientId = patient.getMessageData().getPatientIdentifiers()
                        .stream().filter(x -> x.getIdentifierType() == PEPFAR_IDENTIFIER).findFirst().get().getIdentifier();

                ecewsPatientResponse.setUniqueId(PatientId);

                EcewsIdentifierInfo ecewsIdentifierInfo = new EcewsIdentifierInfo();
                ecewsIdentifierInfo.setIdentifier(PatientId);
                ecewsIdentifierInfo.setIdentifierType("ART");
                identifiers.add(ecewsIdentifierInfo);

                demographicInfo.setFirstName(patient.getMessageData().getDemographics().getFirstName());
                demographicInfo.setLastName(patient.getMessageData().getDemographics().getLastName());
                if (patient.getMessageData().getDemographics().getBirthdate() != null) {
                    demographicInfo.setTreatmentGroup(
                            checkTreatmentGroup(
                                    convertDate(patient.getMessageData().getDemographics().getBirthdate()),
                                    LocalDate.now()
                            ));
                } else {
                    demographicInfo.setTreatmentGroup("UNKnown");
                }

                demographicInfo.setDob(convertDate(patient.getMessageData().getDemographics().getBirthdate()));
                demographicInfo.setPhoneNumber(patient.getMessageData().getDemographics().getPhoneNumber());

                facilityInfo.setFacilityCode(patient.getMessageData().getDemographics().getDatimId());
                facilityInfo.setFacilityName(patient.getMessageHeader().getFacilityName());

                ecewsPatientResponse.setDemographicInfo(demographicInfo);
                ecewsPatientResponse.setFacilityInfo(facilityInfo);
                ecewsPatientResponse.setIdentifiers(identifiers);

                return ecewsPatientResponse;
            } else {
                return null;
            }
        }catch (Exception ex){
            return  null;
        }
    }

    public void getEncounter(List<EncounterType> encounterTypeList,  List<ObsType> obsTypeList,  Container patient, PatientInfo patientInfo ){
        List<PharmacyEncounter> pharmEncounter = new ArrayList<>();
        List<LabEncounter> labEncounters = new ArrayList<>();
        List<ClinicEncounter> clinicEncounters = new ArrayList<>();
        for (EncounterType encounterType : encounterTypeList) {
            if (encounterType.getEncounterTypeId() == PHARMACY_ENCOUNTER_TYPE) {

                PharmacyEncounter p = new PharmacyEncounter();
                p.setEncounterDate(convertDate(encounterType.getEncounterDatetime()));
                pharmEncounter.add(p);

                //Calculate days of refill and next appointment date
                BigDecimal daysOfRefill = getValueNumeric(encounterType.getEncounterId(), DAYS_REFILL, patient);
                LocalDate returnDate = convertDate(encounterType.getEncounterDatetime()).plusDays(daysOfRefill.intValue());

                p.setDaysOfRefill(daysOfRefill);

                p.setTitle("DP Appointment");
                p.setEncounterType(PHARMACY_ENCOUNTER_TYPE);
                p.setEncounterId(encounterType.getEncounterId());

                Optional regimenLine = getObsValue(encounterType.getEncounterId(), obsTypeList, CURRENT_REGIMEN);
                if(regimenLine.isPresent()){
                    ObsType regimenLineValue = (ObsType) regimenLine.get();
                    Optional regimen = getObsValue(encounterType.getEncounterId(), obsTypeList, regimenLineValue.getValueCoded());
                    ObsType regimenValue = (ObsType) regimen.get();
                    p.setCurrentRegimen(regimenValue.getVariableValue());
                }

                p.setReturnDate(returnDate);
            }

            if (encounterType.getEncounterTypeId() == LAB_ENCOUNTER_TYPE) {
                LocalDate returnDate = convertDate(encounterType.getEncounterDatetime()).plusMonths(6);
                LabEncounter l = new LabEncounter();
                l.setEncounterDate(convertDate(encounterType.getEncounterDatetime()));
                labEncounters.add(l);
                l.setEncounterType(LAB_ENCOUNTER_TYPE);
                l.setEncounterId(encounterType.getEncounterId());
                l.setTitle("VL Appointment");
                l.setReturnDate(returnDate);
                l.setViralload(getValueNumeric(encounterType.getEncounterId(), VIRAL_LOAD_CONCEPT, patient));
            }

            if (encounterType.getEncounterTypeId() == CARE_CARD_TYPE) {
                ClinicEncounter c = new ClinicEncounter();
                Optional appt = getObsValue(encounterType.getEncounterId(), obsTypeList, CARE_CARD_CONCEPT);
                ObsType apptDate = (ObsType) appt.get();
                c.setEncounterDate(convertDate(encounterType.getEncounterDatetime()));
                clinicEncounters.add(c);
                c.setEncounterType(CARE_CARD_TYPE);
                c.setEncounterId(encounterType.getEncounterId());
                c.setTitle("Clinic Appointment");
                c.setReturnDate(convertDate(apptDate.getValueDatetime()));
            }
        }
        int i = 0;
        int j = 0;
        int k = 0;
        Collections.sort(pharmEncounter, (o1, o2) -> o1.getEncounterDate().compareTo(o2.getEncounterDate()));

        for (PharmacyEncounter e : pharmEncounter) {
            if(i++ == pharmEncounter.size() - 1){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    Date date1 = simpleDateFormat.parse(e.getReturnDate().toString() );
                    Date date2 = simpleDateFormat.parse(LocalDate.now().toString());
                    if( date1.after(date2)) {
                        e.setStatus("Missed");
                    }else{
                        e.setStatus("Upcoming");
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                e.setStatus("Completed");
            }

        }

        Collections.sort(labEncounters, (o1, o2) -> o1.getEncounterDate().compareTo(o2.getEncounterDate()));
        for (LabEncounter e : labEncounters) {
            if(j++ == labEncounters.size() - 1){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    Date date1 = simpleDateFormat.parse(e.getReturnDate().toString() );
                    Date date2 = simpleDateFormat.parse(LocalDate.now().toString());
                    if( date1.after(date2)) {
                        e.setStatus("Missed");
                    }else{
                        e.setStatus("Upcoming");
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }else{
                e.setStatus("Completed");
            }

        }

        Collections.sort(clinicEncounters, (o1, o2) -> o1.getEncounterDate().compareTo(o2.getEncounterDate()));
        for (ClinicEncounter e : clinicEncounters) {
            if(k++ == clinicEncounters.size() - 1){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    Date date1 = simpleDateFormat.parse(e.getReturnDate().toString() );
                    Date date2 = simpleDateFormat.parse(LocalDate.now().toString());
                    if( date1.after(date2)) {
                        e.setStatus("Missed");
                    }else{
                        e.setStatus("Upcoming");
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }else{
                e.setStatus("Completed");
            }

        }
        patientInfo.setPharmacyEncounter(pharmEncounter);
        patientInfo.setLabEncounter(labEncounters);
        patientInfo.setClinicEncounters(clinicEncounters);
    }
}
