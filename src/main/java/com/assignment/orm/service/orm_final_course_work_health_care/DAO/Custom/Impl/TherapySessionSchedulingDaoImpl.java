package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.TherapySessionSchedulingDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapySessionScheduling;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionSchedulingDaoImpl implements TherapySessionSchedulingDao {
    @Override
    public ArrayList<TherapySessionScheduling> checkByTherapistId(String id) {
   Session session = FactoryConfiguration.getInstance().getSession();

        Query<TherapySessionScheduling> query = session.createQuery("from TherapySessionScheduling where therapist.id = :id", TherapySessionScheduling.class);
        query.setParameter("id", id);
        List<TherapySessionScheduling> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public ArrayList<TherapySessionScheduling> findBYDate(Date date) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query<TherapySessionScheduling> query = session.createQuery("from TherapySessionScheduling where date = :date", TherapySessionScheduling.class);
        query.setParameter("date", date);
        List<TherapySessionScheduling> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public ArrayList<TherapySessionScheduling> findBYStatus(String status) {
       Session session = FactoryConfiguration.getInstance().getSession();

        Query<TherapySessionScheduling> query = session.createQuery("from TherapySessionScheduling where status = :status", TherapySessionScheduling.class);
        query.setParameter("status", status);
        List<TherapySessionScheduling> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public ArrayList<TherapySessionScheduling> getAllCounts(String therapistId, String therapyProgramId) {
       Session session = FactoryConfiguration.getInstance().getSession();

        Query<TherapySessionScheduling> query = session.createQuery("FROM TherapySessionScheduling ts WHERE ts.therapist.id = :therapistId AND ts.therapyProgram.id = :programId", TherapySessionScheduling.class);
        query.setParameter("therapistId", therapistId);
        query.setParameter("programId", therapyProgramId);
        List<TherapySessionScheduling> sessions = query.list();
        return new ArrayList<>(sessions);
    }

    @Override
    public TherapySessionScheduling findById(String therapySessionId) {

        Session session = FactoryConfiguration.getInstance().getSession();

        TherapySessionScheduling therapySession = session.get(TherapySessionScheduling.class, therapySessionId);

        return therapySession;
    }

    @Override
    public String getNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();

        String lastId = session
                .createQuery("SELECT t.id FROM TherapySessionScheduling t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        return lastId;

    }

    @Override
    public ArrayList<TherapySessionScheduling> getAll() throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();

        Query<TherapySessionScheduling> query = session.createQuery("from TherapySessionScheduling", TherapySessionScheduling.class);
        List<TherapySessionScheduling> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public boolean save(TherapySessionScheduling therapySessionScheduling) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(therapySessionScheduling);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public boolean delete(String Id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapySessionScheduling therapySessionScheduling = session.get(TherapySessionScheduling.class, Id);
            session.remove(therapySessionScheduling);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TherapySessionScheduling therapySessionScheduling) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(therapySessionScheduling);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
