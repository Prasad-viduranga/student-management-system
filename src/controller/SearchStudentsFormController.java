package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Student;
import model.StudentTM;
import service.StudentService;
import util.MaterialUI;

import java.util.List;
import java.util.Optional;

public class SearchStudentsFormController {

    public TextField txtQuery;
    public TableView<StudentTM> tblResults;

    private StudentService studentService = new StudentService();

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);

        tblResults.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblResults.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblResults.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));

        loadAllStudents(null);
    }

    private void loadAllStudents(String query){
        tblResults.getItems().clear();

        for (Student student : studentService.findStudents(query == null || query.trim().isEmpty() ? "": query)) {
            tblResults.getItems().add(new StudentTM(student.getNic(), student.getFullName(), student.getAddress()));
        }

    }
}
