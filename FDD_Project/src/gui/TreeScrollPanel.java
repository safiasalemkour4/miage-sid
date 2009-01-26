
package gui;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * @author Romain
 */
public class TreeScrollPanel extends JScrollPane {

    public TreeScrollPanel(){
        super();

        this.setLayout(new ScrollPaneLayout());

        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setWheelScrollingEnabled(true);
        
        
        this.setVisible(true);
    }

}
