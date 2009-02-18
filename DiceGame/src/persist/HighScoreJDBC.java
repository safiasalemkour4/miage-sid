package persist;

import core.Entry;
import core.HighScore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		HighScoreJDBC.java                                                       *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Notre persistence de type BDD                             				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class HighScoreJDBC extends HighScore {

    /* Les parametres de la base */
    private final String SERVEUR = "localhost:8889";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String BDD = "dicegame";

    /* Notre objet manipulant la base */
    private SQL sql = null;

    /**
     * Constructeur
     */
    
    public HighScoreJDBC() {

        sql = new SQL(SERVEUR, USER, PASSWORD, BDD);
    }

    /**
     * Methode Save
     * Permet de sauvegarder la liste des entrees (nom,score)
     */
    
    @Override
    public void save() {

        sql.connexion();
                       
        String DELETE = "TRUNCATE TABLE highscore";
        sql.executeRequest(DELETE);

        ArrayList<Entry> listHighScore = this.getListHighScore();

        for (Entry e : listHighScore) {
            
            String INSERT = "INSERT INTO highscore (name, Score) VALUES ('" + e.getName() + "', " + e.getScore() + ");";
            sql.executeRequest(INSERT);
        }
         
        sql.close();
    }

    /**
     * Methode Load
     * Permet de sauvegarder la liste des entrees (nom,score)
     */

    @Override
    public void load() {

        sql.connexion();

        try {

            String SELECT = "SELECT * FROM highscore";
            sql.executeRequest(SELECT);
            ResultSet rs;

            ArrayList<Entry> listHighScore = new ArrayList<Entry>();

            while ((rs = sql.fetchArray()) != null) {
                listHighScore.add(new Entry(rs.getString("name"), rs.getInt("score")));
            }

            this.setListHighScore(listHighScore);

        } catch (SQLException ex) {
           System.out.println("Probleme au niveau de la base !");
           ex.printStackTrace();
        }

        sql.close();
    }

}
