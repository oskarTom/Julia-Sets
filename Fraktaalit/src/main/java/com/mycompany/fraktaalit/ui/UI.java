package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomos
 */
public class UI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 640;
        int screenHeight = 360;
        double width = 5;
        double height = 2.8125;

        double Re = 0;
        double Im = 0;
        int iterations = 10000;
        Complex c = new Complex(Re,Im);
        
        Canvas juliaCanvas = new Canvas(screenWidth,screenHeight);
        JuliaLogic julia = new JuliaLogic(juliaCanvas);
        
        Canvas mandelbrotCanvas = new Canvas(screenWidth,screenHeight);
        MandelbrotLogic mandelbrot = new MandelbrotLogic(mandelbrotCanvas);
        mandelbrot.draw(width, height, iterations);
        
        Label MandelReCoordinates = new Label("Re: ");
        Label MandelImCoordinates = new Label("Im: ");
        
        Label cValue = new Label("c = "+c.toString());
        
        HBox buttons = new HBox();
        Button saveButton = new Button("Save as png");
        
        buttons.getChildren().add(saveButton);
        
        GridPane canvases = new GridPane();
        canvases.add(juliaCanvas, 1, 1);
        canvases.add(mandelbrotCanvas, 2, 1);
        canvases.add(cValue, 1, 2);
        canvases.add(buttons, 1, 3);
        canvases.add(MandelReCoordinates, 2, 2);
        canvases.add(MandelImCoordinates, 2, 3);
        
        saveButton.setOnAction(e -> {
            Label saveLabel = new Label("Saving functionality here");
            
            StackPane saveLayout = new StackPane();
            saveLayout.getChildren().add(saveLabel);
            
            Scene secondScene = new Scene(saveLayout, 230, 100);
 
            Stage newWindow = new Stage();
            newWindow.setTitle("Save Julia");
            newWindow.setScene(secondScene);
            newWindow.show();
        });
        
        
        mandelbrotCanvas.setOnMouseMoved(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ (x * width / screenWidth - width * 5 / 8));
            MandelImCoordinates.setText("Im: "+ (-y * height / screenHeight + height / 2));
        });
        
        mandelbrotCanvas.setOnMouseClicked(e -> {
            c.setReal(e.getX() * width / screenWidth - width * 5 / 8);
            c.setImaginary(-e.getY() * height / screenHeight + height / 2);
            julia.draw(width, height, iterations, c);
            cValue.setText("c = "+c.toString());
        });
        
        mandelbrotCanvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ (x * width / screenWidth - width * 5 / 8));
            MandelImCoordinates.setText("Im: "+ (-y * height / screenHeight + height / 2));
            
            c.setReal(x * width / screenWidth - width * 5 / 8);
            c.setImaginary(-y * height / screenHeight + height / 2);
            julia.draw(width, height, iterations, c);
            cValue.setText("c = "+c.toString());
        });
        
        BorderPane setup = new BorderPane();
        setup.setCenter(canvases);
        
        julia.draw(width, height, iterations, c);
        
        Scene scene = new Scene(setup);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Julia Sets");
        primaryStage.show();
    }
    
    
    public static void main(String[] args){
        launch(UI.class);
    }
}
