package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.FractalSetup;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.awt.*;

/**
 *
 * @author tomos
 */
public class UI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //---------------------------------------------------
        //                  INITALIZING
        //---------------------------------------------------
        int screenWidth = 640;
        int screenHeight = 360;
        int mandelbrotScreenWidth = 480;
        double Re = 0;
        double Im = 0;
        int iterations = 1000; //default: 5000
        Complex c = new Complex(Re,Im);
        
        Canvas juliaCanvas = new Canvas(screenWidth,screenHeight);
        JuliaLogic julia = new JuliaLogic(juliaCanvas);
        
        Canvas mandelbrotCanvas = new Canvas(mandelbrotScreenWidth, screenHeight);
        MandelbrotLogic mandelbrot = new MandelbrotLogic(mandelbrotCanvas);
        
        Zoom zoomMandelbrot = new Zoom(3.5, 10.5/4, -0.5, mandelbrotCanvas);
        Zoom initialZoomMandelbrot = new Zoom(3.5, 10.5/4, -0.5, mandelbrotCanvas);
        Zoom zoomJulia = new Zoom(5.0, 2.8125, juliaCanvas);
        Zoom initialZoomJulia = new Zoom(5.0, 2.8125, juliaCanvas);
        
        mandelbrot.draw(new FractalSetup(zoomMandelbrot, iterations));

        //---------------------------------------------------
        //                  CUSTOM TITLEBAR
        //---------------------------------------------------

        primaryStage.initStyle(StageStyle.UNDECORATED);
        BorderPane titlebar = new BorderPane();
        HBox toolbarRight = new HBox();
        HBox toolbar = new HBox();

        Point cursorCoord = new Point(0,0);
        titlebar.setOnMouseReleased(e -> {
            cursorCoord.setLocation(0,0);
        });
        titlebar.setOnMousePressed(e -> {
            cursorCoord.setLocation(e.getX(), e.getY());
        });

        titlebar.setOnMouseDragged(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setX(e.getScreenX() - cursorCoord.getX());
            stage.setY(e.getScreenY() - cursorCoord.getY());
        });

        Button exit = new Button("×");
        exit.getStyleClass().add("close-btn");
        Button minimize = new Button("-");
        toolbarRight.getChildren().addAll(minimize,exit);
        toolbarRight.setPadding(new Insets(0,0,1,10));
        toolbarRight.setAlignment(Pos.CENTER_RIGHT);




        MenuItem saveButton = new MenuItem("Save Julia set as png");
        MenuButton filesButton = new MenuButton("Files");
        filesButton.getItems().add(saveButton);

        MenuItem lockToCenterButton = new MenuItem("Unlock Julia from center");
        ToggleButton lockToCenter = new ToggleButton();
        MenuItem resetJuliaZoom = new MenuItem("Reset Julia set zoom");
        MenuItem resetMandelbrotZoom = new MenuItem("Reset Mandelbrot set zoom");
        MenuButton editButton = new MenuButton("Edit");
        editButton.getItems().addAll(lockToCenterButton, resetMandelbrotZoom, resetJuliaZoom);

        toolbar.getChildren().addAll(filesButton, editButton);


        titlebar.setLeft(toolbar);
        titlebar.setCenter(new Label("Julia Sets"));
        titlebar.setRight(toolbarRight);

        SaveMenu saveMenu = new SaveMenu();

        //---------------------------------------------------
        //                  GUI SETUP
        //---------------------------------------------------

        Label MandelReCoordinates = new Label("Re: ");
        Label MandelImCoordinates = new Label("Im: ");
        Label MIterations = new Label("Iterations: "+zoomMandelbrot.getZoom());
        VBox coordinates = new VBox();
        coordinates.getChildren().add(MandelReCoordinates);
        coordinates.getChildren().add(MandelImCoordinates);
        coordinates.getChildren().add(MIterations);
        coordinates.setAlignment(Pos.BOTTOM_LEFT);
        coordinates.setPickOnBounds(false);
        
        Label cValue = new Label("c = "+c.toString());

        StackPane juliaPane = new StackPane();
        juliaPane.getChildren().addAll(juliaCanvas, cValue);
        juliaPane.setAlignment(Pos.BOTTOM_LEFT);

        StackPane mandelbrotPane = new StackPane();
        mandelbrotPane.getChildren().addAll(mandelbrotCanvas, coordinates);
        mandelbrotPane.setAlignment(Pos.BOTTOM_LEFT);

        GridPane canvases = new GridPane();
        canvases.add(juliaPane, 1, 1);
        canvases.add(mandelbrotPane, 2, 1);

        //---------------------------------------------------
        //                  ACTIONS
        //---------------------------------------------------

            //TOOLBAR

        exit.setOnAction(e -> {
            primaryStage.close();
        });

        minimize.setOnAction(e -> {
            primaryStage.setIconified(true);
        });

        saveButton.setOnAction(e -> {
            saveMenu.start(primaryStage, c, zoomJulia);
        });

        lockToCenterButton.setOnAction(e -> {
            if(lockToCenter.isSelected()) {
                lockToCenterButton.setText("Unlock Julia from center");
            } else {
                lockToCenterButton.setText("Lock Julia to center");
            }
            lockToCenter.fire();
        });

            //CANVAS
        
        mandelbrotCanvas.setOnMouseMoved(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ zoomMandelbrot.xRange(x));
            MandelImCoordinates.setText("Im: "+ zoomMandelbrot.yRange(y));
        });
        
        mandelbrotCanvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            c.setReal(zoomMandelbrot.xRange(x));
            c.setImaginary(zoomMandelbrot.yRange(y));
            julia.draw(new FractalSetup(c, zoomJulia, iterations));
            cValue.setText("c = "+c.toString());        
        });
        
        mandelbrotCanvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ zoomMandelbrot.xRange(x));
            MandelImCoordinates.setText("Im: "+ zoomMandelbrot.yRange(y));
            c.setReal(zoomMandelbrot.xRange(x));
            c.setImaginary(zoomMandelbrot.yRange(y));
            julia.draw(new FractalSetup(c, zoomJulia, iterations));
            cValue.setText("c = "+c.toString());
        });
        
        mandelbrotCanvas.setOnScroll(e -> {
            double x = e.getX();
            double y = e.getY();
            double delta = e.getDeltaY();
            FractalSetup fs = new FractalSetup(zoomMandelbrot, iterations);
            MIterations.setText("Iterations: "+fs.getIterations());
            if (delta >= 0) {
                zoomMandelbrot.zoom(delta/32, x, y);
            } else {
                zoomMandelbrot.zoom(delta/32, mandelbrotScreenWidth-x, screenHeight-y);
            }
            mandelbrot.draw(fs);
        });
        
        resetMandelbrotZoom.setOnAction(e -> {
            zoomMandelbrot.setHeight(initialZoomMandelbrot.getHeight());
            zoomMandelbrot.setWidth(initialZoomMandelbrot.getWidth());
            zoomMandelbrot.setXOffset(initialZoomMandelbrot.getXOffset());
            zoomMandelbrot.setYOffset(initialZoomMandelbrot.getYOffset());
            mandelbrot.draw(new FractalSetup(zoomMandelbrot, iterations));
        });
        
        juliaCanvas.setOnScroll(e -> {
            double x = e.getX();
            double y = e.getY();
            double delta = e.getDeltaY();

            if(lockToCenter.isSelected()) {
                if (delta >= 0) {
                    zoomJulia.zoom(delta / 32, x, y);
                } else {
                    zoomJulia.zoom(delta / 32, screenWidth - x, screenHeight - y);
                }
                julia.draw(new FractalSetup(c, zoomJulia, iterations));
            } else {
                if (delta >= 0) {
                    zoomJulia.zoomToCenter(delta / 32, x, y);
                } else {
                    zoomJulia.zoomToCenter(delta / 32, screenWidth - x, screenHeight - y);
                }
                julia.draw(new FractalSetup(c, zoomJulia, iterations));
            }
        });
        
        resetJuliaZoom.setOnAction(e -> {
            zoomJulia.setHeight(initialZoomJulia.getHeight());
            zoomJulia.setWidth(initialZoomJulia.getWidth());
            zoomJulia.setXOffset(initialZoomJulia.getXOffset());
            zoomJulia.setYOffset(initialZoomJulia.getYOffset());
            julia.draw(new FractalSetup(c, zoomJulia, iterations));
        });
        
        //---------------------------------------------------
        //                  FINAL SETUP
        //---------------------------------------------------
        BorderPane setup = new BorderPane();
        setup.setCenter(canvases);


        setup.setTop(titlebar);
        
        julia.draw(new FractalSetup(c, zoomJulia, iterations));
        
        Scene scene = new Scene(setup);
        scene.getStylesheets().add("dark.css");

        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Julia Sets");
        primaryStage.getIcons().add(new Image("JuliaIconBlue.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(UI.class);
    }
}
