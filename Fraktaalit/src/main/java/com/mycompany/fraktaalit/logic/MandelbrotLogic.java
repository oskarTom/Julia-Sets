/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 *
 * @author tomos
 */
public class MandelbrotLogic {
    private Canvas canvas;
    private int screenWidth;
    private int screenHeight;
    
    public MandelbrotLogic(Canvas canvas) {
        this.canvas = canvas;
        this.screenWidth = (int) canvas.getWidth();
        this.screenHeight = (int) canvas.getHeight();
    }
    
    public void draw(Zoom zoom, int iterations) {
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int test = escapeTest(new Complex(zoom.xRange(x), zoom.yRange(y)), iterations);
                if (test == 0) {
                    pencil.setColor(x, y, Color.BLACK);
                } else {
                    if (test <= 100) {
                        pencil.setColor(x, y, Color.hsb(test * 0.4 + 210, 1, 1, test * 0.01 - 0.01));
                    } else {
                        pencil.setColor(x, y, Color.hsb(test * 0.4 + 210, 1, 1));
                    }
                }
            }
        }
    }
    
    public int escapeTest(Complex c, int iterations) {
        Complex f = c;
        for (int i = 1; i < iterations; i++) {
            f = c.add(f.product(f));
            if (f.modulus() > 2) {
                return i;
            }
        }
        return 0;
    }
}
