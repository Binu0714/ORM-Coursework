package com.assignment.orm.service.orm_final_course_work_health_care.DTO;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ViewPatientHistoryDto {
    private String programId;
    private String programName;
    private String sessionId;
    private Date date;
    private Time time;
    private String status;
}
