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
package one.empty3.library;

public class Cube extends Representable implements TRIGenerable {

    /*__
     *
     */
    private static final long serialVersionUID = 3437509687221141764L;
    public static String DATA = null;
    private String id;
    private double mlc = 1.0;
    private Point3D position = new Point3D(0.0, 0.0, 0.0);
    private TRIObject ts = new TRIObject();
    private static Double[][][] coordCube = new Double[][][]{
            {
                    {1.0, -1.0, -1.0},
                    {1.0, 1.0, -1.0},
                    {1.0, 1.0, 1.0},},
            {
                    {1.0, -1.0, -1.0},
                    {1.0, -1.0, 1.0},
                    {1.0, 1.0, 1.0},},
            {
                    {-1.0, -1.0, -1.0},
                    {-1.0, 1.0, -1.0},
                    {-1.0, 1.0, 1.0},},
            {{-1.0, -1.0, -1.0},
                    {-1.0, -1.0, 1.0},
                    {-1.0, 1.0, 1.0},}, {{-1.0, 1.0, -1.0},
            {1.0, 1.0, -1.0},
            {1.0, 1.0, 1.0}
    }, {{-1.0, 1.0, -1.0},
            {-1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0},}, {{-1.0, -1.0, -1.0},
            {1.0, -1.0, -1.0},
            {1.0, -1.0, 1.0}}, {
            {-1.0, -1.0, -1.0},
            {-1.0, -1.0, 1.0},
            {1.0, -1.0, 1.0}
    }, {{-1.0, -1.0, -1.0},
            {-1.0, 1.0, -1.0},
            {1.0, 1.0, -1.0}
    }, {{-1.0, -1.0, -1.0},
            {1.0, -1.0, -1.0},
            {1.0, 1.0, -1.0}
    }, {{-1.0, -1.0, 1.0},
            {-1.0, 1.0, 1.0},
            {1.0, 1.0, 1.0}
    },
            {{-1.0, -1.0, 1.0},
                    {1.0, -1.0, 1.0},
                    {1.0, 1.0, 1.0}
            }
    };

    public Cube() {
    }

    public Cube(ITexture t) {
        texture(t);
    }

    public Cube(double mlc, Point3D position) {
        this.mlc = mlc;
        }

    public Cube(double mlc, Point3D position, ITexture t) {
        this.mlc = mlc;
        texture(t);
    }

    public TRIObject generate() {
        ts.clear();

        for (int i = 0; i < 12; i++) {
            TRI t = new TRI(
                    new Point3D(coordCube[i][0], texture()).mult(mlc).plus(position),
                    new Point3D(coordCube[i][1], texture()).mult(mlc).plus(position),
                    new Point3D(coordCube[i][2], texture()).mult(mlc).plus(position),
                    texture());

            ts.add(t);

        }
        return ts;
    }

    public String getId() {
        return id;
    }

    public double getMlc() {
        return mlc;
    }

    public void setMlc(double mlc) {
        this.mlc = mlc;
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supporteTexture() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "cube(\n\t" + position.toString() + "\n\t" + mlc + "\n)\n";
    }


    public static Double[][][] getData()
    {
        return coordCube;
    }
}
