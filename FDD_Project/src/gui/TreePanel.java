package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Romain
 */
public class TreePanel extends JPanel {

    /** liste des lignes à tracer */
    private ArrayList<Line2D> lineList = new ArrayList<Line2D>();

    /**
     * Constructeur
     */
    public TreePanel() {
        super();
        initUI();
    }

    private void initUI() {
        /* layout null pour procéder à la position absolue */
        this.setLayout(null);
        this.setPreferredSize(new Dimension(UI.TREEPANEL_WIDTH, UI.TREEPANEL_HEIGHT));
        this.setVisible(true);
    }

    public void removeAllLines() {
        lineList.clear();
    }

    
    public void addLine(Line2D line){
        lineList.add(line);
    }


    /* surcharge */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        /* affiche tous les traits */
        for (Line2D line : this.lineList) {
            g2.draw(line);
        }
             //super.paintComponent(g); // clears background
//            Graphics2D g2 = (Graphics2D) g;
//            AffineTransform backup = g2.getTransform();
//            g2.scale(1.1, 1.1);
//            super.paint(g);
//            g2.setTransform(backup);
//            this.revalidate();

    }
}
