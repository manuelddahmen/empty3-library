package tests2.balleclou;


import one.empty3.library.*;import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;


/*__
 * @author Se7en
 */
public class TestBalleClous111 extends TestObjetSub {

    public int MAXFRAMES = 2000;
    public int N = 2;
    private TextureCol tc = new TextureCol(Color.red);
    private BalleClous2 ballec;
    private Point3D[] s;
    private Point3D[] v;
    private double V = 0.03;
    private double D = 1;

    public static void main(String[] args) {
        TestBalleClous111 th = new TestBalleClous111();

        th.loop(true);

        th.setResx(1920);

        th.setResy(1080);

        th.MAXFRAMES = 4000;

        th.setGenerate(GENERATE_IMAGE);

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

        s = new Point3D[N];
        v = new Point3D[N];
        for (int i = 0; i < N; i++) {
            s[i] = new Point3D(Point3D.O0);

            s[i].texture(new TextureCol(Color.GREEN));

            v[i] = new Point3D(Math.random() * (V / 2 - V), Math.random() * (V / 2 - V), Math.random() * (V / 2 - V));

        }
        tc =
                new TextureCol(
                        Color.YELLOW);


        ballec = new BalleClous2(Point3D.O0, 1.0);


        ballec.texture(new TextureCol(Color.WHITE));

        scene().add(ballec);

        scene().lumieres().add(new LumierePonctuelle(Point3D.O0, Color.BLUE));

        Camera camera;
        camera = new Camera(new Point3D(0d, 0d, -2d),
                new Point3D(0d, 0d, 0d));

        scene().cameraActive(camera);

    }

    public void bounce(int i) {
        s[i] = s[i].plus(v[i]);


        if (s[i].getX() > D && v[i].getX() > 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getX() < -D && v[i].getX() < 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getY() > D && v[i].getY() > 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getY() < -D && v[i].getY() < 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getZ() > D && v[i].getZ() > 0) {
            v[i].setZ(-v[i].getZ());
        }
        if (s[i].getZ() < -D && v[i].getZ() < 0) {
            v[i].setZ(-v[i].getZ());
        }
    }

    @Override
    public void testScene() throws Exception {
        for (int i = 0; i < s.length; i++) {
            bounce(i);
        }

        ballec.points().clear();
        double totalA = 0;
        double totalB = 0;

        for (int j = 0; j < s.length; j++) {
            if (s[j].getX() < 0) {
                s[j].setX(s[j].getX() + D);
            }
            if (s[j].getY() < 0) {
                s[j].setY(s[j].getY() + D);
            }
            if (s[j].getX() > D) {
                s[j].setX(s[j].getX() - D);
            }
            if (s[j].getY() > D) {
                s[j].setY(s[j].getY() - D);
            }

            totalA += s[j].getX();
            totalB += s[j].getY();

            ballec.addPoint(new Point2D(s[j].getX(), s[j].getY()));

        }


    }

    private Matrix33 matrix1(double a, double b) {
        return Matrix33.rot(a, b);
    }

    @Override
    public void finit() {
    }
}
