package gui;

import core.Player;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		PlayerView.java                                                          *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Panel represenant la vue "player"                       				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class PlayerView extends JPanel implements Observer {

    /* Variable de classe permettant de sauvegarder le tour courrant */
    protected static int turn = 0;

    /* Instance sur la fenetre maitresse */
    private DiceGameForm diceGameForm;

    /* Instance du joueur */
    private Player player;

    /* Label "Player :" */
    private JLabel labelPlayer;

    /* Label affichant le nom du joueur */
    private JLabel labelPlayerValue;
    
    /* Label "Turn :" */
    private JLabel labelTurn;

    /* Label affichant le tour courrant */
    private JLabel labelTurnValue;

    /* Label "Score :" */
    private JLabel labelScore;

    /* Label affichant la valeur du score */
    private JLabel labelScoreValue;

    /**
     * Constructeur 
     * @param diceGameForm la fenetre maitresse
     */
    
    public PlayerView(DiceGameForm diceGameForm) {

        this.diceGameForm = diceGameForm;

        this.setLayout(new BorderLayout());

        /* On demande et on recupere le nom du joueur */

        String name = (String) JOptionPane.showInputDialog(diceGameForm,"Merci de choisir un nom", "Choix du Nom du Joueur", JOptionPane.QUESTION_MESSAGE, null, null, "Arnaud Knobloch");

        if ((name != null) && (name.length() > 0)) {
            
            this.player = new Player(name);

        } else {
            
            this.player = new Player();
        }
 
        player.addObserver(this);
        
        updateContent();
    }

    /**
     * Methode updateContent
     * Met a jour le contenu de la vue
     */
    
    public void updateContent() {

        this.removeAll();
        this.updateUI();

        JPanel playerPanel = new JPanel();

        this.labelPlayer = new JLabel("Player : "+this.player.getName());
        this.labelPlayerValue = new JLabel();

        playerPanel.add(this.labelPlayer);
        playerPanel.add(this.labelPlayerValue);

        JPanel turnPanel = new JPanel();

        this.labelTurn = new JLabel("Turn : " + turn++);
        this.labelTurnValue = new JLabel();

        turnPanel.add(this.labelTurn);
        turnPanel.add(this.labelTurnValue);

        JPanel scorePanel = new JPanel();

        this.labelScore = new JLabel("Score : " + this.player.getScore());
        this.labelScoreValue = new JLabel();

        scorePanel.add(this.labelScore);
        scorePanel.add(this.labelScoreValue);

        this.add(playerPanel, BorderLayout.WEST);
        this.add(turnPanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.EAST);

    }

    /**
     * Methode update
     * @param o l'objet observe
     * @param arg les arguments
     */
    
    public void update(Observable o, Object arg) {

        /* Si la somme des 2 des est egal a 7 alors on ajoute 10 points */

        if (this.diceGameForm.getDieView().getDie_1().getFaceValue() + this.diceGameForm.getDieView().getDie_2().getFaceValue() == 7) {

            this.player.setScore(this.player.getScore() + 10);
        }
        
        this.updateContent();
    }

    /**
     * Methode startAction
     * Permet de debuter les actions liees a cette vue
     */
    
    public void startAction() {

        this.player.display();
    }

    /**
     * Getter Player
     * @return player
     */

    public Player getPlayer() {
        
        return player;
    }


}
