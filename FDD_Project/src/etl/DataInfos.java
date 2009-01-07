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
    
    public static final int T_STRING = 1, T_NUMERIC = 2, T_BOOL = 3;
    
    private int id;
    private int type;
    
    /* Si variable cible */
    private boolean targetVar;
    
    /* Si de type numerique */
    private int minValue;
    private int maxValue;
    
    /* A une valeur, on associe le nombre d'occurence */
    private HashMap<?,Integer> listValues;
    
    public DataInfos(int id, int type, boolean targetVar) {
        
        this.id = id;
        this.type = type;
        this.targetVar = targetVar;
    }

}
