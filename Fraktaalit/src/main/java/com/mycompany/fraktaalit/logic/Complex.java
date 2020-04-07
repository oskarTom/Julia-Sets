package com.mycompany.fraktaalit.logic;

import static java.lang.Math.sqrt;

/**
 *
 * @author tomos
 */
public class Complex {
    private double re;
    private double im;
    
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
    
    public boolean equals(Complex c) {
        if (this.re == c.getReal()) {
            if (this.im == c.getImaginary()) {
                return true;
            }
        }
        return false;
    }
    
    public Complex add(Complex z) { 
        return new Complex(this.re + z.getReal(), this.im + z.getImaginary());
    }
    
    public Complex product(Complex z) {
        return new Complex(this.re * z.getReal() - this.im * z.getImaginary() , 
                this.re * z.getImaginary() + this.im * z.getReal());
    }
    
    public double modulus() {
        return sqrt(re * re + im * im);
    }
    
    @Override
    public String toString() {
        return this.re + "+" + this.im + "i";
    }
}