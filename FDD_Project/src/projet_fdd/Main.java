/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_fdd;

import cart.Node;
import cart.Scission;
import etl.Data;
import etl.DisplayETL;
import etl.LoadCSV;
import gui.UI;
import gui.TreeFrame;
import gui.TreeBox;
import gui.TreeView;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.geom.Line2D;

/**
 * classe a renommer !
 */
public class Main {

    /** fenetre affichant l'arbre */
    //public static TreeFrame treeFrame;
    public static TreeView treeView;
    /** l'arbre (commence par le noeud racine) */
    public static Node root;

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//
//        treeView = new TreeView();
//
//        /* affichage en plein écran (fullscreen size mode) */
//        //int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
//        //int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
//        //treeFrame.setSize(new Dimension(screenWidth, screenHeight-30));
//        //treeFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
//
//        /* construction de l'arbre test */
//        root = new Node(null, null, "root", 0);
//
//        // on positionne le noeud racine puis affiche l'arbre en entier
//        drawRootNode();
//
//    }

    public Main(Data data){
        
        // On cree le noeud racine (n'a pas de scission d'origine et de niveau 0)
        root = new Node(data, null, "root",  0);
        System.out.println("rootNode = " + root);
        treeView = new TreeView();
        // on positionne le noeud racine puis affiche l'arbre en entier
        drawRootNode();
    }

    public static void drawRootNode() {

        // effacer l'arbre affiché
        treeView.removeAllTreeBox();
        // effacer les lignes
        treeView.removeAllLines();

        /* AFFICHAGE NIVEAU 1 (positionnement de la racine)*/

        System.out.println("height: " + treeView.getHeight() + " width: " + treeView.getWidth());
        // calcul du noeud racine
        int noeudRacineX = UI.MARGIN_LEFT;
        int noeudRacineY = UI.TREEPANEL_HEIGHT / 2;

        System.out.println("height: " + noeudRacineY + " width: " + noeudRacineX);

        TreeBox tb = new TreeBox(root);
        tb.setBounds(noeudRacineX, noeudRacineY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);

        /* on ajoute */
        treeView.addTreeBox(tb);
        tb.setVisible(true);
        //tb.repaint();
        //tb.revalidate();

        /* on appel désormais la fonction d'affichage pour les fils de "parent" (récursive) */
        drawNode(root, noeudRacineX, noeudRacineY);
        treeView.centerOnRootNode();

        treeView.validate();
        treeView.repaint();

    }

    /**
     * fonction récursive pour afficher l'arbre
     */
    public static void drawNode(Node parent, int parentNodeX, int parentNodeY) {
        /* on test si le noeud est déroulé */
        if (parent.isDevelopped() == true) {
            System.out.println("developpé OK!");

            /* cas où les deux fils sont également "developped" */
            int marge_vertical = getVerticalMargin2(parent);

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsDroitX = parentNodeX + UI.TREEBOX_WIDTH + UI.MARGIN_RIGHT;
            int filsDroitY = parentNodeY + (UI.TREEBOX_HEIGHT / 2) - (marge_vertical / 2) - UI.TREEBOX_HEIGHT;

            TreeBox fd = new TreeBox(parent.getRightSon());
            fd.setBounds(filsDroitX, filsDroitY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);
            treeView.addTreeBox(fd);
            fd.setVisible(true);
            fd.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineD = new Line2D.Double(parentNodeX + UI.TREEBOX_WIDTH,
                    parentNodeY + (UI.TREEBOX_HEIGHT / 2),
                    filsDroitX,
                    filsDroitY + (UI.TREEBOX_HEIGHT / 2));
            treeView.addLine(lineD);

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsGaucheX = parentNodeX + UI.TREEBOX_WIDTH + UI.MARGIN_RIGHT;
            int filsGaucheY = parentNodeY + (UI.TREEBOX_HEIGHT / 2) + (marge_vertical / 2);

            TreeBox fg = new TreeBox(parent.getLeftSon());
            fg.setBounds(filsGaucheX, filsGaucheY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);
            treeView.addTreeBox(fg);
            fg.setVisible(true);
            fg.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineG = new Line2D.Double(parentNodeX + UI.TREEBOX_WIDTH,
                    parentNodeY + (UI.TREEBOX_HEIGHT / 2),
                    filsGaucheX,
                    filsGaucheY + (UI.TREEBOX_HEIGHT / 2));
            treeView.addLine(lineG);

            /* appel récursif vers les fils */
            drawNode(parent.getLeftSon(), filsGaucheX, filsGaucheY);
            drawNode(parent.getRightSon(), filsDroitX, filsDroitY);

        } else {
            System.out.println("developpé NON! Feuille !");
        }
    }

    /**
     * Clic sur un noeud
     */
    public static void clickOnTreeBox(Node noeud, Scission scission) {
        System.out.println("click sur un node:");
        System.out.println("noeud lvl = " + noeud.getLevel());
        //noeud.getChartDegrees().g
        System.out.println("paramatreScission = " + scission.toString());

        if (noeud.isDevelopped() == false) {

            /* on développe le noeud avec la scission */
            noeud.developp(scission);
            
//            Node filsGauche =  new Node(noeud.getData(), null, "root",  0);
//            Node filsDroit = new Node(noeud.getData(), null, "root",  0);
//            noeud.setLeftSon(filsGauche);
//            noeud.setRightSon(filsDroit);
//            noeud.setDevelopped(true);

//            System.out.println("==============> lvl = " + noeud.getLevel());
//              if(noeud.getLeftSon() == null || noeud.getRightSon() == null){
//                  System.out.println("=============> NULL CHIRLDREN");
//
//                  System.exit(0);
//              }


//            if( noeud == null)
//                System.out.println("noeud null");
//            if (filsGauche == null)
//                 System.out.println("fils gauche null");
//            if (filsDroit == null)
//                 System.out.println("fils droit null");

            /* on redéssine l'arbre */
            drawRootNode();
        }
    }

     /**
     * Calcul en fonction de la disposition de ses fils la valeur de marge verticale
     * à appliquer entre ses deux fils
     * @param node
     * @return la valeur de la marge vertical en pixel entre ses deux fils
     */
     public static int getVerticalMargin2(Node node) {
        int marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED; /* valeur par défaut */

         /* aucun fils n'est développé = feuille */
        if (node.getRightSon().isDevelopped() == false && node.getLeftSon().isDevelopped() == false) {
            marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
            System.out.println("[" + node.hashCode() + "]" + "Aucun fils developpé.");
            return marge_vertical;
        }

        /* fils droit développé et fils gauche = feuille */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == false) {
            /* si l'arbre du fils droit est mini, on n'agrandit pas */
            int right = getVerticalMargin2(node.getRightSon());
//            if(right == UI.MARGIN_VERTICAL_ONE_DEVELOPPED)
//                marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
//            else
               marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED * 20;
            return marge_vertical + right;
        }

        /* fils gauche développé et fils droit = feuille */
        if (node.getLeftSon().isDevelopped() == true && node.getRightSon().isDevelopped() == false) {    
            /* si l'arbre du fils gauche est mini, on n'agrandit pas */
            int left = getVerticalMargin2(node.getLeftSon());
//            if(left == UI.MARGIN_VERTICAL_ONE_DEVELOPPED)
//                marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
//            else
               marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED * 20;
            return marge_vertical + left;
        }

        /* fils droit et fils gauche développés */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true) {
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED;
            return marge_vertical + getVerticalMargin2(node.getRightSon()) + getVerticalMargin2(node.getLeftSon());
        }

         return marge_vertical;

     }
}
