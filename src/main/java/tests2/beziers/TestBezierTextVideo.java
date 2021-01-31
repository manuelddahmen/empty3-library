/*__
 * *
 * Global license :  *
 * CC Attribution
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package tests2.beziers;


import one.empty3.library.EOFVideoException;
import one.empty3.library.Point3D;
import one.empty3.library.P;
import one.empty3.library.TextureMov;
import one.empty3.library.core.nurbs.SurfaceParametricPolygonalBezier;
import one.empty3.library.core.testing.TestObjetSub;

import java.io.File;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestBezierTextVideo extends TestObjetSub {

    private final Point3D[][] coeff = new Point3D[][]{
            {P.n(2, -2, 0), P.n(2, -1, 0), P.n(2, 0, 0), P.n(2, 1, 0), P.n(2, 2, 0)},
            {P.n(1, -2, 0), P.n(1, -1, 0), P.n(1, 0, 0), P.n(1, 1, 0), P.n(1, 2, 0)},
            {P.n(0, -2, 0), P.n(0, -1, 0), P.n(0, 0, 0), P.n(0, 1, 0), P.n(0, 2, 0)},
            {P.n(-1, -2, 0), P.n(-1, -1, 0), P.n(-1, 0, 0), P.n(-1, 1, 0), P.n(-1, 2, 0)},
            {P.n(-2, -2, 0), P.n(-2, -1, 0), P.n(-2, 0, 0), P.n(-2, 1, 0), P.n(-2, 2, 0)}
    };
    TextureMov textureMov;
    private SurfaceParametricPolygonalBezier s = new SurfaceParametricPolygonalBezier(coeff);

    public TestBezierTextVideo() {
    }

    public static void main(String[] args) {

        TestBezierTextVideo tss = new TestBezierTextVideo();
        tss.setMaxFrames(2000);
        tss.setResx(1024);

        tss.setResy(480);
        tss.loop(true);
        tss.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        new Thread(tss).start();
    }

    @Override
    public void testScene(File f) throws Exception {
    }

    @Override
    public void ginit() {
        textureMov = new TextureMov("C:\\Emptycanvas\\textures\\Il embrasse sur la bouche.mp4");
        s.texture(textureMov);
        scene().add(s);
        scene().cameraActive().setEye(Point3D.Z.mult(-6d));
    }

    @Override
    public void finit() throws EOFVideoException {
        textureMov.nextFrame();
    }

    @Override
    public void testScene() throws Exception {

    }

}
