package special;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class MP1Tests {
/*
    //region Mirror
    Scene createMirrorScene() {
        Scene scene = new Scene("Test mirror scene");
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(20, new Point(160, 0, -100)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(40, new Point(500, 160, -500)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(20, new Point(-70, 0, -100)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(40, new Point(-250, 80, -500)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(5, new Point(0, -20, 30)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(10, new Point(20, 0, 30)).setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Plane(new Point(0, -10, -500), new Point(-500, -10, -500), new Point(-200, -110, -50))
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKr(0.95)));


        scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point(80, 0, 0), new Vector(0, 0, -1))
                .setKl(4E-5).setKq(2E-7).setLengthOfTheSide(14));
        scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point(-80, 50, 0), new Vector(0, 0, -1))
                .setKl(4E-5).setKq(2E-7).setLengthOfTheSide(9));
        scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point(60, 80, 0), new Vector(0, 0, -1))
                .setKl(4E-5).setKq(2E-7).setLengthOfTheSide(20));
        scene.lights.add(new PointLight(new Color(200, 200, 200), new Point(-30, 120, 0))
                .setKl(4E-5).setKq(2E-7).setLengthOfTheSide(5));
        scene.lights.add(new DirectionalLight(new Color(0, 40, 400), new Vector(-1,-1,-1)));

        return scene;
    }

    @Test
    public void mirror() {
        Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(200, 200).setVPDistance(100);

        camera.setImageWriter(new ImageWriter("mirror", 800, 800))
                .setRayTracer(new RayTracerBasic(createMirrorScene()))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void mirrorAntiAliasing() {
        Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setUseAntiAliasing(true).setAliasingRays(9)
                .setVPSize(200, 200).setVPDistance(100);

        camera.setImageWriter(new ImageWriter("mirror - anti aliasing", 800, 800))
                .setRayTracer(new RayTracerBasic(createMirrorScene()))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void mirrorSoftShadows() {
        Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(200, 200).setVPDistance(100);

        PointLight.softShadowsRays = 64;
        camera.setImageWriter(new ImageWriter("mirror - soft shadow", 800, 800))
                .setRayTracer(new RayTracerBasic(createMirrorScene()).setUseSoftShadow(true))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void mirrorAntiAliasingSoftShadows() {
        Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setUseAntiAliasing(true).setAliasingRays(9)
                .setVPSize(200, 200).setVPDistance(100);

        PointLight.softShadowsRays = 64;
        camera.setImageWriter(new ImageWriter("mirror - anti aliasing - soft shadow", 800, 800))
                .setRayTracer(new RayTracerBasic(createMirrorScene()).setUseSoftShadow(true))
                .renderImage()
                .writeToImage();
    }
    //endregion
*/
    private Point calculateScaledPointByVector(Point point, Vector scaleDirection, double toAdd, double scale) {
        return point.add(scaleDirection.normalize().scale(toAdd * scale));
    }

    Intersectable[] createLamp(Point bottomCenter, Vector lampDirection, double scale) {
        if (scale <= 0)
            throw new IllegalArgumentException("Scale must be positive number");

        Material lampMat = new Material().setKd(0.5).setKs(0.1).setKr(0.000001);
        Color lampColor = new Color(150, 150, 150);
        Material lampSphereMat = new Material().setKt(0.95).setKd(0.3);
        Color lampSphereColor = new Color(155, 140, 30);

        Point pointA = calculateScaledPointByVector(bottomCenter, lampDirection, 22, scale);
        Point pointB = calculateScaledPointByVector(bottomCenter, lampDirection, 338, scale);
        Point pointC = calculateScaledPointByVector(bottomCenter, lampDirection, 350, scale);
        Point pointD = calculateScaledPointByVector(bottomCenter, lampDirection, 410, scale);

        Intersectable[] lampComponents = {
                new Cylinder(16 * scale, new Ray(bottomCenter, lampDirection), 360 * scale)
                        .setEmission(lampColor).setMaterial(lampMat),
                new Cylinder(22 * scale, new Ray(bottomCenter, lampDirection), 10 * scale)
                        .setEmission(lampColor).setMaterial(lampMat),
                new Cylinder(19 * scale, new Ray(pointA, lampDirection), 8 * scale)
                        .setEmission(lampColor).setMaterial(lampMat),
                new Cylinder(19 * scale, new Ray(pointB, lampDirection), 8 * scale)
                        .setEmission(lampColor).setMaterial(lampMat),
                new Cylinder(22 * scale, new Ray(pointC, lampDirection), 10 * scale)
                        .setEmission(lampColor).setMaterial(lampMat),

                new Sphere(50 * scale, pointD)
                        .setEmission(lampSphereColor).setMaterial(lampSphereMat),
                new Sphere(55 * scale, pointD)
                        .setEmission(lampSphereColor.add(new Color(20, 20, 20))).setMaterial(lampSphereMat),
        };

        return lampComponents;
    }

    @Test
    public void test() {
        while(true)
            System.out.printf("%5.1f%%", Math.PI);
    }

    Scene createViewScene(boolean day) {
        // sky
        Color skyDay = new Color(0, 210, 215);
        Color skyNight = new Color(0, 0, 60);

        // main directional light color
        Color directionalLightDay = new Color(225,235,230);
        Color directionalLightNight = new Color(100,100,240);


        Scene scene = new Scene("Test view scene")
                .setBackground(day ? skyDay : skyNight);

        // wood
        Material woodMat = new Material().setKd(0.3).setKs(0.01);
        Color woodColor = new Color(155, 60, 0);
        Material leafMat = new Material().setKd(0.1).setKs(0.1);
        Color leafColor = new Color(65, 180, 0);

        // ground
        Material groundMat = new Material().setKd(0.9).setKs(0.00001);
        Color groundColor = new Color(15, 70, 5);

        // mountain
        Material mountainMat = new Material().setKd(0.2).setKs(0.15);
        Color mountainColor = new Color(90, 85, 80);
        Color mountainSnowColor = new Color(240, 245, 243);

        Point lampPoint = calculateScaledPointByVector(new Point(170,10,0),
                new Vector(0, 0, 1), 410, 0.2);
        scene.geometries.add(createLamp(new Point(170,10,0),
                new Vector(0, 0, 1), 0.2));

        //region Trees
        scene.geometries.add(
                new Plane(Point.ZERO, new Vector(0, 0, 1))
                        .setEmission(groundColor).setMaterial(groundMat),

                new Cylinder(2, new Ray(new Point(250, 150, 0), new Vector(0, 0, 1)), 70)
                        .setEmission(woodColor).setMaterial(woodMat),
                new Sphere(20, new Point(250, 150, 80))
                        .setEmission(leafColor).setMaterial(leafMat),
                new Sphere(14, new Point(250, 150, 100))
                        .setEmission(leafColor).setMaterial(leafMat),
                new Sphere(8, new Point(250, 150, 115))
                        .setEmission(leafColor).setMaterial(leafMat),


                new Cylinder(3, new Ray(new Point(170, -110, 0), new Vector(0, 0, 1)), 50)
                        .setEmission(woodColor.reduce(2)).setMaterial(woodMat),
                new Sphere(20, new Point(170, -110, 60))
                        .setEmission(leafColor.reduce(2)).setMaterial(leafMat),
                new Sphere(14, new Point(170, -110, 80))
                        .setEmission(leafColor.reduce(2)).setMaterial(leafMat),
                new Sphere(8, new Point(170, -110, 95))
                        .setEmission(leafColor.reduce(2)).setMaterial(leafMat),


                new Cylinder(2, new Ray(new Point(320, -50, 0), new Vector(0, 0, 1)), 50)
                        .setEmission(woodColor.reduce(3)).setMaterial(woodMat),
                new Sphere(20, new Point(320, -50, 60))
                        .setEmission(leafColor.reduce(3)).setMaterial(leafMat)
        );
        //endregion

        //region Mountains in background
        Point[] mtPoints = new Point[] {
                new Point(2000, 2500, -5), // 1
                new Point(2000, 1500, 750), // 2
                new Point(2000, 500, -5), // 3
                new Point(2000, -500, 800), // 4
                new Point(2000, -1500, -5), // 5
                new Point(2000, -2000, 730), // 6
                new Point(2000, -2500, -5), // 7

                new Point(3000, 1500, -5),  // 8
                new Point(3000, 500, 1100),  // 9
                new Point(3000, -500, -5),  // 10
                new Point(3000, -1500, 1600),  // 11
                new Point(3000, -2500, -5),  // 12

                new Point(1500, 1500, -5), // 13
                new Point(1500, -500, -5), // 14
                new Point(1500, -2000, -5), // 15

                new Point(2500, 500, -5),  // 16
                new Point(2500, -1500, -5),  // 17
        };

        scene.geometries.add(
                new Triangle(mtPoints[0], mtPoints[1], mtPoints[12])
                        .setEmission(mountainColor).setMaterial(mountainMat),
                new Triangle(mtPoints[2], mtPoints[1], mtPoints[12])
                        .setEmission(mountainColor).setMaterial(mountainMat),

                new Triangle(mtPoints[2], mtPoints[3], mtPoints[13])
                        .setEmission(mountainColor).setMaterial(mountainMat),
                new Triangle(mtPoints[4], mtPoints[3], mtPoints[13])
                        .setEmission(mountainColor).setMaterial(mountainMat),


                new Triangle(mtPoints[4], mtPoints[5], mtPoints[14])
                        .setEmission(mountainColor).setMaterial(mountainMat),
                new Triangle(mtPoints[6], mtPoints[5], mtPoints[14])
                        .setEmission(mountainColor).setMaterial(mountainMat),



                new Triangle(mtPoints[7], mtPoints[8], mtPoints[15])
                        .setEmission(mountainColor).setMaterial(mountainMat),
                new Triangle(mtPoints[9], mtPoints[8], mtPoints[15])
                        .setEmission(mountainColor).setMaterial(mountainMat),


                new Triangle(mtPoints[9], mtPoints[10], mtPoints[16])
                        .setEmission(mountainColor).setMaterial(mountainMat),
                new Triangle(mtPoints[11], mtPoints[10], mtPoints[16])
                        .setEmission(mountainColor).setMaterial(mountainMat)
        );
        //endregion

        scene.lights.add(new DirectionalLight(day ? directionalLightDay : directionalLightNight, new Vector(1, -1, -1)));

        PointLight.softShadowsRays = 64;
        scene.lights.add(new PointLight(new Color(255, 255, 255), lampPoint)
                .setKq(2E-5).setLengthOfTheSide(10));

        return scene;
    }

    @Test
    public void view() {
        Camera camera = new Camera(new Point(0, 0, 25), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setUseThreadedRendering(true)
                .setVPSize(128, 72).setVPDistance(60);

        camera.setImageWriter(new ImageWriter("view - night", 1280, 720))
                .setRayTracer(new RayTracerBasic(createViewScene(false)))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void viewAntiAliasing() {
        Camera camera = new Camera(new Point(0, 0, 25), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setUseThreadedRendering(true)
                .setUseAntiAliasing(true).setAliasingRays(13)
                .setVPSize(128, 72).setVPDistance(60);

        camera.setImageWriter(new ImageWriter("view - night - anti aliasing", 1280, 720))
                .setRayTracer(new RayTracerBasic(createViewScene(false)))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void viewSoftShadow() {
        Camera camera = new Camera(new Point(0, 0, 25), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setUseThreadedRendering(true)
                .setVPSize(128, 72).setVPDistance(60);

        camera.setImageWriter(new ImageWriter("view - night - soft shadow", 1280, 720))
                .setRayTracer(new RayTracerBasic(createViewScene(false)).setUseSoftShadow(true))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void viewSoftShadowAntiAliasing() {
        Camera camera = new Camera(new Point(0, 0, 25), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setUseThreadedRendering(true)
                .setUseAntiAliasing(true).setAliasingRays(13)
                .setVPSize(128, 72).setVPDistance(60);

        camera.setImageWriter(new ImageWriter("view - night - anti aliasing - soft shadow", 1280, 720))
                .setRayTracer(new RayTracerBasic(createViewScene(false)).setUseSoftShadow(true))
                .renderImage()
                .writeToImage();
    }
}
