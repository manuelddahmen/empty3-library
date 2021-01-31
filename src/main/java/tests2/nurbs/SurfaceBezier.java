/*__
 * *
 * Global license :  *
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * Creation time 17-sept.-2014
 * <p>
 * *
 */
package tests2.nurbs;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.SurfaceParametricPolygonalBezier;
import one.empty3.library.core.testing.TestObjetSub;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SurfaceBezier extends TestObjetSub {

    public static void main(String[] arg) {
        SurfaceBezier t1 = new SurfaceBezier();
        t1.loop(false);
        new Thread(t1).start();
    }

    @Override
    public void testScene() {
        Point3D[][] p = new Point3D[9][9];
        int m = 0;
        for (int i = -4; i <= 4; i++) {
            int n = 0;
            for (int j = -4; j <= 4; j++) {
                p[m][n] = new Point3D((double) i, (double) j, 0d);
                n++;
            }
            m++;
        }
        SurfaceParametricPolygonalBezier surfaceParametricPolygonalBezier = new SurfaceParametricPolygonalBezier(p);
        boolean add = scene().add(surfaceParametricPolygonalBezier);
        scene().cameraActive(new Camera(Point3D.Z.mult(-10d), Point3D.O0));

    }

    @Override
    public void finit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ginit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
