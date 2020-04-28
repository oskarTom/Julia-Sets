package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.graphics.FractalDrawer;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Luokka tarjoaa toiminnallisuuden Julia joukkojen piirtämiseen
 */
public class JuliaLogic extends FractalDrawer {

    /**
     * Konstruktori
     * @param canvas    Kanvaasi, jolle Julia joukko piirretään
     */
    public JuliaLogic(Canvas canvas) {
        super(canvas);
    }

    /**
     * Metodi testaa, onko syotetty luku Julian joukossa
     * @param z     Testattava luku
     * @param fs    Asetukset iterointeja varten
     * @return      Palauttaa arvon 0, jos luku on joukossa. Jos luku ei ole joukossa, niin palautettu arvo on positiivinen kokonaisluku
     */
    public int escapeTest(Complex z, FractalSetup fs) {
        Complex f = fs.getC().add(z.product(z));
        for (int i = 1; i < fs.getIterations(); i++) {
            f = fs.getC().add(f.product(f));
            if (f.modulus() > 2) {
                return i;
            }
        }
        return 0;
    }
}
