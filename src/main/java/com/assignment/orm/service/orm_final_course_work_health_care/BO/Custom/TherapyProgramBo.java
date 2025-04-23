package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.SessionStaticsDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapySessionSchedulingDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapyProgramBo extends SuperBo {
    String getNextId() throws SQLException;

    ArrayList<TherapyProgramDto> getAll() throws SQLException;

    boolean save(TherapyProgramDto therapyProgram) throws SQLException;

    boolean delete(String Id) throws SQLException;

    boolean update(TherapyProgramDto therapyProgram) throws SQLException;

    TherapyProgramDto findByName(String name) throws SQLException;

    TherapyProgramDto findById(String id) throws SQLException;

    ArrayList<PatientDto> findPatientByProgramId(String proId);

    ArrayList<SessionStaticsDto> getAllDetails() throws SQLException;

    ArrayList<TherapySessionSchedulingDto> getSessionsByProgramId(String programId);
}


