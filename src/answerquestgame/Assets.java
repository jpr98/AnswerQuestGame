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
    public static BufferedImage buttonClicked;
    //  SOUNDS
    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        //  IMAGES
        background = ImageLoader.loadImage("/images/level1.png");
        balloon = ImageLoader.loadImage("/images/balloon.png");
        button = ImageLoader.loadImage("/images/buttonNormal.png");
        buttonClicked = ImageLoader.loadImage("/images/buttonClicked.png");

    }
}
