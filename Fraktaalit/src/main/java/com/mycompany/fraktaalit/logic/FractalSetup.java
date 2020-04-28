package com.mycompany.fraktaalit.logic;

import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.scene.canvas.Canvas;

public class FractalSetup {
    private Complex c;
    private Zoom zoom;
    private int iterations;

    public FractalSetup(Complex c, Zoom zoom, int iterations) {
        this.c = c;
        this.zoom = zoom;
        if (zoom.getZoom() > 2000) {
            this.iterations = iterations;
        } else {
            this.iterations = zoom.getZoom();
        }

    }

    public FractalSetup(Zoom zoom, int iterations) {
        this.zoom = zoom;
        if (zoom.getZoom() > 2000) {
            this.iterations = iterations;
        } else {
            this.iterations = zoom.getZoom();
        }
    }

    public Complex getC(){ return this.c; }

    public Zoom getZoom() { return this.zoom; }

    public int getIterations() { return this.iterations; }

    public void setIterations(int iterations) { this.iterations = iterations; }

}
