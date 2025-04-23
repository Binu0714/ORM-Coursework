package com.assignment.orm.service.orm_final_course_work_health_care.BO;

import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.ChangeCredentialManageBo;
import com.assignment.orm.service.orm_final_course_work_health_care.BO.Custom.Impl.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public enum BoType {
        USER,
        ENCRYPT,

      PATIENT,
       THERAPIST,
       THERAPY_PROGRAM,
        THERAPY_SESSION_SCHEDULING,
        THERAPY_SESSION_SCHEDULING_BO,
        TRACK_SCHEDULE,
      PAYMENT,
    PROGRAM_DETAILS,
        FILTER_PATIENT,
        VIEW_PATIENT_PROGRAMS,
        VIEW_PATIENT_IN_ALL_PROGRAM,
        REPORT_AND_ANALYZE,
        VIEW_PATIENT_HISTORY,
        CHANGE_CREDENTIALS
    }

    public <T extends SuperBo> T getBo(BoType boType) {
        switch (boType) {
            case USER:
                return (T) new UserBoImpl();
            case ENCRYPT:
                return (T) new EncyptAndBycriptImpl(new BCryptPasswordEncoder());
            case THERAPY_PROGRAM:
                return (T) new TherapyProgramBoImpl();
            case THERAPIST:
                return (T) new TherapistBoImpl();
            case PATIENT:
                return (T) new PatientBoImpl();
            case THERAPY_SESSION_SCHEDULING:
                return (T) new TherapySessionSchedulingBoImpl();
            case THERAPY_SESSION_SCHEDULING_BO:
                return (T) new TherapySessionSchedulingBoImpl();
            case TRACK_SCHEDULE:
                return (T) new TrackScheduleBoImpl();
            case PROGRAM_DETAILS:
                return (T) new ProgramDetailsBoImpl();
            case FILTER_PATIENT:
                return (T) new FilterPatientBoImpl();
            case VIEW_PATIENT_PROGRAMS:
                return (T) new ViewPatientProgramBoImpl();
            case VIEW_PATIENT_IN_ALL_PROGRAM:
                return (T) new ViewAllTherapyProgramsImpl();
            case REPORT_AND_ANALYZE:
                return (T) new ReportAndAnalizeBoImpl();
            case VIEW_PATIENT_HISTORY:
                return (T) new ViewPatientHistoryBoImpl();
            case CHANGE_CREDENTIALS:
                return (T) new ChangeCredentialManageBoImpl();
            case PAYMENT:
                return (T) new PaymentInvoiceBoImpl();
            default:
                return null;
        }

    }
}

