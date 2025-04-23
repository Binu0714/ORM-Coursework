package com.assignment.orm.service.orm_final_course_work_health_care.Cunfig;
import com.assignment.orm.service.orm_final_course_work_health_care.Entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        try (InputStream inputStream = FactoryConfiguration.class.getClassLoader().getResourceAsStream("hibernate.properties")) {
            if (inputStream == null) {
                throw new IOException("Unable to find hibernate.properties");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load hibernate.properties", e);
        }
        configuration.setProperties(properties);


        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TherapyProgram.class);
        configuration.addAnnotatedClass(TherapySessionScheduling.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(ProgramDetails.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public  Session getSession(){
        return sessionFactory.openSession();
    }
}
