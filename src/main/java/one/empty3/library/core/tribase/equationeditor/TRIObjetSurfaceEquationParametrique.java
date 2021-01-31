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
 * Global license :  *
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * *
 */
package one.empty3.library.core.tribase.equationeditor;

import one.empty3.library.Point3D;
import one.empty3.library.core.tribase.TRIObjetGenerateurAbstract;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TRIObjetSurfaceEquationParametrique
        extends TRIObjetGenerateurAbstract {

    AnalyseurEquationJep sx;
    AnalyseurEquationJep sy;
    AnalyseurEquationJep sz;

    /*__
     * *
     *
     * @param sx variable : (u,v)
     * @param sy variable : (u,v)
     * @param sz variable : (u,v)
     */
    public TRIObjetSurfaceEquationParametrique(AnalyseurEquationJep sx, AnalyseurEquationJep sy, AnalyseurEquationJep sz) {
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;

        System.out.println(" ( " + sx + " , " + sy + " , " + sz + " ) ");
    }

    public TRIObjetSurfaceEquationParametrique() {
    }

    public Point3D value(double u, double v) {
        sx.setVariable("u", u);
        sy.setVariable("u", u);
        sz.setVariable("u", u);
        sx.setVariable("v", v);
        sy.setVariable("v", v);
        sz.setVariable("v", v);
        return new Point3D(sx.value(), sy.value(), sz.value());
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        return value(1.0 * x / getMaxX(), 1.0 * y / getMaxY());
    }

}
