/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etl;

import java.util.HashMap;

/**
 *
 * @author arnaud
 */
public class DataInfos {
    
    private int id;
    private String type;
    
    /* Si variable cible */
    private boolean targetVar;
    
    /* Si de type numerique */
    private int minValue;
    private int maxValue;
    
    /* A une valeur, on associe le nombre d'occurence */
    private HashMap<?,Integer> listValues;

}
