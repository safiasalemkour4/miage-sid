/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_fdd;

import gui.UI;
import gui.TreeFrame;
import gui.TreeBox;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.geom.Line2D;

/**
 *
 * @author maxime
 */
public class Main {

    /** fenetre affichant l'arbre */
    public static TreeFrame treeFrame;
    /** l'arbre (commence par le noeud racine) */
    public static NodeTest root;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        treeFrame = new TreeFrame();

        /* affichage en plein écran (fullscreen size mode) */
        //int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        //int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        //treeFrame.setSize(new Dimension(screenWidth, screenHeight-30));
        treeFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        /* construction de l'arbre test */
        root = new NodeTest(null, null, "root", 0);
//        NodeTest filsGauche = new NodeTest(null, null, "left", 1);
//        NodeTest filsDroit = new NodeTest(null, null, "right", 1);
//        root.setLeftSon(filsGauche);
//        root.setRightSon(filsDroit);
//        root.setDevelopped(true);

        /* deuxieme niveau */
//        NodeTest lvl2filsGauche = new NodeTest(null, null, "left", 1);
//        NodeTest lvl2filsDroit = new NodeTest(null, null, "right", 1);
//        filsDroit.setLeftSon(lvl2filsGauche);
//        filsDroit.setRightSon(lvl2filsDroit);
//        filsDroit.setDevelopped(true);

//        filsGauche.setLeftSon(lvl2filsGauche);
//        filsGauche.setRightSon(lvl2filsDroit);
//        filsGauche.setDevelopped(true);

