/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

/**
 *
 * @author User
 */
public class Score {
    private String myName;
    private int myScore;
    
    public Score(String name, int score) 
    {
        this.myName = name;
        this.myScore = score;
    }
    
    public int getScore()
    {
        return this.myScore;
    }
    
     public String getName()
    {
        return this.myName;
    }
}
