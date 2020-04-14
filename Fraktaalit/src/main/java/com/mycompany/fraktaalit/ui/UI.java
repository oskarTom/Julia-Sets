package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import com.mycompany.fraktaalit.logic.Zoom;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
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
        Zoom zoom = new Zoom(5, 2.8125, -1);
        Zoom zoomJulia = new Zoom(5, 2.8125);
        
        double Re = 0;
        double Im = 0;
        int iterations = 5000;
        Complex c = new Complex(Re,Im);
        
        Canvas juliaCanvas = new Canvas(screenWidth,screenHeight);
        JuliaLogic julia = new JuliaLogic(juliaCanvas);
        
        Canvas mandelbrotCanvas = new Canvas(screenWidth,screenHeight);
        MandelbrotLogic mandelbrot = new MandelbrotLogic(mandelbrotCanvas);
        mandelbrot.draw(zoom, iterations);
        
        Label MandelReCoordinates = new Label("Re: ");
        Label MandelImCoordinates = new Label("Im: ");
        
        Label cValue = new Label("c = "+c.toString());
        
        HBox buttons = new HBox();
        Button saveButton = new Button("Save as png");
        ToggleButton zoomButton = new ToggleButton("Zoom");
        
        buttons.getChildren().add(saveButton);
        
        GridPane canvases = new GridPane();
        canvases.add(juliaCanvas, 1, 1);
        canvases.add(mandelbrotCanvas, 2, 1);
        canvases.add(cValue, 1, 2);
        canvases.add(buttons, 1, 3);
        canvases.add(MandelReCoordinates, 2, 2);
        canvases.add(MandelImCoordinates, 2, 3);
        canvases.add(zoomButton, 2, 4);
        
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
            MandelReCoordinates.setText("Re: "+ zoom.xRange(x));
            MandelImCoordinates.setText("Im: "+ zoom.yRange(y));
        });
        
        mandelbrotCanvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            if(zoomButton.isSelected()){
                zoom.zoom(2, x, y);
                mandelbrot.draw(zoom, iterations);
            }else{
                c.setReal(zoom.xRange(x));
                c.setImaginary(zoom.yRange(y));
                julia.draw(zoomJulia.getWidth(), zoomJulia.getHeight(), iterations, c);
                cValue.setText("c = "+c.toString());
            }
            
        });
        
        mandelbrotCanvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ zoom.xRange(x));
            MandelImCoordinates.setText("Im: "+ zoom.yRange(y));

            if(!zoomButton.isSelected()){
                c.setReal(zoom.xRange(x));
                c.setImaginary(zoom.yRange(y));
                julia.draw(zoomJulia.getWidth(), zoomJulia.getHeight(), iterations, c);
                cValue.setText("c = "+c.toString());
            }
        });
        
        BorderPane setup = new BorderPane();
        setup.setCenter(canvases);
        
        julia.draw(zoomJulia.getWidth(), zoomJulia.getHeight(), iterations, c);
        
        Scene scene = new Scene(setup);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Julia Sets");
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(UI.class);
    }
}
