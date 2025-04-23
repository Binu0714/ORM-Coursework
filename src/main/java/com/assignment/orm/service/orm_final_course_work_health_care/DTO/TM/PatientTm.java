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
public class PatientTm {
    private String p_id;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String history;
    private Date date;
}
