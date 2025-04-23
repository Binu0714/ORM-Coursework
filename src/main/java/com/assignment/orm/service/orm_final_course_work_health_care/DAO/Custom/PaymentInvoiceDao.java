package com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.DAO.CrudDao;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Payment;
import org.hibernate.Session;

import java.sql.Date;
import java.util.ArrayList;

public interface PaymentInvoiceDao extends CrudDao<Payment, String> {
    boolean savePayment(Session session, Payment payment);

    Payment findById(String id);

    boolean updatePayment(Session session, Payment payment);

    boolean deletePayment(Session session, Payment payment);

    ArrayList<Payment> getPaymentsByDates(Date firstDay, Date lastDay);
}
