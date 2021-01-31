/*
 * 2013 Manuel Dahmen
 */
package tests2.dossierimages;

import one.empty3.library.ECBufferedImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dossier {
    public int limite = Integer.MAX_VALUE;
    private ArrayList<ECBufferedImage> images = new ArrayList<ECBufferedImage>();

    public void addDossier(File f) {
        if (f != null && f.exists() && f.isDirectory() && images.size() < limite) {
            File[] listFiles = f.listFiles();

            for (File l : listFiles) {

                awaitForImage(l);

            }
        }
        //System.out.println("Images size: " + images.size());
    }

    public void awaitForImage(File f) {
        if (f != null && f.exists() && images.size() < limite) {
            try {
                BufferedImage read = ImageIO.read(f);

                if (read != null) {
                    images.add(new ECBufferedImage(read));
                }
            } catch (IOException ex) {
                Logger.getLogger(Dossier.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    public void awaitForRemove(ECBufferedImage i) {
        images.remove(i);
    }

    public void run() {
    }

    public ArrayList<ECBufferedImage> getImages() {
        return images;
    }


}