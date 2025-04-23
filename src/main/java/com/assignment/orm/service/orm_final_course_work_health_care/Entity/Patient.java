package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.*;
import javafx.scene.control.skin.LabeledSkinBase;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Patient implements SuperEntity {
    @Id
    private String p_id;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String history;
    private Date date;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Payment> payment;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TherapySessionScheduling> therapySessionScheduling;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ProgramDetails> programDetails;
}




