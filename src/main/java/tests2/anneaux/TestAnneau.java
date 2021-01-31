package tests2.anneaux;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomialeBezier;
import one.empty3.library.core.testing.TestObjet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestAnneau extends TestObjet {

    private int N = 20;
    List<Point3D> point3D = new ArrayList<>();
    private double latLat = 0.3;
    private CourbeParametriquePolynomialeBezier courbeParametriquePolynomialeBezier;

    @Override
    public void afterRenderFrame() {

    }

    @Override
    public void finit() {

    }


    @Override
    public void ginit() {
    }

    @Override
    public void afterRender() {

    }

    @Override
    public void testScene() throws Exception {
        double lgt = 0;
        for (int i = 0; ; i++) {
            Point3D sphere = Trajectoires.sphere(lgt, Math.random() / latLat, 1);
            point3D.add(sphere);
            lgt += Math.random() / N;
            if (lgt > 1) {
                lgt = 1;
                break;
            }
        }
        Point3D[] ds = new Point3D[this.point3D.size()];
        courbeParametriquePolynomialeBezier = new CourbeParametriquePolynomialeBezier(this.point3D.toArray(ds));
        scene().add(courbeParametriquePolynomialeBezier);

        courbeParametriquePolynomialeBezier.texture(new TextureCol(Color.BLUE));
        courbeParametriquePolynomialeBezier.getParameters().setIncrU(0.0001);
        Camera camera = new Camera(Point3D.O0, Point3D.Z.mult(2d));
        camera.calculerMatrice(Point3D.Y);
        scene().cameraActive(camera);
    }


    public static void main(String... args) {
        TestAnneau testAnneau = new TestAnneau();
        Thread thread = new Thread(testAnneau);
        testAnneau.setMaxFrames(25 * 60 * 5);
        thread.start();
    }
}
