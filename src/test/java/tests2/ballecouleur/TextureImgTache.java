/*__
 * *
 * Global license : * CC Attribution
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package tests2.ballecouleur;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;
import java.util.HashMap;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TextureImgTache extends TextureImg {

    private final HashMap<Point2D, Color> map;
    double dist = 0.0;
    Color actu = null;
    float actuA;
    float actuR;
    float actuG;
    float actuB;

    public TextureImgTache(HashMap<Point2D, Color> colors) {
        super(new ECBufferedImage(100, 100, ECBufferedImage.TYPE_INT_ARGB));
        map = colors;
    }

    @Override
    public int getColorAt(double x, double y) {
        return calculerCouleur(x, y).getRGB();
    }


    public Color calculerCouleur(double x, double y) {
        final Point2D pData = new Point2D(x, y);
        actu = new Color(0f, 0f, 0f);

        final HashMap<Point2D, Double> pond;
        pond = new HashMap<Point2D, Double>();

        map.forEach((u, t) -> {
                    double dist2 = u.distance(pData);

                    pond.put(u, dist2);

                    dist += dist2;
                }
        );

        pond.forEach((t, u) -> {
            actuA += map.get(t).getAlpha() / 256f * u;
            actuR += map.get(t).getRed() / 256f * u;
            actuG += map.get(t).getGreen() / 256f * u;
            actuB += map.get(t).getBlue() / 256f * u;

            actu = new Color((float) (actuR / dist), (float) (actuG / dist), (float) (actuB / dist), (float) (actuA / dist));
        });

        return actu;
    }

}
