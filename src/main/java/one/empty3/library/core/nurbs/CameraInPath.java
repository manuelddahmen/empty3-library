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

package one.empty3.library.core.nurbs;

import one.empty3.library.Camera;
import one.empty3.library.Matrix33;
import one.empty3.library.Point3D;
import one.empty3.library.StructureMatrix;

/*__
 * @author Manuel Dahmen
 */
public class CameraInPath extends Camera {
    private double angleA = 0;
    private double angleB = 0;

    /*__
     * Need to get a position of the camera since it's a Z-axis
     * Rotate, position angles around the camera.
     */
    public void rotateSphere(double angleARad, double angleBRad) {
        this.angleA = angleARad;
        this.angleB = angleBRad;
    }

    private StructureMatrix<ParametricCurve> courbe = new StructureMatrix<>(0, ParametricCurve.class);
    private StructureMatrix <Double> t;

    public CameraInPath(ParametricCurve maCourbe) {
        this.t = new StructureMatrix (0, Double.class) ;
        t.setElem(0.0) ;
        this.courbe.setElem(maCourbe);
    }

    public ParametricCurve getCourbe() {
        return courbe.getElem();
    }

    public void setCourbe(ParametricCurve maCourbe) {
        this.courbe.setElem(maCourbe);
    }


    public void calculerMatriceT(Point3D verticale) {
double t = this. t. getElem() ;

        setEye(courbe.getElem().calculerPoint3D(t));
        setLookat(courbe.getElem().calculerPoint3D(t+ t* 1.001));
        Point3D dt1 = getLookat().moins(getEye()).norme1();
        Point3D dt2 = getEye().moins(courbe.getElem().calculerPoint3D(t - t * 0.001)).norme1();
        Point3D n = dt2.moins(dt1).norme1();
        super.calculerMatrice(verticale == null ? n : verticale);
    }

    @Override
    public Point3D calculerCurveT( double u, double t) {
        this.t.setElem((Double) t);
        calculerMatrice(null);
        return courbe.getElem().calculerPoint3D((Double) t) ;
    }
    @Override
    public Point3D calculerPointDansRepere(Point3D p) {
        return Matrix33.rot(angleA, angleB).mult(super.calculerPointDansRepere(p));
    }

    @Override
    public void declareProperties() {
        super.declareProperties();
        getDeclaredDataStructure().put("courbe", courbe);

    }

    public void setCourbe(StructureMatrix<ParametricCurve> courbe) {
        this.courbe = courbe;
    }

    public void setT(double t) {
        this.t.setElem(t);
    }
}