        // on positionne le noeud racine puis affiche l'arbre en entier
        drawRootNode();


    }

    public static void drawRootNode() {

        // effacer l'arbre affiché
        treeFrame.getContentPane().removeAll();
        TreeFrame.removeAllLines();

        // trouver la hauteur max (en pixel de l'arbre)
        Main.getMaximumHeigth(root);

        /* AFFICHAGE NIVEAU 1 (positionnement de la racine)*/

        System.out.println("height: " + treeFrame.getHeight() + " width: " + treeFrame.getWidth());
        // calcul du noeud racine
        int noeudRacineX = UI.MARGIN_LEFT;
        int noeudRacineY = (treeFrame.getHeight() / 2) - (UI.TREEBOX_HEIGHT / 2);

        System.out.println("height: " + noeudRacineY + " width: " + noeudRacineX);

        TreeBox tb = new TreeBox(root);
        tb.setBounds(noeudRacineX, noeudRacineY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);
        treeFrame.getContentPane().add(tb);
        tb.setVisible(true);
        tb.repaint();

        /* on appel désormais la fonction d'affichage pour les fils de "parent" (récursive) */
        drawNode(root, noeudRacineX, noeudRacineY);

        treeFrame.setVisible(true);
        treeFrame.validate();
        treeFrame.repaint();

    }

    /**
     * fonction récursive pour afficher l'arbre
     */
    public static void drawNode(NodeTest parent, int parentNodeX, int parentNodeY) {
        /* on test si le noeud est déroulé */
        if (parent.isDevelopped() == true) {
            System.out.println("developpé OK!");

            /* cas où les deux fils sont également "developped" */
            int marge_vertical = getVerticalMargin(parent);
//            if (parent.getRightSon().isDevelopped() == true && parent.getLeftSon().isDevelopped() == true) {
//                marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED;
//                System.out.println("Les deux fils developped.");
//            } else {
//                marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
//                System.out.println("[" + node.hashCode() +"]" + "Un seul fils developped.");
//            }

            getVerticalMargin(parent);

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsDroitX = parentNodeX + UI.TREEBOX_WIDTH + UI.MARGIN_RIGHT;
            int filsDroitY = parentNodeY + (UI.TREEBOX_HEIGHT / 2) - (marge_vertical / 2) - UI.TREEBOX_HEIGHT;

            TreeBox fd = new TreeBox(parent.getRightSon());
            fd.setBounds(filsDroitX, filsDroitY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);
            treeFrame.getContentPane().add(fd);
            fd.setVisible(true);
            fd.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineD = new Line2D.Double(parentNodeX + UI.TREEBOX_WIDTH,
                    parentNodeY + (UI.TREEBOX_HEIGHT / 2) + UI.MARGIN_MINE,
                    filsDroitX,
                    filsDroitY + (UI.TREEBOX_HEIGHT / 2) + UI.MARGIN_MINE);
            TreeFrame.getLineList().add(lineD);

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsGaucheX = parentNodeX + UI.TREEBOX_WIDTH + UI.MARGIN_RIGHT;
            int filsGaucheY = parentNodeY + (UI.TREEBOX_HEIGHT / 2) + (marge_vertical / 2);

            TreeBox fg = new TreeBox(parent.getLeftSon());
            fg.setBounds(filsGaucheX, filsGaucheY, UI.TREEBOX_WIDTH, UI.TREEBOX_HEIGHT);
            treeFrame.getContentPane().add(fg);
            fg.setVisible(true);
            fg.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineG = new Line2D.Double(parentNodeX + UI.TREEBOX_WIDTH,
                    parentNodeY + (UI.TREEBOX_HEIGHT / 2) + UI.MARGIN_MINE,
                    filsGaucheX,
                    filsGaucheY + (UI.TREEBOX_HEIGHT / 2) + UI.MARGIN_MINE);
            TreeFrame.getLineList().add(lineG);

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
    public static void clickOnTreeBox(NodeTest noeud, String paramatreScission) {
        System.out.println("click sur un node:");
        System.out.println("noeud lvl = " + noeud.getLevel());
        System.out.println("paramatreScission = " + paramatreScission);


        // appel de la méthode de simon avec 1 noeud + 1 parametre de scission.
        // on récupère les deux noeuds fils.
        // donc on ajoute au noeud >> ses deux fils
        NodeTest filsGauche = new NodeTest(null, null, "left", noeud.getLevel() + 1);
        NodeTest filsDroit = new NodeTest(null, null, "right", noeud.getLevel() + 1);
        noeud.setLeftSon(filsGauche);
        noeud.setRightSon(filsDroit);
        noeud.setDevelopped(true);

        //    | appel de getMaximunHeight
        //    | on dessine le 1er noeud
        //    | drawNode avec tous l'arbre (en passant le noeud racine)
        drawRootNode();
    // et voilà!
    }

    /**
     * Calcul en fonction de la disposition de ses fils la valeur de marge verticale
     * à appliquer entre ses deux fils
     * @param node
     * @return la valeur de la marge vertical en pixel entre ses deux fils
     */
    public static int getVerticalMargin(NodeTest node) {

        int marge_vertical;

        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + le fils gauche du fils droit est développé
         * + le fils droit du fils gauche est développé */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true &&
                node.getRightSon().getLeftSon().isDevelopped() == true && node.getRightSon().getRightSon().isDevelopped() == false &&
                node.getLeftSon().getRightSon().isDevelopped() == true && node.getLeftSon().getLeftSon().isDevelopped() == false){
          
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 2;
            System.out.println("[" + node.hashCode() + "]" + " BANZAII");
            return marge_vertical;
        }

        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + les 2 fils du fils droit sont développé
         * + les 2 fils du fils gauche sont développé */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true && /* 1er niveau */
                node.getRightSon().getRightSon().isDevelopped() == true && node.getRightSon().getLeftSon().isDevelopped() == true && /* 2e niv droit */
                node.getLeftSon().getRightSon().isDevelopped() == true && node.getLeftSon().getLeftSon().isDevelopped() == true) /* 2e niv gauche */ {
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 3;
            System.out.println("[" + node.hashCode() + "]" + " Les deux fils du 1er niveau sont developpés!");
            return marge_vertical;
        }

        /* aucun fils n'est développé */
        if (node.getRightSon().isDevelopped() == false && node.getLeftSon().isDevelopped() == false) {
            marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
            System.out.println("[" + node.hashCode() + "]" + "Pas de developpement du noeud des 2 fils...");
            return marge_vertical;
        }

        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + le fils droit a ses 2 fils développé
         * + le fils droit a juste sont fils droit développé
         */
         if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true /* le fils droit est développé + le fils gauche est développé */
                && node.getRightSon().getRightSon().isDevelopped() == true && node.getRightSon().getLeftSon().isDevelopped() == true /* 2e niv droit */
                && node.getLeftSon().getRightSon().isDevelopped() == true) /* 2e niv gauche */ {
           marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 3;
           System.out.println("[" + node.hashCode() + "]" + "BANANEEEEEEE ");
           return marge_vertical;
        }

        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + le fils gauche a ses 2 fils développé
         * + le fils gauche a juste sont fils droit développé
         */
         if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true /* le fils droit est développé + le fils gauche est développé */
                && node.getLeftSon().getRightSon().isDevelopped() == true && node.getLeftSon().getLeftSon().isDevelopped() == true /* 2e niv droit */
                && node.getRightSon().getLeftSon().isDevelopped() == true) /* 2e niv gauche */ {
           marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 3;
           System.out.println("[" + node.hashCode() + "]" + " TOMATTTE");
           return marge_vertical;
        }


        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + le fils droit a ses 2 fils développé
         */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true &&/* le fils droit est développé + le fils gauche est développé */
                node.getRightSon().getRightSon().isDevelopped() == true && node.getRightSon().getLeftSon().isDevelopped() == true) { /*+ 2 fils du fils droit développés */
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 2;
            System.out.println("[" + node.hashCode() + "]" + " KIKIIIIIIIIIIII DROIT!");
            return marge_vertical;
        }

        /* à partir d'un noeud racine
         * le fils droit est développé + le fils gauche est développé
         * + le fils gauche a ses 2 fils développé
         */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true &&/* le fils droit est développé + le fils gauche est développé */
                node.getLeftSon().getRightSon().isDevelopped() == true && node.getLeftSon().getLeftSon().isDevelopped() == true) { /*+ 2 fils du fils gauche développés */
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED * 2;
            System.out.println("[" + node.hashCode() + "]" + " KIKIIIIIIIIIIII GAUCHE!");
            return marge_vertical;
        }


        /* cas où les deux fils sont "developped" */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true) {
            marge_vertical = UI.MARGIN_VERTICAL_BOTH_DEVELOPPED;
            System.out.println("[" + node.hashCode() + "]" + "Un noeud a ses deux fils developpés !");
            return marge_vertical;
        }

        marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
        System.out.println("[" + node.hashCode() + "]" + "Un seul des 2 fils developpé.");

        System.out.println("Margin: " + marge_vertical);
        return marge_vertical;
    }

    /**
     * Calcul la hauteur max de l'arbre à partir du noeud racine
     * calculer la hauteur max vers le haut à ârtir du noeud racine
     * idem pr le bas
     * quant vise gauche qui "UP" augmenter de 0.5
     */
    public static int getMaximumHeigth(NodeTest root) {
        System.out.println("Calcul de la position du noeud racine.");
        return 0;
    }
}
