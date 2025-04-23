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
public class Therapist implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String contact;

    @ManyToMany
    @JoinTable(
            name = "therapist_therapy_program",
            joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "therapy_program_id")
    )
    private List<TherapyProgram> therapyPrograms;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySessionScheduling> therapySessionScheduling;
}
