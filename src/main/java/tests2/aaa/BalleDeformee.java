package tests2.aaa;

import atlasgen.TextureOpSphere;
import one.empty3.library.*;
import one.empty3.library.core.nurbs.CameraInPath;
import one.empty3.library.core.testing.TestObjetSub;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*__
 * Created by manue on 22-06-19.
 */
public class BalleDeformee extends TestObjetSub {
    private CameraInPath camera;
    private Balle balle;

    public static void main(String[] args) {
        BalleDeformee ba = new BalleDeformee();
        ba.setMaxFrames(25 * 60);
        ba.loop(true);
        new Thread(ba).start();
    }

    @Override
    public void finit() throws Exception {
        double t = 1.0 * (frame() % (25 * 4));
        camera.setT(t);

        Point3D z = Point3D.O0.moins(camera.getCourbe().calculerPoint3D(t)).norme1();
        Point3D x = camera.getCourbe().tangente(t).norme1().mult(-1d);
        Point3D y = x.prodVect(z).norme1();
        camera.setMatrix(x, y, z);
    }

    @Override
    public void ginit() {
        balle = new Balle(new Axe(Point3D.Y.mult(1d), Point3D.Y.mult(-1d)), 100);
        balle.setFormula(Balle.formula3);
        balle.setIncrU(0.001);
        balle.setIncrV(0.001);
        try {
            balle.texture(
                    new TextureOpSphere(
                            new TextureImg(
                                    new ECBufferedImage(
                                            ImageIO.read(
                                                    new File("samples/img/pink-watercolor-texture.jpg"))))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        scene().add(balle);
        camera = new CameraInPath(new Circle(
                new Axe(Point3D.O0.plus(Point3D.Y), Point3D.O0.moins(Point3D.Y)), 800 * 35));
        scene().cameraActive(camera);
    }

    public void testScene() throws Exception {
        balle.bounce();
    }


}
