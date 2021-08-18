package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MaterialUI {


    // Meaning of "Mixini" that instance can be use from-
    // -out side of the it's class without inherit.So that-
    // -method should have static and not private.
    // So we can access that method call by "ClassName.method();"


    public static void paintTextFields(TextField... textFields){
        for (TextField txt : textFields) {
            AnchorPane pneTextContainer = (AnchorPane) txt.getParent();
            String floatedText = txt.getAccessibleText();
            Canvas canvas = new Canvas(pneTextContainer.getPrefWidth(), pneTextContainer.getPrefHeight());
            GraphicsContext ctx = canvas.getGraphicsContext2D();

            pneTextContainer.getChildren().add(0,canvas);
            redrawTextFieldCanvas(canvas, ctx, floatedText, false);

            txt.focusedProperty().addListener((observable, oldValue, newValue) -> {
                redrawTextFieldCanvas(canvas, ctx, floatedText, newValue);
            });

            pneTextContainer.setOnMouseClicked(event -> {
                txt.requestFocus();
            });
        }
    }

    private static void redrawTextFieldCanvas(Canvas canvas, GraphicsContext ctx, String floatedText, boolean focus){
        ctx.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        ctx.setStroke(focus? Color.valueOf("#6200EE") : Color.rgb(0,0,0,0.30));
        ctx.strokeRoundRect(2,4,canvas.getWidth() - 4, canvas.getHeight() - 6,10,10);
        ctx.setFill(Color.WHITE);
        ctx.fillRect(10,0,new Text(floatedText).getLayoutBounds().getWidth() + 10,20);
        ctx.setFill(focus? Color.valueOf("#6200EE"):Color.rgb(0,0,0,0.6) );
        ctx.fillText(floatedText, 15, 10 );
    }


}
