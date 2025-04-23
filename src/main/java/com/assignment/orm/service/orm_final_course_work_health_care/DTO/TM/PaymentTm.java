package com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentTm {
    private String id;
    private double amount;
    private String status;
    private Date date;
    private String patientId;
    private String therapySessionId;
    private String therapyProgramId;
}
