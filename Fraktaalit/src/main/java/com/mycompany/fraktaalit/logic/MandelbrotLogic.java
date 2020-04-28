package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.graphics.FractalDrawer;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;

/**
 * Luokka tarjoaa toiminnallisuuden Mandelbrot joukkojen piirtämiseen
 */
public class MandelbrotLogic extends FractalDrawer {

    /**
     * Konstruktori
     * @param canvas    Kanvaasi, jolle Mandelbrot joukko piirretään
     */
    public MandelbrotLogic(Canvas canvas) {
        super(canvas);
    }

    /**
     * Metodi testaa, onko syotetty luku Mandelbrotin joukossa
     * @param c     Testattava luku
     * @param fs    Asetukset iterointeja varten
     * @return      Palauttaa arvon 0, jos luku on joukossa. Jos luku ei ole joukossa, niin palautettu arvo on positiivinen kokonaisluku
     */
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
