package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Therapist;

public interface TherapistDao extends CrudDao<Therapist, String> {
    Therapist findByName(String email) throws Exception;
    Therapist findById(String id) throws Exception;
}