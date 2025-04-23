package com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class TherapistTm {
    private String id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String contact;
}
