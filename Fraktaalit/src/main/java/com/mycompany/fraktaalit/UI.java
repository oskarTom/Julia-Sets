package com.mycompany.fraktaalit;

import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author tomos
 */
public class UI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 1200;
        int screenHeight = 800;
        double width = 4;
        double height = 2.67;

        Scanner scanner = new Scanner(System.in);
        double Re = -0.68;
        double Im = -0.299;
        int iterations = 10000;
        Complex c = new Complex(Re,Im);
        
        Canvas canvas = new Canvas(screenWidth,screenHeight);
        BorderPane setup = new BorderPane();
        GridPane menu = new GridPane();
        
        menu.setPadding(new Insets(10));
        
        setup.setLeft(menu);
        setup.setCenter(canvas);
        Slider imSlider = new Slider();
        
        
        Slider reSlider = new Slider();
        reSlider.setSnapToTicks(true);
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
                draw(canvas, screenWidth, screenHeight, width, height, iterations, c);
            }
            reSlider.setValue(newValue);
        });
        reSlider.setOnMouseReleased(e -> {
            draw(canvas, screenWidth, screenHeight, width, height, iterations, new Complex(reSlider.getValue(),imSlider.getValue()));
        });
        
        imSlider.setMin(-1);
        imSlider.setMax(1);
        imSlider.setValue(Im);
        imSlider.setShowTickLabels(true);
        imSlider.setShowTickMarks(true);
        imSlider.setMajorTickUnit(1);
        Label imValue = new Label(""+Im);
        menu.add(new Label("Im: "),1,2);
        menu.add(imSlider,2,2);
        menu.add(imValue,3,2);
        //menu.getChildren().add(imLayout);
        
        imSlider.valueProperty().addListener(e -> {
            double newValue = Math.round(imSlider.getValue()*100.0)/100.0;
            imSlider.setValue(newValue);
            imValue.setText(newValue+"");
            if(!c.equals(new Complex(reSlider.getValue(), newValue))){
                c.setImaginary(newValue);
                draw(canvas, screenWidth, screenHeight, width, height, iterations, c);
            }
            imSlider.setValue(newValue);
        });
        
        imSlider.setOnMouseReleased(e -> {
            draw(canvas, screenWidth, screenHeight, width, height, iterations, new Complex(reSlider.getValue(),imSlider.getValue()));
            System.out.println(iterations);
        });
        
        draw(canvas, screenWidth, screenHeight, width, height, iterations, new Complex(reSlider.getValue(),imSlider.getValue()));
        
        Scene scene = new Scene(setup);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static void main(String[] args){
        launch(UI.class);
    }
    
    
    public void draw(Canvas canvas, int screenWidth, int screenHeight, double width, double height, int iterations, Complex c){
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        if(escapeTest(new Complex(0,0), c, iterations) == 0){
            iterations = 50;
        }
        for(int x = 0; x < screenWidth; x++){
            for(int y = 0; y < screenHeight; y++){
                int test = escapeTest(new Complex(x*width/screenWidth-width/2, y*height/screenHeight-height/2), c, iterations);
                if(test == 0){
                    pencil.setColor(x, y, Color.BLACK);
                }else{
                    if(test<=100){
                        pencil.setColor(x, y, Color.hsb(test*0.4+150,1,1,test*0.01-0.01));
                    }else{
                        pencil.setColor(x, y, Color.hsb(test*0.4+150,1,1));
                    }
                }
            }
        }
    }
    
    public int escapeTest(Complex z, Complex c, int iterations){
        Complex f = c.add(z.product(z));
        for (int i = 1; i < iterations; i++) {
            f = c.add(f.product(f));
            if (f.modulus() > 2) {
                return i;
            }
        }
        return 0;
    }

    
}
