package com.mycompany.fraktaalit.ui.graphics;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.FractalSetup;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakti luokka fraktaaleiden piirtäjiä varten
 */
public abstract class FractalDrawer {

    private Canvas canvas;
    private int screenWidth;
    private int screenHeight;
    private Color inSetColor;
    private List<Color> list;

    /**
     * Konstruktori
     * @param canvas    Kanvaasi, jolle fraktaali piirretään
     */
    public FractalDrawer(Canvas canvas) {
        this.canvas = canvas;
        this.screenWidth = (int) canvas.getWidth();
        this.screenHeight = (int) canvas.getHeight();
        this.inSetColor = Color.BLACK;
        this.list = new ArrayList<>();
        this.list.add(Color.DARKBLUE);
        this.list.add(Color.WHITE);
        this.list.add(Color.ORANGE);
        this.list.add(Color.BLACK);
    }

    public Color getInSetColor() {
        return this.inSetColor;
    }

    public List getList() {
        return this.list;
    }

    /**
     * Metodi määrittelee värin pisteelle, joka ei ole joukossa
     * @param list  Lista käytettävistä väreistä
     * @param test  Testi määrittelee, kuinka nopeasti luku on karannut joukosta
     * @return      Väri, jota lopulta käytetään
     */
    public Color outOfSetColor(List<Color> list, double test) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (test % (list.size() * 255) < (i + 1) * 255) {
                Color color = list.get(i);
                Color nextColor = list.get(i + 1);
                return rgbGradient(test, color, nextColor);
            }
        }
        Color color = list.get(list.size() - 1);
        Color nextColor = list.get(0);
        return rgbGradient(test, color, nextColor);
    }

    /**
     * Metodi määrittelee värit kahden valitun värin välille niin, että siitymä yhdestä
     * väristä toiseen on tasainen gradientti
     * @param test      Testi määrittelee, kuinka nopeasti luku on karannut joukosta
     * @param color     Väri josta siirrytään
     * @param nextColor Väri johon siirrytään
     * @return          Testatulle luvulle määritetty väri
     */
    private Color rgbGradient(double test, Color color, Color nextColor) {
        return Color.rgb((int) (color.getRed() * (255 - test % 255) + nextColor.getRed() * (test % 255)),
                         (int) (color.getGreen() * (255 - test % 255) + nextColor.getGreen() * (test % 255)),
                         (int) (color.getBlue() * (255 - test % 255) + nextColor.getBlue() * (test % 255))
        );
    }

    /**
     * Metodi asettaa muuttujan inSetColor määrittelemän värin pisteelle, jos
     * pistettä vastaava luku ei ole joukossa. Muussa tapauksessa pisteelle määritetään
     * väri outOfSetColor metodin avulla
     * @param test      Testi määrittelee, onko luku joukon sisällä
     * @param pencil    Javafx työkalu kanvaasille piirtämistä varten
     * @param x         Pisteen x-koordinaatti
     * @param y         Pisteen y-koordinaatti
     */
    public void setColor(int test, PixelWriter pencil, int x, int y) {
        if (test == 0) {
            pencil.setColor(x, y, inSetColor);
        } else {
            pencil.setColor(x, y, outOfSetColor(list, test * 10));
        }
    }

    /**
     * Metodi käy kanvaasin jokaisen pisteen läpi yksi kerrallaan, ja
     * asettaa jokaiselle pisteelle värin riippuen siitä, karkaavatko ne joukosta
     * vai ei
     * @param fs    Asetukset kuvausta varten
     */
    public void draw(FractalSetup fs) {
        PixelWriter pencil = canvas.getGraphicsContext2D().getPixelWriter();
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int test = escapeTest(new Complex(fs.getZoom().xRange(x), fs.getZoom().yRange(y)), fs);
                setColor(test, pencil, x, y);
            }
        }
    }

    /**
     * Metodi testaa, onko syotetty luku Mandelbrotin joukossa
     *
     * Tämä funktio määritellään uudelleen luokassa, joka perii tämän luokan
     *
     * @param c     Testattava luku
     * @param fs    Asetukset iterointeja varten
     * @return      Palauttaa arvon 0, jos luku on joukossa. Jos luku ei ole joukossa, niin palautettu arvo on positiivinen kokonaisluku
     */
    public int escapeTest(Complex c, FractalSetup fs) {
        return 0;
    }
}
