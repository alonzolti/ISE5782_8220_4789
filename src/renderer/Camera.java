package renderer;


import primitives.*;

import static primitives.Util.isZero;

public class Camera {
    private Point location;
    private Vector vUp;
    private Vector vRight;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;
    
    
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
}
