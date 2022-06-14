package renderer;

import primitives.*;

import static primitives.Util.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import javax.lang.model.util.ElementScanner6;

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
    private int raysNumber = 1;// numbers of rays
    private boolean adaptiveSuperampling = false;
    private int adaptiveSuperSamplingDepth = 3;

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
     * to operate antialising, the number will be the number of columns and rows
     * in the grid
     * 
     * @return this instance of camera
     */
    public Camera antiAliasing(int raysNumber) {
        this.raysNumber = raysNumber;
        return this;
    }

    /**
     * spinning the camera up and down, around the vRight vector
     * 
     * @param angle to spin the camera
     * @return this inatance of camera
     */
    public Camera spinUpDown(double angle) {
        if (angle >= 360)
            angle = angle % 360;
        if (angle > 180)
            angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vUp and vTo vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vTo = vUp.scale(sin0);
        } else if (isZero(sin0)) {
            vTo = vTo.scale(cos0);// if angle is zero - mult by one(no change)...
        } else {// rotate around the To vector using Rodrigues' rotation formula
            vTo = vTo.scale(cos0)
                    .add(vRight.crossProduct(vTo).scale(sin0));
        }
        vUp = vRight.crossProduct(vTo).normalize();
        return this;
    }

    /**
     * spinnig the camera left, around the vUp vector
     * 
     * @param angle to spin the camera
     * @return this inatance of camera
     */
    public Camera spinLeftRight(double angle) {
        if (angle >= 360)
            angle = angle % 360;
        if (angle > 180)
            angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vTo and vRight vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vRight = vTo.scale(sin0);
        } else if (isZero(sin0)) {
            vRight = vRight.scale(cos0);// if angle is zero - mult by one(no change)...
        } else {// rotate around the To vector using Rodrigues' rotation formula
            vRight = vRight.scale(cos0)
                    .add(vUp.crossProduct(vRight).scale(sin0));
        }
        this.vTo = vRight.crossProduct(vUp).scale(-1).normalize();
        return this;
    }

    /**
     * spinnig the camera, around the to vector, clock direction
     * 
     * @param angle to spin the camera
     * @return this inatance of camera
     */
    public Camera spin(double angle) {
        if (angle >= 360)
            angle = angle % 360;
        if (angle > 180)
            angle = angle - 360; // the range of angles now is -180 to 180.
        // moving only the vRight and vUp vectors.
        double angleRad = Math.toRadians(angle);
        double cos0 = Math.cos(angleRad);
        double sin0 = Math.sin(angleRad);
        if (isZero(cos0)) { // 90
            vUp = vRight.scale(sin0);
        } else if (isZero(sin0)) {
            vUp = vUp.scale(cos0);// if angle is zero - mult by one(no change)...
        } else {// rotate around the To vector using Rodrigues' rotation formula
            vUp = vUp.scale(cos0)
                    .add(vTo.crossProduct(vUp).scale(sin0));
        }
        vRight = vTo.crossProduct(vUp).normalize();
        return this;
    }

    /**
     * moving the camera up
     * 
     * @param the units to move
     * @return this instance of camera
     */
    public Camera moveUpDown(double distance) {
        location = distance == 0 ? location : location.add(vUp.scale(distance));
        return this;
    }

    /**
     * moving the camera right
     * 
     * @param the units to move
     * @return this instance of camera
     */
    public Camera moveLeftRight(double distance) {
        location = distance == 0 ? location : location.add(vRight.scale(distance));
        return this;
    }

    /**
     * moving the camera forward
     * 
     * @param the units to move
     * @return this instance of camera
     */
    public Camera moveTowards(double distance) {
        location = distance == 0 ? location : location.add(vTo.scale(distance));
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
     * setter for adaptiveSupersampling field
     * 
     * @param adaptive if the user wan't to use adaptive supersampling or not
     * @return the object itself
     */
    public Camera setAdaptiveSupersampling(boolean adaptive) {
        adaptiveSuperampling = adaptive;
        return this;
    }

    /**
     * setter for adaptiveSuperSamplingDepth field
     * 
     * @param depth depth of the algorithm
     * @return the object itself
     */
    public Camera setAdaptiveSuperSamplingDepth(int depth) {
        adaptiveSuperSamplingDepth = depth;
        return this;
    }

    /**
     * the function gets a specific pixel, and returns the ray that goes from camera
     * throgh that pixel
     * as learned in the theorethic course
     * 
     * @param nX number of pixels in axis x
     * @param nY number of pixels in axis y
     * @param j  pixel in column j
     * @param i  pixel in column i
     * @return Ray ray that goes through the middle of the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // firstly - find the pixel center point
        Point pc = location.add(vTo.scale(distance)); // image center
        // ratio(pixel width and height)
        double rY = height / nY;
        double rX = width / nX;
        // pixel[i,j] center
        double yI = -(i - (nY - 1) / 2d) * rY; // the distance from the center to the middle of the pixel in vRight
                                               // vector
        double xJ = (j - (nX - 1) / 2d) * rX; // the distance from the center to the middle of the pixel in vUp vector
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
     * makes a grid of rays that go through the pixel
     * 
     * @param nX number of pixels in axis x
     * @param nY number of pixels in axis y
     * @param j  pixel in column j
     * @param i  pixel in column i
     * @return rays that goes through the pixel in a grid
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        // firstly - find the pixel center point
        Point pc = location.add(vTo.scale(distance)); // image center
        // ratio(pixel width and height)
        double rY = height / nY;
        double rX = width / nX;
        // pixel[i,j] center
        double yI = -(i - (nY - 1) / 2d) * rY; // the distance from the center to the middle of the pixel in vRight
                                               // vector
        double xJ = (j - (nX - 1) / 2d) * rX; // the distance from the center to the middle of the pixel in vUp vector
        // Point pIJ = pc.add(vRight.scale(xJ)).add(vUp.scale(yI));
        // pIJ is the center that we need
        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI)); // pIJ is the pixel center
        // divide the pixel into segments andfind the middle for every segment
        // ratio segment width and lentgh
        double sRY = rY / raysNumber;
        double sRX = rX / raysNumber;
        // segment[i,j] center
        double sYI, sXJ;
        Point sPIJ;
        for (int si = 0; si < raysNumber; si++) {
            for (int sj = 0; sj < raysNumber; sj++) {
                sPIJ = pIJ;
                sYI = -(si - (raysNumber - 1) / 2d) * sRY;
                sXJ = (sj - (raysNumber - 1) / 2d) * sRX;
                if (!isZero(sXJ))
                    sPIJ = sPIJ.add(vRight.scale(sXJ));
                if (!isZero(sYI))
                    sPIJ = sPIJ.add(vUp.scale(sYI)); // sPIJ is the segment center
                rays.add(new Ray(location, sPIJ.subtract(location)));
            }
        }
        return rays;
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
            for (int j = 0; j < nY; ++j) {

                imageWriter.writePixel(j, i, castRay(nX, nY, j, i));
            }
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
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; i += interval)
            for (int j = 0; j < nY; ++j)
                imageWriter.writePixel(i, j, color);

        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; j += interval)
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
     * the fucntion return the color that the ray pointing at, using a technique,
     * depends on the user preference
     * it use antiAliasing, adaptivesupersampling or traceRay of of the center ray
     * 
     * @param nX number of pixels in axis x
     * @param nY number of pixels in axis y
     * @param j  pixel in column j
     * @param i  pixel in column i
     * @return the color of the pixel
     */
    public Color castRay(int nX, int nY, int j, int i) {
        if (adaptiveSuperampling) {
            return adaptiveSuperSamplingHelp(nX, nY, j, i);
        } else {
            // antialasing
            List<Ray> rays = constructRays(nX, nY, j, i);
            Color color = Color.BLACK;
            for (int k = 0; k < rays.size(); k++)
                color = color.add(rayTracer.traceRay(rays.get(k)));
            color = color.reduce(rays.size());
            return color;
        }
    }

    HashMap<Point, Color> colors = new HashMap<Point, Color>();

    /**
     * the function return the color of the point p on the view plane in the
     * hashMap, and if it isn't exist, then calculate it
     * 
     * @param p the point
     * @return return the color
     */
    private Color getColor(Point p) {
        if (!colors.containsKey(p))// the color hasn't been calculated before
        {
            Color newColor = rayTracer.traceRay(new Ray(location, p.subtract(location)));
            colors.put(p, newColor);
            return newColor;
        } else {
            return colors.get(p);
        }
    }

    /**
     * wrapper function, that called a function that calculate the color of a
     * specific pixel
     * 
     * @param nX number of pixels in axis x
     * @param nY number of pixels in axis y
     * @param j  pixel in column j
     * @param i  pixel in column i
     * @return color of the pixel
     */

    private Color adaptiveSuperSamplingHelp(int nX, int nY, int j, int i) {
        colors.clear();
        Point pc = location.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));

        Point lu = pIJ.add(vRight.scale(-rX / 2)).add(vUp.scale(rY / 2));
        Point ld = pIJ.add(vRight.scale(-rX / 2)).add(vUp.scale(-rY / 2));
        Point ru = pIJ.add(vRight.scale(rX / 2)).add(vUp.scale(rY / 2));
        Point rd = pIJ.add(vRight.scale(rX / 2)).add(vUp.scale(-rY / 2));

        return adaptiveSuperSampling(pIJ, rX, rY, adaptiveSuperSamplingDepth, lu, ld, ru, rd);
    }

    /**
     * recursive function to calculate the color of a specific pixel
     * divided the pixel to four, and called the function again
     * 
     * @param pIJ   center of the current square
     * @param rX    width of the current square
     * @param rY    height of the current square
     * @param depth depth of the function
     * @param lu    left up corner
     * @param ld    left down corner
     * @param rd    right down corner
     * @param ru    right up corner
     * @return color of the square
     */
    private Color adaptiveSuperSampling(Point pIJ, double rX, double rY, int depth, Point lu, Point ld, Point rd,
            Point ru) {

        if (depth <= 0) {
            return Color.BLACK.add(getColor(lu), getColor(ld), getColor(rd), getColor(ru)).reduce(4);
        }
        Color luColor = getColor(lu);
        Color ldColor = getColor(ld);
        Color rdColor = getColor(rd);
        Color ruColor = getColor(ru);

        // if the corners equals
        if (luColor.equals(ldColor) && rdColor.equals(ruColor) && luColor.equals(rdColor))
            return luColor;

        // calculate the point of the four squares
        Point u = pIJ.add(vUp.scale(rY));
        Point l = pIJ.add(vRight.scale(-rX));
        Point d = pIJ.add(vUp.scale(-rY));
        Point r = pIJ.add(vRight.scale(rX));

        return Color.BLACK.add(
                adaptiveSuperSampling(pIJ.add(vRight.scale(-rX / 4)).add(vUp.scale(rY / 4)), rX / 2,
                        rY / 2, depth - 1, lu, l, pIJ, u),
                adaptiveSuperSampling(pIJ.add(vRight.scale(-rX / 4)).add(vUp.scale(-rY / 4)), rX / 2,
                        rY / 2, depth - 1, l, ld, d, pIJ),
                adaptiveSuperSampling(pIJ.add(vRight.scale(rX / 4)).add(vUp.scale(-rY / 4)), rX / 2,
                        rY / 2, depth - 1, pIJ, d, rd, r),
                adaptiveSuperSampling(pIJ.add(vRight.scale(rX / 4)).add(vUp.scale(rY / 4)), rX / 2,
                        rY / 2, depth - 1, u, pIJ, r, ru))
                .reduce(4);

    }
}