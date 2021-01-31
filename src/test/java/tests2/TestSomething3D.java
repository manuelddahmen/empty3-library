package tests2;

import one.empty3.library.Representable;
import one.empty3.library.core.testing.TestObjet;

/*__
 * Created by Win on 29-08-18.
 */
public abstract class TestSomething3D<T extends Representable> extends TestObjet {


    @Override
    public void afterRenderFrame() {

    }

    @Override
    public abstract void finit();

    @Override
    public void ginit() {

    }

    @Override
    public void afterRender() {
        System.gc();
    }

    @Override
    public void testScene() throws Exception {

    }
}
