package persist;

import core.HighScore;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		SerializableKit.java                                                     *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Le kit de persistence Serialisation                        				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class SerializableKit extends PersistKit {

    /**
     * Methode makeKit
     * @return un highscore du type choisit
     */
    
    @Override
    public HighScore makeKit() {
        
        return HighScore.getInstance(PersistKit.SERIALIZABLE);
    }
}
