package persist;

import core.HighScore;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		PersistKit.java                                                          *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Le kit de persistence                                   				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public abstract class PersistKit {

    /* L'instance de notre kit */
    private static PersistKit pesistKit = null;

    /* Les types de persistance possible */
    public static final int JDBC = 1, SERIALIZABLE = 2, XML = 3;

    /**
     * Methode makeKit
     * @return un highscore du type choisit
     */
    
    public abstract HighScore makeKit();

    /**
     * Methode getInstance
     * @param type la persistance
     * @return le kit de persistance choisit
     */
    
    public static PersistKit getInstance(int type) {

        if (pesistKit == null) {

            if (type == JDBC) {

                pesistKit = new JDBCKit();

            } else  if (type == SERIALIZABLE) {

                pesistKit = new SerializableKit();

            } else  if (type == XML) {

                pesistKit = new XMLKit();
            } 
        }

        return pesistKit;
    }
}
