/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.mycompany.fraktaalit.logic.Complex;
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
public class ComplexTest {
    
    public ComplexTest() {
    }
    
    @Test
    public void equalsWorks() {
        Complex z1 = new Complex(0,0);
        Complex z2 = new Complex(2,2);
        Complex z3 = new Complex(-2,-2);
        Complex z5 = new Complex(-1,2);
        Complex z6 = new Complex(-1,2);
        
        assertTrue(z1.equals(z1));
        assertTrue(z5.equals(z6));
        assertFalse(z2.equals(z3));
    }
    
    

    @Test
    public void modulusIsPositiveOrZero() {
        Complex z1 = new Complex(0,0);
        Complex z2 = new Complex(1,1);
        Complex z3 = new Complex(-1,1);
        Complex z4 = new Complex(1,-1);
        Complex z5 = new Complex(-1,-1);
        
        assertTrue(z1.modulus() == 0);
        assertTrue(z2.modulus() > 0);
        assertTrue(z3.modulus() > 0);
        assertTrue(z4.modulus() > 0);
        assertTrue(z5.modulus() > 0);
    }

    @Test
    public void toStringWorks() {
        Complex z1 = new Complex(0,0);
        Complex z2 = new Complex(1,1);
        Complex z3 = new Complex(-1,1);
        Complex z4 = new Complex(1,-1);
        Complex z5 = new Complex(-1,-1);

        assertEquals(z1.toString(),"0.0");
        assertEquals(z2.toString(),"1.0+1.0i");
        assertEquals(z3.toString(),"-1.0+1.0i");
        assertEquals(z4.toString(),"1.0-1.0i");
        assertEquals(z5.toString(),"-1.0-1.0i");
    }
}
