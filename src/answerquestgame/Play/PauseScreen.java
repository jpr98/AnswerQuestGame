/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.NavigationButton;
import answerquestgame.NavigationButton.NavButtonType;
import answerquestgame.Helpers.Assets;
import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class PauseScreen {
    private Level level;
    private NavigationButton homeButton;
    private NavigationButton restartButton;
    private NavigationButton tutorialButton;
    private NavigationButton backButton;
    private boolean showTutorial;
    
    /**
     * Creates a Pause Screen for a given level
     * @param level 
     */
    public PauseScreen(Level level) {
        this.level = level;
    }
    
    /**
     * Initializes the Pause screen object
     */
    public void init() {
        Assets.init();
        
        homeButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 180, 200, 100, NavButtonType.HOME, level.getGame());
        restartButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 330, 200, 100, NavButtonType.RESTART, level.getGame());
        tutorialButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 480, 200, 100, NavButtonType.TUTORIAL, level.getGame());
        backButton = new NavigationButton(37, 720, 120, 60, NavButtonType.BACK, level.getGame());
        
        showTutorial = false;
    }
    
    /**
     * Returns the home button
     * @return homeButton
     */
    public NavigationButton getHomeButton() {
        return homeButton;
    }
    
    /**
     * Returns the restart Button
     * @return restartButton
     */
    public NavigationButton getRestartButton() {
        return restartButton;
    }
    
    /**
     * Returns the tutorial button
     * @return tutorialButton
     */
    public NavigationButton getTutorialButton() {
        return tutorialButton;
    }
    
    /**
     * Returns the back button
     * @return backButton
     */
    public NavigationButton getBackButton() {
        return backButton;
    }
    
    /**
     * Sets whether to show the tutorial screen or not
     * @param tutorial 
     */
    public void setShowTutorial(boolean tutorial) {
        this.showTutorial = tutorial;
    }
    
    /**
     * Renders all the buttons
     * @param g 
     */
    private void renderButtons(Graphics g) {
        if (showTutorial) {
            backButton.render(g);
        } else {
            homeButton.render(g);
            restartButton.render(g);
            tutorialButton.render(g);
        }
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        backButton.tick();
        homeButton.tick();
        restartButton.tick();
        tutorialButton.tick();
    }
    
    /**
     * Draws objects each frame
     * @param g 
     */
    public void render(Graphics g) {
        switch (level.getLevelNumber()) {
            case ONE:
                if (showTutorial) {
                    g.drawImage(Assets.level1tutorial, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                } else {
                    g.drawImage(Assets.level1Pause, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                }
                break;
            case TWO:
                if (showTutorial) {
                    g.drawImage(Assets.level2tutorial, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                } else {
                    g.drawImage(Assets.level2Pause, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                }
                break;
            case THREE:
                if (showTutorial) {
                    g.drawImage(Assets.level3tutorial, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                } else {
                    g.drawImage(Assets.level3Pause, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                }
                break;
        }
        renderButtons(g);
    }
    
}
