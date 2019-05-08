/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Sizes;
import answerquestgame.Play.Level.LevelNumber;
import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class ChooseTopic {
    private final Game game;
    private int sleep;
    private NavigationButton mathButton;
    private NavigationButton spellingButton;
    private NavigationButton scienceButton;
    private NavigationButton backButton;
    private boolean toGame;
    
    /**
     * Creates a ChooseTopic for a game
     * @param game 
     */
    public ChooseTopic(Game game) {
        this.game = game;
    }
    
    /**
     * Initializes the ChooseTopic object
     */
    public void init() {
        sleep = 0;
        mathButton = new NavigationButton(Sizes.topButton, NavigationButton.NavButtonType.MATH, game);
        spellingButton = new NavigationButton(Sizes.midButton, NavigationButton.NavButtonType.SPELLING, game);
        scienceButton = new NavigationButton(Sizes.botButton, NavigationButton.NavButtonType.SCIENCE, game);
        backButton = new NavigationButton(Sizes.backButton, NavigationButton.NavButtonType.BACK, game);
    }
    
    /**
     * Resets the sleep variable to zero
     */
    public void setSleep() {
        sleep = 0;
    }
    
    /**
     * Sets a boolean if the next screen will be a Level
     * @param toGame 
     */
    public void setNextScreenGame(boolean toGame) {
        this.toGame = toGame;
    }
    
    /**
     * Returns the math button
     * @return mathButton
     */
    public NavigationButton getMathButton() {
        return mathButton;
    }
    
    /**
     * Returns the spelling button
     * @return spelling
     */
    public NavigationButton getSpellingButton() {
        return spellingButton;
    }
    
    /**
     * Returns the science button
     * @return scienceButton
     */
    public NavigationButton getScienceButton() {
        return scienceButton;
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        mathButton.tick();
        spellingButton.tick();
        scienceButton.tick();
        backButton.tick();
        
        ScreenType nextScreen;
        if (toGame) {
            game.getLevel().setLevelNumber(LevelNumber.ONE);
            nextScreen = ScreenType.LEVEL;
        } else {
            nextScreen = ScreenType.LEADERBOARD;
        }
        if (mathButton.isPressed() && sleep > 47) {
            if (toGame) {
                game.getLevel().init(1);
            } else {
                game.getLeaderboard().init(1);
            }
            game.setScreen(nextScreen);
        }
        if (spellingButton.isPressed() && sleep > 47) {
            if (toGame) {
                game.getLevel().init(2);
            } else {
                game.getLeaderboard().init(2);
            }
            game.setScreen(nextScreen);
        }
        if (scienceButton.isPressed() && sleep > 47) {
            if (toGame) {
                game.getLevel().init(3);
            } else {
                game.getLeaderboard().init(3);
            }
            game.setScreen(nextScreen);
        }
        if (backButton.isPressed() && sleep > 45) {
            game.setScreen(ScreenType.MENU);
        }
        sleep++;
    }
    
    /**
     * Draws objects each frame
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Assets.chooseScreen, 0, 0, game.getWidth(), game.getHeight(), null);
        mathButton.render(g);
        spellingButton.render(g);
        scienceButton.render(g);
        backButton.render(g);
    }
}
