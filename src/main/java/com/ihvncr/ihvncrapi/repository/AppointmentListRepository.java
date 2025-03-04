package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.AppointmentList;
import com.ihvncr.ihvncrapi.model.Radet;
import org.springframework.data.jpa.repository.JpaRepository;

;


public interface AppointmentListRepository extends JpaRepository<AppointmentList, Long> {
    AppointmentList findAppointmentListByPepid(String patientId);
}