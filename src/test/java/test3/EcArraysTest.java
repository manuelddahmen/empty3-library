package test3;

import one.empty3.library.Point3D;
import one.empty3.library.core.EcArrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/*__
 * Created by manue on 07-09-19.
 */
public class EcArraysTest  {
    Point3D[] point3D_dim1 = new Point3D[]
            {Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0)};
    Point3D[][] point3D_dim2 = new Point3D[][]
            {{Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0)},
                    {Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0)},
                    {Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0)},
                    {Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0),Point3D.random(10.0)}};
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deleteRowAtDim1() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[] point3DS1 = point3DEcArrays.deleteRowAtDim1(point3D_dim1, 2);
        assertTrue(point3DS1.length== 3);
    }

    @Test
    public void deleteRowAtDim2() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[][] point3DS1 = point3DEcArrays.deleteRowAtDim2(point3D_dim2, 2);
        assertTrue(point3DS1.length== 3);
    }

    @Test
    public void deleteColAtDim2() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[][] point3DS1 = point3DEcArrays.deleteColAtDim2(point3D_dim2, 2);
        assertTrue(point3DS1[0].length==3);
    }

    @Test
    public void insertRowAtDim1() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[] point3DS1 = point3DEcArrays.insertRowAtDim1(point3D_dim1, 2, Point3D.O0);
        assertTrue(point3DS1[2].equals(Point3D.O0));
    }

    @Test
    public void insertRowAtDim2() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[][] point3DS1 = point3DEcArrays.insertRowAtDim2(point3D_dim2, 2, point3D_dim2[0].length);
        assertTrue(point3DS1.length==5);
    }

    @Test
    public void insertColAtDim2() throws Exception {
        EcArrays<Point3D> point3DEcArrays = new EcArrays<>();
        Point3D[][] point3DS1 = point3DEcArrays.insertColAtDim2(point3D_dim2, 2);
        assertTrue(point3DS1[0].length==5);
    }

}