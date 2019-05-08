/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import answerquestgame.Game;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class Sizes {
    private Game game;
    
    // Buttons' Positions
    /**
     * Top button sizes in vertical stack order
     */
    public static SizeAndPos topButton;
    /**
     * Mid button sizes in vertical stack order
     */
    public static SizeAndPos midButton;
    /**
     * Bottom button sizes in vertical stack order
     */
    public static SizeAndPos botButton;
    /**
     * Back button sizes
     */
    public static SizeAndPos backButton;
    /**
     * Next button sizes
     */
    public static SizeAndPos nextButton;
    
    /**
     * Title size and position
     */
    public static SizeAndPos title;
    
    /**
     * Player 1 size and position
     */
    public static SizeAndPos player1;
    
    /**
     * Player 2 size and position
     */
    public static SizeAndPos player2;
    
    public static int playerBottomLimit;
    public static int playerTopLimit;
    
    public static SizeAndPos leftButtonP1;
    public static SizeAndPos rightButtonP1;
    public static SizeAndPos leftButtonP2;
    public static SizeAndPos rightButtonP2;
    public static SizeAndPos timerP1;
    public static SizeAndPos timerP2;
    public static int playerMoveUp;
    public static int playerMoveDown;
    
    public static Rectangle questionArea1;
    public static Rectangle questionArea2;
    public static Rectangle answerArea1P1;
    public static Rectangle answerArea2P1;
    public static Rectangle answerArea1P2;
    public static Rectangle answerArea2P2;
    
    public static SizeAndPos overall;
    /**
     * Constructor
     * @param game 
     */
    public Sizes(Game game) {
        this.game = game;
    }
    
    public void init() {
        topButton = new SizeAndPos(widthFor(0.33), heightFor(0.275), heightFor(0.125), widthFor(0.33));
        midButton = new SizeAndPos(widthFor(0.33), heightFor(0.4625), heightFor(0.125), widthFor(0.33));
        botButton = new SizeAndPos(widthFor(0.33), heightFor(0.65), heightFor(0.125), widthFor(0.33));
        backButton = new SizeAndPos(widthFor(0.06166), heightFor(0.9), heightFor(0.075), widthFor(0.2));
        nextButton = new SizeAndPos(widthFor(0.33), heightFor(0.775), heightFor(0.125), widthFor(0.33));
        title = new SizeAndPos(widthFor(0.0416), heightFor(0.035), heightFor(0.15), widthFor(0.9166));
        player1 = new SizeAndPos(widthFor(0.16), heightFor(0.6625), heightFor(0.1375), widthFor(0.1833));
        player2 = new SizeAndPos(widthFor(0.633), heightFor(0.6625), heightFor(0.1375), widthFor(0.1833));
        overall = new SizeAndPos(widthFor(0.2583), heightFor(0.375), heightFor(0.3125), widthFor(0.5));
        playerBottomLimit = heightFor(0.6625);
        playerTopLimit = heightFor(0.2237);
        
        leftButtonP1 = new SizeAndPos(widthFor(0.0283), heightFor(0.8462), heightFor(0.1125), widthFor(0.2083));
        rightButtonP1 = new SizeAndPos(widthFor(0.2566), heightFor(0.8462), heightFor(0.1125), widthFor(0.2083));
        leftButtonP2 = new SizeAndPos(widthFor(0.53), heightFor(0.8462), heightFor(0.1125), widthFor(0.2083));
        rightButtonP2 = new SizeAndPos(widthFor(0.7583), heightFor(0.8462), heightFor(0.1125), widthFor(0.2083));
        
        timerP1 = new SizeAndPos(widthFor(0), heightFor(0.066), heightFor(0.018), widthFor(0.49166));
        timerP2 = new SizeAndPos(widthFor(0.5066), heightFor(0.066), heightFor(0.018), widthFor(0.49166));
        playerMoveUp = heightFor(0.1);
        playerMoveDown = heightFor(0.05);
        questionArea1 = new Rectangle(widthFor(0), heightFor(0), widthFor(0.5), heightFor(0.05875));
        questionArea2 = new Rectangle(widthFor(0.5083), heightFor(0), widthFor(0.5), heightFor(0.05875));
        
        answerArea1P1 = new Rectangle(widthFor(0.0283), heightFor(0.8462), widthFor(0.2083), heightFor(0.1125));
        answerArea2P1 = new Rectangle(widthFor(0.2566), heightFor(0.8462), widthFor(0.2083), heightFor(0.1125));
        answerArea1P2 = new Rectangle(widthFor(0.53), heightFor(0.8462), widthFor(0.2083), heightFor(0.1125));
        answerArea2P2 = new Rectangle(widthFor(0.7583), heightFor(0.8462), widthFor(0.2083), heightFor(0.1125));
    }
    
    
    private int heightFor(double ratio) {
        return (int) Math.round(game.getHeight()*ratio);
    }
    
    private int widthFor(double ratio) {
        return (int) Math.round(game.getWidth()*ratio);
    }
    
    public class SizeAndPos {
        public int x;
        public int y;
        public int height;
        public int width;
    
        public SizeAndPos(int x, int y, int height, int width) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }
    }
}
