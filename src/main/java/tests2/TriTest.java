package tests2;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.nurbs.ParametricSurface;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;


/*__
 * Created by manuel on 03-10-15.
 */
class Forme extends ParametricSurface {
    public Point3D P0 = Point3D.O0;

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D p = Trajectoires.sphere(u, v, 1);
        return p.moins(P0).mult(Math.pow(p.moins(P0).norme(), 2) * Math.exp(1 * Point3D.distance(P0, p) * Point3D.distance(P0, p)) * u * v);
    }

    @Override
    public Point3D calculerVitesse3D(double v, double v1) {
        return null;
    }
}

public class TriTest extends TestObjetSub {

    private Forme f;

    public static void main(String[] args) {
        TriTest test = new TriTest();
        test.setMaxFrames(100);
        test.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        new Thread(test).start();
    }

    @Override
    public void afterRenderFrame() {

    }

    @Override
    public void finit() {

    }

    @Override
    public void ginit() {
        f = new Forme();
        f.texture(new TextureCol(Color.BLUE));
        scene().add(f);
    }

    public void afterRender() {

    }

    @Override
    public void testScene() throws Exception {
        scene().cameraActive(new Camera(Point3D.Z.mult(-100d + 100 * ((float) frame) / getMaxFrames()), Point3D.O0));

    }

}
