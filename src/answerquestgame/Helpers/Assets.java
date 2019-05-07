/*
 * Assets class loads images and sound into the game
 */
package answerquestgame.Helpers;
import answerquestgame.Helpers.ImageLoader;
import answerquestgame.Helpers.SpriteSheet;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {
    
    // LEVEL 1
    public static BufferedImage backgroundOne;
    public static BufferedImage balloonSprites;
    public static BufferedImage balloonMoving[];
    public static BufferedImage balloonFallingSprites;
    public static BufferedImage balloonFalling[];
    public static BufferedImage level1Pause;
    public static BufferedImage level1Player1Win;
    public static BufferedImage level1Player2Win;
    public static BufferedImage level1tutorial;
    
    // LEVEL 2
    public static BufferedImage backgroundTwo;
    public static BufferedImage rocketSprites;
    public static BufferedImage rocketMoving[];
    public static BufferedImage rocketFallingSprites;
    public static BufferedImage rocketFalling[];
    public static BufferedImage level2Pause;
    public static BufferedImage level2Player1Win;
    public static BufferedImage level2Player2Win;
    public static BufferedImage level2tutorial;
    
    // LEVEL 3
    public static BufferedImage backgroundThree;
    public static BufferedImage shipSprites;
    public static BufferedImage shipMoving[];
    public static BufferedImage shipFallingSprites;
    public static BufferedImage shipFalling[];
    public static BufferedImage level3Pause;
    public static BufferedImage level3Player1Win;
    public static BufferedImage level3Player2Win;
    public static BufferedImage level3tutorial;
    
    // MENU
    public static BufferedImage startButton;
    public static BufferedImage startButtonClicked;
    public static BufferedImage tutorialButton;
    public static BufferedImage tutorialButtonClicked;
    public static BufferedImage menuBackground;
    public static BufferedImage highscoreButton;
    public static BufferedImage highscoreButtonClicked;
    public static BufferedImage titleSprites;
    public static BufferedImage titleMoving[];
    public static BufferedImage chooseScreen;
    public static BufferedImage mathButton;
    public static BufferedImage mathButtonClicked;
    public static BufferedImage spellingButton;
    public static BufferedImage spellingButtonClicked;
    public static BufferedImage scienceButton;
    public static BufferedImage scienceButtonClicked;
    public static BufferedImage leaderboard;
    
    // GENERAL
    public static BufferedImage button;
    public static BufferedImage buttonClicked;
    public static BufferedImage timer;
    public static BufferedImage nextLevelButton;
    public static BufferedImage nextLevelButtonClicked;
    public static BufferedImage homeButton;
    public static BufferedImage homeButtonClicked;
    public static BufferedImage restartButton;
    public static BufferedImage restartButtonClicked;
    public static BufferedImage backButton;
    public static BufferedImage backButtonClicked;
    public static BufferedImage overallWinner1;
    public static BufferedImage overallWinner2;

    //  SOUNDS


    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        // LEVEL 1
        backgroundOne = ImageLoader.loadImage("/images/Level1/level1.png");
        level1Player1Win = ImageLoader.loadImage("/images/Level1/level1player1.png");
        level1Player2Win = ImageLoader.loadImage("/images/Level1/level1player2.png");
        level1Pause = ImageLoader.loadImage("/images/Level1/level1Pause.png");
        level1tutorial = ImageLoader.loadImage("/images/Level1/level1tutorial.png");
        
        balloonSprites = ImageLoader.loadImage("/images/Level1/balloon.png");
        SpriteSheet balloonSpriteSheet = new SpriteSheet(balloonSprites);
        balloonMoving = new BufferedImage[4];
        for (int i=0; i<4; i++) {
            balloonMoving[i] = balloonSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        balloonFallingSprites = ImageLoader.loadImage("/images/Level1/poppedBalloon.png");
        SpriteSheet balloonFallingSpriteSheet = new SpriteSheet(balloonFallingSprites);
        balloonFalling = new BufferedImage[5];
        for (int i=0; i<5; i++) {
            balloonFalling[i] = balloonFallingSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        // LEVEL 2
        backgroundTwo = ImageLoader.loadImage("/images/Level2/level2.png");
        level2Player1Win = ImageLoader.loadImage("/images/Level2/level2player1.png");
        level2Player2Win = ImageLoader.loadImage("/images/Level2/level2player2.png");
        level2Pause = ImageLoader.loadImage("/images/Level2/level2Pause.png");
        level2tutorial = ImageLoader.loadImage("/images/Level2/level2tutorial.png");
        
        rocketSprites = ImageLoader.loadImage("/images/Level2/rocket.png");
        SpriteSheet rocketSpriteSheet = new SpriteSheet(rocketSprites);
        rocketMoving = new BufferedImage[3];
        for (int i=0; i<3; i++) {
            rocketMoving[i] = rocketSpriteSheet.crop(0, i * 80, 64, 80);
        }
        
        rocketFallingSprites = ImageLoader.loadImage("/images/Level2/rocketFalling.png");
        SpriteSheet rocketFallingSpriteSheet = new SpriteSheet(rocketFallingSprites);
        rocketFalling = new BufferedImage[6];
        for (int i=0; i<6; i++) {
            rocketFalling[i] = rocketFallingSpriteSheet.crop(0, i * 160, 128, 160);
        }
        
        // LEVEL 3
        backgroundThree = ImageLoader.loadImage("/images/Level3/level3.png");
        level3Pause = ImageLoader.loadImage("/images/Level3/level3pause.png");
        level3Player1Win = ImageLoader.loadImage("/images/Level3/level3player1.png");
        level3Player2Win = ImageLoader.loadImage("/images/Level3/level3player2.png");
        level3tutorial = ImageLoader.loadImage("/images/Level3/level3tutorial.png");
        
        shipSprites = ImageLoader.loadImage("/images/Level3/ship.png");
        SpriteSheet shipSpriteSheet = new SpriteSheet(shipSprites);
        shipMoving = new BufferedImage[6];
        for (int i=0; i<6; i++) {
            shipMoving[i] = shipSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        shipFallingSprites = ImageLoader.loadImage("/images/Level3/shipFalling.png");
        SpriteSheet shipFallingSpriteSheet = new SpriteSheet(shipFallingSprites);
        shipFalling = new BufferedImage[6];
        for (int i=0; i<6; i++) {
            shipFalling[i] = shipFallingSpriteSheet.crop(0, i * 64, 64, 64);
        }
        
        // MENU
        startButton = ImageLoader.loadImage("/images/Menu/startButtonNormal.png");
        startButtonClicked = ImageLoader.loadImage("/images/Menu/startButtonClicked.png");
        tutorialButton = ImageLoader.loadImage("/images/Menu/tutorialButtonNormal.png");
        tutorialButtonClicked = ImageLoader.loadImage("/images/Menu/tutorialButtonClicked.png");
        menuBackground = ImageLoader.loadImage("/images/Menu/menu1.png");
        highscoreButton = ImageLoader.loadImage("/images/Menu/highscoreButtonNormal.png");
        highscoreButtonClicked = ImageLoader.loadImage("/images/Menu/highscoreButtonClicked.png");
        chooseScreen = ImageLoader.loadImage("/images/choose.png");
        mathButton = ImageLoader.loadImage("/images/mathNormal.png");
        mathButtonClicked = ImageLoader.loadImage("/images/mathClicked.png");
        spellingButton = ImageLoader.loadImage("/images/spellingNormal.png");
        spellingButtonClicked = ImageLoader.loadImage("/images/spellingClicked.png");
        scienceButton = ImageLoader.loadImage("/images/scienceNormal.png");
        scienceButtonClicked = ImageLoader.loadImage("/images/scienceClicked.png");
        leaderboard = ImageLoader.loadImage("/images/highscore.png");
        
        titleSprites = ImageLoader.loadImage("/images/Menu/answerQuest.png");
        SpriteSheet titleSpriteSheet = new SpriteSheet(titleSprites);
        titleMoving = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            titleMoving[i] = titleSpriteSheet.crop(0, i * 60, 400, 60);
        }
        
        // GENERAL
        button = ImageLoader.loadImage("/images/buttonNormal.png");
        buttonClicked = ImageLoader.loadImage("/images/buttonClicked.png");
        timer = ImageLoader.loadImage("/images/timer.png");
        nextLevelButton = ImageLoader.loadImage("/images/nextLevelButtonNormal.png");
        nextLevelButtonClicked = ImageLoader.loadImage("/images/nextLevelButtonClicked.png");
        homeButton = ImageLoader.loadImage("/images/homeButtonNormal.png");
        homeButtonClicked = ImageLoader.loadImage("/images/homeButtonClicked.png");
        restartButton = ImageLoader.loadImage("/images/restartButtonNormal.png");
        restartButtonClicked = ImageLoader.loadImage("/images/restartButtonClicked.png");
        backButton = ImageLoader.loadImage("/images/backButtonNormal.png");
        backButtonClicked = ImageLoader.loadImage("/images/backButtonClicked.png");
        overallWinner1 = ImageLoader.loadImage("/images/overall1.png");
        overallWinner2 = ImageLoader.loadImage("/images/overall2.png");
    }
}
