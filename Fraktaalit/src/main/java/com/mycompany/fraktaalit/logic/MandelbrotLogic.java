package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.graphics.FractalDrawer;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;

/**
 *
 * @author tomos
 */
public class MandelbrotLogic extends FractalDrawer {

    public MandelbrotLogic(Canvas canvas) {
        super(canvas);
    }

    public int escapeTest(Complex c, FractalSetup fs) {
        Complex f = c;
        for (int i = 1; i < fs.getIterations(); i++) {
            f = c.add(f.product(f));
            if (f.modulus() > 2) {
                return i;
            }
        }
        return 0;
    }
}
