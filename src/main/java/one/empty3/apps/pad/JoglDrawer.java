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

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import one.empty3.apps.pad.help.PiloteAuto;
import one.empty3.library.*;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomiale;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomialeBezier;
import one.empty3.library.core.nurbs.ParametricCurve;
import one.empty3.library.core.tribase.TRIObjetGenerateur;
import one.empty3.library.core.tribase.TubulaireN2;

import javax.swing.*;
import java.awt.*;
import java.nio.IntBuffer;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class JoglDrawer extends Drawer implements GLEventListener {
    private final GLU glu;
    private final Object component;
    private final GLCanvas glcanvas;
    double INCR_AA = 0.01;
    double DISTANCE_MIN = 100;
    Timer timer;
    TubulaireN2<Lines> path;
    private double maximize = INCR_AA / 10;
    private double minimize = INCR_AA;
    private PositionUpdate mover;
    private Terrain terrain;
    private Bonus bonus;
    private TextRenderer renderer;
    private Vaisseau vaisseau;
    private boolean locked;
    private Circuit circuit;
    private int BUFSIZE;
    private Point2D pickPoint;
    private PiloteAuto piloteAuto;
    private Point3D del;
    private Point3D diff;

    {
        Plasma.scale = 2;
        Plasma.t_factor = 0.000001;
    }

    {
        glu = new GLU();
    }

    public JoglDrawer(DarkFortressGUI darkFortressGUI) {
        this.component = darkFortressGUI;

        //GLProfile.initSingleton();
        //GLProfile.initProfiles(GLProfile.getDefaultDevice());
        GLProfile profile = null;
        if (GLProfile.isAvailable(GLProfile.GL2)) {
            profile = GLProfile.get(GLProfile.GL2);
        } else if (GLProfile.isAvailable(GLProfile.GL4)) {
            profile = GLProfile.get(GLProfile.GL4);
        } else
            System.err.println("GL not available");

        GLCapabilities capabilities = new GLCapabilities(profile);
        capabilities.setDoubleBuffered(true);

        glcanvas = new GLCanvas(capabilities);

        glcanvas.addGLEventListener(this);

        glcanvas.addKeyListener(darkFortressGUI.getPlotter3D());
        glcanvas.addKeyListener(darkFortressGUI.getGameKeyListener());

        glcanvas.setSize(640, 480);

        initFrame((JFrame) component);

        ((JFrame) component).add(glcanvas);

        timer = new Timer();
        timer.init();

        glcanvas.setFocusable(true);


    }

    public PiloteAuto piloteAuto() {
        return piloteAuto;
    }

    public void piloteAuto(PiloteAuto pa) {
        piloteAuto = pa;
    }

    public void color(GL2 gl, Color c) {
        gl.glColor3f(
                c.getRed() / 255f,
                c.getGreen() / 255f, c.getBlue() / 255f);
    }

    private void draw(LineSegment segd, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        Point3D p1 = getTerrain().p3(segd.getOrigine());
        Point3D p2 = getTerrain().p3(segd.getExtremite());
        color(gl, new Color(segd.texture().getColorAt(0.5, 0.5)));
        gl.glVertex3f((float) (double) p1.get(0), (float) (double) p1.get(1), (float) (double) p1.get(2));
        gl.glVertex3f((float) (double) p2.get(0), (float) (double) p2.get(1), (float) (double) p2.get(2));
        gl.glEnd();
        //System.out.print("SD");
        // System.out.println("L");
    }
    /*
     public void draw(Representable rep, GLU glu, GL2 gl)
     {
     throw new UnsupportedOperationException("Objet non supporte par "+getClass().getCanonicalName());
     }*/

    public void draw(TRI tri, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        color(gl, new Color(tri.texture().getColorAt(0.5, 0.5)));
        for (Point3D sommet : tri.getSommet().getData1d()) {
            Point3D p = getTerrain().p3(sommet);
            gl.glVertex3f((float) (double) p.get(0),
                    (float) (double) p.get(1),
                    (float) (double) p.get(2));
        }
        gl.glEnd();
    }

    public void draw2(TRI tri, GLU glu, GL2 gl, boolean guard) {
        if (!guard)
            gl.glBegin(GL2.GL_TRIANGLES);
        color(gl, new Color(tri.texture().getColorAt(0.5, 0.5)));
        for (Point3D sommet : tri.getSommet().getData1d()) {
            Point3D p = sommet;
            gl.glVertex3f((float) (double) p.get(0),
                    (float) (double) p.get(1),
                    (float) (double) p.get(2));
        }
        if (!guard)
            gl.glEnd();
    }

    public void draw(TRIObjetGenerateur s, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (int i = 0; i < s.getMaxX(); i++) {
            for (int j = 0; j < s.getMaxY(); j++) {
                TRI[] tris = new TRI[2];
                Point3D INFINI = Point3D.INFINI;
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                s.getTris(i, j, tris);
                draw(tris[0], glu, gl);
                draw(tris[1], glu, gl);
            }
        }
        gl.glEnd();
    }

    public void draw2(TRIObjetGenerateur s, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (int i = 0; i < s.getMaxX(); i++) {
            for (int j = 0; j < s.getMaxY(); j++) {
                TRI[] tris = new TRI[2];
                Point3D INFINI = Point3D.INFINI;
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                s.getTris(i, j, tris);
                draw2(tris[0], glu, gl, true);
                draw2(tris[1], glu, gl, true);
            }
        }
        gl.glEnd();
    }

    private void draw2(TRISphere2 s, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);


        for (int i = 0; i < s.getMaxX(); i++) {
            for (int j = 0; j < s.getMaxY(); j++) {
                TRI[] tris = new TRI[2];
                Point3D INFINI = Point3D.INFINI;
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                s.getTris(i, j, tris);
                draw2(tris[0], glu, gl, true);
                draw2(tris[1], glu, gl, true);
            }
        }
        gl.glEnd();
    }

    private void draw3(TRISphere2 s, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        s.setCentre(getTerrain().p3(s.getCoords()));
        for (int i = 0; i < s.getMaxX(); i++) {
            for (int j = 0; j < s.getMaxY(); j++) {
                TRI[] tris = new TRI[2];
                Point3D INFINI = Point3D.INFINI;
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                s.getTris(i, j, tris);
                draw2(tris[0], glu, gl, true);
                draw2(tris[1], glu, gl, true);
            }
        }
        gl.glEnd();
    }

    public void draw(TRIGenerable gen, GLU glu, GL2 gl) {
        draw(gen.generate(), glu, gl);
    }

    public void draw(TRIObject gen, GLU glu, GL2 gl) {
        gen.getTriangles().forEach((TRI t) -> {
            draw(t, glu, gl);
        });

    }

    public void draw(RepresentableConteneur rc, GLU glu, GL2 gl) {
        Iterator<Representable> it = rc.iterator();
        while (it.hasNext()) {
            Representable r = null;
            try {
                r = it.next();
            } catch (ConcurrentModificationException ex) {
                break;
            }
            if (r instanceof TRI) {
                draw((TRI) r, glu, gl);
            } else if (r instanceof LineSegment) {
                draw((LineSegment) r, glu, gl);
            } else if (r instanceof TRISphere2) {
                TRISphere2 s = (TRISphere2) r;
                s.setCentre(getTerrain().p3(s.getCoords()));
                draw2(s, glu, gl);
            } else if (r instanceof TRIObjetGenerateur) {
                TRIObjetGenerateur s = (TRIObjetGenerateur) r;
                draw(s, glu, gl);
            }

        }
    }


    public void draw(TRIConteneur con, GLU glu, GL2 gl) {
        /*if(con.getObj()==null && con instanceof TRIGenerable)
         {
         ((TRIGenerable)con).generate();
         }*/
        Iterable<TRI> iterable = con.iterable();
        iterable.forEach((TRI t) -> {
            draw(t, glu, gl);
        });

    }

    public void draw(Cube c, GLU glu, GL2 gl) {
        TRIObject generate = c.generate();
        draw(generate, glu, gl);
    }

    public void draw(String text, Color textColor, GLU glu, GL2 gl) {
        Dimension d = new Dimension(1, 1);
        if (component instanceof JFrame) {
            d = ((JFrame) component).getSize();
        }
        renderer.beginRendering((int) d.getWidth(), (int) d.getHeight());
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw(text, 10, 10);
        renderer.endRendering();
    }

    public void drawToggleMenu(GLU glu, GL2 gl) {
        if (toggleMenu.isDisplayMenu()) {
            Dimension d = new Dimension(1, 1);
            if (component instanceof JFrame) {
                d = ((JFrame) component).getSize();
            }
            renderer.beginRendering((int) d.getWidth(), (int) d.getHeight());
            String[] split = toggleMenu.toString().split("\\n");
            renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
            renderer.draw(split[0], 0, (int) d.getHeight() - 50 - 0 * 20);
            for (int i = 1; i < split.length; i++) {
                if (i - 1 == toggleMenu.getIndex()) {
                    renderer.setColor(0.2f, 0.1f, 0.2f, 0.8f);
                } else {
                    renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
                }
                renderer.draw(split[i], 0, (int) d.getHeight() - 50 - i * 30);
            }
            renderer.endRendering();
        }
    }


    public void draw(String text, Dimension place, Color textColor, GLU glu, GL2 gl) {
        Dimension d = new Dimension(1, 1);
        if (component instanceof JFrame) {
            d = ((JFrame) component).getSize();
        }
        renderer.beginRendering((int) d.getWidth(), (int) d.getHeight());
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw(text, (int) (d.getWidth() - 200), 10);
        renderer.endRendering();
    }
    /*public void drawCard(Card c, GLU glu, GL2 gl)
     {
     //Buffer buffer;
     Dimension d = new Dimension(1,1);
     if(component instanceof JFrame)
     {
     d = ((JFrame)component).getSize();
     }
     //gl.glDrawPixels(0, 0, d.getWidth(), d.getHeight(),  buffer);
            
     }*/

    @Override
    public void display(GLAutoDrawable gLDrawable) {


        final GL2 gl = gLDrawable.getGL().getGL2();

        // Change to projection matrix.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //glu.gluPerspective(60, 1.33, 0.001, 1.0);
        gl.glLoadIdentity();


        Camera camera;
        if (mover.getPlotter3D().isActive())
            camera = mover.getPositionMobile().calcCameraMobile();
        else
            camera = mover.getPositionMobile().calcCamera();
        Point3D pos = camera.getEye();
        Point3D dir = camera.getLookat();
        diff = dir.moins(pos);

        /*
        if(del0!=null) {
            if (del.moins(del0).norme() > 0)
                diff = del.moins(del0).prodVect(del).norme1();
            if (diff.norme() == 0)
                diff = Point3D.Y;
            if (diff.prodScalaire(normale0) < 0)
                diff = diff.mult(-1);
        }
        */

        Point3D position = pos;//getMover().getPositionMobile().calcPosition();

        Point3D normale = /*dir.prodVect(pos);*/getTerrain().calcNormale(position.getX(), position.getY());


        glu.gluLookAt(pos.get(0), pos.get(1),
                pos.get(2), dir
                        .get(0), dir.get(1),
                dir.get(2),
                diff.prodVect(normale.prodVect(diff))
                        .norme1().get(0),
                diff.prodVect(normale.prodVect(diff))
                        .norme1().get(1),
                diff.prodVect(normale.prodVect(diff))
                        .norme1().get(2));
        /*if(circuit==null)
         circuit = mover.getCircuit();
         if(circuit!=null)
         draw((TRIConteneur)circuit, glu, gl);
        */
        if (toggleMenu == null)
            return;
        if (toggleMenu.isDisplayBonus()) {
            bonus.getListRepresentable().forEach(representable -> {
                Point3D center = ((TRISphere2) representable).getCoords();
                ((TRISphere2) representable).getCircle().getAxis().getElem().setCenter(terrain.p3(center));
            });
            draw(bonus, glu, gl);
        }
        if (toggleMenu.isDisplaySky())
            draw3(new Ciel().getBleu(), glu, gl);

        if (mover.getPath().size() >= 2) {
            path = new TubulaireN2<Lines>();
            mover.setPath(new Path());
            Lines lines = new Lines(getMover().getPath());
            path.curve(lines);
            path.nbrAnneaux(100);
            path.nbrRotations(4);
            path.diam(0.01);
            path.generate();

            draw2(path, glu, gl);
        }

        //if (toggleMenu.isDisplayGroundGrid())
        //  draw(terrain, glu, gl);
        if (toggleMenu.isDisplayGround()) {
            if (terrain.isDessineMurs()) {
                displayGround(glu, gl);
            }
        }
        if (toggleMenu.isDisplayArcs() && SolPlan.class.equals(getLevel())) {
            displayArcs(glu, gl);
        }
        if (toggleMenu.isDisplayCharacter()) {
            if (getPlotter3D().isActive()) {
                CourbeParametriquePolynomiale courbeParametriquePolynomiale = null;
                TubulaireN2<CourbeParametriquePolynomiale> segmentDroiteTubulaireN2 = new TubulaireN2<>();
                segmentDroiteTubulaireN2.diam(0.01);
                segmentDroiteTubulaireN2.curve(courbeParametriquePolynomiale = new CourbeParametriquePolynomiale(new Point3D[]{getMover().getPositionMobile().calcPosition2D(),
                        getMover().getPositionMobile().calcDirection2D()}));
                segmentDroiteTubulaireN2.generate();
                draw(courbeParametriquePolynomiale, glu, gl);
            } else {
                Cube object = vaisseau.getObject();
                object.setPosition(mover.calcCposition());
                draw(object, glu, gl);
            }
        }

        if (toggleMenu.isDisplayScore())
            draw("Score :  " + mover.score(), Color.WHITE, glu, gl);
        if (toggleMenu.isDisplayEnergy())
            draw("Life :  " + mover.energy(), new Dimension(30, 10), Color.GREEN, glu, gl);


        drawToggleMenu(glu, gl);

        drawTrajectory(getPlotter3D(), glu, gl);
        Graphics g = null;
        /*if (component instanceof JApplet) {
            g = ((JApplet) component).getGraphics();
        }*/
        if (component instanceof JFrame) {
            g = ((JFrame) component).getGraphics();
        }

        if (g == null) {
            throw new NullPointerException("Problem initialising JFrame graphics");
        }
    }

    private void displayArcs(GLU glu, GL2 gl) {
        Point3D[][] arc = new Point3D[][]{
                {
                        P.n(0.5, 0.5, 0),
                        P.n(0.5 + 0.25, 0.5 + 0.75, 0.5),
                        P.n(0.5 + 0.75, 0.5 + 0.25, 0.5),
                        P.n(0.5 + 1, 0.5, 0)},
                {
                        P.n(0.5 + 1, 0.5, 0),
                        P.n(0.5 + 1, 0.5 + 0.25, 0.5),
                        P.n(0.5 + 1, 0.5 + 0.75, 0.5),
                        P.n(0.5 + 1, 0.5 + 1, 0)}
        };

        TubulaireN2<CourbeParametriquePolynomialeBezier> courbeParametriquePolynomialeBezierTubulaireN2 = new TubulaireN2<>();
        courbeParametriquePolynomialeBezierTubulaireN2.curve(new CourbeParametriquePolynomialeBezier(arc[0]));
        courbeParametriquePolynomialeBezierTubulaireN2.texture(new ColorTexture(Color.GREEN));


        TubulaireN2<CourbeParametriquePolynomialeBezier> courbeParametriquePolynomialeBezierTubulaireN22 = new TubulaireN2<>();
        courbeParametriquePolynomialeBezierTubulaireN22.curve(new CourbeParametriquePolynomialeBezier(arc[1]));
        courbeParametriquePolynomialeBezierTubulaireN22.texture(new ColorTexture(Color.GREEN));

        draw(courbeParametriquePolynomialeBezierTubulaireN2, glu, gl);
        draw(courbeParametriquePolynomialeBezierTubulaireN22, glu, gl);

    }
    private void displayTerrain(GLU glu, GL2 gl) {
        draw((RepresentableConteneur)terrain, glu, gl);
    }

    private void displayGround(GLU glu, GL2 gl) {
        int nbrTriReduce = 0;
        double maxDistance = 0.01;
        gl.glBegin(GL2.GL_TRIANGLES);
        for (double i = 0; i <= 1; i += INCR_AA) {
            for (double j = 0; j <= 1; j += INCR_AA) {

                final Double[][][] faces = Cube.getData();
                TRI[] tris = new TRI[14];
                tris[12] = new TRI(new Point3D(i, j+INCR_AA, 0.), new Point3D(1.+INCR_AA, j+INCR_AA, 0.), new Point3D(0., j+INCR_AA, 0.));
                tris[13] = new TRI(new Point3D(1.+INCR_AA, j, 0.), new Point3D(1.+INCR_AA, j+INCR_AA, 0.), new Point3D(0.+INCR_AA, j, 0.));

                for (int g = 0; g < 3; g++) {
                    tris[12].getSommet().setElem(terrain.p3(tris[12].getSommet().getElem(g)), g);
                    tris[13].getSommet().setElem(terrain.p3(tris[13].getSommet().getElem(g)), g);
                }

                double a = 0;

                tris[12].texture(new ColorTexture(Plasma.color(i + a, j + a, time())));
                draw2(tris[12], glu, gl, true);

                tris[13].texture(new ColorTexture(Plasma.color(i + a, j + a, time())));
                draw2(tris[13], glu, gl, true);


            }
        }
        gl.glEnd();
    }

    private boolean isClose(double maxDistance, TRI toDraw) {
        return Point3D.distance(getTerrain().p3(toDraw.getSommet().getElem(0)), mover.calcCposition()) < maxDistance;
    }


    private void drawTriLines(TRI triCourant, GLU glu, GL2 gl, boolean b) {
    }


    private void draw(ParametricCurve courbeParametriquePolynomiale, GLU glu, GL2 gl) {
        double d0 = courbeParametriquePolynomiale.start();
        for (double d = courbeParametriquePolynomiale.start(); d < courbeParametriquePolynomiale.endU(); d += courbeParametriquePolynomiale.getIncrU().getElem()) {
            draw(new LineSegment(courbeParametriquePolynomiale.calculerPoint3D(d0), courbeParametriquePolynomiale.calculerPoint3D(d)), glu, gl);
            d0 = d;
        }
    }

    private void drawTrajectory(Plotter3D plotter3D, GLU glu, GL2 gl) {
        Point3D impact = plotter3D.getImpact();
        draw(new CourbeParametriquePolynomiale(new Point3D[]
                        {
                                getMover().calcCposition(),

                                getTerrain().calcCposition(impact.getX(), impact.getY())
                        })
                , glu, gl);
    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
                               boolean deviceChanged) {
        System.out.println("displayChanged called");
    }

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        /*
         * System.out.println("init() called"); GL2 gl =
         * gLDrawable.getGL().getGL2(); gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
         * gl.glShadeModel(GL2.GL_FLAT);
         */

        GL2 gl = gLDrawable.getGL().getGL2();
        gLDrawable.setGL(new DebugGL2(gl));

        // Global settings.
        gl.glEnable(GL2.GL_DEPTH_TEST);

        /*
         gl.glDepthFunc(GL2.GL_LEQUAL);
         gl.glShadeModel(GL2.GL_SMOOTH);
         gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

         */
        gl.glClearColor(0f, 0f, 0f, 1f);

        // Start animator (which should be a field).
        FPSAnimator animator = new FPSAnimator(gLDrawable, 60);
        animator.start();
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
    }

    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
                        int height) {
        System.out.println("reshape() called: coordArr = " + x + ", y = " + y
                + ", width = " + width + ", height = " + height);
        final GL2 gl = gLDrawable.getGL().getGL2();

        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }

        final float h = (float) width / (float) height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(60f, h, 0.001f, 2f);

        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        System.out.println("dispose() called");
    }

    @Override
    public void setLogic(PositionUpdate m) {
        this.mover = m;

        vaisseau = new Vaisseau(mover);
        terrain = mover.getTerrain();
        bonus = new Bonus();
        mover.ennemi(bonus);
    }

    private boolean locked() {
        return locked;
    }

    private double time() {
        return timer.getTimeEllapsed();
    }

    @Override
    public LineSegment click(Point2D p) {
        GLU glul = this.glu;

        /*
         double aspect = double(glcanvas.getWidth())/double(glcanvas.getHeight());
         glu.glMatrixMode( GL_PROJECTION );
         glLoadIdentity();
         glFrustum(-near_height * aspect,
         near_height * aspect,
         -near_height,
         near_height,
         zNear,
         zFar );
         int window_y = (window_height - mouse_y) - window_height/2;
         double norm_y = double(window_y)/double(window_height/2);
         int window_x = mouse_x - window_width/2;
         double norm_x = double(window_x)/double(window_width/2);
         float y = near_height * norm_y; float coordArr = near_height * aspect * norm_x;
         float ray_pnt[4] = {0.f, 0.f, 0.f, 1.f}; float ray_vec[4] = {coordArr, y, -near_distance, 0.f};

         GLuint buffer[BUF_SIZE]; glSelectBuffer (BUF_SIZE, buffer);
         GLint hits; glRenderMode(GL_SELECT);
         glRenderMode(GL_RENDER);
         */
        return null;
    }

    /*
     * prints out the contents of the selection array.
     */
    private void processHits(int hits, int buffer[]) {
        int names, ptr = 0;

        System.out.println("hits = " + hits);
        // ptr = (GLuint *) buffer;
        for (int i = 0; i < hits; i++) { /* for each hit */

            names = buffer[ptr];
            System.out.println(" number of names for hit = " + names);
            ptr++;
            System.out.println("  z1 is " + buffer[ptr]);
            ptr++;
            System.out.println(" z2 is " + buffer[ptr]);
            ptr++;
            System.out.print("\n   the name is ");
            for (int j = 0; j < names; j++) { /* for each name */

                System.out.println("" + buffer[ptr]);
                ptr++;
            }
            System.out.println();
        }
    }

    private void drawRects(GL2 gl, int mode) {
        if (mode == GL2.GL_SELECT) {
            gl.glLoadName(1);
        }
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3i(2, 0, 0);
        gl.glVertex3i(2, 6, 0);
        gl.glVertex3i(6, 6, 0);
        gl.glVertex3i(6, 0, 0);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3i(3, 2, -1);
        gl.glVertex3i(3, 8, -1);
        gl.glVertex3i(8, 8, -1);
        gl.glVertex3i(8, 2, -1);
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3i(0, 2, -2);
        gl.glVertex3i(0, 7, -2);
        gl.glVertex3i(5, 7, -2);
        gl.glVertex3i(5, 2, -2);
        gl.glEnd();
    }

    public GLU getGlu() {
        return glu;
    }

    public Object getComponent() {
        return component;
    }
    /*
     * sets up selection mode, name stack, and projection matrix for picking. Then
     * the objects are drawn.
     */

    public PositionUpdate getMover() {
        return mover;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public TextRenderer getRenderer() {
        return renderer;
    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public boolean isLocked() {
        return locked;
    }

    private void setLocked(boolean l) {
        locked = l;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public Timer getTimer() {
        return timer;
    }

    public GLCanvas getGlcanvas() {
        return glcanvas;
    }

    public int getBUFSIZE() {
        return BUFSIZE;
    }

    public Point2D getPickPoint() {
        return pickPoint;
    }

    public PiloteAuto getPiloteAuto() {
        return piloteAuto;
    }

    private void pickRects(GL2 gl) {
        int[] selectBuf = new int[BUFSIZE];
        IntBuffer selectBuffer = Buffers.newDirectIntBuffer(BUFSIZE);
        int hits;
        int viewport[] = new int[4];
        // int coordArr, y;

        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);

        gl.glSelectBuffer(BUFSIZE, selectBuffer);
        gl.glRenderMode(GL2.GL_SELECT);

        gl.glInitNames();
        gl.glPushName(-1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        /* create 5x5 pixel picking region near cursor location */
        glu.gluPickMatrix(pickPoint.getX(),
                viewport[3] - pickPoint.getY(), //
                5.0, 5.0, viewport, 0);
        gl.glOrtho(0.0, 8.0, 0.0, 8.0, -0.5, 2.5);
        drawRects(gl, GL2.GL_SELECT);
        gl.glPopMatrix();
        gl.glFlush();

        hits = gl.glRenderMode(GL2.GL_RENDER);
        selectBuffer.get(selectBuf);
        processHits(hits, selectBuf);
    }

    public Plotter3D getPlotter3D() {
        return plotter3D;
    }

    public void setPlotter3D(Plotter3D plotter3D) {
        this.plotter3D = plotter3D;
    }

}
