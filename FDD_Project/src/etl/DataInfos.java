package etl;

import java.util.HashMap;

/*****************************************************************************************************
 *   					    ~ Data Mining Project (Miage Nancy - SID - 2008/2009) ~             	 *
 *****************************************************************************************************
 *    CLASS  	******		DataInfos.java                                                           *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object representant nos donnees (colonnes)                 				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Romain Lafond, Maxime Hoeffel, Simon Hasne, Eric Nguyen-Van,             *
 *              ******      Florian Collignon, Arnaud Knobloch                 						 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/
public class DataInfos {

    /*
     * Constante servant a definir le type de la colonne :
     * - String : de type chaine de caracteres
     * - Numeric : de type numerique (entier, flottant, ...)
     * - Binary : de type binaire (ne possede que 2 valeurs possible, exemple : oui, non)
     */
    public static final int T_STRING = 1,  T_NUMERIC = 2,  T_BINARY = 3;

    /* L'indice de la colonne (commence a 0) */
    private int id;

    /* Le nom de la colonne (de la variable) */
    private String name;

    /* Le type de la colonne (de la variable) */
    private int type;

    /* Indique si la variable est la variable cible */
    private boolean targetVar;

    /* La valeur minimale de la colonne (Si la colonne est de type numerique) */
    private int minValue;

    /* La valeur maximale de la colonne (Si la colonne est de type numerique) */
    private int maxValue;
    
    /* Collection permettant a une valeur d'associer le nombre d'occurence de celle-ci dans la colonne */
    private HashMap<?, Integer> listValues;

    /**
     * Constructeur
     * @param id l'indice de la colonne
     * @param name le nom de la colonne
     * @param type le  type de la colonne
     * @param targetVar si la colonne est la variable cible ou non
     */
    public DataInfos(int id, String name, int type, boolean targetVar) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.targetVar = targetVar;
    }

    /**
     * Methode toString
     * @return la description des donnees (colonnes)
     */

    @Override
    public String toString() {

        String res = "\nLa colonne " + this.id;

        switch (this.type) {
            case T_STRING:
                res += " est de type String";
                break;
            case T_NUMERIC:
                res += " est de type Numeric (min:" + this.minValue + ", max:" + this.maxValue + ")";
                break;
            case T_BINARY:
                res += " est de type Binaire";
                break;
        }

        if (this.targetVar) {
            res += " et elle est la variable cible";
        } else {
            res += " et elle n'est pas la variable cible";
        }

        res += "\nCette variable possede :\n" + this.listValues;

        return res;
    }

    /**
     * Methode getNbOccurence
     * @param value la valeur choisie
     * @return le nombre d'occurence de cette valeur dans notre colonne
     */

    public int getNbOccurence(String value) {

        return this.listValues.get(value);
    }

    /**
     * Methode getId
     * @return l'id de la colonne
     */
    
    public int getId() {
        
        return id;
    }

    /**
     * Methode getName
     * @return le nom de la colonne
     */
    
    public String getName() {

        return name;
    }

    /**
     * Methode getListValues
     * @return la collection permettant a une valeur d'associer le nombre d'occurence de celle-ci dans la colonne
     */

    public HashMap<?, Integer> getListValues() {

        return listValues;
    }

    /**
     * Methode getMaxValue
     * @return la valeur maximale de la colonne (si elle est de type numerique)
     */

    public int getMaxValue() {

        return maxValue;
    }

    /**
     * Methode getMinValue
     * @return la valeur minimale de la colonne (si elle est de type numerique)
     */
    
    public int getMinValue() {

        return minValue;
    }

    /**
     * Methode getType
     * @return le type de la colonne
     */
    
    public int getType() {

        return type;
    }

    /**
     * Methode isTargetVar
     * @return true si la colonne (this) est la colonne cible, false sinon
     */

    public boolean isTargetVar() {

        return targetVar;
    }

    /**
     * Methode isNumeric
     * @return true si la variable est de type numerique, false sinon
     */

    public boolean isNumeric() {

        return (this.type == T_NUMERIC);
    }

    /**
     * Methode isString
     * @return true si la variable est de type string, false sinon
     */

    public boolean isString() {
        
        return (this.type == T_STRING);
    }

    /**
     * Methode isBinary
     * @return true si la variable est de type binaire, false sinon
     */
    
    public boolean isBinary() {
        
        return (this.type == T_BINARY);
    }

    /**
     * Methode setType
     * @param type le type pour la colonne
     */
    
    public void setType(int type) {
        
        this.type = type;
    }

    /**
     * Methode setListValues
     * @param listValues la collection permettant a une valeur d'associer le nombre d'occurence de celle-ci dans la colonne
     */

    public void setListValues(HashMap<?, Integer> listValues) {

        this.listValues = listValues;
    }

    /**
     * Methode setMaxValue
     * @param maxValue la valeur maximale
     */
    
    public void setMaxValue(int maxValue) {

        this.maxValue = maxValue;
    }

    /**
     * Methode setMinValue
     * @param minValue la valeur minimale
     */
    
    public void setMinValue(int minValue) {
        
        this.minValue = minValue;
    }
}
