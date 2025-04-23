module com.assignment.orm.service.orm_final_course_work_health_care {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires static lombok;
    requires javafx.base;
    requires spring.security.crypto;
    requires spring.security.core;
    requires org.slf4j;
    requires spring.security.config;
    requires mysql.connector.j;

//    requires jbcrypt;

    opens com.assignment.orm.service.orm_final_course_work_health_care.Controllers to javafx.fxml;
    opens com.assignment.orm.service.orm_final_course_work_health_care.Entity to org.hibernate.orm.core;
    opens com.assignment.orm.service.orm_final_course_work_health_care.DTO to javafx.base;
    opens com.assignment.orm.service.orm_final_course_work_health_care.DTO.TM to javafx.base;



    exports com.assignment.orm.service.orm_final_course_work_health_care;
    exports com.assignment.orm.service.orm_final_course_work_health_care.Controllers to javafx.fxml;
    opens com.assignment.orm.service.orm_final_course_work_health_care to javafx.fxml;
}