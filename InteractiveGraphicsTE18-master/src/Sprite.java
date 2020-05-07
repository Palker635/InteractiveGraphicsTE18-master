import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

/**
 * This is a class
 * Created 2020-03-26
 *
 * @author Magnus Silverdal
 */
public class Sprite {
    private int width;
    private int height;
    private int[] pixels;
    private Rectangle boundingBox;

    public Sprite(int w, int h) {
        this.width = w;
        this.height = h;
        pixels = new int[w * h];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFFFFFF;
        }
    }

    public Sprite(int w, int h, int col) {
        this.width = w;
        this.height = h;
        pixels = new int[w * h];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = col;
        }
    }


    public Sprite(String path) {
        BufferedImage image = null;
        try {
            BufferedImage rawImage = ImageIO.read(new File(path));
            // Since the type of image is unknown it must be copied into an INT_RGB
            image = new BufferedImage(rawImage.getWidth(), rawImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            image.getGraphics().drawImage(rawImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        boundingBox = new Rectangle(width, height);
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }



    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }

}

