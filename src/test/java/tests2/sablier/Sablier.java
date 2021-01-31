package tests2.sablier;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricSurface;

/*__
 * Created by manue on 01-11-15.
 */

public class Sablier extends ParametricSurface {

    protected double NFAST = 100;

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D p = new Point3D(
                Math.cos(Math.PI * 2 * u) * Math.sin(Math.PI / 2 + Math.PI * v),
                v,
                Math.sin(Math.PI * 2 * u) * Math.sin(Math.PI / 2 + Math.PI * v));
        p.texture(this.texture());
        return p;
    }

    @Override
    public Point3D calculerVitesse3D(double v, double v1) {
        return null;
    }


}
