/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean left;
    private boolean right;
    private int x;
    private int y;

    public MouseManager(int x, int y) {
        // setting values to fix bug on first click player moving to origin
        this.x = x;
        this.y = y;
    }
    
    public MouseManager() {
        
    }

    /**
     * Get x position
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get y position
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Check if left is on or off
     * @return left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Check if right is on or off
     * @return right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Set left value
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }
    
    /**
     * Set right value
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X:" + e.getX());
        System.out.println("Y:" + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            left = true;
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            left = false;
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
