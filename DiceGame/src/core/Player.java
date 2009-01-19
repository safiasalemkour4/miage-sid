package core;

import java.io.Serializable;
import java.util.Observable;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		Player.java                                                              *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object metier representant un joueur                      				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class Player extends Observable implements Serializable {

    /* Le nom (login) du joueur */
    private String name;

    /* Le score du joueur */
    private int score = 0;

    /**
     * Constructeur par defaut d'un joueur
     */

    public Player() {

        this.name = "Unamed";
    }

    /**
     * Constructeur d'un joueur
     * @param name le nom (login) du joueur
     */

    public Player(String name) {

        this.name = name;
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
     * Getter Name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Getter Score
     * @return score
     */

    public int getScore() {
        return score;
    }

    /**
     * Setter Score
     * @param score le score
     */
    
    public void setScore(int score) {
        this.score = score;
    }
}
