package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class TherapySessionScheduling implements SuperEntity{
    @Id
    private String id;
    private Time startTime;
    private Time endTime;
    private Date date;
    private String status;

@ManyToOne
@JoinColumn(name = "patient_id")
    private Patient patient;

@ManyToOne
@JoinColumn(name = "therapist_id")
    private Therapist therapist;

@ManyToOne
@JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;

    @OneToMany(mappedBy = "therapySessionScheduling" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Payment> payment;

}
