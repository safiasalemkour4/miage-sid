package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		Die.java                                                                 *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object metier representant un de                          				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class Die extends Observable implements Serializable {

    /* La valeur du de */
    private int faceValue = -1;

    /**
     * Constructeur par defaut d'un de
     */
    
    public Die() {
        
    }
    
    /**
     * Methode roll
     * Permet de lancer un de et ainsi d'avoir une valeur compris entre 0 et 7
     */
    
    public void roll() {
        
        /* Un nombre aleatoire entre 0 et 7 */
        this.faceValue = new Random().nextInt(6)+1;
    }

    /**
     * Methode display
     * Permet de notifier les observers qu'il y a eu un changement
     * et qu'il faut donc mettre a jour la vue
     */

    public void display() {

       this.setChanged();
       this.notifyObservers();
    }

    /**
     * Getter faceValue
     * @return faceValue
     */
    
    public int getFaceValue() {

        return faceValue;
    }

    /**
     * Setter faceValue
     * @param faceValue the value
     */
    
    public void setFaceValue(int faceValue) {

        this.faceValue = faceValue;
    }
  
}
