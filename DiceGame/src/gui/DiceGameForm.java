package gui;

import core.DiceGame;
import core.HighScore;
import core.Entry;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    /* Le type de persistance */
    private int persistType;

    /* Notre instance de jeu */
    private DiceGame diceGame;
    
    /* Le Panel principal */
    private JPanel content;

    /* La vue du De 1 */
    private DieView dieView_1;

    /* La vue du De 2 */
    private DieView dieView_2;
    
    /* La vue Player */
    private PlayerView playerView;

    /* Le titre du jeu */
    private JLabel labelTitle;
    
    /* Le bouton "start" */
    private JButton buttonStart;

    /* Le bouton "Stop" */
    private JButton buttonStop;

    /* Le bouton "HighScore" */
    private JButton buttonHighScore;

    /**
     * Constructeur de la fenetre de jeu
     */
    
    public DiceGameForm(int persistType) {

        super();

        this.diceGame = DiceGame.getInstance(persistType);
        
        this.persistType = persistType;

        this.setBackground(Color.BLACK);

        this.labelTitle = new JLabel(new ImageIcon("data/dice_game.png"));
        
        this.dieView_1 = new DieView(this.diceGame.getDie_1());
        this.diceGame.getDie_1().addObserver(dieView_1);
        
        this.dieView_2 = new DieView(this.diceGame.getDie_2());
        this.diceGame.getDie_2().addObserver(dieView_2);
        
        this.playerView = new PlayerView(this.diceGame.getPlayer(), this.diceGame);
        this.diceGame.getPlayer().addObserver(playerView);

        this.content = new JPanel();
        this.content.setLayout(new BorderLayout());

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(0,3));
        this.buttonStart = new JButton(new ImageIcon("data/start.png"));
        this.buttonStart.setBackground(Color.BLACK);
        this.buttonStart.setFocusPainted(false);
		this.buttonStart.setBorderPainted(false);
		this.buttonStart.setContentAreaFilled(false);
        this.buttonStart.addActionListener(this);
        this.buttonStop = new JButton(new ImageIcon("data/stop.png"));
        this.buttonStop.setBackground(Color.BLACK);
        this.buttonStop.setFocusPainted(false);
		this.buttonStop.setBorderPainted(false);
		this.buttonStop.setContentAreaFilled(false);
        this.buttonStop.addActionListener(this);
        this.buttonHighScore = new JButton(new ImageIcon("data/highscore.png"));
        this.buttonHighScore.setBackground(Color.BLACK);
        this.buttonHighScore.setFocusPainted(false);
		this.buttonHighScore.setBorderPainted(false);
		this.buttonHighScore.setContentAreaFilled(false);
        this.buttonHighScore.addActionListener(this);
        actionPanel.add(new JLabel(" "));
        actionPanel.add(new JLabel(" "));
        actionPanel.add(new JLabel(" "));
        actionPanel.add(this.buttonStart);
        actionPanel.add(this.buttonStop);
        actionPanel.add(this.buttonHighScore);

        JPanel panelPlayerView = new JPanel();
        panelPlayerView.setLayout(new BorderLayout());
        panelPlayerView.add(this.labelTitle, BorderLayout.NORTH);
        panelPlayerView.add(this.playerView, BorderLayout.CENTER);

        JPanel panelDieView = new JPanel();
        panelDieView.add(this.dieView_1);
        panelDieView.add(this.dieView_2);

        this.content.add(panelPlayerView, BorderLayout.NORTH);
        this.content.add(panelDieView, BorderLayout.CENTER);
        this.content.add(actionPanel, BorderLayout.SOUTH);

        this.setContentPane(content);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        
        this.setVisible(true);

    }

    /**
     * Methode startAction
     * Permet de debuter les actions
     */

    public void startAction() {
        
        this.dieView_1.startAction();
        this.dieView_2.startAction();
        this.playerView.startAction();

        /* Si on est au tour 10 alors on met fin au jeu */
        if (DiceGame.turn==10) {
            
            endOfTheGame();
            stopAction();
        }

    }

    /**
     * Methode startAction
     * Permet de mettre fin aux actions en cours
     */
    
    public void stopAction() {

        DiceGame.turn = 0;
        this.playerView.getPlayer().setScore(0);

        this.playerView.updateContent();

        this.diceGame.getDie_1().setFaceValue(-1);
        this.diceGame.getDie_2().setFaceValue(-1);

        this.dieView_1.updateContent();
        this.dieView_2.updateContent();
    }

    /**
     * Methode endOfTheGame
     * Permet de mettre fin au jeu (et de sauvegarder l'entree)
     */
    
    public void endOfTheGame() {

        String name = this.playerView.getPlayer().getName();
        int score = this.playerView.getPlayer().getScore();
        
        System.out.println("On save avec le type de persistance :"+this.persistType);

        HighScore highScore = this.diceGame.getHighscore();

        System.out.println("On save : "+name+" / "+score);

        highScore.add(new Entry(name, score));

        highScore.save();

        
        JOptionPane.showMessageDialog(this,  "Merci d'avoir jouer "+name+" ! \nVotre score de "+score+" a été sauvegardé.\n"+
                "\nVous pourrez peut être retrouver celui-ci dans les\nhighscore si vous êtes dans les 20 meilleurs",
                "Sauvegarde de la Partie", JOptionPane.INFORMATION_MESSAGE);

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

            new HighScoreView(this.persistType);
        }
    }

    /**
     * Getter DieView_1
     * @return DieView_1
     */

    public DieView getDieView_1() {

        return dieView_1;
    }

    /**
     * Getter DieView_2
     * @return DieView_2
     */

    public DieView getDieView_2() {

        return dieView_2;
    }
    
    /**
     * Getter PlayerView
     * @return PlayerView
     */
    
    public PlayerView getPlayerView() {
        
        return playerView;
    }
   
}