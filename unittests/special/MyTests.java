package special;

import geometries.*;
import lighting.*;
import primitives.*;
import org.junit.jupiter.api.Test;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class MyTests {

    static Scene scene = new Scene("My Room Test")
            .setBackground(Color.BLACK);
    static Color mainLightIntensity = new Color(800,500,0);//.reduce(2.0);
    static Point mainLightPosition = new Point(5,9, 5);

    static AmbientLight ambientLight = new AmbientLight(new Color(java.awt.Color.WHITE).reduce(2), 0.2);
    static Color floorColor = new Color(java.awt.Color.PINK);
    static Material floorMat = new Material().setKd(0.6).setKs(0.4).setKr(0.15).setShininess(300);
    static Color wallsColor = new Color(java.awt.Color.LIGHT_GRAY);
    static Material wallsMat = new Material().setKd(0.8).setKs(0.2).setShininess(15);

    static Camera camera = new Camera(new Point(9, 1, 9),
            new Vector(-9,0,-9),
            new Vector(0,1,0))
            .setVPSize(200, 200).setVPDistance(500)
            .setUseAntiAliasing(true).setAliasingRays(5)
            .setRayTracer(new RayTracerBasic(scene).setUseSoftShadow(true))
            .setImageWriter(new ImageWriter("my room", 800, 800));

    @Test
    public void drawRoom() {
        scene.setAmbientLight(ambientLight);

        PointLight light = new PointLight(mainLightIntensity, mainLightPosition)
                .setKl(0.001).setKq(0.0002);
        scene.lights.add(light);

        Plane floor = new Plane(Point.ZERO, new Vector(0,1,0));
        Plane wall1 = new Plane(Point.ZERO, new Vector(1,0,0));

        scene.geometries.add(floor.setMaterial(floorMat).setEmission(floorColor));

        Sphere sphere = new Sphere(1d, new Point(1,1,1));
        scene.geometries.add(sphere.setEmission(new Color(java.awt.Color.CYAN).reduce(2))
                .setMaterial(wallsMat));

        camera.renderImage().writeToImage();
    }
}
