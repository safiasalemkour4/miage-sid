package dicegame;

import gui.DiceGameForm;
import javax.swing.JOptionPane;
import persist.PersistKit;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		Main.java                                                                *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Main classe du Projet DiceGame                          				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* Les differents choix de persistances */
        String tabPersistType[] = {"Base de données", "Fichier", "XML"};

        /* Fenetre demandant a l'utilisateur de choisir son type de persistance */
        int typePersist = JOptionPane.showOptionDialog(null, "Merci de choisir le type de persistance voulue", "Choix de la Persistance", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, tabPersistType, tabPersistType[0]);

        /* On charge notre fenetre avec le type souhaite */
        switch (typePersist) {
            case 0: new DiceGameForm(PersistKit.JDBC); break;
            case 1: new DiceGameForm(PersistKit.SERIALIZABLE); break;
            case 2: new DiceGameForm(PersistKit.XML); break;
            default :  new DiceGameForm(PersistKit.JDBC); break;
        }
    }
}
