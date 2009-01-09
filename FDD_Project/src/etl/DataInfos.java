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
    
    private int id; // = le num de la colone (commence a 0)
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

    public String toString() {
        
        String res = "La colonne "+this.id;
        
        switch(this.type) {
            case T_STRING: res+=" est de type String";break;
            case T_NUMERIC: res+=" est de type Numeric (min:"+this.minValue+", max:"+this.maxValue+")";break;
            case T_BOOL: res+=" est de type Binaire";break;
        }
        
        if (this.targetVar) {
                    res +=" et elle est la variable cible";
        } else {
            res +=" et elle n'est pas la variable cible";
        }

        
        return res;
    }
    
    public int getId() {
        return id;
    }

    public HashMap<?, Integer> getListValues() {
        return listValues;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public boolean isTargetVar() {
        return targetVar;
    }

    public int getType() {
        return type;
    }

    
}
