package tests2.spherestexturees;

import one.empty3.library.*;
import one.empty3.library.core.lighting.Colors;
import one.empty3.library.core.testing.TestObjetSub;

/*__
 * @author Manuel DAHMEN
 */
public class Spheres extends TestObjetSub {
    Point3D[] points;
    Point3D[] speed;
    int pointCount;
    double maxSpeed;
    double dimension;

    public Spheres(int n, double v, double dim) {
        pointCount = n;
        maxSpeed = v;
        dimension = dim;
    }

    public static void main(String[] args) {
        Spheres s = new Spheres(100000, 5, 100);
        s.setMaxFrames(10000);
        new Thread(s).start();
    }

    @Override
    public void ginit() {
        RepresentableConteneur representableConteneur = new RepresentableConteneur();
        points = new Point3D[pointCount];
        speed = new Point3D[pointCount];
        for (int i = 0; i < pointCount; i++) {
            points[i] = Point3D.random2(dimension);

            points[i].texture(new TextureCol(Colors.random()));
            points[i].setNormale(Point3D.Z);
            representableConteneur.add(points[i]);

            speed[i] = Point3D.random2(maxSpeed);

        }
        Camera camera = new Camera(Point3D.Z.mult(-dimension * 2), Point3D.O0);
        scene().add(representableConteneur);
        scene().cameraActive(camera);

        System.out.println(representableConteneur);
    }

    public void bounce(int i) {
        points[i] = points[i].plus(speed[i]);
        if (points[i].getX() > dimension && speed[i].getX() > 0) {
            speed[i].setX(-speed[i].getX());
        }
        if (points[i].getX() < -dimension && speed[i].getX() < 0) {
            speed[i].setX(-speed[i].getX());
        }
        if (points[i].getY() > dimension && speed[i].getY() > 0) {
            speed[i].setY(-speed[i].getY());
        }
        if (points[i].getY() < -dimension && speed[i].getY() < 0) {
            speed[i].setY(-speed[i].getY());
        }
        if (points[i].getZ() > dimension && speed[i].getZ() > 0) {
            speed[i].setZ(-speed[i].getZ());
        }
        if (points[i].getZ() < -dimension && speed[i].getZ() < 0) {
            speed[i].setZ(-speed[i].getZ());
        }
    }

    @Override
    public void finit() throws Exception {
        for (int i = 0; i < pointCount; i++)
            bounce(i);
    }

}
