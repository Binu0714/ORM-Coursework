package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientBo extends SuperBo {

    String getNextId() throws SQLException;
    ArrayList<PatientDto> getAll() throws SQLException;
    boolean save(PatientDto patientDto) throws SQLException;
    boolean delete(String Id) throws SQLException;
    boolean update(PatientDto patientDto) throws SQLException;
    PatientDto findById(String p_id) throws SQLException;
    PatientDto findByName(String name) throws SQLException;
    ArrayList<TherapyProgramDto> getProgramsByPatientId(String p_id) throws SQLException;

}


