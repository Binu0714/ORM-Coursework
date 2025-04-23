package com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.Impl;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.BoFactory;
import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.PatientBo;
import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.ProgramDetailsBo;
import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.TherapyProgramBo;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.Custom.ProgramDetailsDao;
import com.assignment.orm.service.orm_final_course_work_health_care.DAO.DaoFactory;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.PatientDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.ProgramDetailsDto;
import com.assignment.orm.service.orm_final_course_work_health_care.DTO.TherapyProgramDto;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.Patient;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.ProgramDetails;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.ProgramDetailsIds;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramDetailsBoImpl implements ProgramDetailsBo {

    ProgramDetailsDao programDetailsDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PROGRAM_DETAILS);
    TherapyProgramBo therapyProgramBo = BoFactory.getInstance().getBo(BoFactory.BoType.THERAPY_PROGRAM);
    PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);


    @Override
    public ArrayList<ProgramDetailsDto> getAll() throws SQLException {
        ArrayList<ProgramDetails> programDetails = programDetailsDao.getAll();

        ArrayList<ProgramDetailsDto> programDetailsDtos = new ArrayList<>();

        for (ProgramDetails programDetail : programDetails) {
            ProgramDetailsDto programDetailsDto = new ProgramDetailsDto();
            programDetailsDto.setPatient(programDetail.getPatient().getP_id());
            programDetailsDto.setTherapyProgram(programDetail.getTherapyProgram().getT_id());
            programDetailsDto.setTherapyProgramName(programDetail.getTherapyProgramName());
            programDetailsDto.setCurrentPaymentStatus(programDetail.getCurrentPaymentStatus());
            programDetailsDto.setRegisterDate(programDetail.getRegisterDate());

            programDetailsDtos.add(programDetailsDto);
        }
                      return programDetailsDtos;

        }

    @Override
    public boolean save(String patient_id, String program_id) throws SQLException {
        ProgramDetailsIds programDetailsIds = new ProgramDetailsIds(patient_id, patient_id);

        TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(program_id);
        PatientDto patientDto = patientBo.findById(patient_id);

        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setT_id(therapyProgramDto.getT_id());
        therapyProgram.setName(therapyProgramDto.getName());
        therapyProgram.setDuration(therapyProgramDto.getDuration());
        therapyProgram.setDescription(therapyProgramDto.getDescription());
        therapyProgram.setFee(therapyProgramDto.getFee());

        Patient patient = new Patient();
        patient.setP_id(patientDto.getP_id());
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setContact(patientDto.getContact());
        patient.setEmail(patientDto.getEmail());
        patient.setHistory(patientDto.getHistory());
        patient.setDate(patientDto.getDate());

        ProgramDetails programDetails = new ProgramDetails();
        programDetails.setIds(programDetailsIds);
        programDetails.setPatient(patient);
        programDetails.setTherapyProgram(therapyProgram);
        programDetails.setTherapyProgramName(therapyProgram.getName());


        return programDetailsDao.save(programDetails);

    }

    @Override
    public boolean delete(String patient_id, String program_id) throws SQLException {
        ProgramDetailsIds programDetailsIds = new ProgramDetailsIds(patient_id, patient_id);

        TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(program_id);
        PatientDto patientDto = patientBo.findById(patient_id);

        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setT_id(therapyProgramDto.getT_id());
        therapyProgram.setName(therapyProgramDto.getName());
        therapyProgram.setDuration(therapyProgramDto.getDuration());
        therapyProgram.setDescription(therapyProgramDto.getDescription());
        therapyProgram.setFee(therapyProgramDto.getFee());

        Patient patient = new Patient();
        patient.setP_id(patientDto.getP_id());
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setContact(patientDto.getContact());
        patient.setEmail(patientDto.getEmail());
        patient.setHistory(patientDto.getHistory());
        patient.setDate(patientDto.getDate());

        ProgramDetails programDetails = new ProgramDetails();
        programDetails.setIds(programDetailsIds);
        programDetails.setPatient(patient);
        programDetails.setTherapyProgram(therapyProgram);
        programDetails.setTherapyProgramName(therapyProgram.getName());





        return programDetailsDao.delete(programDetails);

    }

    @Override
    public ProgramDetailsDto findProgramDetails(String patientId, String programId) {
        ProgramDetails programDetails = programDetailsDao.findProgramDetails(patientId, programId);

        ProgramDetailsDto programDetailsDto = new ProgramDetailsDto();
        programDetailsDto.setPatient(programDetails.getPatient().getP_id());
        programDetailsDto.setTherapyProgram(programDetails.getTherapyProgram().getT_id());
        programDetailsDto.setTherapyProgramName(programDetails.getTherapyProgramName());
        programDetailsDto.setCurrentPaymentStatus(programDetails.getCurrentPaymentStatus());

        return programDetailsDto;
    }

    @Override
    public boolean updateCurrentPayment(Session session, ProgramDetails programDetails) {
        try {

            String patientId = programDetails.getPatient().getP_id();
            String programId = programDetails.getTherapyProgram().getT_id();

            Query<ProgramDetails> query = session.createQuery("FROM ProgramDetails pd WHERE pd.patient.id = :patientId AND pd.therapyProgram.id = :programId", ProgramDetails.class);
            query.setParameter("patientId", patientId);
            query.setParameter("programId", programId);
            ProgramDetails programDetails1 = query.uniqueResult();

            if (programDetails1== null){
                return false;
            }

            session.merge(programDetails);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean save(String programId, String patientId, double programFee, Date registerDate) throws SQLException {
        ProgramDetailsIds programDetailsIds = new ProgramDetailsIds(programId, patientId);

        TherapyProgramDto therapyProgramDto = therapyProgramBo.findById(programId);
        PatientDto patientDto = patientBo.findById(patientId);

        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setT_id(therapyProgramDto.getT_id());
        therapyProgram.setName(therapyProgramDto.getName());
        therapyProgram.setDescription(therapyProgramDto.getDescription());
        therapyProgram.setDuration(therapyProgramDto.getDuration());
        therapyProgram.setFee(therapyProgramDto.getFee());

        Patient patient = new Patient();
        patient.setP_id(patientDto.getP_id());
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setDate(patientDto.getDate());
        patient.setContact(patientDto.getContact());
        patient.setHistory(patientDto.getHistory());

        ProgramDetails programDetails = new ProgramDetails();
        programDetails.setIds(programDetailsIds);
        programDetails.setTherapyProgram(therapyProgram);
        programDetails.setPatient(patient);
        programDetails.setCurrentPaymentStatus(programFee);
        programDetails.setTherapyProgramName(therapyProgram.getName());
        programDetails.setRegisterDate(registerDate);

        return programDetailsDao.save(programDetails);

    }
}
