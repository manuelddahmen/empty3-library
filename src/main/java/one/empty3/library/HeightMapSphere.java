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

package one.empty3.library;

import atlasgen.TextureOpSphere;

import java.awt.image.BufferedImage;

/*__
 * Created by manue on 22-06-19.
 */
public class HeightMapSphere extends Sphere implements HeightMapSurface {
    private double axis;
    private ITexture heightMap;
    private double radius;

    public HeightMapSphere(Axe axe, double radius) {
        super(axe, radius);
    }

    public void setHeightMap(BufferedImage bufferedImage) {
        if (bufferedImage != null)
            this.heightMap = new TextureOpSphere(new TextureImg(new ECBufferedImage(bufferedImage)));
        else
            this.heightMap = new TextureOpSphere(new TextureImg(new ECBufferedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB))));


    }

    public double height(double u, double v) {
        return radius + heightMap.getColorAt(u, v);
    }
}

