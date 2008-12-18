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
public class Die extends Observable {

    private int faceValue = -1;
    
    public int roll() {
        
        Integer a;
        return 1;
    }
    
    public void display() {
        
    }
    
    public void addObserver(Observer observer) {
        this.addObserver(observer);
    }
}
