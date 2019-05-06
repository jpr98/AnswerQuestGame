/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Assets;
import answerquestgame.Play.Level;
import answerquestgame.Play.Level.LevelNumber;
import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class ChooseTopic {
    private Game game;
    private int sleep;
    private NavigationButton mathButton;
    private NavigationButton spellingButton;
    private NavigationButton geoButton;
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
        mathButton = new NavigationButton((game.getWidth()/2 - 100), 180, 200, 100, NavigationButton.NavButtonType.MATH, game);
        spellingButton = new NavigationButton((game.getWidth()/2 - 100), 330, 200, 100, NavigationButton.NavButtonType.SPELLING, game);
        geoButton = new NavigationButton((game.getWidth()/2 - 100), 480, 200, 100, NavigationButton.NavButtonType.GEO, game);
        backButton = new NavigationButton(37, 720, 120, 60, NavigationButton.NavButtonType.BACK, game);
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
     * Returns the geography button
     * @return geoButton
     */
    public NavigationButton getGeoButton() {
        return geoButton;
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        mathButton.tick();
        spellingButton.tick();
        geoButton.tick();
        backButton.tick();
        
        ScreenType nextScreen;
        if (toGame) {
            game.getLevel().setLevelNumber(LevelNumber.ONE);
            nextScreen = ScreenType.LEVEL;
        } else {
            nextScreen = ScreenType.LEADERBOARD;
        }
        if (mathButton.isPressed() && sleep > 30) {
            if (toGame) {
                game.getLevel().init(1);
            } else {
                game.getLeaderboard().init(1);
            }
            game.setScreen(nextScreen);
        }
        if (spellingButton.isPressed() && sleep > 30) {
            if (toGame) {
                game.getLevel().init(2);
            } else {
                game.getLeaderboard().init(2);
            }
            game.setScreen(nextScreen);
        }
        if (geoButton.isPressed() && sleep > 30) {
            if (toGame) {
                game.getLevel().init(3);
            } else {
                game.getLeaderboard().init(3);
            }
            game.setScreen(nextScreen);
        }
        if (backButton.isPressed() && sleep > 30) {
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
        geoButton.render(g);
        backButton.render(g);
    }
}
