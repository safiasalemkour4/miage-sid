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
    
    public static final int T_STRING = 1, T_NUMERIC = 2, T_BINARY = 3;
    
    
    private int id; // = le num de la colone (commence a 0)
    
    private String name;
    
    private int type;
    
    /* Si variable cible */
    private boolean targetVar;
    
    /* Si de type numerique */
    private int minValue;
    private int maxValue;
    
    /* A une valeur, on associe le nombre d'occurence */
    private HashMap<?,Integer> listValues;
    
    public DataInfos(int id, String name, int type, boolean targetVar) {
        
        this.id = id;
        this.name = name;
        this.type = type;
        this.targetVar = targetVar;
    }

    public String toString() {
        
        String res = "La colonne "+this.id;
        
        switch(this.type) {
            case T_STRING: res+=" est de type String";break;
            case T_NUMERIC: res+=" est de type Numeric (min:"+this.minValue+", max:"+this.maxValue+")";break;
            case T_BINARY: res+=" est de type Binaire";break;
        }
        
        if (this.targetVar) {
                    res +=" et elle est la variable cible";
        } else {
            res +=" et elle n'est pas la variable cible";
        }

        res +="\nCette variable possede :";
        
        return res;
    }
    
    public int getNbOccurence(String value) {
        
        return this.listValues.get(value);
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

    public int getType() {
        return type;
    }
        
    public boolean isTargetVar() {
        return targetVar;
    }

    public boolean isNumeric() {
        return (this.type==T_NUMERIC);
    }

    public boolean isString() {
        return (this.type==T_STRING);
    }
        
    public boolean isBinary() {
        return (this.type==T_BINARY);
    }

    public void setListValues(HashMap<?, Integer> listValues) {
        this.listValues = listValues;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    
    
}
