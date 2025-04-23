package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.BoFactory;
import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.*;
import com.assignment.orm.service.orm_final_course_work_health_care.Cunfig.FactoryConfiguration;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.PaymentInvoiceDao;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.DaoFactory;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.*;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.*;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentInvoiceBoImpl implements PaymentInvoiceBo {


    PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
    TherapyProgramBo therapyProgramBo = BoFactory.getInstance().getBo(BoFactory.BoType.THERAPY_PROGRAM);
    ProgramDetailsBo programDetailsBo = BoFactory.getInstance().getBo(BoFactory.BoType.PROGRAM_DETAILS);
    PaymentInvoiceDao paymentAndInvoiceManageDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PAYMENT);
    TherapySessionSchedulingBo therapySessionBo = BoFactory.getInstance().getBo(BoFactory.BoType.THERAPY_SESSION_SCHEDULING);
    TherapistBo therapistBo = BoFactory.getInstance().getBo(BoFactory.BoType.THERAPIST);

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public ArrayList<TherapyProgramDto> findProgramsByPatientId(String patientId) throws SQLException {
        return patientBo.getProgramsByPatientId(patientId);
    }

    @Override
    public ArrayList<TherapySessionSchedulingDto> findSessionsByProgramId(String programId, String patientId) {
        ArrayList<TherapySessionSchedulingDto> therapySessionSchedulingDtos = therapyProgramBo.getSessionsByProgramId(programId);

        ArrayList<TherapySessionSchedulingDto> filteredSessionDtos = new ArrayList<>();

        for (TherapySessionSchedulingDto sessionDto : therapySessionSchedulingDtos) {
            if (sessionDto.getPatientId().equals(patientId) && sessionDto.getTherapyProgramId().equals(programId)) {
                filteredSessionDtos.add(sessionDto);
            }
        }

        return filteredSessionDtos;
    }

    @Override
    public String findCurrentStatus(String patientId, String programId) {
        ProgramDetailsDto programDetailsDto = programDetailsBo.findProgramDetails(patientId, programId);

        if (programDetailsDto != null) {
            double currentPaymentStatus = programDetailsDto.getCurrentPaymentStatus();
            return String.valueOf(currentPaymentStatus);
        }

        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String id = paymentAndInvoiceManageDao.getNextId();

        if (id != null) {
            String substring = id.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("PI%03d", newIdIndex);
        }
        return "PI001";
    }

    @Override
    public boolean save(String paymentId, String patientId, String therapyProgramId, String therapySessionId, double amount, String status, double currentPayment, Date date) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            PatientDto patientDto = patientBo.findById(patientId); // Check if the patient exists

            if (patientDto == null) {
                transaction.rollback();
                return false;
            }

            Patient patient = new Patient();
            patient.setP_id(patientId);
            patient.setName(patientDto.getName());
            patient.setEmail(patientDto.getEmail());
            patient.setDate(patientDto.getDate());
            patient.setContact(patientDto.getContact());
            patient.setHistory(patientDto.getHistory());


            TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(therapyProgramId); // Check if the program exists

            if (therapyProgramDto == null) {
                transaction.rollback();
                return false;
            }

            TherapyProgram therapyProgram = new TherapyProgram();
            therapyProgram.setT_id(therapyProgramId);
            therapyProgram.setName(therapyProgramDto.getName());
            therapyProgram.setDescription(therapyProgramDto.getDescription());
            therapyProgram.setDuration(therapyProgramDto.getDuration());
            therapyProgram.setFee(therapyProgramDto.getFee());

            TherapySessionSchedulingDto therapySessionSchedulingDto = therapySessionBo.findById(therapySessionId); // Check if the session exists

            if (therapySessionSchedulingDto == null) {
                transaction.rollback();
                return false;
            }

            TherapistDto therapistDto = therapistBo.findById(therapySessionSchedulingDto.getTherapistId()); // Check if the therapist exists

            if (therapistDto == null) {
                transaction.rollback();
                return false;
            }

            Therapist therapist = new Therapist();
            therapist.setId(therapySessionSchedulingDto.getId());
            therapist.setName(therapistDto.getName());
            therapist.setEmail(therapistDto.getEmail());
            therapist.setContact(therapistDto.getContact());

            TherapySessionScheduling therapySessionScheduling = new TherapySessionScheduling();
            therapySessionScheduling.setId(therapySessionSchedulingDto.getId());
            therapySessionScheduling.setStartTime(therapySessionSchedulingDto.getStartTime());
            therapySessionScheduling.setDate(therapySessionSchedulingDto.getDate());
            therapySessionScheduling.setStatus(therapySessionSchedulingDto.getStatus());
            therapySessionScheduling.setTherapyProgram(therapyProgram);
            therapySessionScheduling.setTherapist(therapist);
            therapySessionScheduling.setPatient(patient);

            Payment payment = new Payment();
            payment.setId(paymentId);
            payment.setAmount(amount);
            payment.setStatus(status);
            payment.setPaymentDate(date);
            payment.setPatient(patient);
            payment.setTherapyProgram(therapyProgram);
            payment.setTherapySessionScheduling(therapySessionScheduling);

            boolean isSavedPayment = paymentAndInvoiceManageDao.savePayment(session,payment); // Save the payment

            if (!isSavedPayment){
                transaction.rollback();
                return false;
            }

            double newCurrentPayment = currentPayment - amount; // Update the current payment

            ProgramDetails programDetails = new ProgramDetails();
            programDetails.setIds(new ProgramDetailsIds(patient.getP_id(), therapyProgram.getT_id()));
            programDetails.setTherapyProgram(therapyProgram);
            programDetails.setPatient(patient);
            programDetails.setCurrentPaymentStatus(newCurrentPayment);
            programDetails.setTherapyProgramName(therapyProgram.getName());

            if (amount > currentPayment) {
                transaction.rollback();
                return false;
            }

            boolean isUpdatedCurrentPayment = programDetailsBo.updateCurrentPayment(session, programDetails); // Update the ProgramDetails

            if (!isUpdatedCurrentPayment) {
                transaction.rollback();
                return false;
            }

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
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ArrayList<Payment> payments = paymentAndInvoiceManageDao.getAll();

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(payment.getId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setStatus(payment.getStatus());
            paymentDto.setDate( payment.getPaymentDate());
            paymentDto.setPatientId(payment.getPatient().getP_id());
            paymentDto.setTherapyProgramId(payment.getTherapyProgram().getT_id());
            paymentDto.setTherapySessionId(payment.getTherapySessionScheduling().getId());

            paymentDtos.add(paymentDto);
        }

        return paymentDtos;
    }

    @Override
    public boolean update(String id, String newAmount) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            Payment payment = paymentAndInvoiceManageDao.findById(id);

            if (payment == null) {
                return false;
            }

            String programId = payment.getTherapyProgram().getT_id();
            String patientId = payment.getPatient().getP_id();

            ProgramDetailsDto programDetailsDto = programDetailsBo.findProgramDetails(patientId, programId); // find the program details

            if (programDetailsDto == null) {
                return false;
            }

            double currentPaymentStatus = programDetailsDto.getCurrentPaymentStatus();
            double newAmountDouble = Double.parseDouble(newAmount);
            double oldAmount = payment.getAmount();

            if (newAmountDouble > currentPaymentStatus) {
                new Alert(Alert.AlertType.ERROR, "New amount is greater than current payment status").showAndWait();
                return false;
            }

            double newCurrentPaymentStatus = (currentPaymentStatus + oldAmount) - newAmountDouble;

            payment.setAmount(newAmountDouble); // Update the new payment amount
            programDetailsDto.setCurrentPaymentStatus(newCurrentPaymentStatus); // Update the new current payment status

            boolean isUpdatedPayment = paymentAndInvoiceManageDao.updatePayment(session, payment); // Update the payment

            if (!isUpdatedPayment) {
                transaction.rollback();
                return false;
            }

            TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(programId); // find the therapy program

            if (therapyProgramDto == null) {
                return false;
            }

            TherapyProgram therapyProgram = new TherapyProgram();
            therapyProgram.setT_id(programId);
            therapyProgram.setName(therapyProgramDto.getName());
            therapyProgram.setDescription(therapyProgramDto.getDescription());
            therapyProgram.setDuration(therapyProgramDto.getDuration());
            therapyProgram.setFee(therapyProgramDto.getFee());

            PatientDto patientDto = patientBo.findById(patientId); // find the patient

            if (patientDto == null) {
                return false;
            }

            Patient patient = new Patient();
            patient.setP_id(patientId);
            patient.setName(patientDto.getName());
            patient.setEmail(patientDto.getEmail());
            patient.setDate(patientDto.getDate());
            patient.setContact(patientDto.getContact());
            patient.setHistory(patientDto.getHistory());

            ProgramDetails programDetails = new ProgramDetails();
            programDetails.setIds(new ProgramDetailsIds(patient.getP_id(), therapyProgram.getT_id()));
            programDetails.setTherapyProgram(therapyProgram);
            programDetails.setPatient(patient);
            programDetails.setCurrentPaymentStatus(newCurrentPaymentStatus);
            programDetails.setTherapyProgramName(programDetailsDto.getTherapyProgramName());

            boolean isUpdatedProgramDetails = programDetailsBo.updateCurrentPayment(session, programDetails); // Update the program details

            if (!isUpdatedProgramDetails) {
                transaction.rollback();
                return false;
            }

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
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            Payment payment = paymentAndInvoiceManageDao.findById(id);

            if (payment == null) {
                return false;
            }

            String programId = payment.getTherapyProgram().getT_id();
            String patientId = payment.getPatient().getP_id();

            ProgramDetailsDto programDetailsDto = programDetailsBo.findProgramDetails(patientId, programId); // find the program details

            if (programDetailsDto == null) {
                return false;
            }

            double currentPaymentStatus = programDetailsDto.getCurrentPaymentStatus();
            double amountDouble = payment.getAmount();

            double newCurrentPaymentStatus = currentPaymentStatus + amountDouble;

            boolean isDeletedPayment = paymentAndInvoiceManageDao.deletePayment(session, payment); // Delete the payment

            if (!isDeletedPayment) {
                transaction.rollback();
                return false;
            }

            TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(programId); // find the therapy program

            if (therapyProgramDto == null) {
                return false;
            }

            TherapyProgram therapyProgram = new TherapyProgram();
            therapyProgram.setT_id(programId);
            therapyProgram.setName(therapyProgramDto.getName());
            therapyProgram.setDescription(therapyProgramDto.getDescription());
            therapyProgram.setDuration(therapyProgramDto.getDuration());
            therapyProgram.setFee(therapyProgramDto.getFee());

            PatientDto patientDto = patientBo.findById(patientId); // find the patient

            if (patientDto == null) {
                return false;
            }

            Patient patient = new Patient();
            patient.setP_id(patientId);
            patient.setName(patientDto.getName());
            patient.setEmail(patientDto.getEmail());
            patient.setDate(patientDto.getDate());
            patient.setContact(patientDto.getContact());
            patient.setHistory(patientDto.getHistory());

            ProgramDetails programDetails = new ProgramDetails();
            programDetails.setIds(new ProgramDetailsIds(patient.getP_id(), therapyProgram.getT_id()));
            programDetails.setTherapyProgram(therapyProgram);
            programDetails.setPatient(patient);
            programDetails.setCurrentPaymentStatus(newCurrentPaymentStatus);
            programDetails.setTherapyProgramName(programDetailsDto.getTherapyProgramName());

            boolean isUpdatedProgramDetails = programDetailsBo.updateCurrentPayment(session, programDetails); // Update the program details

            if (!isUpdatedProgramDetails) {
                transaction.rollback();
                return false;
            }

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
    public ArrayList<PaymentDto> getPaymentsByDates(Date firstDay, Date lastDay) {
        ArrayList<Payment> payments = paymentAndInvoiceManageDao.getPaymentsByDates(firstDay, lastDay);

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(payment.getId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setStatus(payment.getStatus());
            paymentDto.setDate((Date) payment.getPaymentDate());
            paymentDto.setPatientId(payment.getPatient().getP_id());
            paymentDto.setTherapyProgramId(payment.getTherapyProgram().getT_id());
            paymentDto.setTherapySessionId(payment.getTherapySessionScheduling().getId());

            paymentDtos.add(paymentDto);
        }

        return paymentDtos;
    }
    }
