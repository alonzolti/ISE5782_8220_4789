package unittests.renderer;

import org.junit.jupiter.api.Test;

import renderer.*;
import primitives.*;

/**
 * image writer tests
 */
public class ImageWriterTest {
    /**
     * test the image writer
     */
    @Test
    void writeToImageTest() {
        int nX = 800;
        int nY = 500;
        ImageWriter image = new ImageWriter("grid", nX, nY);
        Color black = new Color(200, 200, 200);
        Color purple = new Color(200, 0, 200);
        for (int j = 0; j < nX; j++) {
            // grid 16 X 10
            // makesa blck image with a purple grid
            for (int i = 0; i < nY; i++)
                image.writePixel(j, i, j % 50 == 0 || i % 50 == 0 ? purple : black);
        }
        image.writeToImage();

    }
}
