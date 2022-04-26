package renderer;


import primitives.*;

import static primitives.Util.isZero;

import java.util.MissingResourceException;

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
     * @return double
     */
    public double getHeight() {
        return height;
    }
    
    /** 
     * @return double
     */
    public double getWidth() {
        return width;
    }
    
    /** 
     * @return double 
     */
    public double getDistance() {
        return distance;
    }
    public Camera(Point location, Vector vUp, Vector vTo) {
        this.location = location;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        if (isZero(vUp.dotProduct(vTo))) {
            this.vRight = vTo.crossProduct(vUp).normalize();
        }
        else{
            throw new IllegalArgumentException("vUp and vTo are not crossing");
        }
    }
    
    /** 
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
     * @param distance
     * @return Camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }
    
    /**
     * setter for image writer field
     * @param imageWriter
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter){
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter for rayTracer field
     * @param rayTracer
     * @return camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer){
        this.rayTracer = rayTracer;
        return this;
    }

    /** 
     * the function gets a specific pixel, ans returns the ray that goes from camera throgh that pixel
     * as learned in the theorethic course
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        // firstly - find the pixel center point
        Point pc = location.add(vTo.scale(distance)); // image center
        // ratio(pixel width and height)
        double rY = height/nY;
        double rX = width/nX;
        // pixel[i,j] center
        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        //Point pIJ = pc.add(vRight.scale(xJ)).add(vUp.scale(yI));
        // pIJ is  the center that we need
        Point pIJ = pc;
        if(xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        // 𝑹𝒂𝒚: {𝒑𝟎 = location, 𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏 = pIJ - location}
        Ray ray = new Ray(location, pIJ.subtract(location));
        return ray;
    }

    /**
     * check that all the fields in camera aren't default values
     */
    public void renderImage(){
        if(location == null || vUp == null || vRight == null || vTo == null || height == 0 || width == 0 || distance == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("some of the fields are null", "Camera", null);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for(int i=0;i<nX;++i)
            for(int j=0;j<nY;++j)
                imageWriter.writePixel(i, j, castRay(constructRay(nX, nY, j, i)));
    }

    /**
     * the function create a net with specific color and interval
     * @param interval interval of the net
     * @param color color of the net
     */
    public void printGrid(int interval, Color color){
        if(imageWriter == null)
            throw new MissingResourceException("image writer field is null","Camera", null);
        for(int i = 0;i<imageWriter.getNx();i += interval)
            for(int j=0;j<imageWriter.getNy();++j)
                imageWriter.writePixel(i, j, color);

        for(int i = 0;i<imageWriter.getNx();++i)
            for(int j=0;j<imageWriter.getNy();j += interval)
                imageWriter.writePixel(i, j, color);
    }

    /**
     * the function write the image.
     * using the function in imageWriter
     */
    public void writeToImage(){
        if(imageWriter == null)
            throw new MissingResourceException("image writer field is null","Camera", null);
        imageWriter.writeToImage();
    }

    /**
     * the fucntion return the color that the ray pointing at
     * @param ray
     * @return
     */
    public Color castRay(Ray ray){
        return rayTracer.traceRay(ray);
    }



}
