package com.mycompany.fraktaalit.logic;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

abstract class Logic {

    public void setColor(int test, PixelWriter pencil, int x, int y) {
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
