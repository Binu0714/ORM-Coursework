package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.TherapistDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistDaoImpl implements TherapistDao {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() throws SQLException {
        Session session = factoryConfiguration.getSession();

        String lastId = session
                .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return lastId;
    }

    @Override
    public ArrayList<Therapist> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<Therapist> query = session.createQuery("from Therapist", Therapist.class);
        List<Therapist> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public boolean save(Therapist therapist) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapist);
            transaction.commit();
            return true;
        } catch (Exception e){
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
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Therapist therapist = session.get(Therapist.class, Id);
            session.remove(therapist);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Therapist therapist) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapist);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Therapist findByName(String email) throws Exception {
        Session session = factoryConfiguration.getSession();

        Query<Therapist> query = session.createQuery("FROM Therapist t WHERE t.name = :therapistName", Therapist.class);
        query.setParameter("therapistName", email);

        Therapist therapist = query.uniqueResult();

        if (therapist != null) {
            return therapist;
        }

        return null;
    }

    @Override
    public Therapist findById(String id) throws Exception {
        Session session = factoryConfiguration.getSession();

        Therapist therapist = session.get(Therapist.class, id);

        return therapist;
    }
}
