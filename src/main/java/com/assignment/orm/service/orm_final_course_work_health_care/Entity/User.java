package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
