package com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM;

import lombok.*;

import java.sql.Time;
import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ViewPatientHistoryTm {
    private String programId;
    private String programName;
    private String sessionId;
    private Date date;
    private Time time;
    private String status;
}
