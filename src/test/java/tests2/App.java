package tests2;

import one.empty3.library.core.testing.TestObjetSub;

/*__
 * Manuel Dahmen
 */
public abstract class App {
    public abstract void initialize(TestObjetSub testObjetSub);

    public static void main(String[] args) {

        if (args.length >= 2) {
            String classname = args[0];
            String appClassname = args[1];
            try {
                Class aClass = Class.forName(classname);
                Object instance = aClass.newInstance();
                TestObjetSub testObjetSub = ((TestObjetSub) instance);
                Class appClass = Class.forName(appClassname);
                Object appInstance = appClass.newInstance();
                ((App) appInstance).initialize(testObjetSub);
                testObjetSub.run();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
