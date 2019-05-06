/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class SettingsButton extends Item {
    private int height;
    private int width;
    private Game game;
    
    public SettingsButton(int x, int y, int height, int width, Game game) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.game = game;
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
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }

     /**
     * Creates a Rectangle object the size of the ball
     * @return rectangle
     */
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isPressed() {
        int xCoord = game.getMouseManager().getX();
        int yCoord = game.getMouseManager().getY();
        boolean mouseClicked = game.getMouseManager().isLeft() || game.getMouseManager().isRight();
        return mouseClicked && getPerimeter().contains(xCoord, yCoord);
    }
    
    /**
     * Sets width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (isPressed()) {
            g.drawImage(Assets.startButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.startButton, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}
