package persist;

import core.HighScore;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		HighScoreSerializable.java                                               *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Notre persistence de type Serialisation                    				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class HighScoreSerializable extends HighScore {

    /* Le path de notre fichier */
    private final String FILE = "data/highscore.save";

    /**
     * Constructeur
     */
    
    public HighScoreSerializable() {
        
    }

    /**
     * Methode Save
     * Permet de sauvegarder la liste des entrees (nom,score)
     */
    
    @Override
    public void save() {

        FileOutputStream outStream = null;

        ObjectOutputStream persistance = null;

        try {

            outStream = new FileOutputStream(FILE);
            persistance = new ObjectOutputStream(outStream);

            /* On sauvegarde le highscore (l'instance) */
            persistance.writeObject(this);
            persistance.flush();
            outStream.close();

        } catch (IOException ex) {

            ex.printStackTrace();

        } finally {
            
            try {
                outStream.close();
                persistance.close();
                
            } catch (IOException ex) {}
        }
    }

    /**
     * Methode Load
     * Permet de sauvegarder la liste des entrees (nom,score)
     */

    @Override
    public void load() {

        FileInputStream input = null;

        ObjectInputStream persistance = null;
        
        try {

            input = new FileInputStream(FILE);
            persistance = new ObjectInputStream(input);
            HighScoreSerializable highScore = (HighScoreSerializable) persistance.readObject();
            this.setListHighScore(highScore.getListHighScore());

        } catch (IOException ex) {

            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();

        } finally {

            try {

                input.close();
                persistance.close();

            } catch (IOException ex) {

               ex.printStackTrace();
            }
        }
    }
}
