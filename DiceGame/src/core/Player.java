/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author arnaud
 */
public class Player extends Observable {

    private String name = "Unamed";
    
    private int score = 0;
    
    public void Player() {
        
    }
    
        public void Player(String name) {
        
            this.name = name;
    }
    
    public void display() {
        
                       this.setChanged();
       this.notifyObservers();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
