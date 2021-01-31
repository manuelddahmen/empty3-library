/*__
 * Global license :
 * <p>
 * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 ***/


package tests2.rotation;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*__
 * cette classe produit une image de sphère avec "Manuel Dahmen" écrit dessus. La sphère tourne
 * puis s'en va et revient à l'écran.
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestRotationsObjets extends TestObjetSub {
    static int nObjets = 2;
    static int rotationsParObjets = 3;
    protected Representable representable; // l'obserbvé (ou observable, à contrôler
    double distance = 300;
    double dim = 100;
    int maxx = 100;
    int maxy = 100;
    TextureImg imageTexture;
    private double globalTimeMillis = 1000;
    private String label;
    private int actionCourante;
    private int actionsParObjet = 3;
    private int nFramesParObjet;
    private int objetCourant;
    private int frameDansLObjet;
    private int nopartielPartielle;
    private double ratio;
    private double pourcentage;
    private int axe;
    private int frameDansLAction;
    private int nFramesParAction;

    public TestRotationsObjets(double globalTimeMillis) {
        this.globalTimeMillis = globalTimeMillis;
        setMaxFrames((int) (globalTimeMillis * 25.0 / 1000.0));
        System.out.println("Nombre de secondes total de la vidéo : " + globalTimeMillis / 1000.0);
        System.out.println("Nombre de secondes de la vidéo par objet : " + globalTimeMillis / 1000.0 / nObjets);
        System.out.println("Nombre de secondes de la vidéo par action : " + globalTimeMillis / 1000.0 / nObjets / rotationsParObjets);
        try {
            imageTexture = new TextureImg(new ECBufferedImage(ImageIO.read(this.getClass().getResourceAsStream("map2.png"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("Fichier non trouvé : texture. Choisir une autre texture ou retrouveer le fichier\nOu rrecompiler si fichier ressource");
            System.exit(-1);
        }
        imageTexture.setColorMask(ITexture.COLOR_MIROR_XY);

    }

    public static void main(String[] args) {

        int globalTSparObjet = 10000;
        TestRotationsObjets ts = new TestRotationsObjets(globalTSparObjet * nObjets);


        // Seules images et films dans un premier temps
        ts.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        ts.loop(true);


        new Thread(ts).start();
    }

    /*__
     * On laisse la caméra toujours à la même place dans ce test, et on fait tourner les objets
     */
    @Override
    public void ginit() {
        scene().cameraActive(new Camera(Point3D.Z.mult(-distance), Point3D.O0));


    }

    protected Representable initObjet(int no) {
        switch (no) {
            case 0:
                representable = new LineSegment(Point3D.Y.mult(-dim), Point3D.Y.mult(dim), new TextureCol(Color.BLACK));

                scene().add(representable);

                return representable;
            case 1:
                representable = new TRISphere(Point3D.O0, dim);
                representable.texture();
                scene().add(representable);
                return representable;

        }
        return representable;
    }

    public void calculDeFrames() {
        actionsParObjet = rotationsParObjets;

        nFramesParObjet = getMaxFrames() / nObjets;

        nFramesParAction = getMaxFrames() / nObjets / actionsParObjet;

        objetCourant = frame() / nFramesParObjet + 1;

        axe = (frame() / nFramesParAction) % 3;


        actionCourante = nFramesParObjet / objetCourant + 1;

        frameDansLObjet = frame() / nObjets / actionsParObjet;


        nopartielPartielle = frame() - frameDansLObjet * (nObjets - 1);

        frameDansLAction = frame() - frameDansLObjet * (nObjets - 1) - nFramesParAction * axe;


        ratio = (1.0 * frameDansLAction / nFramesParAction) - 1.0 * frameDansLObjet / nFramesParObjet * nObjets / 3;

        pourcentage = ratio * 100;
    }

    public Matrix33 rotation(int axe) {


        double angle = 2 * Math.PI * ratio;

        label = "La be.manudahmen.empty3.tests2.rotation autour de l'axe des " + (axe == 0 ? "X" : (axe == 1 ? "Y" : (axe == 2 ? "Z" : "AUCUN AXE CONNU"))) + " est effectuée à hauteur de " +
                pourcentage + " % (no" + frameDansLAction + "/" + nFramesParObjet;

        System.out.println("Frame courante globale = " + frame() + "\nFrame de l'objet : " + nopartielPartielle);

        System.out.println(label);

        switch (axe) {
            case 0:
                return Matrix33.rotationX(angle);
            case 1:
                return Matrix33.rotationY(angle);
            case 2:
                return Matrix33.rotationZ(angle);
            default:
                System.out.println("Choisir un axe!");
                System.exit(-1);
        }
        return Matrix33.I;
    }

    @Override
    public void testScene() throws Exception {
        scene().clear();

        calculDeFrames();

        Matrix33 rot = rotation(axe);

        initObjet(objetCourant);

        //representable.setRotation(representable.new Rotation(rot, Point3D.O0));
        // Rotation autour de l'axe des X
    }

    @Override
    public void finit() {
    }

    @Override
    public void afterRenderFrame() {
    }

}
