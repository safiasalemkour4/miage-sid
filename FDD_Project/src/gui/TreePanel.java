package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

    /** grossissement du zoom */
    private double scale;

    /**
     * Constructeur
     */
    public TreePanel() {
        super();
        initUI();
        scale = 1.0;
    }

    private void initUI() {
        /* layout null pour procéder à la position absolue */
        this.setLayout(null);
        this.setPreferredSize(new Dimension(UIVars.TREEPANEL_WIDTH, UIVars.TREEPANEL_HEIGHT));
        this.setVisible(true);
    }

    public void removeAllLines() {
        lineList.clear();
    }

    
    public void addLine(Line2D line){
        lineList.add(line);
    }

    public void zoomIn(){
        this.scale += 0.1;
    }

    public void zoomOut(){
        this.scale -= 0.1;
    }

    public void resize(){
        this.scale = 1.0;
    }


    /* surcharge */
    @Override
    public void paint(Graphics g) {
        if (scale == 1.0) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            /* affiche tous les traits */
            for (Line2D line : this.lineList) {
                g2.draw(line);
            }
        } else {
            //super.paintComponent(g); // clears background
            Graphics2D g2 = (Graphics2D) g;
            for (Line2D line : this.lineList) {
                g2.draw(line);
            }
            AffineTransform backup = g2.getTransform();
            g2.scale(scale, scale);
            super.paint(g2);
            g2.setTransform(backup);
            			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            this.revalidate();
        }

//			AffineTransform tx = new AffineTransform();
//			tx.scale(scale, scale);
//			Graphics2D ourGraphics = (Graphics2D) g;
////			ourGraphics.setColor(Color.WHITE);
////			ourGraphics.fillRect(0, 0, getWidth(), getHeight());
//			ourGraphics.setTransform(tx);
////			ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
////					RenderingHints.VALUE_ANTIALIAS_ON);
////			ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
////					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//            for (Line2D line : this.lineList) {
//                ourGraphics.draw(line);
//            }
////			ourGraphics.setColor(Color.BLACK);
////			ourGraphics.drawRect(50, 50, 50, 50);
////			ourGraphics.fillOval(100, 100, 100, 100);
////			ourGraphics.drawString("Test Affine Transform", 50, 30);
//            super.paint(g);



      

    }
}
