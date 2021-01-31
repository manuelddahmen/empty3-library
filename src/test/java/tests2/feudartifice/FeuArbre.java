/*__
 * *
 * Global license : * CC Attribution
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package tests2.feudartifice;

import one.empty3.library.*;
import one.empty3.library.core.move.Trajectoires;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class FeuArbre {

    public int NUM = 10;
    public int PROF = 5;
    Noeud racine;
    private int prof = 0;

    public FeuArbre() {
        racine = new Noeud();
        racine.setLocation(Point3D.O0);

    }

    public RepresentableConteneur generate() {
        RepresentableConteneur rc = new RepresentableConteneur();

        generate(racine, rc);

        return rc;
    }

    public void generate(final Noeud n, final RepresentableConteneur rc) {
        ITexture color;
        color = new TextureCol(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        n.addBranches((int) (Math.random() * NUM));
        Iterator<Noeud> iterator = n.iterator();
        while (iterator.hasNext()) {
            Noeud no = iterator.next();
            prof++;
            if (prof < PROF) {
                rc.add(new LineSegment(n.getLocation(), no.getLocation(), color));
                generate(no, rc);
            }
            prof--;
        }

    }

    public class Noeud extends ArrayList<Noeud> {

        private Point3D loc;

        public Point3D getLocation() {
            return loc;
        }

        public void setLocation(Point3D p) {
            loc = p;
        }

        public void addBranches(int n) {
            for (int i = 0; i < n; i++) {
                Point3D sphere = Trajectoires.sphere(Math.random(), Math.random(), 4);
                Noeud noeud = new Noeud();
                noeud.setLocation(getLocation().plus(sphere));
                add(noeud);

            }
        }
    }

}
