package tests2.paintingActs;

import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjet;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * Created by manue on 12-10-15.
 */
public class TestSpheres extends TestObjetSub {
    public static void main(String[] args) {
        TestObjet to = new TestSpheres();

        to.loop(true);
        to.unterminable(false);
        to.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        to.setMaxFrames(2000);

        new Thread(to).start();
    }

    public void ginit() {
        TRISphere sphere = new TRISphere(Point3D.O0, 10);
        sphere.texture(new TextureCol(Color.GREEN));
        scene().add(sphere);
        //TODO CHECK sphere.setPaintingAct(getZ(), scene(), new SpheresPA());
    }

    public void testScene() {

    }
}
