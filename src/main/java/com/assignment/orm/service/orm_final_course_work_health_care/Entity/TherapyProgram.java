package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TherapyProgram implements SuperEntity{
    @Id
    private String T_id;
    private String name;
    private String description;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySessionScheduling> therapySessionScheduling;

    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProgramDetails> programDetails;

    @ManyToMany(mappedBy = "therapyPrograms", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Therapist> therapist;
}
