package com.mycompany.fraktaalit.logic;

import static java.lang.Math.sqrt;

/**
 * Luokka määrittelee kompleksiluvun ja sen yksinkertaiset laskutoimitukset
 */
public class Complex {
    private double re;
    private double im;

    /**
     * Konstruktori
     * @param re    Kompleksiluvun reaaliosa
     * @param im    Kompleksiluvun imaginääriosa
     */
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getReal() {
        return this.re;
    }

    public void setReal(double re) {
        this.re = re;
    }

    public double getImaginary() {
        return this.im;
    }

    public void setImaginary(double im) {
        this.im = im;
    }

    /**
     * Vertaa, ovatko kaksi kompleksilukua yhtäsuuret
     * @param c     Verrattava
     * @return      true / false
     */
    public boolean equals(Complex c) {
        return this.re == c.getReal() && this.im == c.getImaginary();
    }

    /**
     * Summaa kompleksiluvut yhteen
     * @param z     Summattava
     * @return      Summa
     */
    public Complex add(Complex z) { 
        return new Complex(this.re + z.getReal(), this.im + z.getImaginary());
    }

    /**
     * Kertoo kompleksiluvut keskenään
     * @param z     Kerrottava
     * @return      Tulo
     */
    public Complex product(Complex z) {
        return new Complex(this.re * z.getReal() - this.im * z.getImaginary() , 
                        this.re * z.getImaginary() + this.im * z.getReal());
    }

    /**
     * Palauttaa kompleksiluvun moduulin (itseisarvon)
     * @return  moduuli
     */
    public double modulus() {
        return sqrt(re * re + im * im);
    }

    /**
     * Määrittelee miten kompleksiluku ilmaistaan tekstissä
     * @return  Kompleksiluku muodossa Re+Imi. (esim. 2.0+1.5i)
     */
    @Override
    public String toString() {
        if (this.im < 0) {
            return "" + this.re + this.im + "i";
        }
        if (this.im == 0) {
            return this.re + "";
        }
        
        return this.re + "+" + this.im + "i";
    }
}
