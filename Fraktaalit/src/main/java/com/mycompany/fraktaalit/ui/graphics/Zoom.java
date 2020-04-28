/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fraktaalit.ui.graphics;

import javafx.scene.canvas.Canvas;

public class Zoom {
    private int screenWidth;
    private int screenHeight;
    private double width;
    private double height;
    private double xOffset;
    private double yOffset;
    
    public Zoom(double width, double height, Canvas canvas) {
        this.width = width;
        this.height = height;
        this.xOffset = 0;
        this.yOffset = 0;
        this.screenWidth = (int) canvas.getWidth();
        this.screenHeight = (int) canvas.getHeight();
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

    public int getZoom() {
        return (int) (1 / this.height + 300);
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
    
    public void zoom(double multiplier, double x, double y) {
        if (multiplier > 0 && multiplier < 10) {
            this.xOffset = xRangeCenter(x) * (multiplier) + xOffset;
            this.yOffset = yRangeCenter(y) * (multiplier) + yOffset;
            this.width = this.width * (1 / (multiplier + 1));
            this.height = this.height * (1 / (multiplier + 1));
        } else if (multiplier < 0) {
            this.width = this.width * (-multiplier + 1);
            this.height = this.height * (-multiplier + 1);
        }
    }
    
    public double xRange(double x) {
        return x * width / screenWidth - width / 2 + xOffset;
    }
    
    public double xRangeCenter(double x) {
        return x * width / screenWidth - width / 2;
    }
    
    public double yRange(double y) {
        return -y * height / screenHeight + height / 2 + yOffset;
    }
    
    public double yRangeCenter(double y) {
        return -y * height / screenHeight + height / 2;
    }
}
