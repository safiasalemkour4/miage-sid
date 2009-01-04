/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import core.Die;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author arnaud
 */
public class DieView extends JPanel implements Observer {

        private DiceGameForm diceGameForm;
        
    private Die die_1;
    private Die die_2;
        
    private JLabel labelDie_1;
    private JLabel labelDie_2;
    
    private JTextField textField_1;
    private JTextField textField_2;
    
    public DieView(DiceGameForm diceGameForm) {

        this.diceGameForm = diceGameForm;
        
        this.setLayout(new GridLayout(3,2));
        this.die_1 = new Die();
               this.die_1.addObserver(this);
        this.die_2 = new Die();
                 this.die_2.addObserver(this);
        updateContent();
        
 

    }
    
    public void updateContent() {
        
        this.removeAll();
        this.updateUI();
        
        this.labelDie_1 = new JLabel("Die 1 :");
        this.labelDie_2 = new JLabel("Die 2 :");
        
        this.textField_1 = new JTextField(""+this.die_1.getFaceValue());
        this.textField_2 = new JTextField(""+this.die_2.getFaceValue());
        
        this.add(this.labelDie_1);
        this.add(this.textField_1);
        
        this.add(new JLabel());
        this.add(new JLabel());
                
        this.add(this.labelDie_2);
        this.add(this.textField_2);

    }
    
    public void update(Observable o, Object arg) {

        
        this.updateContent();
        
        
    }
    
    public void startAction() {
        

        this.die_1.roll();
        this.die_2.roll();
       this.die_1.display();
              this.die_2.display();
    }

    public Die getDie_1() {
        return die_1;
    }
    
    public Die getDie_2() {
        return die_2;
    }


    

}
