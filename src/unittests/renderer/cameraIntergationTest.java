package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Intersectable;
import renderer.Camera;
import primitives.*;
import geometries.*;

/**
 *intergation Camera Class
 *
 */
public class cameraIntergationTest {

    /**
     * a method that help to count intersections from camera to bodies
     * 
     * @param camera
     * @param intersect
     * @return count
     */
    private int findIntersection(Camera camera, Intersectable intersect){
        int count = 0;
        int nY = 3;
        int nX = 3;
        for(int j = 0; j<nX; j++){
            for(int i = 0; i<nY; i++){
                var check = intersect.findIntersections(camera.constructRay(nX, nY, j, i));
                if (check != null){
                    count += check == null ? 0 : check.size();
                }
            }
        }
        return count;
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void testSphere(){
        // TC01: Small Sphere 2 points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        Sphere sphere = new Sphere(new Point(0,0,-3), 1);
        assertEquals(findIntersection(camera, sphere),2,"small sphere wasn't rihgt");
        // TC02: big Sphere 18 points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        sphere = new Sphere(new Point(0,0,-2.5), 2.5);
        assertEquals(findIntersection(camera, sphere),18,"big sphere wasn't rihgt");
        // TC03: medium Sphere 10 points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        sphere = new Sphere(new Point(0,0,-2), 2);
        assertEquals(findIntersection(camera, sphere),10,"medium sphere wasn't rihgt");
        // TC04: camera in Sphere 9 points
        camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        sphere = new Sphere(new Point(0,0,0), 4);
        assertEquals(findIntersection(camera, sphere),9,"camera in sphere wasn't rihgt");
        // TC05: Sphere behind camera 0 points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        sphere = new Sphere(new Point(0,0,1), 0.5);
        assertEquals(findIntersection(camera, sphere),0,"sphere behind wasn't rihgt");
    }
    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void testPlane(){
        //TC01 parallel plane 9 points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        Plane plane = new Plane(new Point(0,0,-2), new Vector(0,0,1));
        assertEquals(9, findIntersection(camera, plane),"parallel plane isn't working");
        //TC02 plane with small angle 9 points
        camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        plane = new Plane(new Point(0,0,-1.5), new Vector(0,-0.5,1));
        assertEquals(9, findIntersection(camera, plane),"plane with small angle isn't working");
        //TC02 plane parallel to lower ray 3 points
        camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        plane = new Plane(new Point(0,0,-3), new Vector(0,-1,1));
        assertEquals(6, findIntersection(camera, plane),"plane parallel to lower ray isn't working");
            
    }
    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void testTriangle(){
        // TC01 small triangle 1 point
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        camera.setVPDistance(1).setVPSize(3d, 3d);
        Triangle triangle = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(1, findIntersection(camera, triangle),"small triangle is'nt working");
        // TC02 big triangle 2 point
        triangle = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(2, findIntersection(camera, triangle),"big triangle is'nt working");
        
    }
}
