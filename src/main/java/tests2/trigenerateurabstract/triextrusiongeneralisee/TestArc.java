/*__
 * *
 * Global license :  *
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * *
 */
package tests2.trigenerateurabstract.triextrusiongeneralisee;

import one.empty3.library.BezierCubique;
import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.CheminBezier;
import one.empty3.library.core.tribase.TRIExtrusionGeneralisee;

import java.awt.*;

/*__
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestArc extends TestObjetSub {

    private TRIExtrusionGeneralisee eg;

    public static void main(String[] args) {
        TestArc tp = new TestArc();
        tp.setGenerate(GENERATE_IMAGE | GENERATE_MODEL);
        tp.loop(false);
        new Thread(tp).start();
    }

    @Override
    public void ginit() {
        eg = new TRIExtrusionGeneralisee();
        //CheminDroite cd = new CheminDroite(new LineSegment(Point3D.X, Point3D.Y, new TextureCol(Color.WHITE)));
        BezierCubique bezierCubique = new BezierCubique();
        bezierCubique.add(Point3D.O0);
        bezierCubique.add(Point3D.X);
        bezierCubique.add(Point3D.X.plus(Point3D.Y));
        bezierCubique.add(Point3D.Y);
        CheminBezier cheminBezier = new CheminBezier(bezierCubique);

        //eg.setChemin(cheminBezier/*new CheminDroite(new LineSegment(Point3D.O0, Point3D.Y.mult(5)))*/);

        //eg.setSurface(new SurfaceCercle(2));

        eg.setMaxX(100);

        eg.setMaxY(100);

        eg.texture(new TextureCol(Color.WHITE));

        this.description = "Cylindre ";
    }

    @Override
    public void testScene() throws Exception {
        scene().clear();
        scene().add(eg);

        scene().cameraActive(new Camera(Point3D.Z.mult(-10d), Point3D.O0));
    }

    @Override
    public void finit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
