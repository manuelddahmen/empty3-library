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

package one.empty3.library.objloader;

import one.empty3.apps.pad.DarkFortressGUI;
import one.empty3.apps.pad.EcDrawer;
import one.empty3.library.*;

import javax.swing.*;
import java.awt.*;

/*__
 * Created by manue on 02-06-19.
 */
public class E3Example extends EcDrawer {
    private E3Model chairModel;
    private Scene s;

    public E3Example(DarkFortressGUI darkFortress) {
        super(darkFortress);
        if(!loadModels())
            System.exit(1);

    }

    @Override
    public void dessiner() {
        Graphics g = component.getGraphics();

        //z.couleurDeFond(new TColor(Color.BLACK));
        if (g != null && component.getWidth() > 0 && component.getHeight() > 0) {

            s = new Scene();
            z.scene(s);
            if(chairModel!=null) {
                s.add(chairModel);
                s.cameraActive(new Camera
                        (Point3D.Z.mult(100d), Point3D.Z.mult(0d)));
                System.out.println("ok");
            }
            else return;
            Camera camera = new Camera(new Point3D(Point3D.Z.mult(100d)),
                    Point3D.O0);
            camera.calculerMatrice(Point3D.Y);
            s.cameraActive(camera);
            try {
                z.draw(s);
            } catch (Exception ex) {
                System.err.println("Ex");
            }
            ECBufferedImage ri = z.image();

            g.drawImage(ri, 0, 0, component.getWidth(), component.getHeight(), null);

            z.next();
        }

    }


    private Boolean loadModels() {
        chairModel = ModelLoaderOBJ.LoadModelE3("resources/models/c.obj",
                "resources/models/c.mtl");
        if (chairModel == null) {
            System.err.print("Model not loaded");
            return false;
        }
        //System.out.println(chairModel.toString());
        return true;
    }

    public static void main(String[] args) {
        DarkFortressGUI darkFortressGUI = new DarkFortressGUI(E3Example.class);
        darkFortressGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        darkFortressGUI.setVisible(true);
        new E3Example(darkFortressGUI);


    }
}
