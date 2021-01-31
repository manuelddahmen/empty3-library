/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>

 Creation time 25-oct.-2014
 ***/


package tests2.coeur;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricSurface;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Coeur extends ParametricSurface {
    private double b;

    public void param01(double b) {
        this.b = b;
    }
    public Coeur() {
        
    }
    @Override
    public Point3D calculerPoint3D(double x, double y) {
        double a = x;
        double t = y * 2 * Math.PI;
        return new Point3D(a * Math.sin(t + b * 2 * Math.PI) * (1 + Math.cos(t)), a * Math.cos(t + b * Math.PI * 2) * (1 + Math.cos(t)), 0d);
    }

}
