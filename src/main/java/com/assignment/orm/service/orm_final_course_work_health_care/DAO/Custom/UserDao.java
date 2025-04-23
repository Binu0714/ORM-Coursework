package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.User;

import java.util.List;

public interface UserDao extends CrudDao<User, String > {
    List<User>  CheckUser();

}
