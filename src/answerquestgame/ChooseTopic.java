/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Assets;
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
    
    public ChooseTopic(Game game) {
        this.game = game;
    }
    
    public void init() {
        sleep = 0;
        mathButton = new NavigationButton((game.getWidth()/2 - 100), 180, 200, 100, NavigationButton.NavButtonType.MATH, game);
        spellingButton = new NavigationButton((game.getWidth()/2 - 100), 330, 200, 100, NavigationButton.NavButtonType.SPELLING, game);
        geoButton = new NavigationButton((game.getWidth()/2 - 100), 480, 200, 100, NavigationButton.NavButtonType.GEO, game);
    }
    
    public void setSleep() {
        sleep = 0;
    }
    
    public NavigationButton getMathButton() {
        return mathButton;
    }
    
    public NavigationButton getSpellingButton() {
        return spellingButton;
    }
    
    public NavigationButton getGeoButton() {
        return geoButton;
    }
    
    public void tick() {
        if (mathButton.isPressed() && sleep > 5) {
            game.getLevel().setTopic(1);
            game.setScreen(ScreenType.LEVEL);
        }
        if (spellingButton.isPressed()) {
            game.getLevel().setTopic(2);
            game.setScreen(ScreenType.LEVEL);
        }
        if (geoButton.isPressed()) {
            game.getLevel().setTopic(3);
            game.setScreen(ScreenType.LEVEL);
        }
        sleep++;
    }
    
    public void render(Graphics g) {
        g.drawImage(Assets.chooseScreen, 0, 0, game.getWidth(), game.getHeight(), null);
        mathButton.render(g);
        spellingButton.render(g);
        geoButton.render(g);
    }
}
