/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arnaud
 */
public class DiceGameForm extends JFrame {

    private JPanel content;
    
    private JPanel dieView;
    private JPanel playerView;
    
    private JButton buttonStart;
    private JButton buttonStop;
    
    public DiceGameForm() {
        
        
    }

    public DiceGameForm(DieView dieView, PlayerView playerView) {
        
        this.dieView = dieView;
        this.playerView = playerView;
        
        this.content = new JPanel();
        this.content.setLayout(new BorderLayout());
        
        JPanel actionPanel = new JPanel();
        this.buttonStart = new JButton("Start");
        this.buttonStop = new JButton("Stop");
        actionPanel.add(this.buttonStart);
        actionPanel.add(this.buttonStop);
                
        this.content.add(this.playerView, BorderLayout.NORTH);
        this.content.add(this.dieView, BorderLayout.CENTER);
        this.content.add(actionPanel, BorderLayout.SOUTH);
        
        this.setContentPane(content);
        
    }
    
    public void startAction() {
        
        //this.
                           
    }
    
    public void stopAction() {
        
    }
}
