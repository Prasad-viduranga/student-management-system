package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Student;
import service.StudentService;
import util.MaterialUI;

import java.time.LocalDate;
import java.time.Period;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TextField txtDOB;
    public JFXButton btnSave;
    public Label lblTitle;
    public Label lblAge;

    private StudentService studentService = new StudentService();

    public void initialize() {

        MaterialUI.paintTextFields(txtNIC, txtFullName, txtAddress, txtDOB, txtContactNumber, txtEmail);

        setMaxLength(txtDOB, 10);
        setMaxLength(txtContactNumber, 11);


        txtDOB.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.length() > 10) {
//                txtDOB.setText(txtDOB.getText(0, 10));
//            }
            if (txtDOB.getLength() == 10){

//                try {
//                    Date dob = parseDate(txtDOB.getText());
//                    Date today = new Date();
//
//                    long diff = today.getTime() - dob.getTime();
//                    double year = diff / (1000 * 60 * 60 * 24 * 365.0) ;
//                    System.out.println("Year: " + year);

                LocalDate dob2 = LocalDate.parse(txtDOB.getText());
                Period between = Period.between(dob2, LocalDate.now());

                lblAge.setText(between.getYears() + " Years and " + between.getMonths() + " Months " + between.getDays() + " Days old");

//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }
        });


    }

    private void setMaxLength(TextField txt, int maxLength){
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength){
                txt.setText(txt.getText(0,maxLength));
            }
        });
    }

    public void txtDOB_OnKeyTyped(KeyEvent keyEvent) {

        if (keyEvent.getCharacter().equals("-") && (txtDOB.getText().length() == 4 || txtDOB.getText().length() == 7)) {
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {
            keyEvent.consume();
            return;
        }

        if ((txtDOB.getText().length() == 4 || txtDOB.getText().length() == 7) && (txtDOB.getCaretPosition() == txtDOB.getLength())) {
            txtDOB.appendText("-");
            txtDOB.positionCaret(txtDOB.getText().length() + 1);
        }
    }


    public void txtContactNumber_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtContactNumber.getText().length()==3)) {
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {
            keyEvent.consume();
            return;
        }

        if ((txtContactNumber.getText().length() == 3)&&(txtContactNumber.getCaretPosition()==txtContactNumber.getLength())) {
            txtContactNumber.appendText("-");
            txtContactNumber.positionCaret(txtContactNumber.getText().length() + 1);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        Student student = new Student();
        studentService.saveStudent(student);
    }
}
