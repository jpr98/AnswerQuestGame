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
    public static BufferedImage backgroundOne;
    public static BufferedImage backgroundTwo;
    public static BufferedImage button;
    public static BufferedImage buttonClicked;
    public static BufferedImage timer;
    
    // MENU IMAGES
    public static BufferedImage startButton;
    public static BufferedImage startButtonClicked;
    public static BufferedImage tutorialButton;
    public static BufferedImage tutorialButtonClicked;
    public static BufferedImage menuBackground;
    
    //  ANIMATION
    public static BufferedImage titleSprites;
    public static BufferedImage titleMoving[];
    public static BufferedImage balloonSprites;
    public static BufferedImage balloonMoving[];
    public static BufferedImage balloonFallingSprites;
    public static BufferedImage balloonFalling[];
    public static BufferedImage rocketSprites;
    public static BufferedImage rocketMoving[];

    //  SOUNDS


    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        //  IMAGES
        backgroundOne = ImageLoader.loadImage("/images/level1.png");
        backgroundTwo = ImageLoader.loadImage("/images/level2.png");
        button = ImageLoader.loadImage("/images/buttonNormal.png");
        buttonClicked = ImageLoader.loadImage("/images/buttonClicked.png");
        timer = ImageLoader.loadImage("/images/timer.png");
        // MENU
        startButton = ImageLoader.loadImage("/images/Menu/startButtonNormal.png");
        startButtonClicked = ImageLoader.loadImage("/images/Menu/startButtonClicked.png");
        tutorialButton = ImageLoader.loadImage("/images/Menu/tutorialButtonNormal.png");
        tutorialButtonClicked = ImageLoader.loadImage("/images/Menu/tutorialButtonClicked.png");
        menuBackground = ImageLoader.loadImage("/images/Menu/menu.png");
        
        //  ANIMATION   
        titleSprites = ImageLoader.loadImage("/images/Menu/answerQuest.png");
        SpriteSheet titleSpriteSheet = new SpriteSheet(titleSprites);
        titleMoving = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            titleMoving[i] = titleSpriteSheet.crop(0, i * 60, 400, 60);
        }
        
        balloonSprites = ImageLoader.loadImage("/images/balloon.png");
        SpriteSheet balloonSpriteSheet = new SpriteSheet(balloonSprites);
        balloonMoving = new BufferedImage[4];
        for (int i=0; i<4; i++) {
            balloonMoving[i] = balloonSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        balloonFallingSprites = ImageLoader.loadImage("/images/poppedBalloon.png");
        SpriteSheet balloonFallingSpriteSheet = new SpriteSheet(balloonFallingSprites);
        balloonFalling = new BufferedImage[5];
        for (int i=0; i<5; i++) {
            balloonFalling[i] = balloonFallingSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        rocketSprites = ImageLoader.loadImage("/images/rocket.png");
        SpriteSheet rocketSpriteSheet = new SpriteSheet(rocketSprites);
        rocketMoving = new BufferedImage[3];
        for (int i=0; i<3; i++) {
            rocketMoving[i] = rocketSpriteSheet.crop(0, i * 80, 64, 80);
        }
        

    }
}
