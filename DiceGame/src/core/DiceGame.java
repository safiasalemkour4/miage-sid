/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javax.swing.JOptionPane;
import persist.PersistKit;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		DiceGame.java                                                            *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object representant notre jeu                             				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class DiceGame {

    /* Instance du jeu */
    private static DiceGame instance = null;

    /* Variable de classe permettant de sauvegarder le tour courrant */
    public static int turn = 0;
    
    /* Instance du joueur */
    private Player player;
    
    /*
     * Note : j'ai choisi d'utiliser 2 instances de de plutot qu'une
     * collection de de car cela s'applique mieux à notre modele metier
     * /

    /* Instance de notre premier de */
    private Die die_1;

    /* Instance de notre second de */
    private Die die_2;
   
    /* Instance du highScore */
    private HighScore highScore;

    /**
     * Constructeur
     * @param persistType le type de persistence
     */

    private DiceGame(int persistType) {

        highScore = PersistKit.getInstance(persistType).makeKit();

        this.die_1 = new Die();

        this.die_2 = new Die();
                
        /* On demande et on recupere le nom du joueur */

        String name = (String) JOptionPane.showInputDialog(null,"Merci de choisir un nom", "Choix du Nom du Joueur", JOptionPane.QUESTION_MESSAGE, null, null, "Arnaud Knobloch");

        if ((name != null) && (name.length() > 0)) {

            this.player = new Player(name);

        } else {

            this.player = new Player();
        }
    }

    public static DiceGame getInstance(int persistType) {

        if (instance == null) {

            instance = new DiceGame(persistType);
        }
        
        return instance;
    }


    public Player getPlayer() {
        return player;
    }

    public Die getDie_1() {
        return die_1;
    }

    public Die getDie_2() {
        return die_2;
    }

    public HighScore getHighScore() {
        return highScore;
    }

    public HighScore getHighscore() {
        return highScore;
    }

    public void setHighscore(HighScore highScore) {
        this.highScore = highScore;
    }
    
}
