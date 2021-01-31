/*__
 * Importer une autre test: ah si ca pouvait être fait par classes!
 */
package tests2.repereAssocieAUneCourbeEX;

import one.empty3.library.Camera;
import one.empty3.library.EOFVideoException;
import one.empty3.library.Point3D;
import one.empty3.library.TextureMov;
import one.empty3.library.core.nurbs.CameraInPath;
import one.empty3.library.core.testing.TestObjet;
import one.empty3.library.core.tribase.TRICylindre;

import java.awt.*;

/*__
 *
 * @author Manuel Dahmen
 */
public class TestCameraEnMouvementCylindre extends TestObjet {

    TextureMov textureMov;
    private CameraInPath cam;
    private TRICylindre e;

    public static void main(String[] args) {
        TestCameraEnMouvementCylindre t = new TestCameraEnMouvementCylindre();
        t.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        t.setMaxFrames(30 * 25);
        t.setResx(640);
        t.setResy(480);
        new Thread(t).start();
    }

    @Override
    public void afterRenderFrame() {

    }

    @Override
    public void finit() throws EOFVideoException {
        cam.setT(frame / 25.0 / 8);
        textureMov.nextFrame();
    }

    @Override
    public void ginit() {
        CourbeChoisie cc = new CourbeChoisie(11, 11, 21, 8);

        cam = new CameraInPath(cc);

        e = new TRICylindre(10, 20);
        textureMov = new TextureMov("C:\\Users\\Win\\Videos\\MOV0007A.AVI");
        textureMov.setTransparent(Color.BLACK);
        e.texture(textureMov);

        e.setMaxX(40);
        e.setMaxY(40);

        scene().add(e);

        scene().cameraActive(new Camera(new Point3D(30d, 0d, -30d), new Point3D(0d, 0d, 0d)));

        scene().cameraActive(cam);

    }

    @Override
    public void testScene() throws Exception {

    }

    public void afterRender() {

    }
}
