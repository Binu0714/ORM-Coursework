package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.UserDto;

import java.sql.SQLException;

public interface ChangeCredentialManageBo extends SuperBo {
    UserDto checkUser(String text, String text1);
    boolean changeCredential(UserDto userDto, String username, String password) throws SQLException;
}
