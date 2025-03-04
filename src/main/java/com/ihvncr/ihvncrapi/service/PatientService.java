package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.model.*;
import com.ihvncr.ihvncrapi.mongo.model.Container;
import com.ihvncr.ihvncrapi.mongo.model.EncounterType;
import com.ihvncr.ihvncrapi.mongo.model.ObsType;
import com.ihvncr.ihvncrapi.payload.request.PatientRequestDto;
import com.ihvncr.ihvncrapi.payload.request.PatientSignupDto;
import com.ihvncr.ihvncrapi.repository.ArtLineListRepository;
import com.ihvncr.ihvncrapi.repository.AppointmentListRepository;
import com.ihvncr.ihvncrapi.repository.mongo_repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.checkTreatmentGroup;
import static com.ihvncr.ihvncrapi.utils.CommonUtils.covertToLongDate;
import static com.ihvncr.ihvncrapi.utils.ConstantsUtils.*;
import static com.ihvncr.ihvncrapi.utils.HelperFunctions.*;
import static com.ihvncr.ihvncrapi.utils.HelperFunctions.convertToDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final ContainerRepository containerRepository;
    private final ArtLineListRepository artLineListRepository;

    private final AppointmentListRepository appointmentListRepository;

    private PatientInfo patientInfo;

    private  PatientDemographics patientDemographics;

    public PatientDemographics getPatientDemographicsByIdentifier(PatientRequestDto patientRequestDto) {
        try {
            patientDemographics = new PatientDemographics();
            AppointmentList patient = appointmentListRepository.findAppointmentListByPepid(patientRequestDto.getPatientId());
            patientDemographics.setGender(patient.getSex());

            patientDemographics.setBp(patient.getBp());
            patientDemographics.setHeight(patient.getHeight());
            patientDemographics.setWeight(patient.getWeight().toString());
            patientDemographics.setOccupation(patient.getQualification());
            patientDemographics.setCurrentAge(patient.getCurrentAge());
            patientDemographics.setDob(patient.getDob());
            patientDemographics.setIdentifierType("Pepfer");
            patientDemographics.setPatientTpe("Non KP");
            patientDemographics.setPayForHealthCare("No");
            patientDemographics.setTreatmentGroup("ART");
            patientDemographics.setDateOfDiagnosis(patient.getArtstartdate());
            patientDemographics.setFacilityName(patient.getFacilityname());
            patientDemographics.setFacilityCode(patient.getDatimCode());
            patientDemographics.setPhoneNumber(patient.getPhoneno());
            //patientInfo.(patient.getMessageData().getDemographics().getBirthdate());
            patientDemographics.setPepfarId(patient.getPepid());
            patientDemographics.setPatientUniqueId(patient.getPepid() + "_" + patientInfo.getFacilityCode());



            PharmacyEncounter p = new PharmacyEncounter();
            p.setDaysOfRefill(convertToDecimal(patient.getDaysofarvrefill()));
            p.setTitle("VL Appointment");
            p.setEncounterType(PHARMACY_ENCOUNTER_TYPE);
            p.setEncounterId(covertToLongDate(patient.getPharmacyLastpickupdate())+patient.getId());
            p.setCurrentRegimen(patient.getCurrentartregimen());
            p.setEncounterDate(patient.getPharmacyLastpickupdate());
            p.setReturnDate(patient.getDpNextAppointment());
            p.setClinician("Dr MC Adetola");
            LocalDate pNextDate = LocalDate.parse(patient.getDpNextAppointment().toString());

            Period period = Period.between(LocalDate.now(), pNextDate);
            if (period.getDays() == 0) {
                p.setStatus("Completed");
            } else if (period.getDays() < 1) {
                p.setStatus("Missed");
            } else if (period.getDays() > 1) {
                p.setStatus("Upcoming");
            }

            LabEncounter l = new LabEncounter();
            l.setTitle("VL Appointment");
            l.setEncounterType(LAB_ENCOUNTER_TYPE);
            l.setEncounterId(covertToLongDate(patient.getDateofcurrentviralload())+2+patient.getId());
            l.setEncounterDate(patient.getDateofcurrentviralload());
            l.setReturnDate(patient.getVlNextAppointmentDate());
            l.setViralload(convertToDecimal(patient.getCurrentviralload()));
            l.setClinician("Dr MC Adetola");
            LocalDate lNextDate = LocalDate.parse(patient.getDateofcurrentviralload().toString());

            Period lPeriod = Period.between(LocalDate.now(), lNextDate);
            if (lPeriod.getDays() == 0) {
                l.setStatus("Completed");
            } else if (period.getDays() < 1) {
                l.setStatus("Missed");
            } else if (period.getDays() > 1) {
                l.setStatus("Upcoming");
            }

            Cd4Encounter cd4 = new Cd4Encounter();
            cd4.setTitle("CD4 Appointment");
            cd4.setEncounterType(CD4_ENCOUNTER_TYPE);
            cd4.setEncounterId(covertToLongDate(patient.getFirstCd4Date())+2+patient.getId());
            cd4.setEncounterDate(patient.getFirstCd4Date());
            cd4.setReturnDate(patient.getVlNextAppointmentDate());
            cd4.setCd4(convertToDecimal( patient.getFirstCd4()));
            cd4.setClinician("Dr MC Adetola");
            LocalDate lNextCd4Date = LocalDate.parse(patient.getFirstCd4Date().toString());
            Period cd4Period = Period.between(LocalDate.now(), lNextCd4Date);
            if (cd4Period.getDays() == 0) {
                cd4.setStatus("Completed");
            } else if (period.getDays() < 1) {
                cd4.setStatus("Missed");
            } else if (period.getDays() > 1) {
                cd4.setStatus("Upcoming");
            }


            ClinicEncounter c = new ClinicEncounter();
            c.setTitle("Clinic Appointment");
            c.setEncounterType(CARE_CARD_TYPE);
            c.setEncounterId(covertToLongDate(patient.getPharmacyLastpickupdate())+1+patient.getId());
            c.setEncounterDate(patient.getPharmacyLastpickupdate());
            c.setReturnDate(patient.getDpNextAppointment());
            c.setClinician("Dr MC Adetola");
            LocalDate cNextDate = LocalDate.parse(patient.getPharmacyLastpickupdate().toString());
            Period cPeriod = Period.between(LocalDate.now(), cNextDate);
            if (cPeriod.getDays() == 0) {
                c.setStatus("Completed");
            } else if (period.getDays() < 1) {
                c.setStatus("Missed");
            } else if (period.getDays() > 1) {
                c.setStatus("Upcoming");
            }

            patientDemographics.setPharmacyEncounter(p);
            patientDemographics.setLabEncounter(l);
            patientDemographics.setCd4Encounter(cd4);
            patientDemographics.setClinicEncounters(c);

            return patientDemographics;
        } catch (Exception exception) {
            return null;
        }
    }

    public PatientInfo getPatientByIdentifier(PatientRequestDto patientRequestDto) {
        try {
            patientInfo = new PatientInfo();
            AppointmentList patient = appointmentListRepository.findAppointmentListByPepid(patientRequestDto.getPatientId());
            patientInfo.setGender(patient.getSex());

            patientInfo.setBp(patient.getBp());
            patientInfo.setHeight(patient.getHeight());
            patientInfo.setWeight(patient.getWeight().toString());
            patientInfo.setOccupation(patient.getQualification());
            patientInfo.setCurrentAge(patient.getCurrentAge());
            patientInfo.setDob(patient.getDob());

            patientInfo.setFacilityName(patient.getFacilityname());
            patientInfo.setFacilityCode(patient.getDatimCode());
            patientInfo.setPhoneNumber(patient.getPhoneno());
            //patientInfo.(patient.getMessageData().getDemographics().getBirthdate());
            patientInfo.setPepfarId(patient.getPepid());
            patientInfo.setPatientUniqueId(patient.getPepid() + "_" + patientInfo.getFacilityCode());
            List<PharmacyEncounter> pharmEncounter = new ArrayList<>();
            List<LabEncounter> labEncounters = new ArrayList<>();
            List<ClinicEncounter> clinicEncounters = new ArrayList<>();



            PharmacyEncounter p = new PharmacyEncounter();
            p.setDaysOfRefill(convertToDecimal(patient.getDaysofarvrefill()));
            p.setTitle("VL Appointment");
            p.setEncounterType(PHARMACY_ENCOUNTER_TYPE);
            p.setEncounterId(covertToLongDate(patient.getPharmacyLastpickupdate()));
            p.setCurrentRegimen(patient.getCurrentartregimen());
            p.setEncounterDate(patient.getPharmacyLastpickupdate());
            p.setReturnDate(patient.getDpNextAppointment());
            p.setClinician("Dr MC Adetola");
            LocalDate pNextDate = LocalDate.parse(patient.getDpNextAppointment().toString());

            Period period = Period.between(LocalDate.now(), pNextDate);
            if (period.getDays() == 0) {
                p.setStatus("Completed");
            } else if (period.getDays() < 1) {
                p.setStatus("Missed");
            } else if (period.getDays() > 1) {
                p.setStatus("Upcoming");
            }
            pharmEncounter.add(p);
            patientInfo.setPharmacyEncounter(pharmEncounter);

            LabEncounter l = new LabEncounter();
            l.setTitle("VL Appointment");
            l.setEncounterType(LAB_ENCOUNTER_TYPE);
            l.setEncounterId(covertToLongDate(patient.getDateofcurrentviralload()));
            l.setEncounterDate(patient.getDateofcurrentviralload());
            l.setReturnDate(patient.getVlNextAppointmentDate());
            l.setViralload(convertToDecimal(patient.getCurrentviralload()));
            l.setClinician("Dr MC Adetola");
            LocalDate lNextDate = LocalDate.parse(patient.getDateofcurrentviralload().toString());

            Period lPeriod = Period.between(LocalDate.now(), lNextDate);
            if (lPeriod.getDays() == 0) {
                l.setStatus("Completed");
            } else if (period.getDays() < 1) {
                l.setStatus("Missed");
            } else if (period.getDays() > 1) {
                l.setStatus("Upcoming");
            }

            labEncounters.add(l);

            ClinicEncounter c = new ClinicEncounter();
            c.setTitle("Clinic Appointment");
            c.setEncounterType(CARE_CARD_TYPE);
            c.setEncounterId(covertToLongDate(patient.getPharmacyLastpickupdate())+1);
            c.setEncounterDate(patient.getPharmacyLastpickupdate());
            c.setReturnDate(patient.getDpNextAppointment());
            c.setClinician("Dr MC Adetola");
            LocalDate cNextDate = LocalDate.parse(patient.getPharmacyLastpickupdate().toString());

            Period cPeriod = Period.between(LocalDate.now(), cNextDate);
            if (cPeriod.getDays() == 0) {
                c.setStatus("Completed");
            } else if (period.getDays() < 1) {
                c.setStatus("Missed");
            } else if (period.getDays() > 1) {
                c.setStatus("Upcoming");
            }

            clinicEncounters.add(c);

            patientInfo.setPharmacyEncounter(pharmEncounter);
            patientInfo.setLabEncounter(labEncounters);
            patientInfo.setClinicEncounters(clinicEncounters);

            return patientInfo;
        } catch (Exception exception) {
            return null;
        }
    }

    public EcewsPatientResponse signUp(PatientSignupDto patientSignupDto) {

        String quarter = "FY23Q4";
        try {
            EcewsPatientResponse ecewsPatientResponse = new EcewsPatientResponse();
            PatientInfo patientInfo = new PatientInfo();
            List<EcewsIdentifierInfo> identifiers = new ArrayList<>();
            EcewsDemographicInfo demographicInfo = new EcewsDemographicInfo();
            EcewsFacilityInfo facilityInfo = new EcewsFacilityInfo();

            patientInfo = new PatientInfo();
            AppointmentList patient = appointmentListRepository.findAppointmentListByPepid(patientSignupDto.getPatientId());
            patientInfo.setGender(patient.getSex());
            patientInfo.setFacilityName(patient.getFacilityname());
            patientInfo.setFacilityCode(patient.getDatimCode());
            patientInfo.setPhoneNumber(patient.getPhoneno());
            //patientInfo.(patient.getMessageData().getDemographics().getBirthdate());
            patientInfo.setPepfarId(patient.getPepid());
            patientInfo.setPatientUniqueId(patient.getPepid() + "_" + patientInfo.getFacilityCode());
            demographicInfo.setFirstName("1212121asdderer");
            demographicInfo.setLastName("1212121asdderer");
            demographicInfo.setPhoneNumber(patient.getPhoneno());
            demographicInfo.setTreatmentGroup("ART");
            demographicInfo.setDob(LocalDate.now());
            facilityInfo.setFacilityCode(patient.getDatimCode());
            facilityInfo.setFacilityName(patient.getFacilityname());
            EcewsIdentifierInfo ecewsIdentifierInfo = new EcewsIdentifierInfo();
            ecewsIdentifierInfo.setIdentifier(patient.getPepid());
            ecewsIdentifierInfo.setIdentifierType("ART");
            identifiers.add(ecewsIdentifierInfo);
            ecewsPatientResponse.setDemographicInfo(demographicInfo);
            ecewsPatientResponse.setFacilityInfo(facilityInfo);
            ecewsPatientResponse.setIdentifiers(identifiers);
            ecewsPatientResponse.setUniqueId(patient.getPepid());
            return ecewsPatientResponse;
        }catch(Exception exception) {
            return null;
        }
        }

    /*   public PatientInfo getPatientByIdentifier2(PatientRequestDto patientRequestDto) {
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
                   p.setEncounterDate(encounterType.getEncounterDatetime());
                   pharmEncounter.add(p);

                   //Calculate days of refill and next appointment date
                   BigDecimal daysOfRefill = getValueNumeric(encounterType.getEncounterId(), DAYS_REFILL, patient);
                   LocalDate returnDate = convertDate(encounterType.getEncounterDatetime()).plusDays(daysOfRefill.intValue());

                  // p.setDaysOfRefill(daysOfRefill);

                   p.setTitle("DP Appointment");
                   p.setEncounterType(PHARMACY_ENCOUNTER_TYPE);
                   p.setEncounterId(encounterType.getEncounterId());
                   p.setClinician("Dr MC Adetola");

                   Optional regimenLine = getObsValue(encounterType.getEncounterId(), obsTypeList, CURRENT_REGIMEN);
                   if(regimenLine.isPresent()){
                       ObsType regimenLineValue = (ObsType) regimenLine.get();
                       Optional regimen = getObsValue(encounterType.getEncounterId(), obsTypeList, regimenLineValue.getValueCoded());
                       ObsType regimenValue = (ObsType) regimen.get();
                       p.setCurrentRegimen(regimenValue.getVariableValue());
                   }

                 //  p.setReturnDate(returnDate);
               }

               if (encounterType.getEncounterTypeId() == LAB_ENCOUNTER_TYPE) {
                   LocalDate returnDate = convertDate(encounterType.getEncounterDatetime()).plusMonths(6);
                   LabEncounter l = new LabEncounter();
                   l.setEncounterDate(convertDate(encounterType.getEncounterDatetime()));
                   l.setClinician("Dr MC Adetola");
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
                   c.setClinician("Dr MC Adetola");
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

                   Period period = Period.between(LocalDate.now(),convertDate(e.getReturnDate())  );
                   System.out.println(period.getDays());
                   if(period.getDays() == 0) {
                       e.setStatus("Completed");
                   }else if(period.getDays() <1){
                       e.setStatus("Missed");
                   }else if(period.getDays() >1){
                       e.setStatus("Upcoming");
                   }
               }else{
                   e.setStatus("Completed");
               }

           }

           Collections.sort(labEncounters, (o1, o2) -> o1.getEncounterDate().compareTo(o2.getEncounterDate()));
           for (LabEncounter e : labEncounters) {
               if(j++ == labEncounters.size() - 1){
                   Period period = Period.between(LocalDate.now(), e.getReturnDate() );
                   System.out.println(period.getDays());
                   if(period.getDays() == 0) {
                       e.setStatus("Completed");
                   }else if(period.getDays() <1){
                       e.setStatus("Missed");
                   }else if(period.getDays() >1){
                       e.setStatus("Upcoming");
                   }

               }else{
                   e.setStatus("Completed");
               }

           }

           Collections.sort(clinicEncounters, (o1, o2) -> o1.getEncounterDate().compareTo(o2.getEncounterDate()));
           for (ClinicEncounter e : clinicEncounters) {
               if(k++ == clinicEncounters.size() - 1){

                   Period period = Period.between(LocalDate.now(), e.getReturnDate() );
                   System.out.println(period.getDays());

                   if(period.getDays() == 0) {
                       e.setStatus("Completed");
                   }else if(period.getDays() <1){
                       e.setStatus("Missed");
                   }else if(period.getDays() >1){
                       e.setStatus("Upcoming");
                   }

               }else{
                   e.setStatus("Completed");
               }

           }
           patientInfo.setPharmacyEncounter(pharmEncounter);
           patientInfo.setLabEncounter(labEncounters);
           patientInfo.setClinicEncounters(clinicEncounters);
       }
   */
/*
    public EcewsPatientResponse signUp2(PatientSignupDto patientSignupDto) {

        String quarter = "FY23Q4";
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
        } catch (Exception ex) {
            return null;
        }
    }
*/

}
