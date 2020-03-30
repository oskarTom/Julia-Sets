package com.mycompany.fraktaalit;

import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
public class UI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        int screenWidth = 1200;
        int screenHeight = 800;
        double width = 4;
        double height = 2.67;

        Scanner scanner = new Scanner(System.in);
        double Re = -0.8;
        double Im = 0.156;
        int iterations = 2000;
        
        Complex c;
        
        System.out.println("Syötä kompleksiluvun reaaliosa:");
        Re = Double.parseDouble(scanner.nextLine());
        System.out.println("Syötä kompleksiluvun imaginääriosa:");
        Im = Double.parseDouble(scanner.nextLine());
        System.out.println("Kuinka monta iteraatiota?");
        iterations = Integer.parseInt(scanner.nextLine());
        
        c = new Complex(Re,Im);
        
        //------------------------------------------------
        //                    DRAWING
        //------------------------------------------------
        
        Canvas canvas = new Canvas(screenWidth,screenHeight);
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        
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
        
        BorderPane setup = new BorderPane();
        setup.setCenter(canvas);
        Scene scene = new Scene(setup);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static void main(String[] args){
        launch(UI.class);
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
