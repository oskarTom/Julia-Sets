package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.graphics.FractalDrawer;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaLogic extends FractalDrawer {

    public JuliaLogic(Canvas canvas) {
        super(canvas);
    }

    public int escapeTest(Complex z, FractalSetup setup) {
        Complex f = setup.getC().add(z.product(z));
        for (int i = 1; i < setup.getIterations(); i++) {
            f = setup.getC().add(f.product(f));
            if (f.modulus() > 2) {
                return i;
            }
        }
        return 0;
    }
}
