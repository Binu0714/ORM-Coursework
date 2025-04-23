package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapySessionScheduling;

import java.sql.Date;
import java.util.ArrayList;

public interface TherapySessionSchedulingDao extends CrudDao<TherapySessionScheduling,String> {
    ArrayList<TherapySessionScheduling>  checkByTherapistId(String id);
    ArrayList<TherapySessionScheduling> findBYDate(Date date);
    ArrayList<TherapySessionScheduling> findBYStatus(String status);

    ArrayList<TherapySessionScheduling> getAllCounts(String therapistId, String therapyProgramId);

    TherapySessionScheduling findById(String therapySessionId);
}




