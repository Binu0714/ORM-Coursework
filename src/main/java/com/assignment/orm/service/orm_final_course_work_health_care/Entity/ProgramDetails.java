package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class ProgramDetails implements SuperEntity{


    @EmbeddedId
    private ProgramDetailsIds ids;


    @MapsId("p_id")
    @JoinColumn(name = "Patient_ID")
    @ManyToOne
    private Patient patient;

    @ManyToOne
    @MapsId("t_id")
    @JoinColumn(name = "Therapy_ID")
    private TherapyProgram therapyProgram;

    private String therapyProgramName;

    private double currentPaymentStatus;

    private Date registerDate;
}
