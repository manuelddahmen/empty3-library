/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <ibiiztera.it@gmail.com>
 ***/


package tests2.spirale;

import one.empty3.library.core.testing.TestObjetSub;

/*__
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Spirale extends TestObjetSub {
    public static void main(String[] args) {
        Spirale s = new Spirale();

        s.setResx(2000);
        s.setResy(1500);

        s.setMaxFrames(1500);

        s.setGenerate(GENERATE_IMAGE);

        new Thread(s).start();

    }

    @Override
    public void ginit() {
    }

    @Override
    public void testScene() throws Exception {
    }

}
