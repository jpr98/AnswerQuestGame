/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class Player extends Item {
    private int height;
    private int width;
    private Game game;
    private boolean isPlayer1;
    private int moveCounter;
    private boolean canMove;
    
    /**
     * Constructor
     * @param width
     * @param height
     * @param game
     */
    public Player(int x, int y, int height, int width, boolean isPlayer1, Game game) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.isPlayer1 = isPlayer1;
        this.game = game;
        moveCounter = 0;
        canMove = true;
    }

     /**
     * Gets height
     * @return height
     */   
    public int getHeight(){
        return height;
    }
    
    /**
     * Gets width
     * @return width
     */
    public int getWidth(){
        return width;
    }

    /**
     * Sets height
     * @return height
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Sets width
     * @return width
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Moves the player over a period of time
     */
    private void moving() {
        if (moveCounter > 0) {
            setY(getY()-1);
            moveCounter--;
        }
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        // Checking if player reaches top
        if (getY() + getHeight() < 179) {
            canMove = false;
            // manage winning
        } 

        // Moving player with corresponding keys
        if (isPlayer1 && canMove) {
            if (game.getKeyManager().left) {
                moveCounter = 80;
            }
            if (game.getKeyManager().right) {
                moveCounter = 80;
            }
            moving();
        } else if (!isPlayer1 && canMove){
            if (game.getKeyManager().a) {
                moveCounter = 80;
            }
            if (game.getKeyManager().d) {
                moveCounter = 80;
            }
            moving();
        }
    }

    /**
     * Draw images for each frame
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.balloon, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
