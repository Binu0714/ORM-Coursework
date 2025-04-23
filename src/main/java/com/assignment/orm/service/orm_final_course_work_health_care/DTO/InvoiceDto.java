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
public class InvoiceDto {
    private String patientId;
    private String patientName;
    private String programName;
    private String sessionId;
    private Date sessionDate;
    private Date invoiceDate;
    private double fee;
    private String status;
}
