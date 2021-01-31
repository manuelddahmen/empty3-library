/*__
 * *
 * Global license :  *
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * *
 */
package tests2.trigenerateurabstract.paraboloidehyperbolique;

import one.empty3.library.Camera;
import one.empty3.library.LumierePointSimple;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjet;
import one.empty3.library.core.tribase.ParaboloideHyperbolique;

import java.awt.*;

/*__
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestPH extends TestObjet {

    private ParaboloideHyperbolique ph = null;

    public static void main(String[] argd) {
        TestPH tth = new TestPH();

        tth.loop(true);
        tth.setMaxFrames(250);

        new Thread(tth).start();
    }

    @Override
    public void afterRenderFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finit() {
        double angleU = Math.abs(Math.cos(2.0 * Math.PI * frame() / getMaxFrames() * 6));
        double angleV = Math.abs(Math.sin(2.0 * Math.PI * frame() / getMaxFrames() * 6));
        ph = new ParaboloideHyperbolique(angleU, angleV, 1);
        scene().add(ph);
        ph.texture(new TextureCol(Color.RED));
    }

    @Override
    public void ginit() {

    }

    @Override
    public void testScene() throws Exception {
        scene().cameraActive(new Camera(Point3D.Z.mult(-5d), Point3D.O0));
        scene().lumieres().add(new LumierePointSimple(Color.WHITE, Point3D.X.plus(Point3D.Y), 1));
    }

    public void afterRender() {

    }
}
