package unittests.renderer;

import org.junit.jupiter.api.Test;

import renderer.*;
import primitives.*;

public class ImageWriterTest {
    @Test
    void writeToImageTest(){
        int nX = 800;
        int nY = 500;
        ImageWriter image = new ImageWriter("grid", nX, nY);
        Color black = new Color(200,200,200);
        Color purple = new Color(200,0,200);
        for(int j = 0; j < nX; j++){
            //grid 16 X 10
            //makesa blck image with a purple grid
            for(int i = 0; i< nY; i++)
            {
                if(j % 50 ==0 || i% 50 ==0){
                    image.writePixel(j, i, purple);
                }
                else{
                    image.writePixel(j, i, black);
                }
            }
        }
        image.writeToImage();

    }
}
