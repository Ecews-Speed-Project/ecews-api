package com.ihvncr.ihvncrapi.service;


import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;

import com.ihvncr.ihvncrapi.model.AppUser;
import com.ihvncr.ihvncrapi.model.AppointmentSchedule;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.payload.request.AppointmentRescheduleRequest;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;

import com.ihvncr.ihvncrapi.repository.AppointmentScheduleRepository;
import com.ihvncr.ihvncrapi.utils.ConstantsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ihvncr.ihvncrapi.utils.CommonUtils.getCurrentQuarterCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentScheduleService {
    private final AppointmentScheduleRepository appointmentSchedule;
    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final AppUserService appUserService;

    public ResponseEntity<MessageResponse> create(AppointmentRescheduleRequest request) {

       AppointmentSchedule appt = appointmentSchedule.findById(request.getId()).orElse(null);
        if (appt == null) {
            appt = new AppointmentSchedule();
            appt.setEncounterId(request.getEncounterId());
            appt.setEncounterType(request.getEncounterType());
            appt.setNewEncounterDate(request.getNewEncounterDate());
            appt.setReturnDate(request.getReturnDate());
            appt.setPatientIdentifier(request.getPatientIdentifier());
            appt.setPatientUniqueId(request.getPatientUniqueId());
            appointmentSchedule.save(appt);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: mobile or email is already taken!"));
    }



    public List<AppointmentSchedule> getAppointments(Long caseManagerId) {
        //String quarter = getCurrentQuarterCode(LocalDate.now());
        String quarter =  "FY23Q4";
        List<String> patientIds =  new ArrayList<>();
        List<AppUser> listOfPatients =  appUserService.getUsersByCaseManagers(caseManagerId);
        listOfPatients.forEach(appUser -> {
            patientIds.add(appUser.getPatientIdentifier());
        });

        List<AppointmentSchedule> appointmentSchedules = new ArrayList<>();
        CriteriaBuilder<Tuple> appointment = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("patientUniqueId", "patient_identifier")
                .select("patientUuid", "patient_uuid")
                .select("lastPickupDate", "last_pickup_date")
                .select(" ADD_DAY(lastPickupDate, CAST_INTEGER(daysOfArvRefil))", "return_date")
                .select(" WEEK_DIFF(now(), return_date)", "weeks")
                .select(" DAY_DIFF(now(), return_date)", "days")
                .where("quarter").eq(quarter)
                .where("return_date").ge(LocalDate.now())
                .where("patientUniqueId").in(patientIds)
                .orderByDesc("quarter", true);

        List<Tuple> listOfAppointment = appointment.getResultList();

        listOfAppointment.forEach(tuple -> {
            AppointmentSchedule appt = new AppointmentSchedule();
            appt.setPatientIdentifier(tuple.get("patient_identifier",String.class));
            appt.setPatientUniqueId(tuple.get("patient_uuid",String.class));
            appt.setEncounterType(ConstantsUtils.PHARMACY_ENCOUNTER_TYPE);
            appt.setNewEncounterDate(tuple.get("last_pickup_date",LocalDate.class));
            appt.setReturnDate(tuple.get("return_date",LocalDate.class));
            appt.setWeeks((tuple.get("weeks",Integer.class)));
            appt.setDays((tuple.get("days",Integer.class)));
            appointmentSchedules.add(appt);
        });

        CriteriaBuilder<Tuple> labAppointment = cbf.create(em, Tuple.class)
                .from(ArtLinelist.class, "a")
                .select("patientUniqueId", "patient_identifier")
                .select("patientUuid", "patient_uuid")
                .select("viralLoadEncounterDate", "current_viralLoad_date")
                .select(" MONTH_DIFF(artStartDate,now())", "months_on_art")
                .select(" YEAR_DIFF(viralLoadEncounterDate,now())", "years")
                .select(" ADD_DAY(viralLoadEncounterDate, 12)", "return_date")
                .select(" WEEK_DIFF(return_date,now() )", "week")
                .select(" DAY_DIFF(return_date,now())", "days")
                .where("quarter").eq(quarter)
                .where("months_on_art").ge(6)
                .where("years").gt(1)
                .where("patientUniqueId").in(patientIds)
                //.where("years").gt(12)
               // .where("return_date").ge(LocalDate.now())
                .orderByDesc("quarter", true);

        List<Tuple> listOfLabAppointment = labAppointment.getResultList();
        System.out.println(listOfLabAppointment.size());

        listOfLabAppointment.forEach(tuple -> {
            AppointmentSchedule appt = new AppointmentSchedule();
            appt.setPatientIdentifier(tuple.get("patient_identifier",String.class));
            appt.setPatientUniqueId(tuple.get("patient_uuid",String.class));
            appt.setEncounterType(ConstantsUtils.LAB_ENCOUNTER_TYPE);
            appt.setNewEncounterDate(tuple.get("current_viralLoad_date",LocalDate.class));
            appt.setReturnDate(tuple.get("return_date",LocalDate.class));
            appt.setWeeks((tuple.get("week",Integer.class)));
            appt.setDays((tuple.get("days",Integer.class)));
            appointmentSchedules.add(appt);
        });
        return appointmentSchedules;
    }



}
