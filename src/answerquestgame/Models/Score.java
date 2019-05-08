/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Models;

/**
 * Score Class
 * @author User
 */
public class Score {
    private String myName;
    private int myScore;
    
    /**
     * Constructor
     * @param name
     * @param score 
     */
    public Score(String name, int score) 
    {
        this.myName = name;
        this.myScore = score;
    }
    
    /**
     * Returns the score as integer
     * @return  score
     */
    public int getScore()
    {
        return this.myScore;
    }
    
    /**
     * Returns the user's name
     * @return name
     */
     public String getName()
    {
        return this.myName;
    }
}
