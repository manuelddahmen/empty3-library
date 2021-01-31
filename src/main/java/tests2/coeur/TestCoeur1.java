/*__
 * *
 * Global license : * GNU GPL v3
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * Creation time 25-oct.-2014 SURFACE D'ÉLASTICITÉ DE FRESNEL Fresnel's
 * elasticity surface, Fresnelsche Elastizitätfläche
 * http://www.mathcurve.com/surfaces/elasticite/elasticite.shtml *
 */
package tests2.coeur;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

/*__
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestCoeur1 extends TestObjet {

    Coeur coeur;

    
    @Override
    public void ginit() {
        coeur = new Coeur();// new SurfaceElasticite(5, 3, 1);
        //try {
            coeur.texture(new TextureMov("resources/mov/VID_20200528_105353.mp4" ));
        //} catch (IOException ex) {
            //coeur.texture(new TextureCol(Color.PINK));
        //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
        //}

        coeur.setIncrV(.01);
        coeur.setIncrU(0.01);

        scene().add(coeur);

        scene().cameraActive(new Camera(Point3D.Z.mult(-100d), Point3D.O0));

    }

    @Override
    public void testScene() throws Exception {

    }

    @Override
    public void finit() {

    }

    @Override
    public void afterRenderFrame() {

        //coeur.drawStructureDrawFast(getZ());

    }

    public void afterRender() {

    }

}
