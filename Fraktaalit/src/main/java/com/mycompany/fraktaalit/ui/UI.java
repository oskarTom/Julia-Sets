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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomos
 */
public class UI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 700;
        int screenHeight = 500;
        double width = 3.5;
        double height = 2.5;

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
        /*
        HBox canvases = new HBox();
        canvases.getChildren().add(juliaCanvas);
        canvases.getChildren().add(mandelbrotCanvas);
        ca*/
        
        GridPane canvases = new GridPane();
        canvases.add(juliaCanvas, 1, 1);
        canvases.add(mandelbrotCanvas, 2, 1);
        canvases.add(MandelReCoordinates, 2, 2);
        canvases.add(MandelImCoordinates, 2, 3);
        
        mandelbrotCanvas.setOnMouseMoved(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ (x * width / screenWidth - width * 5 / 8));
            MandelImCoordinates.setText("Im: "+ (y * height / screenHeight - height / 2));
        });
        
        mandelbrotCanvas.setOnMouseClicked(e -> {
            c.setReal(e.getX() * width / screenWidth - width * 5 / 8);
            c.setImaginary(e.getY() * height / screenHeight - height / 2);
            julia.draw(width, height, iterations, c);
        });
        
        
        BorderPane setup = new BorderPane();
        GridPane menu = new GridPane();
        
        menu.setPadding(new Insets(10));
        
        setup.setBottom(menu);
        setup.setCenter(canvases);
        Slider imSlider = new Slider();
        
        
        Slider reSlider = new Slider();
        reSlider.setMin(-2);
        reSlider.setMax(1);
        reSlider.setValue(Re);
        reSlider.setShowTickLabels(true);
        reSlider.setShowTickMarks(true);
        reSlider.setMajorTickUnit(1);
        reSlider.setMinorTickCount(5);
        
        reSlider.setBlockIncrement(0.01);
        Label reValue = new Label(""+Re);
        menu.add(new Label("Re: "),1,1);
        menu.add(reSlider,2,1);
        menu.add(reValue,3,1);
        
        reSlider.valueProperty().addListener(e -> {
            double newValue = Math.round(reSlider.getValue()*100.0)/100.0;
            reSlider.setValue(newValue);
            reValue.setText(newValue+"");
            if(!c.equals(new Complex(newValue, imSlider.getValue()))){
                c.setReal(newValue);
                julia.draw(width, height, iterations, c);
            }
            reSlider.setValue(newValue);
        });
        
        imSlider.setMin(-1);
        imSlider.setMax(1);
        imSlider.setValue(Im);
        imSlider.setShowTickLabels(true);
        imSlider.setShowTickMarks(true);
        imSlider.setMajorTickUnit(1);
        imSlider.setMinorTickCount(5);
        imSlider.setBlockIncrement(0.01);
        Label imValue = new Label(""+Im);
        menu.add(new Label("Im: "),1,2);
        menu.add(imSlider,2,2);
        menu.add(imValue,3,2);
        
        imSlider.valueProperty().addListener(e -> {
            double newValue = Math.round(imSlider.getValue()*100.0)/100.0;
            imSlider.setValue(newValue);
            imValue.setText(newValue+"");
            if(!c.equals(new Complex(reSlider.getValue(), newValue))){
                c.setImaginary(newValue);
                julia.draw(width, height, iterations, c);
            }
            imSlider.setValue(newValue);
        });
        
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
