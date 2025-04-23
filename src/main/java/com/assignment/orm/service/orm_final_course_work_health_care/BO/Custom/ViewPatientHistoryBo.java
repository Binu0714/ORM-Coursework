package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.ViewPatientHistoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewPatientHistoryBo extends SuperBo {
    ArrayList<String> getAllPatientIds() throws SQLException;

    ArrayList<ViewPatientHistoryDto> loadPatientHistory(String id) throws SQLException;
}
