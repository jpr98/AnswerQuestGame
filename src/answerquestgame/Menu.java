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
    private Animation titleAnimation;

    public Menu(Game game) {
        this.game = game;
    }

    public void init() {
        Assets.init();
        titleAnimation = new Animation(Assets.titleMoving, 100);
        startButton = new MenuButton((game.getWidth()/2 - 100), 220, 100, 200, MenuButtonType.START, this);
    }
    
    public Game getGame() {
        return game;
    }

    public MenuButton getStartButton() {
        return startButton;
    }

    public void tick() {
        titleAnimation.tick();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.menuBackground, 0, 0, getGame().getWidth(), getGame().getHeight(), null);
        startButton.render(g);
        g.drawImage(titleAnimation.getCurrentFrame(), 30,70, 550, 120, null);
    }
}
