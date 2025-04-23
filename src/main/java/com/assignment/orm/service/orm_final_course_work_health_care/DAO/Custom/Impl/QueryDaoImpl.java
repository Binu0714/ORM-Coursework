package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.QueryDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public ArrayList<Patient> checkPatientInPrograms() {
        Session session = factoryConfiguration.getSession();
        String HQL = "SELECT COUNT(tp) FROM TherapyProgram tp";
        Long totalPrograms = session.createQuery(HQL, Long.class).getSingleResult();

        String hql = "SELECT p FROM Patient p JOIN p.programDetails pd GROUP BY p HAVING COUNT(DISTINCT pd.therapyProgram) = :totalPrograms";
        Query<Patient> query = session.createQuery(hql, Patient.class);
        query.setParameter("totalPrograms", totalPrograms);
        List<Patient> list = query.list();
        return new ArrayList<>(list);

    }
}
