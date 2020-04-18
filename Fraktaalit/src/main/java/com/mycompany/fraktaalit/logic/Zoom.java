/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fraktaalit.logic;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author tomos
 */
public class Zoom {
    private int screenWidth;
    private int screenHeight = 360;
    private double width;
    private double height;
    private double xOffset;
    private double yOffset;
    
    public Zoom(double width, double height) {
        this.width = width;
        this.height = height;
        this.xOffset = 0;
        this.yOffset = 0;
    }
    
    public Zoom(double width, double height, double xOffset, Canvas canvas) {
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = 0;
        this.screenWidth = (int) canvas.getWidth();
        this.screenHeight = (int) canvas.getHeight();
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getXOffset() {
        return this.xOffset;
    }
    
    public double getYOffset() {
        return this.yOffset;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setXOffset(double xOffset) {
        this.xOffset = xOffset;
    }
    
    public void setYOffset(double yOffset) {
        this.yOffset = yOffset;
    }
    
    public void zoom(double multiplier, double x, double y){
        this.xOffset = xRange(x);
        this.yOffset = yRange(y);
        this.width = this.width * (1 / multiplier);
        this.height = this.height * (1 / multiplier);
    }
    
    public double xRange(double x){
        return x * width / screenWidth - width / 2 + xOffset;
    }
    
    public double yRange(double y){
        return -y * height / screenHeight + height / 2 + yOffset;
    }
}
