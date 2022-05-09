package unittests.renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																												// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																												// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																												// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}

	// for stage 4.
	/**
     * test for spinnig the camera, the spin function is in camera and not in imageWriter
     * but it easier to test it from the image Writer.
     */
    @Test
    public void spinnigCameraTest(){
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));
		
		scene.lights.add(new PointLight(new Color(800, 500, 0), new Point(-50, -50, 25)).setKl(0.001).setKq(0.0002));
		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																												// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																												// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																												// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		double angle;
// ============ Equivalence Partitions Tests ==============	angle is between 0 - 180
		angle = 30;
		//tc01: spinning right in 30 degree
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: spinning up in 30 degree
		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: spinning  in 30 degree
		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
// ============ Equivalence Partitions Tests ==============	angle is between 180 - 360
		angle = 210;
		//tc01: spinning right in 210 degree
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: spinning up in 210 degree
		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: spinning  in 210 degree
		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1))
// ============ Equivalence Partitions Tests ==============	angle is between -180 - 0
		angle = -30;
		//tc01: spinning right in -30 degree
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: spinning up in -30 degree
		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: spinning  in -30 degree
		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
//============= boundary value Tests ==================
		angle = 0;
		//tc01: angle is 0
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
//============= boundary value Tests ==================
		//tc01: angle is 0
		angle = 0;
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		
		//tc02: angle is 180
		angle = 180;
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		angle = 0;
		//tc03: angle is 360
		camera.spinLeftRight(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spinUpDown(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

		camera.spin(angle);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

    }
	// for stage 4.
	/**
     * test for moving the camera, the spin function is in camera and not in imageWriter
     * but it easier to test it from the image Writer.
     */
	@Test
	public void movingCamera(){
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));
		
		scene.lights.add(new PointLight(new Color(800, 500, 0), new Point(-50, -50, 25)).setKl(0.001).setKq(0.0002));
		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																												// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																												// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																												// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		double distance;
// ============ Equivalence Partitions Tests ==============	distance > 0
		distance = 1.5;
		//tc01: moving forward 1.5
		camera.moveTowards(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: moving left 1.5
		camera.moveLeftRight(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: moving up 1.5
		camera.moveUpDown(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
// ============ Equivalence Partitions Tests ==============	distance < 0
		distance = -1.5;
		//tc01: moving forward -1.5
		camera.moveTowards(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: moving left -1.5
		camera.moveLeftRight(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: moving up -1.5
		camera.moveUpDown(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
//============= boundary value test ======================= 
		distance = 0;
		//tc01: moving forward 0
		camera.moveTowards(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc02: moving left 0
		camera.moveLeftRight(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
		//tc03: moving up 0
		camera.moveUpDown(distance);
		camera.writeToImage();
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));

	}

	// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid
	 */
	@Test
	public void basicRenderMultiColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))) //
				.setBackground(new Color(black));
		scene.geometries.add( //
				new Sphere(new Point(0, 0, -100), 50),
				// up left
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
						.setEmission(new Color(GREEN)),
				// down left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(RED)),
				// down right
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
						.setEmission(new Color(BLUE)));

		Camera camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("color render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 * 
	 * @throws ParserConfigurationException
	 */
	@Test
	public void basicRenderXml() throws Exception {
		Scene scene = new Scene("XML Test scene");
		// enter XML file name and parse from XML file into scene object
		// ...
		try {
			scene.setXml(System.getProperty("user.dir") + "/xml/basicRenderTestTwoColors.xml");
		} catch (Exception e) {
			throw new Exception("cannot convert the xml file");
		}
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d).setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}
}
