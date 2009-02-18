package gui;

import core.Die;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*****************************************************************************************************
 *   					    ~ Dice Game (Miage Nancy - SID - 2008/2009) ~							 *
 *****************************************************************************************************
 *    CLASS  	******		DieView.java                                                             *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		Panel represenant la vue "de"                             				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Arnaud Knobloch                 										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class DieView extends JPanel implements Observer {

    /* L'objet associe a la vue */
    private Die die;
    
    /* Label "Die" */
    private JLabel labelDie;

    /* Nos images de de */
    private ImageIcon[] tabDiePictures = {
        new ImageIcon("data/die_0.png"),
        new ImageIcon("data/die_1.png"),
        new ImageIcon("data/die_2.png"),
        new ImageIcon("data/die_3.png"),
        new ImageIcon("data/die_4.png"),
        new ImageIcon("data/die_5.png"),
        new ImageIcon("data/die_6.png"),
    };
    
    /**
     * Constructeur
     * @param diceGameForm la fenetre maitresse
     */
    
    public DieView(Die die) {
        
        this.die = die;

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

        this.labelDie = new JLabel(tabDiePictures[0]);

        if (this.die.getFaceValue() == -1) {

            this.labelDie = new JLabel(tabDiePictures[0]);

        } else {
            
            this.labelDie = new JLabel(tabDiePictures[this.die.getFaceValue()]);
        }

        this.add(this.labelDie);
    }

    /**
     * Methode update
     * @param o l'objet observe
     * @param arg les arguments
     */
    
    public void update(Observable o, Object arg) {

        this.updateContent();
    }

    /**
     * Methode startAction
     * Permet de debuter les actions liees a cette vue
     */
    
    public void startAction() {

        this.die.roll();

        this.die.display();

    }

    /**
     * Getter Die
     * @return die
     */
    
    public Die getDie() {
        
        return die;
    }

}
