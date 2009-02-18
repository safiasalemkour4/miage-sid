package core;

import java.io.Serializable;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		Entry.java                                                              *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object metier representant une entree                      				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class Entry implements Serializable {

    /* Le nom(login) associe a l'entree */
    private String name;

    /* Le score associe a l'entree (et donc au login) */
    private int score;

    /**
     * Constructeur par defaut (vide)
     */
    
    public Entry() {
        
    }
    
    /**
     * Constructeur d'une entree
     * @param name le login du joueur
     * @param score le scoure du joueur (pour cettre entree)
     */
    
    public Entry(String name, int score) {

        this.name = name;
        this.score = score;
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
     * Setter Name
     * @param name le login du joueur
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter Score
     * @param score le score
     */

    public void setScore(int score) {
        this.score = score;
    }
}
