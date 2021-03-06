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
    private int oWidth;
    private int oHeight;
    private NavButtonType type;
    private Game game;
    
    public enum NavButtonType {
        NEXTLEVEL, HOME, TUTORIAL, RESTART, BACK, MATH, SPELLING, GEO
    }
    
    /**
     * Creates a NavigationButton object with the given attributes
     * @param x
     * @param y
     * @param width
     * @param height
     * @param type
     * @param game 
     */
    public NavigationButton(int x, int y, int width, int height, NavButtonType type, Game game) {
        super(x,y);
        this.width = width;
        this.height = height;
        oWidth = width;
        oHeight = height;
        this.type = type;
        this.game = game;
    }
    
    /**
     * Sets the object's width
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Sets the object's height
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Returns the object's width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the object's height
     * @return 
     */
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

    /**
     * Checks if the buttons is pressed
     * @return boolean
     */
    public boolean isPressed() {
        int xCoord = game.getMouseManager().getX();
        int yCoord = game.getMouseManager().getY();
        boolean mouseClicked = game.getMouseManager().isLeft() || game.getMouseManager().isRight();
        //game.getMouseManager().setLeft(false);
        game.getMouseManager().setRight(false);
        return mouseClicked && getPerimeter().contains(xCoord, yCoord);
    }

    public void hover() {
        if (width < oWidth + 20) {
            x--;
            width += 2;
        }
        if (height < oHeight + 20) {
            y--;
            height += 2;
        }
    }
    
    public void hoverBack() {
        if (width > oWidth) {
            x++;
            width -= 2;
        }
        if (height > oHeight) {
            y++;
            height -= 2;
        }
    }
    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        int xCoord = game.getMouseManager().getX();
        int yCoord = game.getMouseManager().getY();
        if (getPerimeter().contains(xCoord, yCoord)) {
            hover();
        } else {
            hoverBack();
        }
    }

    /**
     * Draws objects each frame
     * @param g 
     */
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
            case HOME:
               if (isPressed()) {
                   g.drawImage(Assets.homeButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.homeButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case TUTORIAL:
               if (isPressed()) {
                   g.drawImage(Assets.tutorialButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.tutorialButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case RESTART:
               if (isPressed()) {
                   g.drawImage(Assets.restartButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.restartButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case BACK:
               if (isPressed()) {
                   g.drawImage(Assets.backButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.backButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case MATH:
               if (isPressed()) {
                   g.drawImage(Assets.mathButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.mathButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case SPELLING:
               if (isPressed()) {
                   g.drawImage(Assets.spellingButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.spellingButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
            case GEO:
               if (isPressed()) {
                   g.drawImage(Assets.geoButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
               } else {
                   g.drawImage(Assets.geoButton, getX(), getY(), getWidth(), getHeight(), null);
               }
               break;
       }
    }
}
