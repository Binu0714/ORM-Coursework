package com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionStaticsTm {
    private String id;
    private String name;
    private int completedSessionCount;
    private int bookedSessionCount;
    private int rescheduleSessionCount;
    private int canceledSessionCount;
}
