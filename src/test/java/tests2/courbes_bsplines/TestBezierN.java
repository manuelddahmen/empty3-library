/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests2.courbes_bsplines;

import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomialeBezier;
import one.empty3.library.core.testing.TestObjet;

import java.awt.*;

/*__
 * Test OK
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestBezierN extends TestObjet {
    private CourbeParametriquePolynomialeBezier b;

    public static void main(String[] args) {
        TestBezierN ts = new TestBezierN();

        ts.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        ts.loop(false);

        ts.setMaxFrames(10);

        new Thread(ts).start();


    }

    @Override
    public void afterRenderFrame() {
    }

    @Override
    public void finit() {
        scene().clear();

        b = new CourbeParametriquePolynomialeBezier(TestsBSpline.p(frame()));

        b.texture(new TextureCol(Color.WHITE));


        scene().add(b);

        scene.cameraActive().setEye(Point3D.Z.mult(-(2d * frame() + 2d)));

    }

    @Override
    public void ginit() {
    }

    @Override
    public void testScene() throws Exception {
    }

    public void afterRender() {
    }
}
