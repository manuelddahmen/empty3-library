/*__
 Global license :

 CC Attribution

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.beziers;

import one.empty3.library.*;
import one.empty3.library.core.nurbs.SurfaceParametricPolygonalBezier;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestBezierTextImage extends TestObjetSub {
    private final Point3D[][] coeff = new Point3D[][]{
            {P.n(2, -2, 0), P.n(2, -1, 0), P.n(2, 0, 0), P.n(2, 1, 0), P.n(2, 2, 0)},
            {P.n(1, -2, 0), P.n(1, -1, 0), P.n(1, 0, 0), P.n(1, 1, 0), P.n(1, 2, 0)},
            {P.n(0, -2, 0), P.n(0, -1, 0), P.n(0, 0, 0), P.n(0, 1, 0), P.n(0, 2, 0)},
            {P.n(-1, -2, 0), P.n(-1, -1, 0), P.n(-1, 0, 0), P.n(-1, 1, 0), P.n(-1, 2, 0)},
            {P.n(-2, -2, 0), P.n(-2, -1, 0), P.n(-2, 0, 0), P.n(-2, 1, 0), P.n(-2, 2, 0)}
    };
    TextureImg imgTexture;
    private SurfaceParametricPolygonalBezier s = new SurfaceParametricPolygonalBezier(coeff);

    public TestBezierTextImage() {
    }

    public static void main(String[] args) {

        TestBezierTextImage tss = new TestBezierTextImage();
        tss.setMaxFrames(2000);
        tss.loop(true);
        tss.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        new Thread(tss).start();
    }

    @Override
    public void testScene(File f) throws Exception {
    }

    @Override
    public void ginit() {
        try {
            imgTexture = new TextureImg(ECBufferedImage.getFromFile(new File("C:\\Emptycanvas\\textures\\text1.jpg")));
            s.texture(imgTexture);
        } catch (IOException ex) {
            s.texture(new TextureCol(Color.WHITE));
            Logger.getLogger(TestBezierTextImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene().add(s);
        scene().cameraActive().setEye(Point3D.Z.mult(-1d));

    }

    @Override
    public void finit() {
        System.gc();
    }

    @Override
    public void testScene() throws Exception {

    }

}
