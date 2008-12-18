/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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

    private JLabel labelTurn;
    private JLabel labelTurnValue;
    private JLabel labelScore;
    private JLabel labelScoreValue;

    public PlayerView() {

        this.setLayout(new BorderLayout());
        
        updateContent();
    }

    public void updateContent() {

        this.removeAll();
        this.updateUI();

        JPanel turnPanel = new JPanel();
        
        this.labelTurn = new JLabel("Turn :");
        this.labelTurnValue = new JLabel();

        turnPanel.add(this.labelTurn);
        turnPanel.add(this.labelTurnValue);
        
        JPanel scorePanel = new JPanel();
                
        this.labelScore = new JLabel("Score :");
        this.labelScoreValue = new JLabel();

        scorePanel.add(this.labelScore);
        scorePanel.add(this.labelScoreValue);
        
        this.add(turnPanel, BorderLayout.WEST);
        this.add(scorePanel, BorderLayout.EAST);

    }

    public void update(Observable o, Object arg) {
        this.updateContent();
    }

    public void startAction() {
        //this.die.
    }
}
