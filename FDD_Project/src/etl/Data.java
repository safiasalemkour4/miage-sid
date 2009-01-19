package etl;

import java.util.ArrayList;
import java.util.HashMap;

/*****************************************************************************************************
 *   					    ~ Data Mining Project (Miage Nancy - SID - 2008/2009) ~             	 *
 *****************************************************************************************************
 *    CLASS  	******		Data.java                                                                *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Object representant nos donnees                         				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Romain Lafond, Maxime Hoeffel, Simon Hasne, Eric Nguyen-Van,             *
 *              ******      Florian Collignon, Arnaud Knobloch                 						 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class Data {

    /**
     * Tableau representant les valeurs des donnees :
     * - 1ere dimension [] la ligne
     * - 2ème dimension [][] la colonne
     */

    private String[][] tabDataValues;

    /**
     * Tableau donnant les informations sur les donnees
     * 
     * Pour un objet, on peut connaitre :
     * - L'id de la colonne  
     * - Le type de la colonne
     * - La liste de valeurs possible pour cette colonne
     */
    
    private DataInfos[] tabDataInfos;

    /**
     * Construteur
     * @param tabDataValues Tableau representant les valeurs des donnees
     * @param tabDataInfos Tableau donnant les informations sur les donnees
     */

    public Data(String[][] tabDataValues, DataInfos[] tabDataInfos) {

        this.tabDataValues = tabDataValues;
        this.tabDataInfos = tabDataInfos;
    }

    /**
     * Methode getNewDataInfo
     * @param oldDataInfos les DataInfos du noeud superieur
     * @return le tableau des nouveaux DataInfos (correspondant au noeud courrant)
     */
    
    public DataInfos[] getNewDataInfo(String[][] newTabDataValues, DataInfos[] oldTabDataInfos) {

        /*
         * On reprend notre ancien DataInfos[]
         * Il reste maintenant a mettre a jour les statistiques
         */
        
        DataInfos[] res = oldTabDataInfos;

        /* Tableau : A une valeur, on associe le nombre d'occurence */

        HashMap tabListValues[] = new HashMap[this.getNbColumn()];

        for (int i = 0; i < tabListValues.length; i++) {
            tabListValues[i] = new HashMap();
        }

        /* On met a jour les informations : minValue, maxValue ainsi que les couples <value,nbOcurrence> */
        
        for (int i = 0; i < newTabDataValues.length; i++) {

            for (int j = 0; j < res.length; j++) {

                if (res[j].isNumeric()) {

                    if (res[j].getMinValue() > new Integer(newTabDataValues[i][j]).intValue()) {
                        res[j].setMinValue(new Integer(newTabDataValues[i][j]).intValue());
                    }

                    if (res[j].getMaxValue() < new Integer(newTabDataValues[i][j]).intValue()) {
                        res[j].setMaxValue(new Integer(newTabDataValues[i][j]).intValue());
                    }
                }

                /* On fait les couples */
                if (tabListValues[j].containsKey(newTabDataValues[i][j])) {

                    Integer nbOccurence = (Integer) tabListValues[j].get(newTabDataValues[i][j]);

                    /* Le put ecrase */
                    tabListValues[j].put(newTabDataValues[i][j], nbOccurence.intValue() + 1);

                } else {
                    tabListValues[j].put(newTabDataValues[i][j], 1);
                }
            }
        }

        /* On met les couples <value, nbOccurence> */
        
        for (int i = 0; i < res.length; i++) {
            res[i].setListValues(tabListValues[i]);
        }

        /* Enfin on test si la colonne est de devenue de type binaire */

        for (int i = 0; i < res.length; i++) {

            if (res[i].getListValues().keySet().size() == 2) {
                res[i].setType(DataInfos.T_BINARY);
            }
        }

        return res;
    }
    
    /**
     * Methode isTargetVar
     * @param column la colonne cible
     * @return true si la colonne est la colonne cible, false sinon
     */

    public boolean isTargetVar(int column) {

        return this.tabDataInfos[column].isTargetVar();
    }

    /**
     * Methode isNumeric
     * @param column la colonne cible
     * @return true si la colonne est de type numerique, false sinon
     */

    public boolean isNumeric(int column) {

        return this.tabDataInfos[column].isNumeric();
    }

    /**
     * Methode isString
     * @param column la colonne cible
     * @return true si la colonne est de type string, false sinon
     */
    
    public boolean isString(int column) {

        return this.tabDataInfos[column].isString();
    }

    /**
     * Methode isBinary
     * @param column la colonne cible
     * @return true si la colonne est de type binaire, false sinon
     */
    
    public boolean isBinary(int column) {

        return this.tabDataInfos[column].isBinary();
    }

    /**
     * Methode getBoundary
     * @param column la colonne cible
     * @return la liste des bornes de la colonne (si elle est de type numerique)
     */

    public ArrayList<Integer> getBoundary(int column) {

        if (this.tabDataInfos[column].isNumeric()) {
            return this.tabDataInfos[column].getListBoundary();
        } else {
            return null;
        }
    }
    
    /**
     * Methode getMaxValue
     * @param column la colonne cible
     * @return la valeur maximum de la colonne (si elle est de type numerique)
     */

    public int getMaxValue(int column) {

        if (this.tabDataInfos[column].isNumeric()) {
            return this.tabDataInfos[column].getMaxValue();
        } else {
            return -1;
        }
    }

    /**
     * Methode getMinValue
     * @param column la colonne cible
     * @return la valeur minimum de la colonne (si elle est de type numerique)
     */

    public int getMinValue(int column) {

        if (this.tabDataInfos[column].isNumeric()) {
            return this.tabDataInfos[column].getMinValue();
        } else {
            return -1;
        }
    }

    /**
     * Methode getNbOccurence
     * @param column la colonne cible
     * @param valueBoundary la borne choisie
     * @return le nombre d'occurence qui sont inferieur ou egal a la borne choisie
     */

    public int getNbOccurence(int column, int valueBoundary) {

        int res =0;
        
        if (this.tabDataInfos[column].isNumeric()) {
            //return this.tabDataInfos[column].getNbOccurence(value);
        } else {
            return -1;
        }
        return -1;
    }
    
    /**
     * Methode getNbOccurence
     * @param column la colonne cible
     * @param value la valeur choisie
     * @return le nombre d'occurence de la valeur dans la colonne cible
     */
    
    public int getNbOccurence(int column, String value) {

        if (this.tabDataInfos[column].isBinary() || this.tabDataInfos[column].isString()) {
            return this.tabDataInfos[column].getNbOccurence(value);
        } else {
            return -1;
        }
    }

    /**
     * Methode getListOccurence
     * @param column la colonne cible
     * @return un tableau contenant toutes les valeurs presenten dans la colonne cible
     */

    public Object[] getListOccurence(int column) {
        
        return this.tabDataInfos[column].getListValues().keySet().toArray();

    }

    /**
     * Methode getNbColumn
     * @return le nombre de colonne des donnees
     */

    public int getNbColumn() {

        return this.tabDataInfos.length;
    }

    /**
     * Methode getNbRow
     * @return le nombre de ligne des donnees
     */
    
    public int getNbRow() {
        
        return this.tabDataValues.length;
    }


    /**
     * Methode getValue
     * @param row la ligne
     * @param column la colonne
     * @return la valeur a la ligne et a la colonne donnee
     */

    public String getValue(int row, int column) {

        return this.tabDataValues[row][column];
    }

    /**
     * Methode getTargetVarId
     * @return l'indice de la colonne cible
     */

    public int getTargetVarId() {

       for (int i = 0; i < tabDataInfos.length; i++) {

           if (tabDataInfos[i].isTargetVar()) {
               return i;
           }
        }

       return -1;          
    }

    /**
     * Methode getTargetVar
     * @return le nom de la colonne (variable) cible
     */

    public String getTargetVar() {

       for (int i = 0; i < tabDataInfos.length; i++) {

           if (tabDataInfos[i].isTargetVar()) {
               return tabDataInfos[i].getName();
           }
        }

       return "";
    }

    /**
     * Methode getTabDataValues
     * @return le tableau representant les valeurs des donnees
     */
    
    public String[][] getTabDataValues() {

        return tabDataValues;
    }

    /**
     * Methode getTabDataInfos
     * @return le tableau donnant les informations sur les donnees
     */
    
    public DataInfos[] getTabDataInfos() {

        return tabDataInfos;
    }

    /**
     * Methode toString
     * @return la description des donnees
     */
    
    @Override
    public String toString() {

        String res = "";

        System.out.println("\n--------- Header ---------\n");

        for (int i = 0; i < tabDataInfos.length; i++) {

            System.out.println("- " + tabDataInfos[i]);
        }

        System.out.println("\n--------- DATA ---------\n");

        for (int i = 0; i < tabDataValues.length; i++) {

            for (int j = 0; j < tabDataValues[0].length; j++) {

                System.out.print(tabDataValues[i][j] + ", ");
            }
            
            System.out.println();
        }

        return res;
    }
}
