package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import util.AppBar;
import util.AppBar;
import util.NavActionListener;

import java.io.IOException;

public class MainFormController {

    public static final int NAV_ICON_NONE = 0;
    public static final int NAV_ICON_BACK = 1;
    public static final int NAV_ICON_HOME = 2;

    public ImageView imgClose;
    public ImageView imgMinimize;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    public AnchorPane pneSearchStudents;
    public AnchorPane pneAddNewStudent;
    public JFXRippler rprAddNewStudent;
    public JFXRippler rprSearchStudents;
    public AnchorPane pneStage;
    public ImageView imgBack;
    public ImageView imgNav;
    private double xMousePos;
    private double yMousePos;
    private AppBar icon =  AppBar.NAV_ICON_NONE;
    private NavActionListener navActionListener = null;

    public void initialize() { initWindow(); }

    public void navigate(String title, String url, AppBar icon) {
        navigate(title, url, icon, null);
    }

    public void navigate(String title, String url, AppBar icon, NavActionListener navActionListener) {

        try {
            this.icon = icon;
            this.navActionListener = navActionListener;
            imgNav.setVisible(true);

            if (this.navActionListener == null){
                imgNav.setCursor(Cursor.DEFAULT);
            }else{
                imgNav.setCursor(Cursor.HAND);
            }

            switch (icon) {
                case NAV_ICON_NONE:
                    imgNav.setVisible(false);
                    imgNav.setUserData(null);
                    break;
                case NAV_ICON_HOME:
                    imgNav.setImage(new Image("view/assets/icons/home.png"));
                    imgNav.setUserData(new Image("view/assets/icons/home-hover.png"));
                    break;
                case NAV_ICON_BACK:
                    imgNav.setImage(new Image("view/assets/icons/arrow.png"));
                    imgNav.setUserData(new Image("view/assets/icons/arrow-hover.png"));
                    break;
            }

            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            FadeTransition ft = new FadeTransition(Duration.millis(750), root);

            pneStage.getChildren().clear();
            pneStage.getChildren().add(root);
            Stage stage = (Stage) (pneStage.getScene().getWindow());
            lblTitle.setText(title);

            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            Platform.runLater(() -> {
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

        imgNav.setOnMouseEntered(event -> swapNavIcon());
        imgNav.setOnMouseExited(event -> swapNavIcon());

        imgNav.setOnMouseClicked(event -> {
            if (navActionListener != null) {
                navActionListener.handler();
            }
        });

        imgClose.setOnMouseEntered(event -> imgClose.setImage(new Image("/view/assets/icons/close-hover.png")));
        imgClose.setOnMouseExited(event -> imgClose.setImage(new Image("/view/assets/icons/close.png")));
        imgClose.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).close());

        imgMinimize.setOnMouseEntered(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize-hover.png")));
        imgMinimize.setOnMouseExited(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize.png")));
        imgMinimize.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).setIconified(true));

        imgNav.setOnMouseEntered(event -> swapNavIcon());
        imgNav.setOnMouseExited(event -> swapNavIcon());


    }

    private void swapNavIcon() {

        if (icon != AppBar.NAV_ICON_NONE && navActionListener != null) {
            Image tempImage = imgNav.getImage();
            imgNav.setImage((Image) imgNav.getUserData());
            imgNav.setUserData(tempImage);

        }
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
