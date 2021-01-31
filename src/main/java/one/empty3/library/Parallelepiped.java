/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package one.empty3.library;/*
 * Copyright (c) 2017. Tous les fichiers dans ce programme sont soumis à la License Publique Générale GNU créée par la Free Softxware Association, Boston.
 * La plupart des licenses de parties tièrces sont compatibles avec la license principale.
 * Les parties tierces peuvent être soumises à d'autres licenses.
 * Montemedia : Creative Commons
 * ECT : Tests à valeur artistique ou technique.
 * La partie RayTacer a été honteusement copiée sur le Net. Puis traduite en Java et améliorée.
 * Java est une marque de la société Oracle.
 *
 * Pour le moment le programme est entièrement accessible sans frais supplémentaire. Get the sources, build it, use it, like it, share it.
 */

/*__
 * *
 * Global license : * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * *
 */

import one.empty3.library.Point3D;
import one.empty3.library.Polygon;
import one.empty3.library.RepresentableConteneur;
import one.empty3.library.TextureCol;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class Parallelepiped extends RepresentableConteneur {

    private double a = 1, b = 1, c = 1;

    public Parallelepiped(double a, double b, double c, TextureCol texture) {
        this.a = a;
        this.b = b;
        this.c = c;
        texture(texture);
        Point3D[] p = new Point3D[4];
        for (int x = -1; x <= 1; x++) {

            p[0] = new Point3D(x * a, -1 * b, -1 * c);
            p[1] = new Point3D(x * a, 1 * b, -1 * c);
            p[2] = new Point3D(x * a, 1 * b, 1 * c);
            p[3] = new Point3D(x * a, -1 * b, 1 * c);

            add(new Polygon(p, texture()));
        }
        for (int y = -1; y <= 1; y++) {
            p[0] = new Point3D(1 * a, y * b, 1 * c);
            p[1] = new Point3D(1 * a, y * b, -1 * c);
            p[2] = new Point3D(-1 * a, y * b, -1 * c);
            p[3] = new Point3D(-1 * a, y * b, 1 * c);

            add(new Polygon(p, texture()));
        }
        for (int z = -1; z <= 1; z++) {
            p[0] = new Point3D(-1 * a, -1 * b, z * c);
            p[1] = new Point3D(-1 * a, 1 * b, z * c);
            p[2] = new Point3D(1 * a, 1 * b, z * c);
            p[3] = new Point3D(1 * a, -1 * b, z * c);

            add(new Polygon(p, texture()));
        }
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
