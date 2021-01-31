/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests2.film_1;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjet;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;
import java.util.ArrayList;

;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class Scene1 extends TestObjet {
    private static final int NUMBRE = 50;
    private TRISphere s1;
    private TRISphere s2;
    private ArrayList<TRISphere> spheres = new ArrayList<TRISphere>();

    {
        description = "Matraque";
    }

    public static void main(String[] args) {
        Scene1 s = new Scene1();

        s.loop(true);
        s.setMaxFrames(6000);
        s.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);
        new Thread(s).start();
    }

    private double longiI(int i) {
        return (i * 1.0 / spheres.size()) + (1.0) * frame() / 25. / 10.0;
    }

    private double latI(int i) {
        return 0;
    }

    private double RI(int i) {
        return 1000;
    }

    private double longi2() {
        return -1.0 * frame() / 25. / 10.0;
    }

    private double longiC() {
        return 0;
    }

    private double lat1() {
        return 0.0;
    }

    private double lat2() {
        return 0.0;
    }

    private double latC() {
        return 1.0 * frame() / 25. / 20.0;
    }

    private double R1() {
        return 1000;
    }

    private double R2() {
        return 1000;
    }

    private double RC() {
        return 5000;
    }

    @Override
    public void afterRenderFrame() {
    }

    @Override
    public void finit() {
    }

    @Override
    public void ginit() {
        /*s1 = new TRISphere(Point3D.X.mult(1000), 100);
        s2 = new TRISphere(Point3D.X.mult(-1000), 100);

        s1.texture(new TextureCol(Color.BLACK));
        s2.texture(new TextureCol(Color.BLACK));

        scene().add(s1);
        scene().add(s2);
*/
        for (int i = 0; i < NUMBRE; i++) {
            s1 = new TRISphere(Point3D.X.mult(1000d), 100d);
            s1.texture(new TextureCol(Color.BLACK));
            spheres.add(s1);
            scene().add(s1);
        }

        scene().cameraActive(new Camera(Point3D.Z.mult(RC()), Point3D.O0));
    }

    @Override
    public void testScene() throws Exception {
        for (int i = 0; i < NUMBRE; i++) {
            s1 = spheres.get(i);
            s1.setCentre(Trajectoires.sphere(longiI(i), latI(i), RI(i)));
        }
        scene().cameraActive().setEye(Trajectoires.sphere(longiC(), latC(), RC()));
    }

    public void afterRender() {
    }
}
