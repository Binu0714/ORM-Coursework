package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapistDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Therapist;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistBo extends SuperBo {
    String getNextId() throws SQLException;
    ArrayList<TherapistDto> getAll() throws SQLException;
    boolean save(TherapistDto therapistDto, List<String> Name) throws SQLException;
    boolean delete(String Id) throws SQLException;
    boolean update(TherapistDto therapistDto,List<String> list) throws SQLException;
    TherapistDto findById(String id) throws Exception;
    TherapistDto findByName(String email) throws Exception;

    List<TherapyProgram> getProgramsByTherapistId(String id) throws Exception;
}


