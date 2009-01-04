/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arnaud
 */
public class DiceGameForm extends JFrame implements ActionListener {

    private JPanel content;
    private DieView dieView;
    private PlayerView playerView;
    private JButton buttonStart;
    private JButton buttonStop;

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
        actionPanel.add(this.buttonStart);
        actionPanel.add(this.buttonStop);

        this.content.add(this.playerView, BorderLayout.NORTH);
        this.content.add(this.dieView, BorderLayout.CENTER);
        this.content.add(actionPanel, BorderLayout.SOUTH);

        this.setContentPane(content);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public void startAction() {
        
        this.dieView.startAction();
        this.playerView.startAction();

    }

    public void stopAction() {
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.buttonStart) {

            System.out.println("Start...");
            startAction();
        }

        if (e.getSource() == this.buttonStop) {

            System.out.println("Stop...");
            stopAction();
        }
    }

    public DieView getDieView() {
        return dieView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
    
    
}