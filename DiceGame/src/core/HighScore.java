/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.LinkedList;
import persist.HighScoreJDBC;
import persist.HighScoreSerializable;
import persist.PersistKit;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		HighScore.java                                                           *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object metier representant un HighScore                     			 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public abstract class HighScore implements Serializable {

    /* Instance d'un HighScore */
    private static HighScore highscore = null;

    /* List des HighScore<Entry> : (nom, score) */
    private LinkedList<Entry> listHighScore = new LinkedList<Entry>();

    /**
     * Constructeur par defaut (vide)
     */
    
    public HighScore() {
    }

    /**
     * Methode add
     * @param entry l'entree a ajouter a la liste
     */

    public void add(Entry entry) {
        
        listHighScore.add(entry);
    }

    /**
     * Methode load (abstraite)
     * Permet de charger les donnees
     */
    
    public abstract void load();

    /**
     * Methode save (abstraire)
     * Permet d'enregistrer les donnees
     */
    
    public abstract void save();

    /**
     * Methode getInstance
     * @param type le type de persistance que l'on desire
     * @return une instance de HighScore correspondant au type de persistance choisi
     */

    public static HighScore getInstance(int type) {

        if (highscore == null) {

            if (type == PersistKit.JDBC) {

                highscore = new HighScoreJDBC();

            } else if (type == PersistKit.SERIALIZABLE) {

                highscore = new HighScoreSerializable();
            }
        }

        return highscore;
    }

    /**
     * Getter ListHighScore
     * @return ListHighScore
     */

    public LinkedList<Entry> getListHighScore() {

        return listHighScore;
    }

    /**
     * Setter ListHighScore
     */

    public void setListHighScore(LinkedList<Entry> listHighScore) {

        this.listHighScore = listHighScore;
    }
}
