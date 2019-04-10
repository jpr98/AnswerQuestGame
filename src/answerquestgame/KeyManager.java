/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class KeyManager implements KeyListener {
    public boolean left;
    public boolean right;
    public boolean a;
    public boolean d;

    private boolean keys[];

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keys[e.getKeyCode()]) {
            keys[e.getKeyCode()] = true;
            System.out.println("key pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void tick() {
        // setting values of pressed keys to directions
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
    }
}