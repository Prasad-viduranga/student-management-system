package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.AppBar;

import java.io.IOException;

public class HomeFormController {

    public String preTitle = "Student Management System";
    public String preUrl = "/view/HomeForm.fxml";
    public JFXRippler rprAddNewStudent;
    public AnchorPane pneAddNewStudent;
    public JFXRippler rprSearchStudents;
    public AnchorPane pneSearchStudents;

    public void initialize() {

        rprAddNewStudent.setControl(pneAddNewStudent);
        rprSearchStudents.setControl(pneSearchStudents);
        pneAddNewStudent.setFocusTraversable(true);
        pneSearchStudents.setFocusTraversable(true);

    }

    public void pneAddNewStudent_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigate("Add New Student", "/view/StudentForm.fxml");
        }
    }

    public void pneSearchStudents_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {

            navigate("Search Student", "/view/StudentForm.fxml");
        }
//        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
//            rprSearchStudents.createManualRipple().run();
//        }
    }

    public void pneSearchStudents_OnMouseClicked(MouseEvent mouseEvent) {
        navigate("Search Students", "/view/SearchStudentsForm.fxml");
    }

    public void pneAddNewStudent_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        navigate("Add New Student", "/view/StudentForm.fxml");
    }


    public void pneAddNewStudent_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            preTitle = "Add New Student";
            preUrl = "/view/StudentForm.fxml";
            navigate("Add New Student", "/view/StudentForm.fxml");
        }
    }

    public void pneSearchStudents_OnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            preTitle = "Search Students";
            preUrl = "/view/SearchStudentsForm.fxml";
            navigate("Search Students", "/view/SearchStudentsForm.fxml");
        }
    }

    private void navigate(String title, String url) {

//        System.out.println(preTitle + "     " + preUrl);
        MainFormController ctrl = (MainFormController) pneSearchStudents.getScene().getUserData();
//        ctrl.navigate(title, url, MainFormController.NAV_ICON_BACK, () -> {
//
////                MainFormController ctrl = (MainFormController) pneSearchStudents.getScene().getUserData();
//            ctrl.navigate(preTitle, preUrl, MainFormController.NAV_ICON_BACK);
//        });
        ctrl.navigate(title, url, AppBar.NAV_ICON_BACK, () ->
                ctrl.navigate("Student Management System", "/view/HomeForm.fxml", AppBar.NAV_ICON_HOME));

    }
}
