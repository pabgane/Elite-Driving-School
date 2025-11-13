module edu.ijse.elite_driving_schoolorm {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jbcrypt;


    opens edu.ijse.elite_driving_schoolorm.configuration to jakarta.persistence;
    opens edu.ijse.elite_driving_schoolorm.entity to org.hibernate.orm.core;

    opens edu.ijse.elite_driving_schoolorm to javafx.fxml;
    opens edu.ijse.elite_driving_schoolorm.controllers to javafx.fxml;
    opens edu.ijse.elite_driving_schoolorm.dto to javafx.base;
    exports edu.ijse.elite_driving_schoolorm.dto.tm;
    exports edu.ijse.elite_driving_schoolorm;
}