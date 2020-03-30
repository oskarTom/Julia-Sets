/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.fraktaalit.Complex;
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
public class FraktaalitTest {
    /*
    public FraktaalitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
*/
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void kompleksiluvunItseisarvoOnEpanegatiivinen(){
        Complex z1 = new Complex(1,1);
        Complex z2 = new Complex(1,-1);
        Complex z3 = new Complex(-1,1);
        Complex z4 = new Complex(-1,-1);
        Complex z5 = new Complex(0,0);
        
        assertTrue(z1.modulus() > 0);
        assertTrue(z2.modulus() > 0);
        assertTrue(z3.modulus() > 0);
        assertTrue(z4.modulus() > 0);
        assertTrue(z5.modulus() == 0);
    }
}
