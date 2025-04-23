package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapyProgram;

public interface TherapyProgramDao extends CrudDao<TherapyProgram, String> {
    TherapyProgram findByName(String name);
    TherapyProgram findById(String id);
}
