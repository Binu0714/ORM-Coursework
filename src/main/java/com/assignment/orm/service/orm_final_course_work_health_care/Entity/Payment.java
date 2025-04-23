package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import com.mysql.cj.MysqlConnection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment implements SuperEntity{
    @Id
    @JoinColumn(name = "Payment_Id")
    private String id;
    private double amount;
    private Date paymentDate;
    private String method;
private String status;

    @ManyToOne
    @JoinColumn(name = "Patient_Id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "Therapist_Program_Id")
    private TherapyProgram therapyProgram;

    @ManyToOne
    @JoinColumn(name = "Therapy_Session_Sheduling")
    private TherapySessionScheduling therapySessionScheduling;



}
