package com.mycompany.fraktaalit.ui;

import com.mycompany.fraktaalit.logic.Complex;
import com.mycompany.fraktaalit.logic.FractalSetup;
import com.mycompany.fraktaalit.logic.JuliaLogic;
import com.mycompany.fraktaalit.ui.graphics.Zoom;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class SaveMenu {

    public SaveMenu() {

    }

    public void start(Stage stage, Complex c, Zoom zoom) {
        Label saveLabel = new Label("Choose resolution:");
        Button saveButton = new Button("Save");
        ComboBox resolutions = new ComboBox();
        resolutions.getItems().addAll(
                "720p",
                "1080p",
                "1440p",
                "4K"
        );
        resolutions.setValue("1080p");


        VBox saveLayout = new VBox();
        saveLayout.getChildren().addAll(saveLabel, resolutions, saveButton);
        saveLayout.setAlignment(Pos.CENTER);

        Scene secondScene = new Scene(saveLayout, 230, 100);
        secondScene.getStylesheets().add("dark.css");
        Stage newWindow = new Stage();
        newWindow.setTitle("Save Julia");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        newWindow.show();

        saveButton.setOnAction(e -> {
            int width;
            int height;
            if(resolutions.getValue().equals("720p")){
                width = 1280;
                height = 720;
            } else if(resolutions.getValue().equals("1080p")){
                width = 1920;
                height = 1080;
            } else if(resolutions.getValue().equals("1440p")){
                width = 2560;
                height = 1440;
            } else {
                width = 3840;
                height = 2160;
            }
            Canvas canvas = new Canvas(width, height);
            JuliaLogic juliaLogic = new JuliaLogic(canvas);
            FractalSetup fs = new FractalSetup(c,
                    new Zoom(
                            zoom.getWidth(),
                            zoom.getHeight(),
                            zoom.getXOffset(),
                            zoom.getYOffset(),
                            canvas), 100);
            fs.setIterations(10000);
            juliaLogic.draw(fs);

            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)","*.png");
            fileChooser.getExtensionFilters().add(extensionFilter);

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(width, height);
                    canvas.snapshot(null,writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            newWindow.close();
        });
    }
}
