package edu.ijse.elite_driving_schoolorm.controllers;

import edu.ijse.elite_driving_schoolorm.bo.BOFactory;
import edu.ijse.elite_driving_schoolorm.bo.BOTypes;
import edu.ijse.elite_driving_schoolorm.bo.custom.UserBO;
import edu.ijse.elite_driving_schoolorm.configuration.FactoryConfiguration;
import edu.ijse.elite_driving_schoolorm.dto.UserDTO;
import edu.ijse.elite_driving_schoolorm.Util.Authutil;
import edu.ijse.elite_driving_schoolorm.Util.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

public class LoginPageController {
    public TextField username;
    public TextField password;
    public Button login;

    private final String userNamePattern = "^[A-Za-z0-9_ ]{3,}$";
    private final String passwordPattern = "^[A-Za-z0-9@#$%^&+=]{6,}$";
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);


    private void initialize() {
        username.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        password.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        login.setDisable(true);
    }

    private void validFields() {
        boolean isValidUsername = username.getText().matches(userNamePattern);
        boolean isValidPassword = password.getText().matches(passwordPattern);

        username.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        password.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        if (!isValidUsername)
            username.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        if (!isValidPassword)
            password.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        login.setDisable(!(isValidUsername && isValidPassword));
    }

    public void login(ActionEvent actionEvent) {
        String inputUsername = username.getText();
        String inputPassword = password.getText();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            UserDTO user = userBO.getUserByEmail(inputUsername);

            if (user != null && PasswordUtils.checkPassword(inputPassword, user.getPassword())) {

                Authutil.setCurrentUser(user);

                // Login success
                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                Stage dashboardStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                dashboardStage.setScene(new Scene(dashboardRoot));
                dashboardStage.setTitle("Elite Driving School");
                dashboardStage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Login Fail", ButtonType.OK).show();
        }
    }
}
