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
    private NavigationButton musicButton;
    private int sleep;
    
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
        titleAnimation = new Animation(Assets.titleMoving, 180);
        startButton = new MenuButton(Sizes.topButton, MenuButtonType.START, this);
        highscoreButton = new MenuButton(Sizes.midButton, MenuButtonType.HIGHSCORE, this);
        backButton = new NavigationButton(Sizes.backButton, NavigationButton.NavButtonType.BACK, this.getGame());
        tutorialButton = new MenuButton(Sizes.botButton, MenuButtonType.INSTRUCTIONS, this);
        musicButton = new NavigationButton(Sizes.musicButton, NavigationButton.NavButtonType.MUSIC, this.getGame());
        showTutorial = false;
        sleep = 0;
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
     * Returns the music button
     * @return musicButton
     */
    public NavigationButton getmusicButton() {
        return musicButton;
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
        
        
        sleep++;
        
        startButton.tick();
        highscoreButton.tick();
        tutorialButton.tick();
        backButton.tick();
        musicButton.tick();
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
            musicButton.render(g);
            g.drawImage(titleAnimation.getCurrentFrame(), Sizes.title.x, Sizes.title.y, Sizes.title.width, Sizes.title.height, null);
        }
    }
}
