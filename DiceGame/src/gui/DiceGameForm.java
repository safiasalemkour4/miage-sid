package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		DiceGameForm.java                                                        *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Fenetre principale du projet DiceGame                      				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class DiceGameForm extends JFrame implements ActionListener {

    /* Le Panel principal */
    private JPanel content;

    /* La vue De */
    private DieView dieView;

    /* La vu Player */
    private PlayerView playerView;

    /* Le bouton "start" */
    private JButton buttonStart;

    /* Le bouton "Stop" */
    private JButton buttonStop;

    /* Le bouton "HighScore" */
    private JButton buttonHighScore;

    /**
     * Constructeur de la fenetre de jeu
     */
    
    public DiceGameForm() {

        super();

        this.dieView = new DieView(this);
        this.playerView = new PlayerView(this);
   
        this.content = new JPanel();
        this.content.setLayout(new BorderLayout());

        JPanel actionPanel = new JPanel();
        this.buttonStart = new JButton("Start");
        this.buttonStart.addActionListener(this);
        this.buttonStop = new JButton("Stop");
        this.buttonStop.addActionListener(this);
        this.buttonHighScore = new JButton("View HighScore");
        this.buttonHighScore.addActionListener(this);
        actionPanel.add(this.buttonStart);
        actionPanel.add(this.buttonStop);
        actionPanel.add(new JLabel(" "));
        actionPanel.add(this.buttonHighScore);
                
        this.content.add(this.playerView, BorderLayout.NORTH);
        this.content.add(this.dieView, BorderLayout.CENTER);
        this.content.add(actionPanel, BorderLayout.SOUTH);

        this.setContentPane(content);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * Methode startAction
     * Permet de debuter les actions
     */

    public void startAction() {
        
        this.dieView.startAction();
        this.playerView.startAction();

    }

    /**
     * Methode startAction
     * Permet de mettre fin aux actions en cours
     */
    
    public void stopAction() {

    }

    /**
     * Methode actionPerformed
     * Permet de definir des actions en fonction d'evenements
     * @param e l'action (evenement)
     */
    
    public void actionPerformed(ActionEvent e) {

        /* Si clique sur le bouton "Start" */
        if (e.getSource() == this.buttonStart) {

            startAction();
        }

        /* Si clique sur le bouton "Stop" */
        if (e.getSource() == this.buttonStop) {

            stopAction();
        }

        /* Si clique sur le bouton "HighScore" */
        if (e.getSource() == this.buttonHighScore) {

            new HighScoreView();
        }
    }

    /**
     * Getter DieView
     * @return DieView
     */

    public DieView getDieView() {

        return dieView;
    }

    /**
     * Getter PlayerView
     * @return PlayerView
     */
    
    public PlayerView getPlayerView() {
        
        return playerView;
    }
   
}