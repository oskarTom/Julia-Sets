package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.logic.MandelbrotLogic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author tomos
 */
public class UI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //loadWindow.initStyle(StageStyle.UNDECORATED);
        
        
        //---------------------------------------------------
        //                  INITALIZING
        //---------------------------------------------------
        int screenWidth = 640;
        int screenHeight = 360;
        int mandelbrotScreenWidth = 480;
        double Re = 0;
        double Im = 0;
        int iterations = 2000; //default: 5000
        Complex c = new Complex(Re,Im);
        
        Canvas juliaCanvas = new Canvas(screenWidth,screenHeight);
        JuliaLogic julia = new JuliaLogic(juliaCanvas);
        
        Canvas mandelbrotCanvas = new Canvas(mandelbrotScreenWidth, screenHeight);
        MandelbrotLogic mandelbrot = new MandelbrotLogic(mandelbrotCanvas);
        
        Zoom zoomMandelbrot = new Zoom(3.5, 10.5/4, -0.5, mandelbrotCanvas);
        Zoom initialZoomMandelbrot = new Zoom(3.5, 10.5/4, -0.5, mandelbrotCanvas);
        Zoom zoomJulia = new Zoom(5, 2.8125);
        Zoom initialZoomJulia = new Zoom(5, 2.8125);
        
        mandelbrot.draw(zoomMandelbrot, iterations);
        
        Label MandelReCoordinates = new Label("Re: ");
        Label MandelImCoordinates = new Label("Im: ");
        VBox coordinates = new VBox();
        coordinates.getChildren().add(MandelReCoordinates);
        coordinates.getChildren().add(MandelImCoordinates);
        
        Label cValue = new Label("c = "+c.toString());
        
        HBox buttons = new HBox();
        Button saveButton = new Button("Save as png");
        Button resetJuliaZoom = new Button("Reset Zoom");
        Button resetZoom = new Button("Reset Zoom");
        
        buttons.getChildren().add(saveButton);
        buttons.getChildren().add(resetJuliaZoom);
        
        GridPane canvases = new GridPane();
        canvases.add(juliaCanvas, 1, 1);
        canvases.add(mandelbrotCanvas, 2, 1);
        
        
        BorderPane juliaMenu = new BorderPane();
        juliaMenu.setLeft(cValue);
        juliaMenu.setRight(buttons);
        //juliaMenu.setPadding(new Insets());
        canvases.add(juliaMenu, 1, 2);
        
        
        
        HBox mandelButtons = new HBox();
        mandelButtons.getChildren().add(resetZoom);
        
        BorderPane mandelbrotMenu = new BorderPane();
        mandelbrotMenu.setLeft(coordinates);
        mandelbrotMenu.setRight(mandelButtons);
        canvases.add(mandelbrotMenu, 2, 2);

        //---------------------------------------------------
        //                  ACTIONS
        //---------------------------------------------------

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extensionFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)","*.png");
            fileChooser.getExtensionFilters().add(extensionFilter);

            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(screenWidth, screenHeight);
                    juliaCanvas.snapshot(null,writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

/*
            Label saveLabel = new Label("Saving functionality here");

            StackPane saveLayout = new StackPane();
            saveLayout.getChildren().add(saveLabel);

            Scene secondScene = new Scene(saveLayout, 230, 100);
            secondScene.getStylesheets().add("dark.css");
            Stage newWindow = new Stage();
            newWindow.setTitle("Save Julia");
            newWindow.setScene(secondScene);
            newWindow.setResizable(false);
            newWindow.show();
*/
        });

        
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
            julia.draw(zoomJulia, iterations, c);
            cValue.setText("c = "+c.toString());        
        });
        
        mandelbrotCanvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            MandelReCoordinates.setText("Re: "+ zoomMandelbrot.xRange(x));
            MandelImCoordinates.setText("Im: "+ zoomMandelbrot.yRange(y));
            c.setReal(zoomMandelbrot.xRange(x));
            c.setImaginary(zoomMandelbrot.yRange(y));
            julia.draw(zoomJulia, iterations, c);
            cValue.setText("c = "+c.toString());
        });
        
        mandelbrotCanvas.setOnScroll(e -> {
            double x = e.getX();
            double y = e.getY();
            double delta = e.getDeltaY();
            if (delta >= 0) {
                zoomMandelbrot.zoom(delta/32, x, y);
            } else {
                zoomMandelbrot.zoom(delta/32, mandelbrotScreenWidth-x, screenHeight-y);
            }
            mandelbrot.draw(zoomMandelbrot, iterations);
        });
        
        resetZoom.setOnAction(e -> {
            zoomMandelbrot.setHeight(initialZoomMandelbrot.getHeight());
            zoomMandelbrot.setWidth(initialZoomMandelbrot.getWidth());
            zoomMandelbrot.setXOffset(initialZoomMandelbrot.getXOffset());
            zoomMandelbrot.setYOffset(initialZoomMandelbrot.getYOffset());
            mandelbrot.draw(zoomMandelbrot, iterations);
        });
        
        juliaCanvas.setOnScroll(e -> {
            double x = e.getX();
            double y = e.getY();
            double delta = e.getDeltaY();
            if (delta >= 0) {
                zoomJulia.zoom(delta*0.05, x, y);
            } else {
                zoomJulia.zoom(delta*0.05, screenWidth-x, screenHeight-y);
            }
            julia.draw(zoomJulia, iterations, c);
        });
        
        resetJuliaZoom.setOnAction(e -> {
            zoomJulia.setHeight(initialZoomJulia.getHeight());
            zoomJulia.setWidth(initialZoomJulia.getWidth());
            zoomJulia.setXOffset(initialZoomJulia.getXOffset());
            zoomJulia.setYOffset(initialZoomJulia.getYOffset());
            julia.draw(zoomJulia, iterations, c);
        });
        
        //---------------------------------------------------
        //                  FINAL SETUP
        //---------------------------------------------------
        BorderPane setup = new BorderPane();
        setup.setCenter(canvases);
        
        julia.draw(zoomJulia, iterations, c);
        
        Scene scene = new Scene(setup);
        scene.getStylesheets().add("dark.css");

        scene.setFill(Color.TRANSPARENT);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);

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
