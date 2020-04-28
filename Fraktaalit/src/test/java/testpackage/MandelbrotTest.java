package testpackage;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.FractalSetup;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;
import org.junit.Test;
import static org.junit.Assert.*;

public class MandelbrotTest {
    
    private Canvas canvas;
    private MandelbrotLogic logic;
    
    public MandelbrotTest() {
        canvas = new Canvas();
        logic = new MandelbrotLogic(canvas);
    }

    
    @Test
    public void doesntEscape(){
        Complex c = new Complex(0, 0);
        assertEquals(logic.escapeTest(c, new FractalSetup(new Zoom(100,100,canvas), 10000)),0);
    }
    
    @Test
    public void escapes(){
        Complex c = new Complex(-1.5, 0.01);
        assertTrue(logic.escapeTest(c, new FractalSetup(new Zoom(100,100,canvas), 10000)) > 0);
    }
}
