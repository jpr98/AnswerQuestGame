/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Graphics;

import answerquestgame.Helpers.*;

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

    public Menu(Game game) {
        this.game = game;
    }

    public void init() {
        Assets.init();
        titleAnimation = new Animation(Assets.titleMoving, 180);
        startButton = new MenuButton((game.getWidth()/2 - 100), 220, 100, 200, MenuButtonType.START, this);
        highscoreButton = new MenuButton((game.getWidth()/2 - 100), 370, 100, 200, MenuButtonType.HIGHSCORE, this);
        backButton = new NavigationButton(37, 720, 120, 60, NavigationButton.NavButtonType.BACK, this.getGame());
        tutorialButton = new MenuButton((game.getWidth()/2 - 100), 520, 100, 200, MenuButtonType.INSTRUCTIONS, this);
        
        showTutorial = false;
    }
    
    public Game getGame() {
        return game;
    }

    public MenuButton getStartButton() {
        return startButton;
    }
    
    public MenuButton getTutorialButton() {
        return tutorialButton;
    }
    
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
