package tests2.triangles;

import one.empty3.library.Point3D;
import one.empty3.library.RepresentableConteneur;
import one.empty3.library.TRI;

import java.util.ArrayList;
import java.util.Iterator;

public class SiPiKi3D extends RepresentableConteneur {
    /*__
     *
     */
    private static final long serialVersionUID = 1L;
    private final int nRecursion;

    public SiPiKi3D(int nRecursion) {
        super();
        this.nRecursion = nRecursion;
    }

    /*__
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }





    public void add(TRI t0, int nRecursions) {

        ArrayList<TRI>[] t = new ArrayList[2];

        t[0] = new ArrayList<TRI>();

        t[0].add(t0);


        for (int i = 0; i < nRecursions; i++) {
            t[1] = new ArrayList<TRI>();

            for (int j = 0; j < t[0].size(); j++) {
                TRI tt = t[0].get(j);
                for (int k = 0; k < 3; k++) {
                    Point3D p0 = tt.getSommet().getElem(k);
                    Point3D p1 = tt.getSommet().getElem((3 + k - 1) % 3).plus(tt.getSommet().getElem(k)).mult(0.5);
                    Point3D p2 = tt.getSommet().getElem((3 + k + 1) % 3).plus(tt.getSommet().getElem(k)).mult(0.5);
                    t[1].add(new TRI(p0, p1, p2, tt.texture()));
                }
            }


            t[0] = t[1];
        }

        Iterator<TRI> it = t[0].iterator();
        while (it.hasNext()) {
            TRI tt = it.next();
            super.add(tt);
        }
    }


}
