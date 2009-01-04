/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Player;
import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author arnaud
 */
public class PlayerView extends JPanel implements Observer {

    private static int turn = 0;
    
    private DiceGameForm diceGameForm;
    
    private Player player;
    private JLabel labelTurn;
    private JLabel labelTurnValue;
    private JLabel labelScore;
    private JLabel labelScoreValue;

    public PlayerView(DiceGameForm diceGameForm) {

        this.diceGameForm = diceGameForm;
        
        this.setLayout(new BorderLayout());
        this.player = new Player();
                player.addObserver(this);
        updateContent();
    }

    public void updateContent() {

        this.removeAll();
        this.updateUI();

        JPanel turnPanel = new JPanel();
        
        this.labelTurn = new JLabel("Turn :"+turn++);
        this.labelTurnValue = new JLabel();

        turnPanel.add(this.labelTurn);
        turnPanel.add(this.labelTurnValue);
        
        JPanel scorePanel = new JPanel();
                
        this.labelScore = new JLabel("Score :"+this.player.getScore());
        this.labelScoreValue = new JLabel();

        scorePanel.add(this.labelScore);
        scorePanel.add(this.labelScoreValue);
        
        this.add(turnPanel, BorderLayout.WEST);
        this.add(scorePanel, BorderLayout.EAST);

    }

    public void update(Observable o, Object arg) {
        
        if (this.diceGameForm.getDieView().getDie_1().getFaceValue()+this.diceGameForm.getDieView().getDie_2().getFaceValue()==7) {
            this.player.setScore(this.player.getScore()+10);
        }
        this.updateContent();
    }

    public void startAction() {
        
        this.player.display();
    }
    

}
