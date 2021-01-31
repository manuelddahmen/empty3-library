package tests2.mammaires;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricSurface;

/*__
 * Created by manuel on 23-05-17.
 */
public class mammaires {
    static class Sein1 extends ParametricSurface {
        @Override
        public Point3D calculerPoint3D(double u, double v) {
            Point3D p = new Point3D(
                    Math.cos(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v),
                    v,
                    Math.sin(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v));
            p.texture(this.texture());
            return p;
        }

        @Override
        public Point3D calculerVitesse3D(double v, double v1) {
            return null;
        }
    }

    /*__
     * Spheric Ball
     */
    static class Sein2 extends ParametricSurface {
        @Override
        public Point3D calculerPoint3D(double u, double v) {
            Point3D p = new Point3D(
                    Math.cos(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v),
                    Math.cos(Math.PI * v),
                    Math.sin(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v));
            p.texture(this.texture());
            return p;
        }

        @Override
        public Point3D calculerVitesse3D(double v, double v1) {
            return null;
        }
    }

    /*__
     *
     */
    static class Sein3 extends ParametricSurface {
        @Override
        public Point3D calculerPoint3D(double u, double v) {
            Point3D p = new Point3D(
                    Math.cos(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v),
                    v * v,
                    Math.sin(Math.PI * 2 * u) * Math.sin(Math.PI + Math.PI * v));
            p.texture(this.texture());
            return p;
        }

        @Override
        public Point3D calculerVitesse3D(double v, double v1) {
            return null;
        }
    }

}
