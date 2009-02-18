package gui;

import core.DiceGame;
import core.Entry;
import core.EntryComparator;
import core.HighScore;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

public class HighScoreView extends JDialog implements ActionListener {

    /* Le type de persistance */
    private int persistType;
    
    /* La liste des HighScore (=entry) */
    private LinkedList<Entry> listHightScore;
    
    /* Le contenu de la JFrame */
    private JPanel content;
    
    /* Le bouton de fermeture de la fenetre */
    private JButton exitButton;

    /**
     * Constructeur
     */
    
    public HighScoreView(int persistType) {

        super();

        this.persistType = persistType;

        this.setModal(true);
        this.setBackground(Color.BLACK);

        this.content = new JPanel();
        this.content.setLayout(new BorderLayout());

        updateContent();
        
        this.setContentPane(content);

        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }

    /**
     * Methode updateContent
     * Met a jour le contenu de la vue (de la fenetre donc)
     */
    
    public void updateContent() {

        DiceGame dicegame = DiceGame.getInstance(this.persistType);
        HighScore highScore = dicegame.getHighscore();
        highScore.load();
        ArrayList<Entry> listHighScore = highScore.getListHighScore();
        
        this.content.removeAll();
        this.content.updateUI();

        Vector<String> columnNames = new Vector<String>();
        columnNames.add(0, "Name");
        columnNames.add(1, "Score");

        Vector<Vector<String>> entries = new Vector<Vector<String>>();
                
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(0, 2));

        /* On trie notre liste */
        Collections.sort(listHighScore, new EntryComparator());

        int cpt = 0;
        
        for (Entry entry : listHighScore) {
            
            /* On affiche que les 20 meilleurs resultats */
            if (cpt==20) {
                break;
            }
            
            Vector<String> vectorEntry = new Vector<String>();
            vectorEntry.add(entry.getName());
            vectorEntry.add(""+entry.getScore());
            entries.add(vectorEntry);

            cpt++;
        }

        JTable table = new JTable(entries, columnNames);
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(new Color(139,0,0));
        table.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 18));
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setGridColor(Color.WHITE);
        table.setRowHeight(20);
        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.BLACK);
        table.setFillsViewportHeight(true);

        this.content.add(new JLabel(new ImageIcon("data/dice_game_highscore.png")), BorderLayout.NORTH);
        this.content.add(scrollPane, BorderLayout.CENTER);

        this.exitButton = new JButton(new ImageIcon("data/exit.png"));
        this.exitButton.setBackground(Color.BLACK);
        this.exitButton.setFocusPainted(false);
		this.exitButton.setBorderPainted(false);
		this.exitButton.setContentAreaFilled(false);
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