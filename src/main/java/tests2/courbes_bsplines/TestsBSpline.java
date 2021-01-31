/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests2.courbes_bsplines;

import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;

import java.awt.*;

/*__
 * Test BAD UGLY COMME TA MERE
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestsBSpline {
    public static double[] u(int N) {
        double[] u = new double[N];
        int cpt = 0;
        int cpt0 = cpt;
        for (int i = cpt0; i < N/3 + cpt0; i++) {
            u[i] = 1.0;

            cpt++;
        }
        cpt0 = cpt;
        for (int i = N/3; i < 2*N/3; i++) {
            u[i] = 1.0 * i / N;

            cpt++;
        }
        cpt0 = cpt;
        for (int i = 2*N/3; i < N; i++) {
            u[i] = 1.0;

            cpt++;
        }
        return u;
    }

    public static Point3D[] p(int N) {
        Point3D[] p = new Point3D[N];
        for (int i = 0; i < N; i++) {
            p[i] = new Point3D(1.0 * i * Math.cos((i / 6) * (Math.PI * 2.0) * ((double) i) / N),
                    1.0 * i * Math.sin((i / 6) * (Math.PI * 2.0) * ((double) i) / N), 0d);
            p[i].texture(new TextureCol(Color.WHITE));
        }
        return p;
    }

    public static Point3D[] p2(int N) {
        Point3D[] p = new Point3D[N];
        for (int i = 0; i < N; i++) {
            p[i] = new Point3D(1.0 * i * Math.cos((i / 6.0) * (Math.PI * 2.0) * ((double) i) / N),
                    1.0 * i * Math.sin((i / 6.0) * (Math.PI * 2.0) * ((double) i) / N), 0d);
            p[i].texture(new TextureCol(Color.WHITE));
        }
        return p;
    }
}

