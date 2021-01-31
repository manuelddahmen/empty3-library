package tests2.sw;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/*__
 * Created by Win on 16-01-16.
 */
public class TestTitle extends TestObjetSub {
    String text;
    TextObjet textObjet = new TextObjet(new Point3D(1000d, 1000d, 1000d), new Point3D(-2000d, 0d, 0d), new Point3D(0d, -2000d * Math.tan(angle()), -2000d * Math.sin(angle())));
    Camera c = new Camera(new Point3D(0d, 0d, -1000d), new Point3D(0d, 0d, 1000d));

    public static void main(String[] args) {
        TestTitle ts = new TestTitle();

        ts.loop(true);

        ts.setMaxFrames(200);

        new Thread(ts).start();
    }

    public double angle() {
        return 45.0 / 360 * 2 * Math.PI;
    }

    @Override
    public void ginit() {
        byte[] readbefore = new byte[512];
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("text.txt");
            while (is.read(readbefore) > 0) {
                text += readbefore.toString();
            }
        } catch (IOException e) {
            text = "Not initialized or error";
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        textObjet.setTexTextureCol(Color.WHITE);
        textObjet.setText(text);

        scene().add(textObjet);
    }

    @Override
    public void finit() {
        textObjet.deplace(new Point3D(
                0d, 10d * Math.tan(angle()), 20d * Math.sin(angle())));
    }

    @Override
    public void testScene() throws Exception {
        super.testScene();
    }

}

