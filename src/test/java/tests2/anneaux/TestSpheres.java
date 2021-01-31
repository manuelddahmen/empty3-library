package tests2.anneaux;


import one.empty3.library.*;
import one.empty3.library.core.nurbs.CameraInPath;
import one.empty3.library.core.testing.TestObjetSub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestSpheres extends TestObjetSub {

    public static final int CIRCLES_COUNT = 10;
    public double step = 10000.0;

    public static void main(String... args) {
        TestSpheres testSpheres = new TestSpheres();
        testSpheres.setResolution(800, 800);
        testSpheres.setMaxFrames(24*30);
        new Thread(testSpheres).start();
    }

    @Override
    public void afterRenderFrame() {

    }

    @Override
    public void finit() {
        CameraInPath camera = new CameraInPath(new Circle(
                new Axe(Point3D.O0.plus(Point3D.X), Point3D.O0.moins(Point3D.X)), 400d));
        scene().add(camera);
        scene().cameraActive(camera);double t = 1.0*frame()/(getMaxFrames());
        camera.setT(t);

        Point3D z = Point3D.O0.moins(camera.getCourbe().calculerPoint3D(t)).norme1();
        Point3D x = camera.getCourbe().tangente(t).norme1().mult(-1d);
        Point3D y = x.prodVect(z).norme1();
        camera.setMatrix(x, y, z);


    }

    @Override
    public void ginit() {
        scene().getObjets().data1d = new ArrayList<>();
        Sphere[] spheres = new Sphere[TestSpheres.CIRCLES_COUNT];
        for (int i = 0; i < spheres.length; i++) {
            Axe axe = new Axe(Point3D.random(100d), Point3D.random(100d));
            spheres[i] = new Sphere(axe,
                    100);
            spheres[i].texture(new TextureCol(Color.ORANGE));
            spheres[i].setIncrU(.01);
            spheres[i].setIncrV(.01);
            try {
                TextureImg imageTexture = new TextureImg(
                        new ECBufferedImage(
                                ImageIO.read(new File("./samples/img/herbe.jpg"))));

                spheres[i].texture(imageTexture);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene().add(spheres[i]);
        }
    }

    @Override
    public void afterRender() {

    }

    @Override
    public void testScene() throws Exception {

    }


    public void gc() {
        Runnable gc = () -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.gc();
            }
        };
        new Thread(gc).start();
    }
}
