/*__
 Global license :

 CC Attribution

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.nurbs;

import one.empty3.library.LineSegment;
import one.empty3.library.Point3D;
import one.empty3.library.RepresentableConteneur;
import one.empty3.library.TextureCol;

import java.awt.*;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
class Axes extends RepresentableConteneur {
    public Axes(TextureCol a1, TextureCol a2, TextureCol a3) {
        add(new LineSegment(Point3D.O0, Point3D.X, a1));
        add(new LineSegment(Point3D.O0, Point3D.Y, a2));
        add(new LineSegment(Point3D.O0, Point3D.Z, a3));

    }

    public Axes() {
        this(new TextureCol(Color.RED), new TextureCol(Color.GREEN), new TextureCol(Color.BLUE));
    }
}
