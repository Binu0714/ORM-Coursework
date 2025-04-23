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
public class ProgramDetailsTm {
    private String patient;
    private String therapyProgram;
    private String therapyProgramName;
    private Date registerDate;
    private String fee;
}
