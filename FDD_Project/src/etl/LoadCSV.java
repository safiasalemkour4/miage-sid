package etl;

import cart.Cart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import projet_fdd.Main;

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

        BufferedReader firstReader = new BufferedReader(new FileReader(filePath));

        // Header
        String headerLine = firstReader.readLine();
        header = headerLine.split(",");

        String line;

        int nbLine = 0;
        while ((line = firstReader.readLine()) != null) {
            nbLine++;
        }

        firstReader.close();

        tabDataValues = new String[nbLine][header.length];
        tabDataInfos = new DataInfos[header.length];
    }

    public static void LoadCSVData(String filePath, int targetColumn) throws IOException {

        BufferedReader lastReader = new BufferedReader(new FileReader(filePath));

        /* Tableau : A une valeur, on associe le nombre d'occurence */
        HashMap tabListValues[] = new HashMap[header.length];

        for (int i = 0; i < tabListValues.length; i++) {
            tabListValues[i] = new HashMap();
        }

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

                    tabDataInfos[i] = dataInfo;


                    /* On oubli pas d'ajouter la premiere ligne quand m'm */

                    tabListValues[i].put(result[i], 1);

                    if (tabDataInfos[i].isNumeric()) {
                        tabDataInfos[i].setMinValue(new Integer(result[i]).intValue());
                        tabDataInfos[i].setMaxValue(new Integer(result[i]).intValue());
                    }

                    tabDataValues[l][i] = result[i];

                }

            } /* Si ce n'est pas la premiere ligne */

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
                   
                    /* on fait les couples */
                    if (tabListValues[i].containsKey(result[i])) {

                        Integer nbOccurence = (Integer) tabListValues[i].get(result[i]);

                        /* Le put ecrase */
                        tabListValues[i].put(result[i], nbOccurence.intValue() + 1);

                    } else {
                        tabListValues[i].put(result[i], 1);
                    }

                    // On charge les donnees 
                    tabDataValues[l][i] = result[i];

                }
            }

            l++;
        }

        /* On met les nb d'occurence */
        for (int i = 0; i < tabDataInfos.length; i++) {
            tabDataInfos[i].setListValues(tabListValues[i]);
        }

        /* On test si la colonne est de type binaire */

        for (int i = 0; i < tabDataInfos.length; i++) {

            if (tabDataInfos[i].getListValues().keySet().size() == 2) {
                tabDataInfos[i].setType(DataInfos.T_BINARY);
            }
        }

        data = new Data(tabDataValues, tabDataInfos);

        new Cart(data);
        //System.out.println(Cart.tree.get(0).getChartDegrees());

        
        lastReader.close();

        System.out.println("=========== Affichage de l'arbre : ============");
        new Main(data);
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
