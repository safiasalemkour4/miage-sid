package core;

import java.util.Comparator;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		EntryComparator.java                                                     *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object permettant de comparer les entrees                  				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class EntryComparator implements Comparator {

    /**
     * Methode compare
     * @param entry_1 l'entree a comparer
     * @param entry_2 l'entree a comparer
     * @return
     */
    
    public int compare(Object entry_1, Object entry_2) {

        Integer score_1 = new Integer(((Entry) entry_1).getScore());
        Integer score_2 = new Integer(((Entry) entry_2).getScore());
        return score_2.compareTo(score_1);
    }
}

