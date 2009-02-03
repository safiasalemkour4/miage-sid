package etl;

import gui.TreeIntervalFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/*****************************************************************************************************
 *   					    ~ Data Mining Project (Miage Nancy - SID - 2008/2009) ~             	 *
 *****************************************************************************************************
 *    CLASS  	******		LoadCSV.java                                                             *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Classe permettant de charger les donnees CSV (en memoire) 				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Romain Lafond, Maxime Hoeffel, Simon Hasne, Eric Nguyen-Van,             *
 *              ******      Florian Collignon, Arnaud Knobloch                 						 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class LoadCSV {

    /* Constante : ici ',' */
    private static final String SEPARATOR = ",";

    /* Le header (entete) de notre fichier CSV */
    public static String[] header;

    /* Tableau representant les valeurs des donnees */
    public static String[][] tabDataValues;

    /* Tableau donnant les informations sur les donnees */
    public static DataInfos[] tabDataInfos;

    /* Les donnees */
    public static Data data;

    /**
     * Constructeur par defaut
     */

    public LoadCSV() {}

    /**
     * Methode LoadCSVHeader
     * @param filePath le chemin du fichier
     * @throws java.io.IOException les exceptions d'entrees/sorties
     */

    public static void LoadCSVHeader(String filePath) throws IOException {

        BufferedReader firstReader = new BufferedReader(new FileReader(filePath));

        /* Notre Header */
        String headerLine = firstReader.readLine();
        header = headerLine.split(",");

        String line;
        int nbLine = 0;

        /* On compte le nombre de ligne */
        while ((line = firstReader.readLine()) != null) {
            nbLine++;
        }

        firstReader.close();

        tabDataValues = new String[nbLine][header.length];
        tabDataInfos = new DataInfos[header.length];
    }

    /**
     * Methode LoadCSVData
     * @param filePath le chemin du fichier
     * @param targetColumn l'indice de la colonne cible
     * @throws java.io.IOException les exceptions d'entrees/sorties
     */
    public static void LoadCSVData(String filePath, int targetColumn) throws IOException {

        BufferedReader lastReader = new BufferedReader(new FileReader(filePath));

        /* Tableau : A une valeur, on associe le nombre d'occurence */
        HashMap tabListValues[] = new HashMap[header.length];

        for (int i = 0; i < tabListValues.length; i++) {
            tabListValues[i] = new HashMap();
        }

        /* Le header (on le passe */
        lastReader.readLine();
        int l = 0;

        String line;

        while ((line = lastReader.readLine()) != null) {
            
            if (l == 0) {

                String firstLine = line;

                /* Ici, on regarde la ligne 1 afin de reperer les types */
                
                String[] result = line.split(SEPARATOR);

                for (int i = 0; i < result.length; i++) {

                    /* On test le type */
                    DataInfos dataInfo = null;

                    if (isANumber(result[i])) {

                        if (targetColumn == i) {

                            dataInfo = new DataInfos(i, header[i], DataInfos.T_NUMERIC, true);

                        } else {

                            dataInfo = new DataInfos(i, header[i], DataInfos.T_NUMERIC, false);
                        }

                    } else {
                        
                        if (targetColumn == i) {

                            dataInfo = new DataInfos(i, header[i], DataInfos.T_STRING, true);

                        } else {

                            dataInfo = new DataInfos(i, header[i], DataInfos.T_STRING, false);
                        }
                    }

                    tabDataInfos[i] = dataInfo;

                    /* On oubli pas d'ajouter la premiere ligne quand meme */

                    tabListValues[i].put(result[i], 1);

                    if (tabDataInfos[i].isNumeric()) {
                        tabDataInfos[i].setMinValue(new Integer(result[i]).intValue());
                        tabDataInfos[i].setMaxValue(new Integer(result[i]).intValue());
                    }

                    tabDataValues[l][i] = result[i];

                }

            }

            /* Si ce n'est pas la premiere ligne */

            else {

                String[] result = line.split(SEPARATOR);

                for (int i = 0; i < result.length; i++) {

                   if (tabDataInfos[i].isNumeric()) {

                       if (tabDataInfos[i].getMinValue()>new Integer(result[i]).intValue()) {

                         tabDataInfos[i].setMinValue(new Integer(result[i]).intValue());
                       }

                       if (tabDataInfos[i].getMaxValue()<new Integer(result[i]).intValue()) {

                        tabDataInfos[i].setMaxValue(new Integer(result[i]).intValue());
                       }

                    }
                   
                    /* On fait les couples */
                    if (tabListValues[i].containsKey(result[i])) {

                        Integer nbOccurence = (Integer) tabListValues[i].get(result[i]);

                        /* Le put ecrase */
                        tabListValues[i].put(result[i], nbOccurence.intValue() + 1);

                    } else {
                        tabListValues[i].put(result[i], 1);
                    }

                    /* On charge les donnees */
                    tabDataValues[l][i] = result[i];
                }
            }

            l++;
        }

        /* On met le nombre d'occurence */
        
        for (int i = 0; i < tabDataInfos.length; i++) {

            tabDataInfos[i].setListValues(tabListValues[i]);
        }

        /* On test si la colonne est de type binaire */

        for (int i = 0; i < tabDataInfos.length; i++) {

            if (tabDataInfos[i].getListValues().keySet().size() == 2) {
                tabDataInfos[i].setType(DataInfos.T_BINARY);
            }
        }


  /*
         * On test si la colonne est numerique et si c'est le cas,
         * on demande a l'utilisateur de saisir els bornes
         * une fois le parsing effectué, on peut demandé les intervalles
         * sur les colonnes numériques */
        data = new Data(tabDataValues, tabDataInfos); // uniquement pour parser les colonnes (temporaire)

        TreeIntervalFrame tiv = TreeIntervalFrame.getInstance();
        tiv.setLocationRelativeTo(null);
        tiv.setVisible(true);

         /** Map associant pour une colonne numerique renseignee les intervalles saisies */
         HashMap<Integer, ArrayList<Integer>> intervalsMap = tiv.getIntervalsMap();


        for (int i = 0; i < tabDataInfos.length; i++) {

            if (tabDataInfos[i].isNumeric()) {
                
                /* On recupere les bornes */
                ArrayList<Integer> listBoundary = intervalsMap.get(i);

                tabDataInfos[i].setListBoundary(listBoundary);
            }
        }

        data = new Data(tabDataValues, tabDataInfos);

        lastReader.close();

    }

    /**
     * Retourne les données (Data)
     * @return data
     */
    public static Data getData(){
        return data;
    }

    /**
     * Methode isANumber
     * @param s la chaine de caractere
     * @return true si la chaine est un nombre (entier ou flottant), false sinon
     */
    
    public static boolean isANumber(String s) {

        if (s == null) {

            return false;
        }

        try {

            new java.math.BigDecimal(s);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }
}
