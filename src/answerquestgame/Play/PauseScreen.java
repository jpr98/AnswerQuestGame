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
    private NavigationButton continueButton;
    
    public PauseScreen(Level level) {
        this.level = level;
    }
    
    public void init() {
        Assets.init();
        
        homeButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 180, 200, 100, NavButtonType.HOME, level.getGame());
        restartButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 330, 200, 100, NavButtonType.RESTART, level.getGame());
        continueButton = new NavigationButton((level.getGame().getWidth()/2 - 100), 480, 200, 100, NavButtonType.CONTINUE, level.getGame());
    }
    
    public void render(Graphics g) {
        switch (level.getLevelNumber()) {
            case ONE:
                g.drawImage(Assets.level1Pause, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                break;
            case TWO:
                g.drawImage(Assets.level2Pause, 0, 0, level.getGame().getWidth(), level.getGame().getHeight(), null);
                break;
            case THREE:
                break;
            case FOUR:
                break;
        }
        
        homeButton.render(g);
        restartButton.render(g);
        continueButton.render(g);
    }
    
}
