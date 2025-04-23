package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;

import java.sql.SQLException;

public interface PatientDao extends CrudDao<Patient,String> {
    Patient  findById(String p_id) throws SQLException;
    Patient  findByName(String name) throws SQLException;
}
