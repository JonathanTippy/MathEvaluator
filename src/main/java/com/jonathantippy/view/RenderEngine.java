package com.jonathantippy.view;

import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

public class RenderEngine {
    public static void renderGraph(WritableImage writableImage) {
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < writableImage.getHeight(); y++) {
            for (int x = 0; x < writableImage.getWidth(); x++) {
                pixelWriter.setArgb(x, y, 0xFF808080);
            }
        }
    }
}