package tests2.position;

import one.empty3.library.Barycentre;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * @author Se7en
 */
public class TestTRISphere extends TestObjetSub {

    public TestTRISphere() {
    }

    public static void main(String[] args) {
        TestTRISphere ts = new TestTRISphere();
        ts.loop(false);
        ts.run();

    }

    @Override
    public void testScene() throws Exception {
        scene().cameraActive().eye().setZ(-10d);

        TRISphere s = new TRISphere(Point3D.O0, 1);
        Barycentre barycentre = new Barycentre();
        barycentre.position = Point3D.Y.mult(5d);

        s.texture(new TextureCol(Color.WHITE));
        scene().add(s);

    }

    @Override
    public void finit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ginit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
