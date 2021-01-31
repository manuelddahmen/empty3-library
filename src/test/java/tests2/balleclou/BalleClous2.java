package tests2.balleclou;

/*
 * 2013 Manuel Dahmen
 */

import one.empty3.library.Point2D;
import one.empty3.library.Point3D;
import one.empty3.library.Sphere;

import java.util.ArrayList;

/*__
 * @author Manuel Dahmen
 */
public class BalleClous2 extends Sphere {
    private ArrayList<Point2D> points = new ArrayList<Point2D>();
    private double d;

    public BalleClous2(Point3D c, double r) {
        super(c, r);
    }

    public void addPoint(Point2D p) {
        this.points.add(p);
    }

    public ArrayList<Point2D> points() {
        return points;
    }

    public double formula(Point2D p1, Point2D p2) {
        double d = dmaxdist(p1, p2);

        return 1 / (d * d + 0.01);
    }

    public void param(double d) {
        this.d = d;
    }

    public double param() {
        return d;
    }

    public double dmindist(Point2D p0, Point2D p1) {

        double[] x = new double[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
        double[] y = new double[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};
        double min = 100;
        for (int i = 0; i < 9; i++) {
            double cur;
            cur = Point2D.dist(p0, Point2D.plus(p1, new Point2D(x[i], y[i])));
            if (cur < min && cur > 0)
                min = cur;
        }
        return min;
    }
    public double dmaxdist(Point2D p0, Point2D p1) {

        double[] x = new double[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
        double[] y = new double[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};
        double max = -1.0;
        for (int i = 0; i < 9; i++) {
            double cur;
            cur = Point2D.dist(p0, Point2D.plus(p1, new Point2D(x[i], y[i])));
            if (cur > max)
                max = cur;
        }
        return max;
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        Point3D p = super.calculerPoint3D(u, v);

        Point2D p0 = new Point2D(u, v);

        double mult = 1.0;

        for (int i = 0; i < points.size(); i++) {

            mult += formula(p0, points.get(i));

        }

        return p.moins(getCircle().getCenter()).mult(mult / points.size()).plus(getCircle().getCenter());

    }


}
