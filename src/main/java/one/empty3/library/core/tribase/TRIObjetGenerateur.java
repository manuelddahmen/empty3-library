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

/*

 Vous êtes libre de :

 */
package one.empty3.library.core.tribase;

import one.empty3.library.Point3D;
import one.empty3.library.TRI;

/*__
 * Implémentations requises: TRIGenerable, TourDeRevolution, Tubulaire, Spheres
 *
 * @author manuel
 */
public interface TRIObjetGenerateur {

    Point3D coordPoint3D(int x, int y);

    boolean getCirculaireX();

    void setCirculaireX(boolean cx);

    boolean getCirculaireY();

    void setCirculaireY(boolean cy);

    int getMaxX();

    void setMaxX(int maxX);

    int getMaxY();

    void setMaxY(int maxX);

    Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY);


    void getTris(int numX, int numY, TRI[] tris);

}
