package com.assignment.orm.service.orm_final_course_work_health_care.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentDto {
    private String id;
    private double amount;
    private String status;
   private Date date;
    private String patientId;
    private String therapySessionId;
    private String therapyProgramId;
}
