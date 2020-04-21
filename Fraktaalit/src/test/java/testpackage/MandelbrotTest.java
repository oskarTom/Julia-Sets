/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
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
        assertEquals(logic.escapeTest(c, 10000),0);
    }
    
    @Test
    public void escapes(){
        Complex c = new Complex(-1.5, 0.01);
        assertTrue(logic.escapeTest(c, 10000) > 0);
    }}
