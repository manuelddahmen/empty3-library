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

/*__
 * *
 * Global license : * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * Creation time 17-sept.-2014
 * <p>
 * *
 */
package one.empty3.library.core.nurbs;

import one.empty3.library.Point3D;
import one.empty3.library.StructureMatrix;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class SurfaceParametriquePolynomialeBezier extends ParametricSurface implements SurfaceElem{

    protected StructureMatrix<Point3D> coefficients = new StructureMatrix<>(2, Point3D.class);;
    protected StructureMatrix<Integer> power1 =new StructureMatrix<>(0, Integer.class);
    protected StructureMatrix<Integer> power2=new StructureMatrix<>(0, Integer.class);;


    public SurfaceParametriquePolynomialeBezier(Point3D[][] coefficients)
    {
        this();
        this.coefficients.setAll(coefficients);
        power1.setElem(coefficients.length);
        power2.setElem(coefficients[0].length);
    }
    public SurfaceParametriquePolynomialeBezier()
    {
        this.coefficients = new StructureMatrix<>(2, Point3D.class);
        this.coefficients.setAll(
        new Point3D[][]
                {

                        {Point3D.n(2d, -2d, 0d), Point3D.n(2, -1, 0), Point3D.n(2, 0, 0), Point3D.n(2, 1, 0), Point3D.n(2, 2, 0)},
                        {Point3D.n(1d, -2d, 0d), Point3D.n(1d, -1d, 0d), Point3D.n(1, 0, 0), Point3D.n(1, 1, 0), Point3D.n(1, 2, 0)},
                        {Point3D.n(0d, -2d, 0d), Point3D.n(0d, -1d, 0d), Point3D.n(0, 0, 0), Point3D.n(0, 1, 0), Point3D.n(0, 2, 0)},
                        {Point3D.n(-1d, -2d, 0d), Point3D.n(-1d, -1d, 0d), Point3D.n(-1, 0, 0), Point3D.n(-1, 1, 0), Point3D.n(-1, 2, 0)},
                        {Point3D.n(-2d, -2d, 0d), Point3D.n(-2d, -1d, 0d), Point3D.n(-2, 0, 0), Point3D.n(-2, 1, 0), Point3D.n(-2, 2, 0)}

                });
        power1.setElem(coefficients.getData2d().size());
        power2.setElem(coefficients.getData2d().get(0).size());
    }

    public double B(int i, int n, double t) {
        return factorielle(n) / factorielle(i) / factorielle(n - i)
                * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D sum = Point3D.O0;
        for (int i = 0; i <getPower1(); i++) {
            for (int j = 0; j < getPower2(); j++) {
                sum = sum.plus(coefficients.getElem(i,j).mult(B(i, power1.getElem() - 1, u) * B(j, power2.getElem() - 1, v)));
            }
        }
        return sum;
    }


    protected double factorielle(int n) {
        double sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }

    public StructureMatrix<Point3D> getCoefficients() {
        return coefficients;
    }

    @Override
    public void declareProperties() {
        super.declareProperties();
        getDeclaredDataStructure().put("coefficients/points de contrôle", coefficients);
        getDeclaredDataStructure().put("power1/puissance par defaut #dim1", power1);
        getDeclaredDataStructure().put("power2/puissance par defaut #dim2", power2);

    }

    public void setCoefficients(StructureMatrix<Point3D> coefficients) {
        this.coefficients = coefficients;
    }

    public Integer getPower1() {
        return coefficients.getData2d().size();
    }

    public void setPower1(Integer power1) {
        this.power1 = new StructureMatrix<>(power1, Integer.class);
    }

    public Integer getPower2() {
        return coefficients.getData2d().get(0).size();
    }

    public void setPower2(Integer power2) {
        this.power2 = new StructureMatrix<>(power2, Integer.class);
    }

    @Override
    public String toString() {
        String s = "bezier2(";

        s += "controls : "+coefficients.toString();
        s += "power1 : "+power1.toString();
        s += "power2 : "+power2.toString();

        s+=")";
        return s;
    }
}
