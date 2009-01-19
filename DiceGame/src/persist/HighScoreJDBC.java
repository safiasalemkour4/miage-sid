/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persist;

import core.Entry;
import core.HighScore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arnaud
 */
public class HighScoreJDBC extends HighScore {
    
    private final String SERVEUR = "localhost";
    private final String USER = "root";
    private final String PASSWORD = "270382";
    private final String BDD = "jeudedes";
    private Mysql sql = null;

    public HighScoreJDBC() {
        sql = new Mysql(SERVEUR, USER, PASSWORD, BDD);
        sql.connexion();
    }

    @Override
    public void load() {
        try {
            String SELECT = "SELECT * FROM Highscore";
            sql.executer_requete(SELECT);
            ResultSet rs;
                while ((rs = sql.fetch_array()) != null) {
                    this.add(new Entry(rs.getString("Nom"), rs.getInt("Score")));
                }

        } catch (SQLException ex) {
            Logger.getLogger(HighScoreJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save() {

        /*
        String DELETE = "DELETE FROM Highscore;";
        sql.executer_requete(DELETE);
        LinkedList<Entry> ll = this.getListes_highscore();
        Collections.sort(ll, new ScoreComparator());
        Integer count = 0;
        for (Entry e : ll) {
            String INSERT = "INSERT INTO Highscore (Nom, Score) VALUES ('" + e.getName() + "', " + e.getScore() + ");";
            sql.executer_requete(INSERT);
            count++;
            if (count == 15) {
                break;
            }
        }
         * */

    }


}
