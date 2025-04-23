package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.TherapyProgramDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramDaoImpl implements TherapyProgramDao {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public TherapyProgram findByName(String therapistName) {
        Session session = factoryConfiguration.getSession();
        Query<TherapyProgram> query = session.createQuery("FROM TherapyProgram tp WHERE tp.name = :therapyProgramName", TherapyProgram.class);
        query.setParameter("therapyProgramName", therapistName);

        TherapyProgram therapyProgram = query.uniqueResult();


        return therapyProgram;

    }

    @Override
    public TherapyProgram findById(String id) {
        Session session = factoryConfiguration.getSession();
        TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
        return therapyProgram;
    }

    @Override
    public String getNextId() throws SQLException {
      Session session = factoryConfiguration.getSession();

      String lastId = session.createQuery("select T_id from TherapyProgram order by T_id desc", String.class)
              .setMaxResults(1)
              .getSingleResult();
      return lastId;
    }

    @Override
    public ArrayList<TherapyProgram> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<TherapyProgram> query = session.createQuery("from TherapyProgram", TherapyProgram.class);
        List<TherapyProgram> list = query.list();
        return new ArrayList<>(list);

    }

    @Override
    public boolean save(TherapyProgram therapyProgram) throws SQLException {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(therapyProgram);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, Id);
            session.remove(therapyProgram);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TherapyProgram therapyProgram) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(therapyProgram);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
