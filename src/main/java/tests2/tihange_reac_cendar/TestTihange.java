package tests2.tihange_reac_cendar;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.core.move.Trajectoires;
import one.empty3.library.core.testing.TestObjetSub;

public class TestTihange extends TestObjetSub {
    public TestTihange(){
        
        setMaxFrames(1);
        setGenerate(GENERATE_MOVIE | GENERATE_IMAGE | GENERATE_MODEL);
       
    }
    public static void main(String... args) {
        
        TestTihange testTihange = new TestTihange();
        testTihange.setMaxFrames(1);
        testTihange.setGenerate(GENERATE_MOVIE | GENERATE_IMAGE | GENERATE_MODEL);
        new Thread(testTihange).start();
    }

    public void finit() {
        scene().cameraActive(new Camera(
                Trajectoires.sphere(Math.random(), Math.random()
                        , 8),
                new Point3D(0d, 0d, 1d)));
        scene().add(new tests2.tihange_reac_cendar.Tihange());
    }

    @Override
    public void testScene() throws Exception {
    }
}
