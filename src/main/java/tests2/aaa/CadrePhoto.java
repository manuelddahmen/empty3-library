package tests2.aaa;

import one.empty3.library.*;
import one.empty3.library.core.nurbs.CameraInPath;
import one.empty3.library.core.testing.TestObjetSub;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*__
 * Created by manue on 22-06-19.
 */
public class CadrePhoto extends TestObjetSub {
    private CameraInPath camera;
    private Balle balle;

    public static void main(String[] args) {
        CadrePhoto especeDeMammiferes = new CadrePhoto();
        especeDeMammiferes.setMaxFrames(10000);
        especeDeMammiferes.loop(true);
        new Thread(especeDeMammiferes).start();
    }

    @Override
    public void finit() throws Exception {
        camera = new CameraInPath(new Circle(
                new Axe(Point3D.O0.plus(Point3D.X), Point3D.O0.moins(Point3D.X)), 800 * 4));
        scene().add(camera);
        double t = 0;
        camera.setT(t);

        Point3D z = Point3D.O0.moins(camera.getCourbe().calculerPoint3D(t)).norme1();
        Point3D x = camera.getCourbe().tangente(t).norme1().mult(-1d);
        Point3D y = x.prodVect(z).norme1();
        camera.setMatrix(x, y, z);
    }

    @Override
    public void ginit() {
        balle = new Balle(new Axe(Point3D.X.mult(1d), Point3D.X.mult(-1d)), 100d);
        try {
            balle.texture(new TextureImg(
                    new ECBufferedImage(
                            ImageIO.read(
                                    new File("samples/img/herbe.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene().add(balle);
    }

    public void testScene() throws Exception {
        balle.bounce();
    }


}
