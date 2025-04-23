package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewPatientProgramBo extends SuperBo {
    ArrayList<TherapyProgramDto> getProgramsByPatientId(String patientId) throws SQLException;
}
