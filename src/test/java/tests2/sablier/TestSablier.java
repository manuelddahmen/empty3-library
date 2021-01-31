package tests2.sablier;

import one.empty3.library.Camera;
import one.empty3.library.Point2D;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjetSub;
import tests2.sablier.Sablier;

import java.awt.*;


/*__
 * Created by manuel on 01-11-15.
 * Copyright Manuel Dahmen. 2017
 */
public class TestSablier extends TestObjetSub {
    public static void main(String[] args) {

        TestSablier target = new TestSablier();
        target.loop(true);
        target.setMaxFrames(600);
        target.setGenerate(GENERATE_IMAGE | GENERATE_MODEL | GENERATE_MOVIE);
        new Thread(target).start();
    }

    public void ginit() {
        this.setMaxFrames(1);
        Sablier s = new Sablier();
        s.texture(new TextureCol(Color.BLUE));
        scene().add(s);

    }

    private Point2D cercle() {

        return Trajectoires.sphere(0.0 + 1.0 * frame / getMaxFrames(), 0, 1).get2D();
    }

    public void testScene() {
        scene().cameraActive(new Camera(cercle().get3D().mult(2.5), Point3D.O0));

    }
}
