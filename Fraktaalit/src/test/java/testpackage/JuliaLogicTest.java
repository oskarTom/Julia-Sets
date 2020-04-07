/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import javafx.scene.canvas.Canvas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomos
 */
public class JuliaLogicTest {
    
    private Canvas canvas;
    private JuliaLogic julia;
    
    public JuliaLogicTest() {
        canvas = new Canvas();
        julia = new JuliaLogic(canvas);
    }
    
    @Test
    public void doesntEscape(){
        Complex c = new Complex(0, 0);
        assertEquals(julia.escapeTest(new Complex(0,0), c, 10000),0);
    }
}
