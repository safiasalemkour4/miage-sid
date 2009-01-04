/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.Observable;

/**
 *
 * @author arnaud
 */
public class Die extends Observable {

    private int faceValue = -1;
    
    public void roll() {
        
        Integer a;
        this.faceValue = (int)(Math.random()*10);
        
    }
    
    public void display() {

               this.setChanged();
       this.notifyObservers();
    }

    public int getFaceValue() {
        return faceValue;
    }
    
    
}
