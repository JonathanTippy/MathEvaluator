package com.jonathantippy.view;

import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

public class RenderEngine {
    public static WritableImage renderGraph(WritableImage blankImage) {
        PixelWriter pixelWriter = blankImage.getPixelWriter();
        for (int y = 0; y < blankImage.getHeight(); y++) {
            for (int x = 0; x < blankImage.getWidth(); x++) {
                // Set pixel to a color (e.g., red)
                pixelWriter.setArgb(x, y, 0xFFFF0000); // ARGB value for red
            }
        }
        return blankImage;
    }
}