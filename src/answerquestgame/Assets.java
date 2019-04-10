/*
 * Assets class loads images and sound into the game
 */
package answerquestgame;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {
    //  IMAGES
    public static BufferedImage background;
    public static BufferedImage balloon;
    public static BufferedImage button;
    //  SOUNDS
    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        //  IMAGES
        background = ImageLoader.loadImage("/images/background.gif");
        balloon = ImageLoader.loadImage("/images/balloon.gif");
        button = ImageLoader.loadImage("/images/dButton.gif");

    }
}
