package com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM;

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
public class TherapySessionSchedulingTm {
    private String id;
    private Time startTime;
    private Time endTime;
    private Date date;
    private String status;
    private String patientId;
    private String therapistId;
    private String therapyProgramId;

}
