package tests2.tihange_reac_cendar;

import one.empty3.library.ITexture;
import one.empty3.library.Point3D;
import one.empty3.library.RepresentableConteneur;
import one.empty3.library.TextureCol;
import one.empty3.library.core.nurbs.ParametricSurface;

import java.awt.*;

/*__
 * Created by Win on 22-12-18.
 */
public class Tihange extends RepresentableConteneur {

    public Tihange() {

        clear();

        ITexture texture1 = new TextureCol(Color.RED);
        ITexture texture2 = new TextureCol(Color.GREEN);
        ITexture texture3 = new TextureCol(Color.BLUE);
        ITexture texture4 = new TextureCol(Color.MAGENTA);
        ITexture texture5 = new TextureCol(Color.CYAN);
        ITexture texture6 = new TextureCol(Color.YELLOW);


        // Paroi extérieure haut
        ParametricSurface surface = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
                texture(texture1);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                double largeur = 8 + (v - 0.5) * (v - 0.5) * 4;
                double z = (v - 0.5) * 2;
                double coordLarge = 2 * Math.PI * u;
                return new Point3D(Math.cos(coordLarge) * largeur, Math.sin(coordLarge) * largeur,
                        z);
            }
        };
        // Paroi intérieur haut
        ParametricSurface surface1 = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
                texture(texture2);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                double largeur = 6;
                double z = (v - 0.5) * 2;
                double coordLarge = 2 * Math.PI * u;
                return new Point3D(Math.cos(coordLarge) * largeur, Math.sin(coordLarge) * largeur,
                        z);
            }
        };
        // Surface sommet haut
        ParametricSurface surface2 = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
                texture(texture3);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                double coordLarge = 2 * Math.PI * u;
                double largeur = v + 6;
                double hauteur = 1.0;
                return new Point3D(largeur * Math.cos(coordLarge),
                        largeur * Math.sin(coordLarge), hauteur);
            }
        };
        // Surface intérieur bas horizontale
        ParametricSurface surface3 = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
                texture(texture4);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                double largeur = v * 9;
                double coordLarge = 2 * Math.PI * u;
                return new Point3D(Math.cos(coordLarge) * largeur,
                        largeur * Math.sin(coordLarge), -1d);
            }
        };
        // Paroi extérieure bas horizontale
        ParametricSurface surface4 = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
                texture(texture5);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                double coordLarge = 2 * Math.PI * u;
                double largeur = 9 * v;
                double z = -1.2;
                return new Point3D(Math.cos(coordLarge) *
                        largeur, Math.sin(coordLarge) * largeur, z);
            }
        };

        // Paroi extérieure bas méridionale
        ParametricSurface surface5 = new ParametricSurface() {
            {
                setIncrU(0.01);
                setIncrV(0.01);
                setCirculaireX(true);
                setCirculaireY(false);
            }

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                texture(texture6);
                double largeur = 9;
                double coordLarge = 2 * Math.PI * u;
                double z = -v / 5 - 1.0;
                return new Point3D(
                        largeur * Math.cos(coordLarge),
                        largeur * Math.sin(coordLarge), z);
            }
        };
        add(surface);
        add(surface1);
        add(surface2);
        add(surface3);
        add(surface4);
        add(surface5);
    }

}
