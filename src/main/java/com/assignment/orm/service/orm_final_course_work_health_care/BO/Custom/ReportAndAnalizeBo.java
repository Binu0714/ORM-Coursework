package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.SessionStaticsDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;

import java.util.ArrayList;

public interface ReportAndAnalizeBo extends SuperBo {

    ArrayList<TherapyProgramDto> findById(String id) throws Exception;

    int[] getAllCounts(String therapistId , String therapyProgramId);

    ArrayList<SessionStaticsDto> getAllDetails() throws Exception;

}
