package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;

import java.sql.Date;
import java.util.ArrayList;

public interface FilterPatientBo extends SuperBo {
    ArrayList<PatientDto> findPatientByProgramId(String ProId) throws Exception;
    ArrayList<PatientDto>  findPatientByDate(Date date) throws Exception;
    ArrayList<PatientDto>  findPatientByStatus(String status) throws Exception;
}
