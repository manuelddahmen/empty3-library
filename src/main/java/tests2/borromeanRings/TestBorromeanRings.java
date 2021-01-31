package tests2.borromeanRings;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjetSub;

/*__
 * Created by Win on 11-09-18.
 */
public class TestBorromeanRings extends TestObjetSub {
    @Override
    public void finit() {
        camera(new Camera(Trajectoires.sphere(
                1.0 * frame() / getMaxFrames(), 0, 0.0001),
                Point3D.O0));
    }

    @Override
    public void ginit() {
        BorromeanRings br = new BorromeanRings();
        scene().add(br);
    }

    public static void main(String... args) {
        TestBorromeanRings tbr = new TestBorromeanRings();
        tbr.setMaxFrames(100);
        new Thread(tbr).start();
    }
}
