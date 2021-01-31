/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>

 Creation time 05-nov.-2014
 ***/


package tests2.getobjectat;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TRI;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;

/*__
 * Future: this should be.manudahmen.empty3.tests2.be very low level: like :
 * Representable.setSelectionZoneId(long szid);
 * ZBuffer.getSelectionZoneId(int coordArr, int y);
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestGetObjectAt extends TestObjetSub {

    @Override
    public void testScene() throws Exception {
        scene().getObjets().getData1d().clear();
        scene().add(new TRI(Point3D.O0, Point3D.X, Point3D.Y, new TextureCol(Color.BLUE)));
        scene().cameraActive(new Camera(Point3D.Z.mult(-1d), Point3D.O0));

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
