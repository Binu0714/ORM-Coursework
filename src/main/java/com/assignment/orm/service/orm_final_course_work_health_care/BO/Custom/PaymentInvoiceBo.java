package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.SuperBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PaymentDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapySessionSchedulingDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Payment;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentInvoiceBo extends SuperBo {

    ArrayList<TherapyProgramDto> findProgramsByPatientId(String patientId) throws SQLException;

    ArrayList<TherapySessionSchedulingDto> findSessionsByProgramId(String programId, String patientId);

    String findCurrentStatus(String patientId, String programId);

    String getNextId() throws SQLException;

    boolean save(String paymentId, String patientId, String therapyProgramId, String therapySessionId, double amount, String status, double currentPayment, Date date) throws SQLException;

    ArrayList<PaymentDto> getAll() throws SQLException;

    boolean update(String id, String newAmount) throws SQLException;

    boolean delete(String id);

    ArrayList<PaymentDto> getPaymentsByDates(Date firstDay, Date lastDay);
}

