package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {

    public ImageView imgClose;
    public ImageView imgMinimize;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    public AnchorPane pneSearchStudents;
    public AnchorPane pneAddNewStudent;
    public JFXRippler rprAddNewStudent;
    public JFXRippler rprSearchStudents;
    public AnchorPane pneStage;
    private double xMousePos;
    private double yMousePos;

    public void initialize() {
        initWindow();


    }


    public void navigate(String title, String url) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            pneStage.getChildren().clear();
            pneStage.getChildren().add(root);
            lblTitle.setText(title);
            Stage stage = (Stage) (pneStage.getScene().getWindow());

            Platform.runLater(()->{
                stage.sizeToScene();
                stage.centerOnScreen();

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initWindow() {
        lblTitle.setMouseTransparent(true);

        Platform.runLater(() -> {
            lblTitle.setText(((Stage) (imgClose.getScene().getWindow())).getTitle());
        });

        pneAppBar.setOnMousePressed(event -> {
            xMousePos = event.getX();
            yMousePos = event.getY();
        });

        pneAppBar.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                Window mainWindow = imgMinimize.getScene().getWindow();
                mainWindow.setX(event.getScreenX() - xMousePos);
                mainWindow.setY(event.getScreenY() - yMousePos);
            }
        });

        imgClose.setOnMouseEntered(event -> imgClose.setImage(new Image("/view/assets/icons/close-hover.png")));
        imgClose.setOnMouseExited(event -> imgClose.setImage(new Image("/view/assets/icons/close.png")));
        imgClose.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).close());

        imgMinimize.setOnMouseEntered(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize-hover.png")));
        imgMinimize.setOnMouseExited(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize.png")));
        imgMinimize.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).setIconified(true));
    }


    public void pneAddNewStudent_OnKeyPress(KeyEvent keyEvent) {
    }

    public void pneAddNewStudent_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void pneSearchStudents_OnKeyPress(KeyEvent keyEvent) {
    }

    public void pneSearchStudents_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void pneSearchStudents_OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void pneAddNewStudent_OnMouseClicked(MouseEvent mouseEvent) {
    }
}
