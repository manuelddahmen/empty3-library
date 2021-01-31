package tests2.sw;

import one.empty3.library.ECBufferedImage;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.TextureImg;
import one.empty3.library.core.tribase.Plan3D;

import java.awt.*;
import java.awt.image.BufferedImage;

/*__
 * Created by Win on 16-01-16.
 */
public class TextObjet extends Plan3D {
    private Point3D orig;
    private Point3D x2Vect;
    private Point3D y2Vect;

    private Color texTextureCol;


    private ECBufferedImage prerenderedImg;
    private String textString;


    public TextObjet(Point3D orig, Point3D x2Vect, Point3D y2Vect) {
        this.pointOrigine(orig);
        this.pointXExtremite(orig.plus(x2Vect));
        this.pointYExtremite(orig.plus(y2Vect));

        TextureCol c = new TextureCol(Color.BLACK);
        texture(c);


    }

    public Color texTextureCol() {
        return texTextureCol;
    }

    public void setTexTextureCol(Color color) {
        this.texTextureCol = color;
    }

    public void setText(String txt) {
        this.textString = txt;
        prerenderedImg = new ECBufferedImage(new BufferedImage(1920, 1080 / 5 * textString.length(), BufferedImage.TYPE_INT_ARGB));

        Graphics prerenderedImgGraphics = prerenderedImg.getGraphics();

        prerenderedImgGraphics.setColor(texTextureCol);

        prerenderedImgGraphics.drawString(txt, 0, 0);

        texture(new TextureImg(prerenderedImg));

    }


    public void deplace(Point3D point3D) {
        this.pointOrigine(this.pointOrigine().plus(point3D.getX()));
        this.pointXExtremite(this.pointXExtremite().plus(point3D.getY()));
        this.pointYExtremite(this.pointYExtremite().plus(point3D.getY()));
    }
}
