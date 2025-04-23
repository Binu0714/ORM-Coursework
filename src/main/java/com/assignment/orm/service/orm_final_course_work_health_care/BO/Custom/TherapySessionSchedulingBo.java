package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapySessionSchedulingDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapySessionScheduling;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapySessionSchedulingBo extends SuperBo {
     ArrayList<TherapySessionSchedulingDto> checkByTherapistId(String id) ;
     ArrayList<PatientDto> findBYDate(Date date) ;
     ArrayList<PatientDto> findBYStatus(String status) ;
     String getNextId() throws SQLException ;
     ArrayList<TherapySessionSchedulingDto> getAll() throws SQLException ;
     boolean save(TherapySessionSchedulingDto therapySessionSchedulingDto) throws Exception;
     boolean delete(String Id) throws SQLException ;
     boolean update(TherapySessionSchedulingDto therapySessionSchedulingDto) throws Exception;

    ArrayList<TherapySessionSchedulingDto> getAllCounts(String therapistId, String therapyProgramId);

    TherapySessionSchedulingDto findById(String therapySessionId);
}

