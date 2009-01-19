package gui;

import core.Die;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    /* Instance sur la fenetre maitresse */
    private DiceGameForm diceGameForm;
    
    /*
     * Note : j'ai choisi d'utiliser 2 isntances de de plutot qu'une 
     * collection de de car cela s'applique mieux à notre modele metier
     * /
  
    /* Notre premier de */
    private Die die_1;

    /* Notre second de */
    private Die die_2;

    /* Label "Die 1 :" */
    private JLabel labelDie_1;

    /* Label "Die 2 :" */
    private JLabel labelDie_2;

    /* TextField affichant la valeur du premier de */
    private JTextField textField_1;

    /* TextField affichant la valeur du second de */
    private JTextField textField_2;

    /**
     * Constructeur
     * @param diceGameForm la fenetre maitresse
     */
    
    public DieView(DiceGameForm diceGameForm) {

        this.diceGameForm = diceGameForm;

        this.setLayout(new GridLayout(3, 2));
        
        this.die_1 = new Die();
        this.die_1.addObserver(this);
        this.die_2 = new Die();
        this.die_2.addObserver(this);
        
        updateContent();

    }

    /**
     * Methode updateContent
     * Met a jour le contenu de la vue
     */
    
    public void updateContent() {

        this.removeAll();
        this.updateUI();

        this.labelDie_1 = new JLabel("Die 1 :");
        this.labelDie_2 = new JLabel("Die 2 :");

        if (this.die_1.getFaceValue() == -1 || this.die_2.getFaceValue() == -1) {

            this.textField_1 = new JTextField("");
            this.textField_2 = new JTextField("");

        } else {
            
            this.textField_1 = new JTextField("" + this.die_1.getFaceValue());
            this.textField_2 = new JTextField("" + this.die_2.getFaceValue());
        }

        this.add(this.labelDie_1);
        this.add(this.textField_1);

        this.add(new JLabel());
        this.add(new JLabel());

        this.add(this.labelDie_2);
        this.add(this.textField_2);

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

        this.die_1.roll();
        this.die_2.roll();
        this.die_1.display();
        this.die_2.display();
    }

    /**
     * Getter Die_1
     * @return die_1
     */
    
    public Die getDie_1() {
        
        return die_1;
    }

    /**
     * Getter Die_2
     * @return die_2
     */

    public Die getDie_2() {
        
        return die_2;
    }
}
