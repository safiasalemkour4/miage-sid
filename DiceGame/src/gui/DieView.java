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

    private Die die;
    
    private JLabel labelDie_1;
    private JLabel labelDie_2;
    
    private JTextField textField_1;
    private JTextField textField_2;
    
    public DieView() {
        
        this.setLayout(new GridLayout(3,2));
        this.die = new Die();
        
        updateContent();
    }
    
    public void updateContent() {
        
        this.removeAll();
        this.updateUI();
        
        this.labelDie_1 = new JLabel("Die 1 :");
        this.labelDie_2 = new JLabel("Die 2 :");
        
        this.textField_1 = new JTextField();
        this.textField_2 = new JTextField();
        
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
        //this.die.
    }

}
