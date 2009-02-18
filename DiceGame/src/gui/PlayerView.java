package gui;

import core.DiceGame;
import core.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
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

    /* Instance du jeu */
    private DiceGame diceGame;
    
    /* L'objet associe a la vue */
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
    
    public PlayerView(Player player, DiceGame diceGame) {

        this.player = player;
        this.diceGame = diceGame;
        
        this.setLayout(new GridLayout(0,2));

        this.setBackground(Color.BLACK);
        
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

        this.labelPlayer = new JLabel("<html><font color='#8b0000'>Player : </font>"+this.player.getName()+"</html>");
        this.labelPlayer.setForeground(Color.WHITE);
        this.labelPlayer.setFont(new Font("Arial Black", Font.BOLD, 18));
        this.labelPlayerValue = new JLabel();

        playerPanel.add(this.labelPlayer);
        playerPanel.add(this.labelPlayerValue);

        JPanel turnPanel = new JPanel();

        this.labelTurn = new JLabel("<html><font color='#8b0000'>Turn : </font>" + ++DiceGame.turn+"</html>");
        this.labelTurn.setForeground(Color.WHITE);
        this.labelTurn.setFont(new Font("Arial Black", Font.BOLD, 18));
        this.labelTurnValue = new JLabel();

        turnPanel.add(this.labelTurn);
        turnPanel.add(this.labelTurnValue);

        JPanel scorePanel = new JPanel();

        this.labelScore = new JLabel("<html><font color='#8b0000'>Score : </font>" + this.player.getScore()+"</html>");
        this.labelScore.setForeground(Color.WHITE);
        this.labelScore.setFont(new Font("Arial Black", Font.BOLD, 18));
        this.labelScoreValue = new JLabel();

        scorePanel.add(this.labelScore);
        scorePanel.add(this.labelScoreValue);

        this.add(playerPanel);
        this.add(turnPanel);
        this.add(scorePanel);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
    }

    /**
     * Methode update
     * @param o l'objet observe
     * @param arg les arguments
     */
    
    public void update(Observable o, Object arg) {

        /* Si la somme des 2 des est egal a 7 alors on ajoute 10 points */

        if (this.diceGame.getDie_1().getFaceValue() + this.diceGame.getDie_2().getFaceValue() == 7) {

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
