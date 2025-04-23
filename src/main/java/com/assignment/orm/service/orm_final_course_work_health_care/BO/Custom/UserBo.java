package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.UserDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBo extends SuperBo {
    UserDto checkUser(String text, String text1);
    public String getNextId() throws SQLException;
    public ArrayList<UserDto> getAll() throws SQLException;
    public boolean save(UserDto user) throws SQLException;
    public boolean delete(String Id) throws SQLException;
    public boolean update(UserDto user) throws SQLException;


}


