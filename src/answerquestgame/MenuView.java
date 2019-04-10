/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Canvas;
import java.awt.Dimension;

/**
 *
 * @author juanpabloramos
 */
public class MenuView {
    private Canvas canvas;
    private int width;
    private int height;
    private Game game;
    
    
    public MenuView(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        this.game = game;
        createDisplay();
    }
    
    public void createDisplay() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        game.getDisplay().getJframe().add(canvas);
    }

    
}
