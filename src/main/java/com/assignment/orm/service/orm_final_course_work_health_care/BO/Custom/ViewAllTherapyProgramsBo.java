package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;

import java.util.ArrayList;

public interface ViewAllTherapyProgramsBo extends SuperBo {
    ArrayList<PatientDto>  checkPatientInPrograms();
}
