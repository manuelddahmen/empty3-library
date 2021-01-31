package test3.xmlrepresentation;

import junit.framework.TestCase;
import one.empty3.library.Scene;
import org.junit.Test;

/*__
 * Created by manue on 20-09-19.
 */
public class XmlSceneTest extends TestCase {
    @Test
    public void testScene()
    {
        Scene scene = new Scene();
        StringBuilder stringBuilder = new StringBuilder();
        scene.declareProperties();
        scene.declarations();
        scene.xmlRepresentation(null, scene, stringBuilder);
        System.out.println(stringBuilder);

        assertTrue(stringBuilder.toString().length()>0);
    }
}
