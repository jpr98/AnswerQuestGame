/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Graphics;

import answerquestgame.Helpers.*;
import answerquestgame.MenuButton.MenuButtonType;

/**
 *
 * @author juanpabloramos
 */
public class Menu {
    private Game game;
    private MenuButton startButton;
    private MenuButton highscoreButton;
    private MenuButton tutorialButton;
    private NavigationButton backButton;
    private Animation titleAnimation;
    
    private boolean showTutorial;

    /**
     * Creates a menu for a given game
     * @param game 
     */
    public Menu(Game game) {
        this.game = game;
    }

    /**
     * Initializes the game menu
     */
    public void init() {
        Assets.init();
        titleAnimation = new Animation(Assets.titleMoving, 180);
        startButton = new MenuButton((game.getWidth()/2 - 100), 220, 100, 200, MenuButtonType.START, this);
        highscoreButton = new MenuButton((game.getWidth()/2 - 100), 370, 100, 200, MenuButtonType.HIGHSCORE, this);
        backButton = new NavigationButton(37, 720, 120, 60, NavigationButton.NavButtonType.BACK, this.getGame());
        tutorialButton = new MenuButton((game.getWidth()/2 - 100), 520, 100, 200, MenuButtonType.INSTRUCTIONS, this);
        
        showTutorial = false;
    }
    
    /**
     * Returns the game object
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Returns the start button
     * @return startButton
     */
    public MenuButton getStartButton() {
        return startButton;
    }
    
    /**
     * Returns the highscore button
     * @return highscoreButton
     */
    public MenuButton getHighscoreButton() {
        return highscoreButton;
    }
    
    /**
     * Returns the tutorial button
     * @return tutorialButton
     */
    public MenuButton getTutorialButton() {
        return tutorialButton;
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        if (showTutorial) {
            if (backButton.isPressed()) {
                showTutorial = false;
            }
        } else {
            if (tutorialButton.isPressed()) {
                showTutorial = true;
            }
            titleAnimation.tick();
        }
    }

    /**
     * Draw images for each frame
     * @param g 
     */
    public void render(Graphics g) {
        if (showTutorial) {
            g.drawImage(Assets.level1tutorial, 0, 0, getGame().getWidth(), getGame().getHeight(), null);
            backButton.render(g);
        } else {
            g.drawImage(Assets.menuBackground, 0, 0, getGame().getWidth(), getGame().getHeight(), null);
            startButton.render(g);
            highscoreButton.render(g);
            tutorialButton.render(g);
            g.drawImage(titleAnimation.getCurrentFrame(), 30,70, 550, 120, null);
        }
    }
}
