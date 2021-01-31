package tests2.mammaires;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;


/*__
 * Created by manuel on 01-11-15.
 * Copyright Manuel Dahmen. 2017
 */
public class TestSeins extends TestObjetSub {
    public static void main(String[] args) {

        TestSeins target = new TestSeins();
        target.loop(true);
        target.setMaxFrames(600);
        target.setGenerate(GENERATE_IMAGE | GENERATE_MODEL | GENERATE_MOVIE);
        new Thread(target).start();
    }

    public void ginit() {
        this.setMaxFrames(3);

    }

    @Override
    public void finit() {
        if (frame() == 0) {
            tests2.mammaires.mammaires.Sein1 s = new mammaires.Sein1();
            s.texture(new TextureCol(Color.BLUE));
            scene().add(s);
        } else if (frame() == 1) {
            mammaires.Sein2 s = new mammaires.Sein2();
            s.texture(new TextureCol(Color.BLUE));
            scene().clear();
            scene().add(s);
        } else if (frame() == 2) {
            mammaires.Sein3 s = new mammaires.Sein3();
            s.texture(new TextureCol(Color.BLUE));
            scene().clear();
            scene().add(s);
        }
    }

    private Point3D cercle() {

        return Trajectoires.sphere(0.0 + 1.0 * frame / getMaxFrames(), 0, 3);
    }

    public void testScene() {
        scene().cameraActive(new Camera(cercle(), Point3D.O0));

    }
}
