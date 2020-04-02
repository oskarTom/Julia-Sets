package com.mycompany.fraktaalit;

import static java.lang.Math.sqrt;

/**
 *
 * @author tomos
 */
public class Complex {
    private double Re;
    private double Im;
    
    public Complex(double Re, double Im){
        this.Re = Re;
        this.Im = Im;
    }
    
    public double getReal(){
        return this.Re;
    }
    
    public void setReal(double Re){
        this.Re = Re;
    }
    
    public double getImaginary(){
        return this.Im;
    }
    
    public void setImaginary(double Im){
        this.Im = Im;
    }
    
    public boolean equals(Complex c){
        if(this.Re == c.getReal()){
            if(this.Im == c.getImaginary()){
                return true;
            }
        }
        return false;
    }
    
    public Complex add(Complex z){ 
        return new Complex(this.Re + z.getReal(), this.Im + z.getImaginary());
    }
    
    public Complex product(Complex z){
        return new Complex(this.Re*z.getReal() - this.Im*z.getImaginary() , 
                this.Re*z.getImaginary() + this.Im*z.getReal());
    }
    
    public double modulus(){
        return sqrt(Re*Re + Im*Im);
    }
    
    @Override
    public String toString(){
        return this.Re + "+" + this.Im + "i";
    }
}
