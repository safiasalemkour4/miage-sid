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

    public static MyFrame treeFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        treeFrame = new MyFrame();
        treeFrame.setPreferredSize(new Dimension(800, 600));

        /* construction de l'arbre test */
        NodeTest root = new NodeTest(null, null, "root", 0);
        NodeTest filsGauche = new NodeTest(null, null, "left", 1);
        NodeTest filsDroit = new NodeTest(null, null, "right", 1);
        root.setLeftSon(filsGauche);
        root.setRightSon(filsDroit);
        root.setDevelopped(true);

        // ALGO !!!
        // trouver la hauteur max (en pixel de l'arbre)
        Main.getMaximumHeigth(root);
        // fixer la position de la racine (facile)
        // puis dérouler l'arbre récursivement
        // méthode "drawNode"

        


        /* AFFICHAGE NIVEAU 1 (racine)*/

        System.out.println("height: " + treeFrame.getHeight() + " width: " + treeFrame.getWidth());
        // calcul du noeud racine
        int noeudRacineX = DataUI.MARGIN_LEFT;
        int noeudRacineY = (treeFrame.getHeight() + DataUI.TREEBOX_HEIGHT) / 2 - DataUI.TREEBOX_HEIGHT;

        System.out.println("height: " + noeudRacineY + " width: " + noeudRacineX);

        TreeBox tb = new TreeBox();
        tb.setBounds(noeudRacineX, noeudRacineY, DataUI.TREEBOX_WIDTH, DataUI.TREEBOX_HEIGHT);
        treeFrame.getContentPane().add(tb);
        tb.setVisible(true);
        tb.repaint();        

        // on appel désormais la fonction d'affichage pour le ou les fils de "parent"
        drawNode(root, noeudRacineX, noeudRacineY);

        treeFrame.setVisible(true);
        treeFrame.validate();
    }

    /**
     * fonction récursive pour afficher l'arbre
     */
    public static void drawNode(NodeTest parent, int parentNodeX, int parentNodeY){
        /* on test si le noeud est déroulé */
        if(parent.isDevelopped() == true){
            System.out.println("developpé OK!");

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsDroitX = parentNodeX + DataUI.TREEBOX_WIDTH + DataUI.MARGIN_RIGHT;
            int filsDroitY = parentNodeY + (DataUI.TREEBOX_HEIGHT/2) - (DataUI.MARGIN_VERTICAL/2) - DataUI.TREEBOX_HEIGHT;            

            TreeBox fd = new TreeBox();
            fd.setBounds(filsDroitX, filsDroitY, DataUI.TREEBOX_WIDTH, DataUI.TREEBOX_HEIGHT);
            treeFrame.getContentPane().add(fd);
            fd.setVisible(true);
            fd.repaint();

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsGaucheX = parentNodeX + DataUI.TREEBOX_WIDTH + DataUI.MARGIN_RIGHT;
            int filsGaucheY = parentNodeY + (DataUI.TREEBOX_HEIGHT/2) + (DataUI.MARGIN_VERTICAL/2);

            TreeBox fg = new TreeBox();
            fg.setBounds(filsGaucheX, filsGaucheY, DataUI.TREEBOX_WIDTH, DataUI.TREEBOX_HEIGHT);
            treeFrame.getContentPane().add(fg);
            fg.setVisible(true);
            fg.repaint();

            /* appel récursif vers les fils */
            drawNode(parent.getLeftSon(), filsGaucheX, filsGaucheY);
            drawNode(parent.getRightSon(), filsDroitX, filsDroitY);

        }else{
            System.out.println("developpé NON!");
        }
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
