package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 *
 * @author tomos
 */
public class JuliaLogic {
    private Canvas canvas;
    private int screenWidth;
    private int screenHeight;
    
    public JuliaLogic(Canvas canvas) {
        this.canvas = canvas;
        this.screenWidth = (int) canvas.getWidth();
        this.screenHeight = (int) canvas.getHeight();
    }
    
    public void draw(Zoom zoom, int iterations, Complex c) {
        double width = zoom.getWidth();
        double height = zoom.getHeight();
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        iterations = (escapeTest(new Complex(0, 0), c, iterations) == 0) ? 100 : iterations;
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int test = escapeTest(new Complex(x * width / screenWidth - width / 2, y * height / screenHeight - height / 2), c, iterations);
                if (test == 0) {
                    pencil.setColor(x, y, Color.BLACK);
                } else {
                    if (test <= 50) {
                        pencil.setColor(x, y, Color.hsb(test * 0.4 + 250, 1, 1, test * 0.01 + 0.49));
                    } else {
                        pencil.setColor(x, y, Color.hsb(test * 0.4 + 250, 1, 1));
                    }
                }
            }
        }
    }
    
    public int escapeTest(Complex z, Complex c, int iterations) {
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
