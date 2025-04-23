package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapySessionSchedulingDto;
import org.hibernate.dialect.function.array.ArrayAggFunction;

import java.util.ArrayList;

public interface TrackScheduleBo extends SuperBo {
 ArrayList<TherapySessionSchedulingDto> checkTherapySessionSchedule(String id) throws Exception;
}
