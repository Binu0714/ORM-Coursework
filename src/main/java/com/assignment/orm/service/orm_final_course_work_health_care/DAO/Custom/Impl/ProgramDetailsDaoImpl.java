package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.ProgramDetailsDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.ProgramDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDetailsDaoImpl implements ProgramDetailsDao {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<ProgramDetails> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<ProgramDetails> query = session.createQuery("from ProgramDetails", ProgramDetails.class);
        List<ProgramDetails> list = query.list();


        return new ArrayList<>(list);
    }

    @Override
    public boolean save(ProgramDetails programDetails) throws SQLException {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(programDetails);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            return false;
        } finally {
            if (session != null){
                session.close();
            }
        }

    }

    @Override
    public boolean delete(String Id) throws SQLException {
       return false;
    }
    @Override
    public boolean update(ProgramDetails programDetails) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ProgramDetails programDetails) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(programDetails);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            return false;
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public ProgramDetails findProgramDetails(String patientId, String programId) {
        Session session = factoryConfiguration.getSession();
        Query<ProgramDetails> query = session.createQuery("FROM ProgramDetails pd WHERE pd.id.p_id = :patientId AND pd.id.t_id = :programId", ProgramDetails.class);
        query.setParameter("patientId", patientId);
        query.setParameter("programId", programId);
        return query.uniqueResult();
    }
}
