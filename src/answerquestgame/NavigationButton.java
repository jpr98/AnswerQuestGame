/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Helpers.*;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class NavigationButton extends Item {
    private int width;
    private int height;
    private NavButtonType type;
    private Game game;
    
    public enum NavButtonType {
        NEXTLEVEL
    }
    
    public NavigationButton(int x, int y, int width, int height, NavButtonType type, Game game) {
        super(x,y);
        this.width = width;
        this.height = height;
        this.type = type;
        this.game = game;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
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

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
       switch(type) {
           case NEXTLEVEL:
               if (isPressed()) {
                   g.drawImage(Assets.nextLevelButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.nextLevelButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
       }
    }
}
