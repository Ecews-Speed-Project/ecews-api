package com.ihvncr.ihvncrapi.repository;

import com.ihvncr.ihvncrapi.model.AppointmentSchedule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentScheduleRepository extends PagingAndSortingRepository<AppointmentSchedule, Long> {

}
