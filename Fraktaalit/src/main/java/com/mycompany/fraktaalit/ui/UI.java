package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tomos
 */
public class UI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 600;
        int screenHeight = 400;
        double width = 4;
        double height = 2.67;

        double Re = 0;
        double Im = 0;
        int iterations = 10000;
        Complex c = new Complex(Re,Im);
        
        Canvas juliaCanvas = new Canvas(screenWidth,screenHeight);
        JuliaLogic julia = new JuliaLogic(juliaCanvas);
        
        Canvas mandelbrotCanvas = new Canvas(screenWidth,screenHeight);
        MandelbrotLogic mandelbrot = new MandelbrotLogic(mandelbrotCanvas);
        mandelbrot.draw(width, height, 100000);
        
        BorderPane setup = new BorderPane();
        GridPane menu = new GridPane();
        
        menu.setPadding(new Insets(10));
        
        setup.setLeft(menu);
        VBox canvases = new VBox();
        canvases.getChildren().add(juliaCanvas);
        canvases.getChildren().add(mandelbrotCanvas);
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
