package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.PatientDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.config.annotation.web.session.SessionSecurityMarker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Patient findById(String p_id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Patient patient = session.get(Patient.class, p_id);
        return patient;
    }

    @Override
    public Patient findByName(String name) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<Patient> query = session.createQuery("from Patient where name = :name", Patient.class);
        query.setParameter("name", name);

        Patient patient = query.uniqueResult();
        if (patient != null) {
            return patient;
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        Session session = factoryConfiguration.getSession();

        String lastId = session
                .createQuery("SELECT p.id FROM Patient p ORDER BY p.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return lastId;
    }

    @Override
    public ArrayList<Patient> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<Patient> query = session.createQuery("from Patient", Patient.class);
        List<Patient> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public boolean save(Patient patient) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(patient);
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
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class, Id);
            session.remove(patient);
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
    public boolean update(Patient patient) throws SQLException {
       Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(patient);
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
