package etl;


import java.io.File;


public class Data {
    
	/**
	 * Tableau représentant les valeurs des données :
         * - 1ere dimension [] la ligne
         * - 2ème dimension [][] la colonne
	 */
    
        public String [][] tabDataValues;
        
    	/**
	 * Tableau donnant les informations sur les données
         * 
         * Pour un objet, on peut connaitre :
         * - L'id de la colonne  
         * - Le type de la colonne
         * - La liste de valeurs possible pour cette colonne
	 */
    
        public DataInfos[] tabDataInfos;


        public Data (String [][] tabDataValues, DataInfos[] tabDataInfos) {
            
            this.tabDataValues = tabDataValues;
            this.tabDataInfos = tabDataInfos;
        }

    public DataInfos[] getTabDataInfos() {
        return tabDataInfos;
    }

    public String[][] getTabDataValues() {
        return tabDataValues;
    }
        
        

}
