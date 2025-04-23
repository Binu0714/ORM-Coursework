package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.UserDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public List<User> CheckUser() {
        Session session = factoryConfiguration.getSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> list = query.list();
        return list;
    }

    @Override
    public String getNextId() throws SQLException {
        Session session = factoryConfiguration.getSession();

        String lastId = session.createQuery("select id from User order by id desc", String.class)
                .setMaxResults(1)
                .getSingleResult();
        return lastId;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> list = query.list();
        return new ArrayList<>(list);
    }

    @Override
    public boolean save(User user) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.persist(user);
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
            User user = session.get(User.class, Id);
            session.remove(user);
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
    public boolean update(User user) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(user);
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
