package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.ProgramDetails;

public interface ProgramDetailsDao extends CrudDao<ProgramDetails,String> {
    boolean delete (ProgramDetails programDetails) ;

    ProgramDetails findProgramDetails(String patientId, String programId);

}
