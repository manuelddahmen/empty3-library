package tests2.pieuvre;

import one.empty3.library.*;import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;


public class TestPieuvre extends TestObjetSub {
    private Pieuvre pieuvre;

    public void ginit() {
        this.pieuvre = new Pieuvre(10,
                Color.YELLOW);
        scene().add(pieuvre);
        scene().cameraActive(new Camera(Point3D.Z.mult(2d), Point3D.O0));
    }

    private double time() {
        return 1.0 * frame() / getMaxFrames() * 25.0;
    }

    public void testScene() {
        pieuvre.setT(time());
    }

    public static void main(String[] args) {
        TestPieuvre testPieuvre = new TestPieuvre();
        testPieuvre.setMaxFrames(10000);
        new Thread(testPieuvre).start();

    }
}
