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
	 * test for spinnig the camera, the spin function is in camera and not in
	 * imageWriter
	 * but it easier to test it from the image Writer.
	 */
	@Test
	public void spinnigCameraTest() {
		
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
				.setImageWriter(new ImageWriter("test spin", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		int angle;
		// ============ Equivalence Partitions Tests ==============
		//EP01 angle is between 0 to 180
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin 30 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = 30;
//		camera.moveUpDown(0); // 30 deg up
//		camera.renderImage();
//		camera.printGrid(100, new Color(YELLOW));
//		camera.writeToImage();
		//tc01 spin up
		camera.spinUpDown(angle); // 30 deg up
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // 30 deg left
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// tc03 spin
		camera.spin(angle); // spin in 30 deg
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//EP02 angle is between 180 - 360 
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin 210 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = 210;
		//tc01 spin up
		camera.spinUpDown(angle); // 210 deg up
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // 210 deg left
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc03 spin
		camera.spin(angle); // spin in 210 deg
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//EP03 angke is less than 0
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin -30 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = -30;
		//tc01 spin up
		camera.spinUpDown(angle); // -30 deg up
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // -30 deg left
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc03 spin
		camera.spin(angle); // spin in -30 deg
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//============Boundary value tests
		//BV01 angle is 0
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin 0 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = 0;
		//tc01 spin up
		camera.spinUpDown(angle); // 0 deg up
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // 0 deg left
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//tc03 spin
		camera.spin(angle); // spin in 0 deg
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		//BV02 angle is 180
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin 180 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = 180;
		//tc01 spin up
		camera.spinUpDown(angle); // 180 deg up
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // 180 deg left
		camera.writeToImage();
		//tc03 spin
		camera.spin(angle); // spin in 180 deg
		camera.writeToImage();
		//BV03 angle is 360
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test spin 360 deg", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		angle = 360;
		//tc01 spin up
		camera.spinUpDown(angle); // 360 deg up
		camera.writeToImage();
		//tc02 spin left
		camera.spinLeftRight(angle); // 360 deg left
		camera.writeToImage();
		//tc03 spin
		camera.spin(angle); // spin in 360 deg
		camera.writeToImage();
	}

	@Test
	public void movingCameraTest(){
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
				.setImageWriter(new ImageWriter("test moving", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		int distance;
		// ============ Equivalence Partitions Tests ==============
		//EP01 distan is bigger than 0
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test positive moving", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		distance = 50;
		//tc01 move forward
		camera.moveTowards(distance - 30);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move left
		camera.moveLeftRight(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move up
		camera.moveUpDown(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//EP02 distance is negative
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
		.setVPDistance(100) //
		.setVPSize(500d, 500d) //
		.setImageWriter(new ImageWriter("test negative moving", 1000, 1000))
		.setRayTracer(new RayTracerBasic(scene));
		distance = -50;
		//tc01 move forward
		camera.moveTowards(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move left
		camera.moveLeftRight(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move up
		camera.moveUpDown(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//============Boundary Value Tests============
		//BV01 distanc is 0
		camera = new Camera(Point.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)) //
				.setVPDistance(100) //
				.setVPSize(500d, 500d) //
				.setImageWriter(new ImageWriter("test positive moving", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		distance = 0;
		//tc01 move forward
		camera.moveTowards(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move left
		camera.moveLeftRight(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		//tc01 move up
		camera.moveUpDown(distance);
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));

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
