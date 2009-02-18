package persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		SQL.java                                                                 *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Notre objet manipulant la base de donnees                  				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class SQL {

    /* Adresse du serveur SQL */
    private String adressServer;

    /* Login de connexion au serveur */
    private String loginServer;

    /* Mot de passe de connexion */
    private String passwordServer;

    /* Nom de la base concernee */
    private String database;

    /* Flux de donnees correspondant a la connexion */
    private Statement streamConnexion;

    /* Flux de donnees */
    private ResultSet streamResponse;

    /* Debug mode */
    private final boolean DEBUG = true;

    /**
     * Constructeur
     * @param adressServer l'adresse du serveur
     * @param loginServer le login du serveur
     * @param passwordServer le password du serveur
     * @param database le nom de la base de donnees
     */
    
    public SQL(String adressServer, String loginServer, String passwordServer, String database) {

        this.adressServer = adressServer;
        this.loginServer = loginServer;
        this.passwordServer = passwordServer;
        this.database = database;

        this.streamResponse = null;
    }

    /**
     * Methode connexion
     * Permet de se connect a la base de donnees
     */
    
    public boolean connexion() {

        /* On tente de charger le driver */
        try {

            Class.forName("org.gjt.mm.mysql.Driver");

        } catch (ClassNotFoundException ex) {
            return false;
        }

        /* Connexion a la BDD */

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://" + this.adressServer + "/" + this.database, this.loginServer, this.passwordServer);
            this.streamConnexion = con.createStatement();

        } catch (SQLException ex) {

            if (DEBUG) {

                System.out.println("Probleme lors de la connexion a la base !");
                ex.printStackTrace();
            }

            return false;
        }

        return true;
    }

    /**
     * Methode executeRequest
     * @param sqlRequest la requete
     * @return true si l'execution c'est bien passe, false sinon
     */
    
    public boolean executeRequest(String sqlRequest) {

        try {

            if (sqlRequest.substring(0, 6).equalsIgnoreCase("SELECT")) {

                this.streamResponse = this.streamConnexion.executeQuery(sqlRequest);

            } else {
                
                this.streamConnexion.executeUpdate(sqlRequest);
            }

        } catch (SQLException ex) {

            if (DEBUG) {
                
                System.out.println("Probleme lors de l'execution de la requete");
                ex.printStackTrace();
            }
            
            return false;
        }
        return true;
    }

    /**
     * Methode nbResponse
     * @return le nombre de tuple de la requete
     */
    
    public int nbResponse() {
        
        ResultSet resultSet = this.streamResponse;
        int size = 0;

        try {

            resultSet.last();
            size = resultSet.getRow();
            
        } catch (SQLException e) {
            
            return 0;
        }

        return size;
    }

    /**
     * Methode fetchArray
     * @return le tupe sous forme d'un ResultSet
     */
    
    public ResultSet fetchArray() {
        
        try {

            if (this.streamResponse.next()) {

                return this.streamResponse;
                
            } else {

                return null;
            }

        } catch (SQLException ex) {

            if (DEBUG) {

                System.out.println("Probleme lors de l'extraction des donnees");
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Methode close
     * Permet de fermer la connexion SQL
     */
    
    public void close() {

        try {

            if (this.streamResponse != null) {

                this.streamResponse.close();
            }

            this.streamResponse.close();

        } catch (SQLException ex) {

            if (DEBUG) {

                System.out.println("Probleme a la fermeture de la connexion a la base !");
                ex.printStackTrace();
            }
        }
    }
}