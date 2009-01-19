/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Entry;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		HighScoreView.java                                                       *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		JFrame represenant la vue "highscore"                      				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class HighScoreView extends JFrame implements ActionListener {

    /* La liste des HighScore (=entry) */
    private LinkedList<Entry> listHightScore;
    
    /* Le contenu de la JFrame */
    private JPanel content;
    
    /* Le bouton de fermeture de la fenetre */
    private JButton exitButton;

    /**
     * Constructeur
     */
    
    public HighScoreView() {

        super();

        //this.listHightScore = DiceGame.getInstance(choix).getHighscore().getListes_highscore();

        this.content = new JPanel();
        this.content.setLayout(new BorderLayout());

        updateContent();
        
        this.setContentPane(content);

        this.pack();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * Methode updateContent
     * Met a jour le contenu de la vue (de la fenetre donc)
     */
    
    public void updateContent() {

        this.content.removeAll();
        this.content.updateUI();
        
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(0, 2));

        for (int i = 0; i < 3; i++) {

            highScorePanel.add(new JLabel("Nom"));
            highScorePanel.add(new JLabel("Score"));
        }

        this.content.add(new JLabel("DiceGame - HightScore"), BorderLayout.NORTH);
        this.content.add(highScorePanel, BorderLayout.CENTER);

        this.exitButton = new JButton("Quitter");
        this.exitButton.addActionListener(this);
        
        this.content.add(this.exitButton, BorderLayout.SOUTH);

    }

    /**
     * Methode actionPerformed
     * Permet de definir des actions en fonction d'evenements
     * @param e l'action (evenement)
     */
    
    public void actionPerformed(ActionEvent e) {

        /* Si clique sur le bouton Exit */
        if (e.getSource()==exitButton) {
            
            this.setVisible(false);
            this.dispose();
        }
    }
}