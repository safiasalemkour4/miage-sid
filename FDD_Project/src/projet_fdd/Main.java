/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_fdd;

import cart.Node;
import projet_fdd.NodeTest;
import gui.DataUI;
import gui.MyFrame;
import gui.TreeBox;
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

        /* construction de l'arbre test */
        NodeTest root = new NodeTest(null, null, "root", 0);
        NodeTest filsGauche = new NodeTest(null, null, "left", 1);
        NodeTest filsDroit = new NodeTest(null, null, "right", 1);
        root.setLeftSon(filsGauche);
        root.setRightSon(filsDroit);

        // ALGO !!!
        // trouver la hauteur max (en pixel de l'arbre)
        Main.getMaximumHeigth(root);
        // fixer la position de la racine (facile)
        // puis dérouler l'arbre

        


        /* AFFICHAGE NIVEAU 1 (racine)*/

        System.out.println("height: " + treeFrame.getHeight() + " width: " + treeFrame.getWidth());
        // calcul du noeud racine
        int noeudRacineX = DataUI.MARGIN_LEFT;
        int noeudRacineY = (treeFrame.getHeight() + DataUI.TREEBOX_HIGH) / 2 - DataUI.TREEBOX_HIGH;

        System.out.println("height: " + noeudRacineY + " width: " + noeudRacineX);

        TreeBox tb = new TreeBox();
        tb.setBounds(noeudRacineX, noeudRacineY, DataUI.TREEBOX_WIDTH, DataUI.TREEBOX_HIGH);
        treeFrame.getContentPane().add(tb);
        tb.setVisible(true);
        tb.repaint();
        treeFrame.setVisible(true);

        /* AFFICHAGE NIVEAU 2 */
        TreeBox tb2 = new TreeBox();
        // calcul du noeud racine
        int noeud2X = DataUI.MARGIN_LEFT + DataUI.TREEBOX_WIDTH + DataUI.MARGIN_RIGHT;
        int noeud2Y = (treeFrame.getHeight() + DataUI.TREEBOX_HIGH) / 2 - DataUI.TREEBOX_HIGH;
        tb2.setBounds(noeudRacineX, noeudRacineY, DataUI.TREEBOX_WIDTH, DataUI.TREEBOX_HIGH);

    }

    /**
     * Calcul la hauteur max de l'arbre à partir du noeud racine
     * calculer la hauteur max vers le haut à ârtir du noeud racine
     * idem pr le bas
     * quant vise gauche qui "UP" augmenter de 0.5
     */
    public static int getMaximumHeigth(NodeTest root) {        
        return 0;
    }
}
