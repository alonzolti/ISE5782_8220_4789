package renderer;

import primitives.*;

import static primitives.Util.isZero;

import java.util.MissingResourceException;

/**
 * class Camera represent a camera view
 */
public class Camera {
    private Point location;
    private Vector vUp;
    private Vector vRight;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * getter for height
     * 
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter for width
     * 
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for distance
     * 
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * constructor
     * 
     * @param location location of the camera
     * @param vUp      vector up
     * @param vTo      vector forward
     * @throws IllegalArgumentException if the vectors in the parameters are not
     *                                  orthogonal
     */
    public Camera(Point location, Vector vUp, Vector vTo) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("vUp and vTo are not crossing");
        this.location = location;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * spinning the camera up and down, around the vRight vector
     * 
     * @param angle
     * @return the camera
     */
    public Camera spinUpDown(double angle){
        if (angle>=360) angle = angle % 360;
        if (angle>180) angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vUp and vTo vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vTo = vUp.scale(sin0);
        } else if (isZero(sin0)) {
            vTo = vTo.scale(cos0);// if angle is zero - mult by one(no change)...
        }
        else {//rotate around the To vector using Rodrigues' rotation formula
            vTo = vTo.scale(cos0)
                .add(vRight.crossProduct(vTo).scale(sin0));
        }
        vUp = vRight.crossProduct(vTo).normalize();
        return this;
    }
    /**
     * spinnig the camera left and right, around the vUp vector
     * @param angle
     * @return
     */
    public Camera spinLeftRight(double angle){
        if (angle>=360) angle = angle % 360;
        if (angle>180) angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vTo and vRight vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vRight = vTo.scale(sin0);
        } else if (isZero(sin0)) {
            vRight = vRight.scale(cos0);// if angle is zero - mult by one(no change)...
        }
        else {//rotate around the To vector using Rodrigues' rotation formula
            vRight = vRight.scale(cos0)
                .add(vUp.crossProduct(vRight).scale(sin0));
        }
        this.vTo = vRight.crossProduct(vUp).scale(-1).normalize();
        return this;
    }
    /**
     * spinnig the camera, around the to vector
     * @param angle
     * @return
     */
    public Camera spin(double angle){
        if (angle>=360) angle = angle % 360;
        if (angle>180) angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vRight and vUp vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vUp = vRight.scale(sin0);
        } else if (isZero(sin0)) {
            vUp = vUp.scale(cos0);// if angle is zero - mult by one(no change)...
        }
        else {//rotate around the To vector using Rodrigues' rotation formula
            vUp = vUp.scale(cos0)
                .add(vTo.crossProduct(vUp).scale(sin0));
        }
        vRight = vTo.crossProduct(vRight).scale(-1).normalize();
        return this;
    }
    /**
     * moving up
     * @param distance
     * @return
     */
    public Camera moveUpDown(double distance){
        location = location.add(vUp.scale(distance));
        return this;
    }
    /**
     * moving left
     * @param distance
     * @return
     */
    public Camera moveLeftRight(double distance){
        location = location.add(vRight.scale(distance));
        return this;
    }
    /**
     * moving towards
     * @param distance
     * @return
     */
    public Camera moveTowards(double distance){
        location = location.add(vTo.scale(distance));
        return this;
    }

    /**
     * setter for the view plane
     * 
     * @param width
     * @param height
     * @return Camera
     */
    public Camera setVPSize(Double width, Double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * setter for distance
     * 
     * @param distance
     * @return Camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setter for image writer field
     * 
     * @param imageWriter
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter for rayTracer field
     * 
     * @param rayTracer
     * @return camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * the function gets a specific pixel, ans returns the ray that goes from camera
     * throgh that pixel
     * as learned in the theorethic course
     * 
     * @param nX number of pixels in axis x
     * @param nY number of pixels in axis y
     * @param j  pixel in column j
     * @param i  pixel in column i
     * @return Ray ray the goes through the middle of the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // firstly - find the pixel center point
        Point pc = location.add(vTo.scale(distance)); // image center
        // ratio(pixel width and height)
        double rY = height / nY;
        double rX = width / nX;
        // pixel[i,j] center
        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        // Point pIJ = pc.add(vRight.scale(xJ)).add(vUp.scale(yI));
        // pIJ is the center that we need
        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));
        // 𝑹𝒂𝒚: {𝒑𝟎 = location, 𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏 = pIJ - location}
        return new Ray(location, pIJ.subtract(location));
    }

    /**
     * check that all the fields in camera aren't default values
     * and then, write the color of the pixel into the image
     * 
     * @return the object itself
     */
    public Camera renderImage() {
        if (location == null || vUp == null || vRight == null || vTo == null || height == 0 || width == 0
                || distance == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("some of the fields are null", "Camera", null);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j)
                imageWriter.writePixel(j, i, castRay(constructRay(nX, nY, j, i)));
        return this;
    }

    /**
     * the function create a net with specific color and interval
     * 
     * @param interval interval of the net
     * @param color    color of the net
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("image writer field is null", "Camera", null);
        for (int i = 0; i < imageWriter.getNx(); i += interval)
            for (int j = 0; j < imageWriter.getNy(); ++j)
                imageWriter.writePixel(i, j, color);

        for (int i = 0; i < imageWriter.getNx(); ++i)
            for (int j = 0; j < imageWriter.getNy(); j += interval)
                imageWriter.writePixel(i, j, color);
    }

    /**
     * the function write the image.
     * using the function in imageWriter
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("image writer field is null", "Camera", null);
        imageWriter.writeToImage();
    }

    /**
     * the fucntion return the color that the ray pointing at
     * 
     * @param ray
     * @return
     */
    public Color castRay(Ray ray) {
        return rayTracer.traceRay(ray);
    }

}
