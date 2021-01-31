/*
 * 2013 Manuel Dahmen
 */
package tests2.triangles;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TRI;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;

public class TestTriangles extends TestObjetSub {

    private SiPiKi3D si;

    public TestTriangles() {
    }

    /*__
     * @param args
     */
    public static void main(String[] args) {
        TestTriangles tt = new TestTriangles();
        tt.loop(true);
        tt.setMaxFrames(10);
        new Thread(tt).start();
    }

    @Override
    public void ginit()  {
        scene().getObjets().getData1d().clear();


        si = new SiPiKi3D(frame());


        si.add(new TRI(Point3D.O0, Point3D.X, Point3D.Y, Color.BLUE), frame);

        scene().add(si);

        scene().cameraActive(new Camera(new Point3D(0d, 0d, 2d), new Point3D(0d, 0d, 0d)));
    }

}
