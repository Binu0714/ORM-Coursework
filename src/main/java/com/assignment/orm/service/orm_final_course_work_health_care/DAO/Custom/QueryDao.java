package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.SuperDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    ArrayList<Patient> checkPatientInPrograms();
}
