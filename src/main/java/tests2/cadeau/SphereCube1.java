/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.cadeau;

import one.empty3.library.Camera;
import one.empty3.library.Cube;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SphereCube1 extends TestObjetSub {
    private final double t0 = -1;
    private final double t1 = 1;
    double d = 90;
    private TRISphere s;

    private double F = 3;
    private Camera cam;

    public static void main(String[] args) {


        SphereCube1 sc = new SphereCube1();

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

        cam = new Camera(s.getCentre().mult(F), Point3D.O0);

        scene().cameraActive(cam);
    }

    @Override
    public void testScene() throws Exception {

        double pc = 1.0 * frame() / getMaxFrames();

        double TT;
        TT = t0 + (t1 - t0) * pc;

        s.setCentre(Point3D.X.mult(TT * d));

        cam.setEye(s.getCircle().getCenter().mult(F));
    }

    @Override
    public void finit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
