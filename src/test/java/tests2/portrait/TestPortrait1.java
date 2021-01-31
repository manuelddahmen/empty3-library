/*__
 * *
 * Global license : * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package tests2.portrait;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestPortrait1 extends TestObjetSub {

    public static void main(String[] args) {
        TestPortrait1 tp = new TestPortrait1();
        tp.loop(false);
        new Thread(tp).start();
    }

    @Override
    public void testScene() throws Exception {

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                Cube c1 = new Cube(1.0, new Point3D(i * 2d, 0.5, j * 2d), new TextureCol(Color.white));
                scene().add(c1);
            }
        }

        TRISphere ts = new TRISphere(new Point3D(0d, 1.5, 0d), 2d);

        ts.texture(new TextureCol(Color.WHITE));

        scene().add(ts);

        Camera c = new Camera(new Point3D(0d, 3d, -20d), Point3D.O0);

        scene().cameraActive(c);
        scene().lumieres()
                .add(
                        new LumierePonctuelle(new Point3D(0d, 20d, 20d), Color.orange));
        scene().lumieres()
                .add(
                        new LumierePonctuelle(new Point3D(20d, 20d, 20d), Color.magenta));
    }

    @Override
    public void finit() {
    }

    @Override
    public void ginit() {
    }
}
