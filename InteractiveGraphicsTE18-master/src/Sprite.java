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

    public Sprite(int x, int y) {
        this.width = x;
        this.height = y;
        pixels = new int[x * y];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFFFFFF;
        }
    }

    public Sprite(String path, int x, int y) {
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
        boundingBox = new Rectangle(x, y, width, height);
    }
/*
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
*/


    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }

}

