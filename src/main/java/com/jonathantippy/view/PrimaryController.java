package com.jonathantippy.view;

import javafx.scene.canvas.Canvas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.GraphicsContext;

public class PrimaryController {

    @FXML
    private Canvas display;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        GraphicsContext gc = display.getGraphicsContext2D();
        WritableImage writableImage = new WritableImage((int) display.getWidth(), (int) display.getHeight());
        writableImage = RenderEngine.renderGraph(writableImage);
        gc.drawImage(writableImage, 0, 0);
    }

}
