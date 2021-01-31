/*__
 * *
 * Global license :  *
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package tests2.formesbase;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRIEllipsoide;

/*__
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestEllipsoide extends TestObjetSub {

    public static void main(String[] args) {

        new Thread(new TestEllipsoide())

                .start();

    }

    @Override
    public void ginit() {
        setGenerate(GENERATE_IMAGE | GENERATE_MODEL | GENERATE_MOVIE);
        setMaxFrames(1);
        loop(false);
    }

    @Override
    public void testScene() {
        TRIEllipsoide e = new TRIEllipsoide(Point3D.O0, 5.0, 2.0, 1.0);

        e.setMaxX(100);
        e.setMaxY(100);

        scene().add(e);

        scene().cameraActive(new Camera(Point3D.Z.mult(-10d), Point3D.O0));

    }
}
