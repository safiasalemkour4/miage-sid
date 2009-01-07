package etl;

import etl.DataInfos;
import etl.DataInfos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author arnaud
 */
public class LoadCSV {

    public static String[] header;
    public static String[][] tabDataValues;
    public static DataInfos[] tabDataInfos;
    
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

    public static void LoadCSVData(String filePath) throws IOException {

        BufferedReader lastReader = new BufferedReader(new FileReader(filePath));
                

        String line;
        
        // Le header (on le passe)
        lastReader.readLine();
        int l = 0;

        // ICI IL FAUT REGARDER LA LIGNE 1 AFIN DE REPERER LES TYPES
        
        while ((line = lastReader.readLine()) != null) {

            System.out.println("Ligne :");

            String[] result = line.split(",");

            for (int i = 0; i < result.length; i++) {
                System.out.println("- " + result[i]);

                // On charge les donnees 
                tabDataValues[l][i] = result[i];

            }
        }

        lastReader.close();
    }
}
