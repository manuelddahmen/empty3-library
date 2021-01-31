package tests2.texturevideo;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureMov;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.Plan3D;

import java.io.File;

/*__
 * @author Dahmen Manuel
 */
public class PlanVideo extends TestObjetSub {
    
    private String avi1;
    private String avi2;
    private String avi3;
    private String avi4;

    public void ginit() {
        String f1;
        String f2;
        String f3;
        String f4;
        /*
        if (args.length >= 4) {
            f1 = args[0];
            f2 = args[1];
            f3 = args[2];
            f4 = args[3];

        } else {*/

            f1 = "resources/mov/VID_20191013_155351.mp4";
            f2 = f1;
            f3 = f2;
            f4 = f3;
       // }

        avi1 = f1;
        avi2 = f2;
        avi3 = f3;
        avi4 = f4;
       
        
        if (new File(f1).isFile() && new File(f2).isFile() && new File(f3).isFile() && new File(f4).isFile()) {

          

            loop(true);

            

        } else {

            System.err.println("Erreur un fichier ou l'autre n'existe pas");

            
        }
        
    

    
        TextureMov tc1, tc2, tc3, tc4;

        tc1 = new TextureMov(avi1);
        tc2 = new TextureMov(avi1);
        tc3 = new TextureMov(avi1);
        tc4 = new TextureMov(avi1);

        Plan3D p1, p2, p3, p4;

        p1 = new Plan3D();
        p1.texture(tc1);
        p1.texture(tc1);
        p1.pointOrigine(Point3D.O0);
        p1.pointXExtremite(Point3D.X);
        p1.pointYExtremite(Point3D.Y);

        p2 = new Plan3D();
        p2.texture(tc2);
        p2.pointOrigine(Point3D.O0);
        p2.pointXExtremite(Point3D.X.mult(-1d));
        p2.pointYExtremite(Point3D.Y);

        p3 = new Plan3D();
        p3.texture(tc3);
        p3.pointOrigine(Point3D.O0);
        p3.pointXExtremite(Point3D.X);
        p3.pointYExtremite(Point3D.Y.mult(-1d));

        p4 = new Plan3D();
        p4.texture(tc4);
        p4.pointOrigine(Point3D.O0);
        p4.pointXExtremite(Point3D.X.mult(-1d));
        p4.pointYExtremite(Point3D.Y.mult(-1d));
        p1.setIncrU(0.01);
        p1.setIncrV(0.01);
        p2.setIncrU(0.01);
        p2.setIncrV(0.01);
        p3.setIncrU(0.01);
        p3.setIncrV(0.01); 
        p4.setIncrU(0.01);
        p4.setIncrV(0.01);
        scene().cameraActive(
                new Camera(
                        new Point3D(0d, 0d, -1.2d),
                        Point3D.O0
                )
        );


        scene().add(p1);
        scene().add(p2);
        scene().add(p3);
        scene().add(p4);


    }
}
