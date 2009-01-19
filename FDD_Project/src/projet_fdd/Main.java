/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_fdd;

import gui.MyFrame;
import gui.TreeBox;
import gui.TreeFrame;
import java.awt.Dimension;

/**
 *
 * @author maxime
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       MyFrame treeFrame = new MyFrame();
        treeFrame.setPreferredSize(new Dimension(800, 600));

        TreeBox tb = new TreeBox();
        TreeBox tb2 = new TreeBox();
        tb.setBounds(200, 200, 183, 150);
        tb2.setBounds(210, 100, 183, 150);

        treeFrame.getContentPane().add(tb);
        treeFrame.getContentPane().add(tb2);
        tb.setVisible(true);
        tb2.setVisible(true);
        treeFrame.setVisible(true);

        tb.repaint();

        treeFrame.pack();

    }
}
