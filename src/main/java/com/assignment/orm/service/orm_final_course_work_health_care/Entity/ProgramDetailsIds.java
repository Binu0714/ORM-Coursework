package com.assignment.orm.service.orm_final_course_work_health_care.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProgramDetailsIds {
    private String p_id;
    private String  t_id;
}
