package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class TriangleTest {
    @Test
    void testGetNormal() {
        Triangle p1 = new Triangle(new Point(1, 2, 3), new Point(2, 1.2, 4), new Point(3, 4, 5));
 
        //TC01: Test that getNormal of Plane made of 3 points works
        assertEquals(new Vector(-0.7071067811865476, 0.0, 0.7071067811865476),
                p1.getNormal(new Point(1, 2, 3)),
                "getNormal() wrong result");


    }
}
