package com.mycompany.fraktaalit;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author tomos
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 800;
        int screenHeight = 800;
        double width = 4;
        double height = 4;
        
        
         //LOGIC
        Scanner scanner = new Scanner(System.in);
        double Re = 0;
        double Im = 0.75;
        int iterations = 20;
        
        Complex c;
        
        //System.out.println("Syötä kompleksiluvun reaaliosa:");
        //Re = Double.parseDouble(scanner.nextLine());
        //System.out.println("Syötä kompleksiluvun imaginääriosa:");
        //Im = Double.parseDouble(scanner.nextLine());
        //System.out.println("Kuinka monta iteraatiota?");
        //iterations = Integer.parseInt(scanner.nextLine());
        
        c = new Complex(Re,Im);
        
        /*Complex f = c.add(z.product(z));
        for (int i = 0; i < iterations; i++) {
            f = c.add(f.product(f));
            if (f.modulus() > 2) {
                System.out.println("Escaped! No of iterations: "+i);
            }
        }
        if (f.modulus() <= 2) System.out.println("In Julia set");
        */
        
        //------------------------------------------------
        //                    DRAWING
        //------------------------------------------------
        Canvas canvas = new Canvas(screenWidth,screenHeight);
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        
        
        for(int x = 0; x < screenWidth; x++){
            for(int y = 0; y < screenHeight; y++){
                if (escapeTest(new Complex(x*width/screenWidth-width/2, y*height/screenHeight-height/2), c, iterations)) {
                    pencil.setColor(x, y, Color.BLACK);
                }
            }
        }
        
        pencil.setColor(0, 0, Color.RED);
        
        BorderPane setup = new BorderPane();
        setup.setCenter(canvas);
        Scene scene = new Scene(setup);
        
        
       
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(Main.class);
    }
    
    
    public boolean escapeTest(Complex z, Complex c, int iterations){
        Complex f = c.add(z.product(z));
        for (int i = 1; i < iterations; i++) {
            f = c.add(f.product(f));
            if (f.modulus() > 2) {
                return false;
            }
        }
        return true;
    }

    
}
