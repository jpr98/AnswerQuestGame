/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import answerquestgame.Game;

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
