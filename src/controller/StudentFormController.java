package controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.MaterialUI;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TextField txtDOB;
    public JFXButton btnSave;
    public Label lblTitle;

    public void initialize() {

        MaterialUI.paintTextFields(txtNIC, txtFullName, txtAddress, txtDOB, txtContactNumber, txtEmail);

        txtDOB.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                txtDOB.setText(txtDOB.getText(0, 10));
            }
        });

        txtContactNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 11) {
                txtContactNumber.setText(txtDOB.getText(0, 11));
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
}
