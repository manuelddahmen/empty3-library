/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.TestSphere;

import one.empty3.library.*;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjetSub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestSphere extends TestObjetSub {
    public int size = 10;
    public double taille = 1.0;
    public double incrlong = 0.001;
    public double incrlat = 0.001;
    private double[] longpc;
    private double[] latpc;
    private double[][] incrpc;
    private Sphere[] s;

    public static void main(String[] args) {
        TestSphere ts = new TestSphere();

        ts.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        ts.loop(true);

        ts.setMaxFrames(1);

        new Thread(ts).start();


    }

    @Override
    public void ginit() {
        longpc = new double[size];
        latpc = new double[size];
        incrpc = new double[size][2];
        for (int i = 0; i < size; i++) {
            longpc[i] = 0;
            latpc[i] = 0;
            incrpc[i][0] = Math.random() * 0.1;
            incrpc[i][1] = Math.random() * 0.1;
        }

        s = new Sphere[size - 1];

        for (int i = 0; i < s.length; i++) {
            s[i] = new Sphere(Point3D.O0, taille);
            try {
                s[i].texture(new TextureImg(new ECBufferedImage(ImageIO.read(new File("resources/img/2iu2h2w0.bmp")))));
            } catch (IOException ex) {
                s[i].texture(new TextureCol(Color.PINK));
                Logger.getLogger(TestSphere.class.getName()).log(Level.SEVERE, null, ex);
            }
            scene().add(s[i]);
        }
    }

    public double longpc(int item) {
        longpc[item] += incrpc[item][0];
        return longpc[item];
    }

    public double latpc(int item) {
        latpc[item] += incrpc[item][1];
        return latpc[item];
    }

    @Override
    public void testScene() throws Exception {
        scene().cameraActive(new Camera(
                Trajectoires.sphere(longpc(0), latpc(0), 0.5),
                Trajectoires.sphere(longpc(0), latpc(0), 0.5)

        ));
        scene().cameraActive().calculerMatrice(Point3D.Y);
        for (int i = 0; i < s.length; i++) {
            Point3D pA = s[i].getCircle().getAxis().getElem().getP1().getElem();
            Point3D pB = Trajectoires.sphere(longpc(i + 1), latpc(i + 1), size);
            s[i].getCircle().getAxis().getElem().getP1().setElem(pA);
            s[i].getCircle().getAxis().getElem().getP2().setElem(pB);
            s[i].getCircle().calculerRepere1();
        }
    }
}
