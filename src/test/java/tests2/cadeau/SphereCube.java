/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.cadeau;

import one.empty3.library.Cube;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SphereCube extends TestObjetSub {
    private final double t0 = -1;
    private final double t1 = 1;
    double d = 90;
    private TRISphere s;

    public static void main(String[] args) {


        SphereCube sc = new SphereCube();

        sc.setMaxFrames(300);

        sc.loop(true);

        new Thread(sc).start();


    }

    @Override
    public void ginit() {

        Cube c;

        //c.texture(new TextureCol(Color.RED));
        c = new Cube(d / 10, Point3D.O0);


        c.texture(new TextureCol(Color.BLUE));

        s = new TRISphere(Point3D.X.mult(t0), d / 10);

        s.texture(new TextureCol(Color.YELLOW));

        scene().add(c);
        scene().add(s);


    }

    @Override
    public void testScene() throws Exception {

        double pc = 1.0 * frame() / getMaxFrames();

        double TT;
        TT = t0 + (t1 - t0) * pc;

        s.setCentre(Point3D.X.mult(TT * d));

    }

    @Override
    public void finit() {
    }

}
