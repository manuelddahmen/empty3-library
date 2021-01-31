package tests2.balleclou;

import one.empty3.library.*;
import one.empty3.library.core.lighting.Colors;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;

/*__
 * @author Manuel Dahmen
 */
public class TestBalleClous1111 extends TestObjetSub {

    public int MAXFRAMES = 2000;

    public int N = 1;
    private ITexture tc = new TextureCol(Color.red);
    private BalleClous2 ballec;
    private Point3D[][] s;
    private Point3D[][] v;
    private double V = 0.03;
    private double D = 1;
    private TextureMov textureMov;
    private BalleClous2[] balles = new BalleClous2[1];
    private int nBalles = 5;

    public static void main(String[] args) {
        TestBalleClous1111 th = new TestBalleClous1111();

        th.loop(true);

        //th.setResx(640);
        //th.setResy(480);

        th.setMaxFrames(th.MAXFRAMES);

        th.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        th.run();
    }

    @Override
    public void ginit() {
        LumierePonctuelle lumierePonctuelle = new LumierePonctuelle(Point3D.X, Color.RED);
        lumierePonctuelle.setR0(1);

        scene().lumieres().add(lumierePonctuelle);

        lumierePonctuelle = new LumierePonctuelle(Point3D.Y, Color.BLUE);
        lumierePonctuelle.setR0(1);

        scene().lumieres().add(lumierePonctuelle);
        scene().lumieres().add(new LumierePonctuelle(Point3D.O0, Colors.random()));

        s = new Point3D[nBalles][N];
        v = new Point3D[nBalles][N];

        for (int b = 0; b < nBalles; b++) {
            for (int i = 0; i < N; i++) {
                s[b][i] = new Point3D(Point3D.O0);

                s[b][i].texture(new TextureCol(Colors.random()));

                v[b][i] = new Point3D(Math.random() * (V / 2 - V), Math.random() * (V / 2 - V), Math.random() * (V / 2 - V));

            }
            tc =
                    new TextureCol(
                            Colors.random());


        }
        for (int i = 0; i < balles.length; i++
        ) {


            ballec = new BalleClous2(Point3D.O0, 1);

            balles[i] = ballec;
            ballec.texture(new TextureCol(Colors.random()));
            //textureMov = new TextureMov("C:\\Emptycanvas\\Resources\\BigFloEtOlie.mp4");
            //textureMov.setTransparent(Color.BLACK);
            //ballec.texture(textureMov);
            scene().add(ballec);


        }
        Camera camera;
        camera = new Camera(new Point3D(-5d, 0d, 0d),
                new Point3D(0d, 0d, 0d));

        scene().cameraActive(camera);
    }

    public void bounce(int numBalle, int i) {
        s[numBalle][i] = s[numBalle][i].plus(v[numBalle][i]);


        if (s[numBalle][i].getX() > D && v[numBalle][i].getX() > 0) {
            v[numBalle][i].setX(-v[numBalle][i].getX());
        }
        if (s[numBalle][i].getX() < -D && v[numBalle][i].getX() < 0) {
            v[numBalle][i].setX(-v[numBalle][i].getX());
        }
        if (s[numBalle][i].getY() > D && v[numBalle][i].getY() > 0) {
            v[numBalle][i].setY(-v[numBalle][i].getY());
        }
        if (s[numBalle][i].getY() < -D && v[numBalle][i].getY() < 0) {
            v[numBalle][i].setY(-v[numBalle][i].getY());
        }
        if (s[numBalle][i].getZ() > D && v[numBalle][i].getZ() > 0) {
            v[numBalle][i].setZ(-v[numBalle][i].getZ());
        }
        if (s[numBalle][i].getZ() < -D && v[numBalle][i].getZ() < 0) {
            v[numBalle][i].setZ(-v[numBalle][i].getZ());
        }
    }

    @Override
    public void testScene() throws Exception {
        for (int b = 0; b < balles.length; b++) {
            ballec = balles[b];

            for (int i = 0; i < s[0].length; i++) {
                bounce(b, i);
            }


            ballec.points().clear();
            double totalA = 0;
            double totalB = 0;

            for (int j = 0; j < s[b].length; j++) {
                if (s[b][j].getX() < 0) {
                    s[b][j].setX(s[b][j].getX() + D);
                }
                if (s[b][j].getY() < 0) {
                    s[b][j].setY(s[b][j].getY() + D);
                }
                if (s[b][j].getX() > D) {
                    s[b][j].setX(s[b][j].getX() - D);
                }
                if (s[b][j].getY() > D) {
                    s[b][j].setY(s[b][j].getY() - D);
                }

                totalA += s[b][j].getX();
                totalB += s[b][j].getY();

                ballec.addPoint(new Point2D(s[b][j].getX(), s[b][j].getY()));

            }

        }
    }

}
