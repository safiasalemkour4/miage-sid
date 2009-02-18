package persist;

import core.HighScore;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		HighScoreXML.java                                                        *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Notre persistence de type XML                           				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class HighScoreXML extends HighScore {

    /* Le path de notre fichier */
    private final String FILE = "data/highscore.xml";

    /**
     * Constructeur
     */
    public HighScoreXML() {

    }

    /**
     * Methode Save
     * Permet de sauvegarder la liste des entrees (nom,score)
     */
    
    @Override
    public void save() {

        XMLEncoder e = null;

        try {

            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILE)));
            e.writeObject(this);
            e.flush();
            
        } catch (FileNotFoundException ex) {

            ex.printStackTrace();

        } finally {

            e.close();
        }
    }

    /**
     * Methode Load
     * Permet de sauvegarder la liste des entrees (nom,score)
     */
    
    @Override
    public void load() {

        HighScore highScore = null;
 
        XMLDecoder decoder = null;

        try {

            decoder = new XMLDecoder(new FileInputStream(FILE));

            highScore = (HighScore) decoder.readObject();

            this.setListHighScore(highScore.getListHighScore());

        } catch (FileNotFoundException ex) {

            ex.printStackTrace();

        } finally {

            decoder.close();
        }
    }
}
