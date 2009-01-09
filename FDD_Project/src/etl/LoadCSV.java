package etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author arnaud
 */
public class LoadCSV {

    private static final String SEPARATOR = ",";
    public static String[] header;
    public static String[][] tabDataValues;
    public static DataInfos[] tabDataInfos;
    public static Data data;
    
    public LoadCSV() {
    }

    public static void LoadCSVHeader(String filePath) throws IOException {

        System.out.println("Loading file " + filePath);

        BufferedReader firstReader = new BufferedReader(new FileReader(filePath));

        // Header
        String headerLine = firstReader.readLine();
        header = headerLine.split(",");

        String line;

        int nbLine = 1;
        while ((line = firstReader.readLine()) != null) {
            nbLine++;
        }

        firstReader.close();

        System.out.println("Nombre de ligne : " + nbLine);
        System.out.println("Nombre de colonne : " + header.length);

        //DataInfos(int id, int type, boolean targetVar)

        // ICI AFFICHER la premiere lecture (avec le hearder) pr le choix de la var cible !

        tabDataValues = new String[nbLine][header.length];
        tabDataInfos = new DataInfos[header.length];
    }

    public static void LoadCSVData(String filePath, int targetColumn) throws IOException {

        BufferedReader lastReader = new BufferedReader(new FileReader(filePath));

        //InputStreamReader isr = new InputStreamReader(new FileInputStream("test"), Charset.forName("ISO-8859-1"));
        String line;

        // Le header (on le passe)
        lastReader.readLine();
        int l = 0;



        while ((line = lastReader.readLine()) != null) {

            if (l == 0) {

                String firstLine = line;

                // ICI IL FAUT REGARDER LA LIGNE 1 AFIN DE REPERER LES TYPES 

                String[] result = line.split(SEPARATOR);

                for (int i = 0; i < result.length; i++) {

                    // On test le type
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

                    tabDataInfos[i]=dataInfo;
                    
                    // TODO IL FAUDRAIT TESTER SI C BINAIRE !

                    /* On oubli pas d'ajouter la premiere ligne quand m'm */
                    tabDataValues[l][i] = result[i];

                }

            } 
            
            /* Si ce n'est pas la premiere ligne */ 
            else {

                String[] result = line.split(SEPARATOR);

                for (int i = 0; i < result.length; i++) {

                    // On charge les donnees 
                    tabDataValues[l][i] = result[i];

                }

            }

            l++;
        }

        data = new Data(tabDataValues, tabDataInfos);
        
        lastReader.close();
    }

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
