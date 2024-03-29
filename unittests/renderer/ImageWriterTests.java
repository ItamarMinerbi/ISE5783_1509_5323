package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

public class ImageWriterTests {
    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("image", 800, 500);
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if ((i % 50 == 0) || (j % 50 == 0)) {
                    imageWriter.writePixel(i, j, new Color(199, 21, 133));
                } else {
                    imageWriter.writePixel(i, j, new Color(255, 0, 0));
                }
            }
        }

        imageWriter.writeToImage();
    }
}
