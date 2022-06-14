package unittests.renderer;

import java.util.Random;

//import javax.swing.plaf.synth.SynthPasswordFieldUI;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

//took 1843 seconds with adaptivesupersampling(depth = 2)
public class coolPictureTest {
    private void addClouds(Scene scene, Point point, double size){
        scene.geometries.add(
            new Sphere (point,size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.9)),
            
            new Sphere (point.add(new Vector(-size*0.3,size*0.3,size/6)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(size*0.6,size*0.7,size/5)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(0,size,size/4)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(-size*0.6,size*1.3,-size/5)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(-size*0.3,size*1.7,size/6)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(0,size*2,0)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(0,size*0.6,-size/6)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8)),
            new Sphere (point.add(new Vector(0,-size*1.4,-size/6)),size).setEmission(new Color(75,75,75)).setMaterial(new Material().setKt(0.6).setKd(0.8))
        );
    }
    private void addRocket(Scene scene, Point location, double size, Vector vX,Vector vY, Color color) {
        //0.866025;// Math.cos(Math.PI/6);
        //0.5;//Math.sin(Math.PI/6);
        //0.777146;//Math.cos(Math.PI/6 + Math.PI/20);
        //0.629320;//Math.sin(Math.PI/6 + Math.PI/20);
        //0.669131 = 0.669131;//Math.cos(Math.PI/6 + Math.PI/10);
        //0.743145 = 0.743145;//Math.sin(Math.PI/6 + Math.PI/10);

        //-0.866025 = -0.866025;//Math.cos(Math.PI*5/6);
        //0.5 = 0.5;//Math.sin(Math.PI*5/6);
        //-0.933580 = -0.933580;//Math.cos(Math.PI*5/6 + Math.PI/20);
        //0.358368 = 0.358368;//Math.sin(Math.PI*5/6 + Math.PI/20);
        //-0.97814 = -0.97814;//Math.cos(Math.PI*5/6 + Math.PI/10);
        //0.207912 = 0.207912;//Math.sin(Math.PI*5/6 + Math.PI/10);
        
        //0 = 0;//Math.cos(Math.PI*9/6);
        //-1 = -1;//Math.sin(Math.PI*9/6);
        //0.156434 = 0.156434;//Math.cos(Math.PI*9/6 + Math.PI/20);
        //-0.987688 = -0.987688;//Math.sin(Math.PI*9/6 + Math.PI/20);
        //0.301017 = 0.301017;//Math.cos(Math.PI*9/6 + Math.PI/10);
        //-0.951057 = -0.951057;//Math.sin(Math.PI*9/6 + Math.PI/10);
        
        vX = vX.normalize();
        vY = vY.normalize();
        Vector vZ = vX.crossProduct(vY);
        scene.lights.add(new PointLight(new Color(50, 0, 5), location.add(vZ.scale(-size*1.1))).setKc(0.2).setKl(0.2).setKq(0.2));
        scene.geometries.add(
            new Sphere(location,size).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Cylinder(new Ray(location.add(vZ.scale(-size*1.3)), vZ), size*0.4, size*1.5).setEmission(new Color(100,0,0)).setMaterial(new Material().setShininess(30).setKt(0.9).setKd(0.9).setKs(0.9)),
//stander
            new Triangle(location.add(vX.scale(0.866025*size).add(vY.scale(0.5*size))), location.add(vX.scale(0.866025*size/2d).add(vY.scale(0.5*size/2d).add(vZ.scale(-size*0.75)))), location.add(vX.scale(0.777146*size*1.5).add(vY.scale(0.629320*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(0.669131*size).add(vY.scale(0.743145*size))), location.add(vX.scale(0.669131*size/2d).add(vY.scale(0.743145*size/2d).add(vZ.scale(-size*0.75)))), location.add(vX.scale(0.777146*size*1.5).add(vY.scale(0.629320*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(0.669131*size).add(vY.scale(0.743145*size))), location.add(vX.scale(0.866025*size).add(vY.scale(0.5*size))), location.add(vX.scale(0.777146*size*1.5).add(vY.scale(0.629320*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            
            new Triangle(location.add(vX.scale(-0.866025*size).add(vY.scale(0.5*size))), location.add(vX.scale(-0.866025*size/2d).add(vY.scale(0.5*size/2d).add(vZ.scale(-size*0.75)))), location.add(vX.scale(-0.933580*size*1.5).add(vY.scale(0.358368*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(-0.97814*size).add(vY.scale(0.207912*size))), location.add(vX.scale(-0.97814*size/2d).add(vY.scale(0.207912*size/2d).add(vZ.scale(-size*0.75)))), location.add(vX.scale(-0.933580*size*1.5).add(vY.scale(0.358368*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(-0.97814*size).add(vY.scale(0.207912*size))), location.add(vX.scale(-0.866025*size).add(vY.scale(0.5*size))), location.add(vX.scale(-0.933580*size*1.5).add(vY.scale(0.358368*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            
            new Triangle(location.add(vY.scale(-size)), location.add(vY.scale(-size/2d).add(vZ.scale(-size*0.75))), location.add(vX.scale(0.156434*size*1.5).add(vY.scale(-0.987688*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(0.301017*size).add(vY.scale(-0.951057*size))), location.add(vX.scale(0.301017*size/2d).add(vY.scale(-0.951057*size/2d).add(vZ.scale(-size*0.75)))), location.add(vX.scale(0.156434*size*1.5).add(vY.scale(-0.987688*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(0.301017*size).add(vY.scale(-0.951057*size))), location.add(vY.scale(-1*size)), location.add(vX.scale(0.156434*size*1.5).add(vY.scale(-0.987688*size*1.5).add(vZ.scale(-size*1.5))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
//body  
            new Cylinder(new Ray(location.add(vZ.scale(size/2d)), vZ), size*0.8, size*7).setEmission(new Color(192,192,192)).setMaterial(new Material().setShininess(50).setKd(0.7).setKs(0.7).setKr(0.4)),
            
            (vZ == new Vector(0, 0, 1) ? new Elepsoaide(location.add(vZ.scale(size*7)), size*0.8, size*0.8, size*2).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            vZ == new Vector(0, 1, 0)? new Elepsoaide(location.add(vZ.scale(size*7)), size*0.8, size*2, size*0.8).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            vZ == new Vector(1, 0, 0)? new Elepsoaide(location.add(vZ.scale(size*7)), size*2, size*0.8, size*0.8).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            vZ == new Vector(0, 0, -1)? new Elepsoaide(location.add(vZ.scale(size*7)), size*0.8, size*0.8, size*2).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            vZ == new Vector(0, -1, 0)? new Elepsoaide(location.add(vZ.scale(size*7)), size*0.8, size*2, size*0.8).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            vZ == new Vector(-1, 0, 0)? new Elepsoaide(location.add(vZ.scale(size*7)), size*2, size*0.8, size*0.8).setEmission(new Color (30,30,30)).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)):
            new Sphere(location.add(vZ.scale(size*7.7)), size*0.8)),
//wings
//                                    pi/6 + pi/20                                                                                                                pi/6                                                                                      pi/6 + pi/20
            new Triangle(location.add(vX.scale(size*0.8*0.777145961457).add(vY.scale(size*0.8*0.62932039105).add(vZ.scale(size*6)))), location.add(vX.scale(size*0.8*0.866025403).add(vY.scale(size*0.8*0.5).add(vZ.scale(size*2)))), location.add(vX.scale(size*2*0.777145961457).add(vY.scale(size*2*0.62932039105).add(vZ.scale(size*1))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            //                        pi/6 + pi/20                                                                                                                  pi/6 + pi/10                                                                                    pi/6 pi/20
            new Triangle(location.add(vX.scale(size*0.8*0.777145961457).add(vY.scale(size*0.8*0.62932039105).add(vZ.scale(size*6)))), location.add(vX.scale(size*0.8*0.669130606359).add(vY.scale(size*0.8*0.743144825477).add(vZ.scale(size*2)))), location.add(vX.scale(size*2*0.777145961457).add(vY.scale(size*2*0.62932039105).add(vZ.scale(size*1))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(size*0.8*0.866025403).add(vY.scale(size*0.8*0.5).add(vZ.scale(size*2)))), location.add(vX.scale(size*0.8*0.669130606359).add(vY.scale(size*0.8*0.743144825477).add(vZ.scale(size*2)))), location.add(vX.scale(size*2*0.777145961457).add(vY.scale(size*2*0.62932039105).add(vZ.scale(size*1))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            
            //                         4pi/6 + pi/20                                                                                  4pi/6 + pi/20                                                                                                4pi/6 + pi/20
            new Triangle(location.add(vX.scale(size*0.8*-0.62932039105).add(vY.scale(size*0.8*0.777145961457).add(vZ.scale(size*6)))),location.add(vX.scale(size*0.8*-0.62932039105).add(vY.scale(size*0.8*0.777145961457).add(vZ.scale(size/2)))),location.add(vX.scale(size*2.5*-0.62932039105).add(vY.scale(size*2.5*0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            //                         4pi/6 + pi/20                                                                                  4pi/6                                                                                                4pi/6 + pi/20
            new Triangle(location.add(vX.scale(size*0.8*-0.62932039105).add(vY.scale(size*0.8*0.777145961457).add(vZ.scale(size*6)))),location.add(vX.scale(size*0.8*-0.5).add(vY.scale(size*0.8*0.866025403).add(vZ.scale(size/2)))),location.add(vX.scale(size*2.5*-0.62932039105).add(vY.scale(size*2.5*0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(size*0.8*-0.5).add(vY.scale(size*0.8*0.866025403).add(vZ.scale(size/2)))),location.add(vX.scale(size*2.5*-0.62932039105).add(vY.scale(size*2.5*0.777145961457).add(vZ.scale(size/2)))),location.add(vX.scale(size*0.8*-0.62932039105).add(vY.scale(size*0.8*0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            
            //                         -2pi/6 + pi/20                                                                                  -2pi/6 + pi/20                                                                                                -2pi/6 + pi/20
            new Triangle(location.add(vX.scale(size*0.8*0.62932039105).add(vY.scale(size*0.8*-0.777145961457).add(vZ.scale(size*6)))),location.add(vX.scale(size*0.8*0.62932039105).add(vY.scale(size*0.8*-0.777145961457).add(vZ.scale(size/2)))),location.add(vX.scale(size*2.5*0.62932039105).add(vY.scale(size*2.5*-0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            //                         -2pi/6 + pi/20                                                                                  -2pi/6 + pi/10                                                                                                -2pi/6 + pi/20                                                                                     
            new Triangle(location.add(vX.scale(size*0.8*0.62932039105).add(vY.scale(size*0.8*-0.777145961457).add(vZ.scale(size*6)))),location.add(vX.scale(size*0.8*0.743144825477).add(vY.scale(size*0.8*-0.669130606359).add(vZ.scale(size/2)))),location.add(vX.scale(size*2.5*0.62932039105).add(vY.scale(size*2.5*-0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5)),
            new Triangle(location.add(vX.scale(size*2.5*0.62932039105).add(vY.scale(size*2.5*-0.777145961457).add(vZ.scale(size/2)))), location.add(vX.scale(size*0.8*0.743144825477).add(vY.scale(size*0.8*-0.669130606359).add(vZ.scale(size/2)))), location.add(vX.scale(size*0.8*0.62932039105).add(vY.scale(size*0.8*-0.777145961457).add(vZ.scale(size/2))))).setEmission(color).setMaterial(new Material().setShininess(13).setKd(0.5).setKs(0.5))
            );  
        
    }
    private void addHills(Scene scene,int y,int range){
        Random random = new Random();
        double rand;
        float distance = 8/range * 800;
        for(int i = -range; i < range; i++){
            rand = random.nextDouble();
            scene.geometries.add(new Sphere(new Point(distance*i * rand*distance/4 - distance/8, y - rand*distance/4 - distance/8, -500 + rand*100 - 50),1000 + rand*100 - 50).setEmission(new Color(0,190,0)).setMaterial(new Material().setShininess(13).setKd(0.4).setKs(0.4)));
        }
    }
    @Test
    public void rocket(){
        Scene scene = new Scene("cool scene")
        .setAmbientLight(new AmbientLight(new Color(0, 0, 0),new Double3(0, 0, 0))) //
        .setBackground(new Color(0, 0, 150));
        double b = 0.378;
        addRocket(scene, new Point(2500,2500,1000), 400, new Vector(0, b, b), new Vector(-2/b, 1/b, -1/b), new Color(192, 50, 50));//vZ = (-1,-1,1) : vX = (0,b,b) vY = (-2/b,1/b,-1/b)
        addRocket(scene, new Point(-2500, -2500, 1000), 400, new Vector(0,b,-b), new Vector(-2/b, 1/b, 1/b), new Color(50, 192, 50)); //vZ = (1,1,1) : vX = (0,b,-b) vY = (-2/b,1/b,1/b)
        addRocket(scene, new Point(2500, -2500, 1000), 400, new Vector(0, b, -b), new Vector(-2/b, -1/b, -1/b), new Color(50, 50, 192));// vz = (-1,1,1) : vx = (0,b,-b) vy = (-2/b,-1/b,-1/b)
//        addRocket(scene, new Point(-2500, 2500, 1000), 400, new Vector(0, b, b), new Vector(-2/b, -1/b, 1/b), new Color(192, 192, 50));//vz = (1,-1,1) : vx = (0,b,b) vy = (-2/b,-1/b,1/b)
//        addRocket(scene, new Point(0, 0, 800), 250, new Vector(1.29,3.75,0), new Vector(-3.783,1.301352,0), new Color(192,192,192));//vz = (0,0,1) : vx = (1.29,3.75,0) vy = (-3.783,1.301352,0)
        addClouds(scene, new Point(-3000, 1000, 1850), 300);
        addClouds(scene, new Point(1000, -2500, 850), 300);
//        addClouds(scene, new Point(1750, 3860, 2600), 300);
//        addClouds(scene, new Point(-750, -1170, 1000), 300);
        for (int i = -6; i < 7; i++) {
            addHills(scene, i * 800, 6);
        }
        scene.geometries.add(
//ground
            //new Plane(new Point(0,0,-300),new Vector(0,0,1)).setEmission(new Color(0,190,0)).setMaterial(new Material().setShininess(3)),
//sun
            new Sphere(new Point(5000,1800,4500),1500).setEmission(new Color(255,255,0)).setMaterial(new Material().setKt(1))

            );
//        scene.lights.add(new PointLight(new Color(100,100,100), new Point(-15000,0,1000)).setKc(0.7).setKl(0.7).setKq(0.7));
        scene.lights.add(new DirectionalLight(new Color(100,100,100), new Vector(-5, -1.8, -5)));
        Camera camera =  new Camera(new Point(-15000, 0, 1000),new Vector(0.03, 0, 1),new Vector(1, 0, -0.03))
        //Camera camera =  new Camera(new Point(-5000, -10000, 200),new Vector(0, 0, 1),new Vector(1, 2, 0))
        //Camera camera =  new Camera(new Point(0, 0, 5000),new Vector(1, 0, 0),new Vector(0, 0, -1))
        .setVPSize(50d, 50d).setVPDistance(100) //maybe need to be changed
//        .antiAliasing(5)
            .setImageWriter(new ImageWriter("rocket test", 1000, 1000))
            .setRayTracer(new RayTracerBasic(scene)).setAdaptiveSupersampling(false).setAdaptiveSuperSamplingDepth(2)
            .antiAliasing(8);
        camera.renderImage().writeToImage();

    }
}
