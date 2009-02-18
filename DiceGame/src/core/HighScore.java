/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import persist.HighScoreJDBC;
import persist.HighScoreSerializable;
import persist.HighScoreXML;
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
    protected static HighScore highscore = null;


    /* List des HighScore<Entry> : (nom, score) */
    private ArrayList<Entry> listHighScore = new ArrayList<Entry>();

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

            } else if (type == PersistKit.XML) {

                highscore = new HighScoreXML();
            }
        }

        return highscore;
    }

    /**
     * Getter ListHighScore
     * @return ListHighScore
     */

    public ArrayList<Entry> getListHighScore() {

        return listHighScore;
    }

    /**
     * Setter ListHighScore
     */

    public void setListHighScore(ArrayList<Entry> listHighScore) {

        this.listHighScore = listHighScore;
    }
}
