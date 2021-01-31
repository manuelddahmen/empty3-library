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

package one.empty3.apps.pad;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DarkFortressGUI extends JFrame {
    private final Class clazz;
    protected PositionUpdate mover;
    Plotter3D plotter3D;
    private Drawer drawer;
    private Class drawerType;
    String Title;
    private one.empty3.apps.pad.DarkFortressGUIKeyListener gameKeyListener;
    private Game game;


    public Plotter3D getPlotter3D() {
        return plotter3D;
    }

    public DarkFortressGUI(Class clazz) {
        super();
        this.clazz = clazz;
        Title = "Dark Fortress ";
        setTitle(Title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setLevel(Class sol, Player player) {
        try {
            Terrain t;
            t = (Terrain) sol.newInstance();
            mover = new PositionUpdateImpl(t, player);
            //new Thread(mover).start();
            gameKeyListener= new DarkFortressGUIKeyListener(mover);
            plotter3D = new Plotter3D(mover);
            mover.setPlotter3D(plotter3D);
            new Thread(mover).start();
            new Thread(gameKeyListener).start();
            new Thread(plotter3D).start();


            this.drawerType = clazz;

            Logger.getLogger(DarkFortressGUI.class.getName()).log(Level.INFO, drawerType.getSimpleName());

            if (drawerType.equals(JoglDrawer.class)) {
                Title += "with OpenGL bindings";
                drawer = new JoglDrawer(this);

            } else if (drawerType.equals(EcDrawer.class)) {
                Title += "with Empty Canvas rendering";
                drawer = new EcDrawer(this);
            }


            drawer.setLogic(mover);
            drawer.setPlotter3D(plotter3D);
            drawer.setLevel(sol);

            addKeyListener(gameKeyListener);
            addKeyListener(plotter3D);

        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DarkFortressGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public DarkFortressGUIKeyListener getGameKeyListener() {
        return gameKeyListener;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
