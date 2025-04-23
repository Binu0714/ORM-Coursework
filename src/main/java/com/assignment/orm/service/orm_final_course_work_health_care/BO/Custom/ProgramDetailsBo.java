package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.SuperDao;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.ProgramDetailsDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.ProgramDetails;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgramDetailsBo extends SuperBo {
    ArrayList<ProgramDetailsDto> getAll() throws SQLException;
    boolean save(String patientId,  String programId) throws SQLException;
    boolean delete(String patient_id, String program_id) throws SQLException;
    ProgramDetailsDto findProgramDetails(String patientId, String programId);

    boolean updateCurrentPayment(Session session, ProgramDetails programDetails);

    boolean save(String programId, String patientId, double programFee, Date registerDate) throws SQLException;


}


