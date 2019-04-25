/*
 * Assets class loads images and sound into the game
 */
package answerquestgame.Helpers;
import answerquestgame.Helpers.ImageLoader;
import answerquestgame.Helpers.SpriteSheet;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {
    //  IMAGES
    public static BufferedImage background;
    public static BufferedImage balloon;
    public static BufferedImage button;
    public static BufferedImage buttonClicked;
    public static BufferedImage timer;
    public static BufferedImage startButton;
    public static BufferedImage startButtonClicked;
    public static BufferedImage menuBackground;
    //  ANIMATION
    public static BufferedImage titleSprites;
    public static BufferedImage titleMoving[];

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
        timer = ImageLoader.loadImage("/images/timer.png");
        startButton = ImageLoader.loadImage("/images/Menu/startButtonNormal.png");
        startButtonClicked = ImageLoader.loadImage("/images/Menu/startButtonClicked.png");
        menuBackground = ImageLoader.loadImage("/images/Menu/menu.png");
        
        //  ANIMATION   
        titleSprites = ImageLoader.loadImage("/images/Menu/answerQuest.png");
        SpriteSheet titleSpriteSheet = new SpriteSheet(titleSprites);
        
        titleMoving = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            titleMoving[i] = titleSpriteSheet.crop(0, i * 60, 400, 60);
        }

    }
}
