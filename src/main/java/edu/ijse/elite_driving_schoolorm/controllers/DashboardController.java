package edu.ijse.elite_driving_schoolorm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public AnchorPane ancDashboard;

    void navigateTo(String path) {
        try {
            ancDashboard.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashboard.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashboard.heightProperty());

            ancDashboard.getChildren().add(anchorPane);

            if (path.equals("/view/Dashboard.fxml")) {
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/StudentManagePage.fxml");
    }

    public void btnInstructorOnAction(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/InstructorManagePage.fxml");
    }

    public void btnCourseOnAction(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/CourseManagePage.fxml");
    }

    @FXML
    void logOut(MouseEvent event) {
        navigateTo("/view/CourseManagePage.fxml");
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/UserManagePage.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/mainDashbord.fxml");
    }

    public void goTOComponents(MouseEvent mouseEvent) {

    }

    @FXML
    void Lesson(ActionEvent event) {
        navigateTo("/view/LessonsManagePage.fxml");
    }

}
