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
 * *
 */
package one.empty3.library;

import java.awt.*;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public final class LumierePonctuelle extends Lumiere {

    private StructureMatrix<ITexture> couleurLumiere = new StructureMatrix<>(0, ITexture.class);
    private StructureMatrix<Point3D> position = new StructureMatrix<>(0, Point3D.class);
    private double r0 = 11;
    private StructureMatrix<Boolean> directional = new StructureMatrix<>(0, Boolean.class);

    public LumierePonctuelle() {
        position.setElem(Point3D.O0);
        this.couleurLumiere.setElem(new ColorTexture(Color.WHITE));
        directional.setElem(Boolean.FALSE);
    }
    public LumierePonctuelle(Point3D pos, Color couleurLumiere) {
        this.position .setElem(pos);
        this.couleurLumiere.setElem(new ColorTexture(couleurLumiere));
    }

    @Override
    public int getCouleur(int base, Point3D p, Point3D n) {
        double x = p.moins(position.getElem()).norme();
        double r = 0.0;
        if (directional.getElem()) {
            r = 1 - 1 / (directional.getElem() ? 1.0 : x) * r0
                    / (Math.acos(Math.abs(position.getElem().prodScalaire(n)) / position.getElem().norme() / n.norme() / Math.PI * 2));
        } else {
            r = 1 - 1 / (directional.getElem() ? 1.0 : x) * r0
                    / (Math.acos(Math.abs(position.getElem().moins(p).prodScalaire(n)) / position.getElem().moins(p).norme() / n.norme() / Math.PI * 2));
        }
        if (r < 0) {
            r = 0;
        }
        if (r > 1) {
            r = 1.0;
        }

        Color couleurObjet = new Color(base);
        Color color = new Color(couleurLumiere.getElem().getColorAt(0, 0));
        return new Color(
                (float) ((couleurObjet.getRed() / 256.0) * r + (color.getRed() / 256.0) * (1 - r)),
                (float) ((couleurObjet.getGreen() / 256.0) * r + (color.getGreen() / 256.0) * (1 - r)),
                (float) ((couleurObjet.getBlue() / 256.0) * r + (color.getBlue() / 256.0) * (1 - r))).getRGB();
    }

    public void intensite(int r0) {
        this.r0 = r0;
    }

    public StructureMatrix<Boolean> getDirectional() {
        return directional;
    }

    public void setDirectional(StructureMatrix<Boolean> directional) {
        this.directional = directional;
    }

    public void setR0(double r0) {
        this.r0 = r0;
    }


    public void declareProperties()
    {
        getDeclaredDataStructure().put("position/Position de la provenace lumineuse", position);
        getDeclaredDataStructure().put("color/Couleur de la lumière", couleurLumiere);
        getDeclaredDataStructure().put("directinal/isDirectional rayons parallèle et sphèrique", directional);
    }
}
