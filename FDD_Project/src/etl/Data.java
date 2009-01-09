package etl;

public class Data {

    /**
     * Tableau représentant les valeurs des données :
     * - 1ere dimension [] la ligne
     * - 2ème dimension [][] la colonne
     */
    private String[][] tabDataValues;
    /**
     * Tableau donnant les informations sur les données
     * 
     * Pour un objet, on peut connaitre :
     * - L'id de la colonne  
     * - Le type de la colonne
     * - La liste de valeurs possible pour cette colonne
     */
    private DataInfos[] tabDataInfos;

    public Data(String[][] tabDataValues, DataInfos[] tabDataInfos) {

        this.tabDataValues = tabDataValues;
        this.tabDataInfos = tabDataInfos;
        
        System.out.println("vcxvcv");
    }

    public boolean isTargetVar(int column) {

        return this.tabDataInfos[column].isTargetVar();
    }

    public boolean isNumeric(int column) {

        return this.tabDataInfos[column].isNumeric();
    }

    public boolean isString(int column) {

        return this.tabDataInfos[column].isString();
    }

    public boolean isBinary(int column) {

        return this.tabDataInfos[column].isBinary();
    }

    public int getMaxValue(int column) {

        if (this.tabDataInfos[column].isNumeric()) {
            return this.tabDataInfos[column].getMaxValue();
        } else {
            return -1;
        }
    }

    public int getMinValue(int column) {

        if (this.tabDataInfos[column].isNumeric()) {
            return this.tabDataInfos[column].getMinValue();
        } else {
            return -1;
        }

    }

    public int getNbOccurence(int column, String value) {

        if (this.tabDataInfos[column].isBinary() || this.tabDataInfos[column].isString()) {
            return this.tabDataInfos[column].getNbOccurence(value);
        } else {
            return -1;
        }

    }

    public DataInfos[] getTabDataInfos() {
        return tabDataInfos;
    }

    public String[][] getTabDataValues() {
        return tabDataValues;
    }
    
    public String toString() {
        
            String res = "";

          System.out.println("\n--------- Header ---------\n");

           DataInfos[] tabDataInfos = LoadCSV.data.getTabDataInfos();

            for (int i = 0; i < tabDataInfos.length; i++) {

               System.out.println("- " + tabDataInfos[i]);

            }
        
            System.out.println("\n--------- DATA ---------\n");
            
            String [][] tabDataValues = LoadCSV.data.getTabDataValues();
            
            for (int i = 0; i < tabDataValues.length; i++) {
                    for (int j = 0; j < tabDataValues[0].length; j++) {
                        System.out.print(tabDataValues[i][j]+", ");
                    }
                    System.out.println();
            }
            System.out.println(res);
            
            return res;
    }
}
