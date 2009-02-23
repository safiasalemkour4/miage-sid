package gui;

import cart.Node;
import cart.Scission;
import etl.Data;
import etl.DataInfos;
import etl.LoadCSV;
import java.awt.geom.Line2D;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

/**
 * Classe contenant l'algo pour afficher l'arbre
 */
public class TreeBuilder {

    /** fenetre affichant l'arbre */
    public static TreeFrame treeView;
    /** l'arbre (commence par le noeud racine) */
    public static Node root;

    /**
     * Constructeur
     * @param data
     */
    public TreeBuilder() {

        /* configuration d'affichage des tooltips */
        ToolTipManager.sharedInstance().setDismissDelay(3000);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setReshowDelay(0);

        // On cree le noeud racine (n'a pas de scission d'origine et de niveau 0)
        root = new Node(LoadCSV.data, null, "root", 0);
        treeView = TreeFrame.getInstance();
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
        int noeudRacineX = UIVars.MARGIN_LEFT;
        int noeudRacineY = UIVars.TREEPANEL_HEIGHT / 2;

        System.out.println("height: " + noeudRacineY + " width: " + noeudRacineX);

        NodePanel tb = new NodePanel(root);
        tb.setBounds(noeudRacineX, noeudRacineY, UIVars.TREEBOX_WIDTH, UIVars.TREEBOX_HEIGHT);

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
            int filsDroitX = parentNodeX + UIVars.TREEBOX_WIDTH + UIVars.MARGIN_RIGHT;
            int filsDroitY = parentNodeY + (UIVars.TREEBOX_HEIGHT / 2) - (marge_vertical / 2) - UIVars.TREEBOX_HEIGHT;

            NodePanel fd = new NodePanel(parent.getRightSon());
            fd.setBounds(filsDroitX, filsDroitY, UIVars.TREEBOX_WIDTH, UIVars.TREEBOX_HEIGHT);
            treeView.addTreeBox(fd);
            fd.setVisible(true);
            fd.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineD = new Line2D.Double(parentNodeX + UIVars.TREEBOX_WIDTH,
                    parentNodeY + (UIVars.TREEBOX_HEIGHT / 2),
                    filsDroitX,
                    filsDroitY + (UIVars.TREEBOX_HEIGHT / 2));
            treeView.addLine(lineD);

            /**************************************************/
            /* calcul des coordonnées du TreeBox "FILS DROIT" */
            /**************************************************/
            int filsGaucheX = parentNodeX + UIVars.TREEBOX_WIDTH + UIVars.MARGIN_RIGHT;
            int filsGaucheY = parentNodeY + (UIVars.TREEBOX_HEIGHT / 2) + (marge_vertical / 2);

            NodePanel fg = new NodePanel(parent.getLeftSon());
            fg.setBounds(filsGaucheX, filsGaucheY, UIVars.TREEBOX_WIDTH, UIVars.TREEBOX_HEIGHT);
            treeView.addTreeBox(fg);
            fg.setVisible(true);
            fg.repaint();

            /* on ajoute le trait jusqu'à son parent */
            Line2D lineG = new Line2D.Double(parentNodeX + UIVars.TREEBOX_WIDTH,
                    parentNodeY + (UIVars.TREEBOX_HEIGHT / 2),
                    filsGaucheX,
                    filsGaucheY + (UIVars.TREEBOX_HEIGHT / 2));
            treeView.addLine(lineG);

            /*****************************************************************************************/
            /** Ajout des labels variable discrimante, occurence gauche et droite de cette dernière **/
            /*****************************************************************************************/

            /* On récolte les 2 occurences de la variable de scission */
            String occurenceDiscrDroit = "?";
            String occurenceDiscrGauche = "?";
            String listeOccurenceGauche = "?";
            String variableDiscr = "?";
            /* pour tous les noeuds sauf la racine */
            if (parent.getRightSon() != null) {
                for (DataInfos di : parent.getData().getTabDataInfos()) {
                    if (di.getId() == parent.getLeftSon().getOriginScission().getIdColumnCriteria()) {
                        variableDiscr = di.getName();
                        /* occurence gauche */
                        occurenceDiscrGauche = (String) parent.getLeftSon().getData().getListOccurence(di.getId())[0];

                        /* s'il n'y a qu'une seule occurence droite (non gauche) */
                        if (parent.getRightSon().getData().getListOccurence(di.getId()).length == 1) {
                            occurenceDiscrDroit = (String) parent.getRightSon().getData().getListOccurence(di.getId())[0];
                            listeOccurenceGauche = "Une seule occurence de non " + occurenceDiscrGauche + ".";
                        } else {
                            /* il y en a plusieurs, on récupère les occurences droites (non gauche) */
                            occurenceDiscrDroit = "<html><b style='color:#ff0000'>¬</b> " + occurenceDiscrGauche + "<br />  " + "   (<u style='color:#AC58FA'>détails</u>)</html>";
                            /* liste des 'non gauche' */
                            listeOccurenceGauche = "<html>";
                            for (int i = 0; i < parent.getRightSon().getData().getListOccurence(di.getId()).length; i++) {
                                listeOccurenceGauche += "- <b>" + parent.getRightSon().getData().getListOccurence(di.getId())[i] + "</b><br />";
                            }
                            listeOccurenceGauche += "</html>";
                        }
                    }
                }
            }

            /* ajout du label de la valeur de la variable discrimante choisie */
            JLabel lbOccurenceDiscrDroit = new JLabel(occurenceDiscrGauche);
            lbOccurenceDiscrDroit.setBounds(parentNodeX + UIVars.TREEBOX_WIDTH + 5,
                    parentNodeY + UIVars.TREEBOX_HEIGHT / 2 - UIVars.TREEBOXLABEL_HEIGHT / 2 - 40,
                    UIVars.TREEBOXLABEL_WIDTH, UIVars.TREEBOXLABEL_HEIGHT);
            lbOccurenceDiscrDroit.setVisible(true);            
            treeView.addLabel(lbOccurenceDiscrDroit);

            /* ajout du label de la valeur de la variable discrimante choisie */
            JLabel lbOccurenceDiscrGauche = new JLabel(occurenceDiscrDroit);
            lbOccurenceDiscrGauche.setBounds(parentNodeX + UIVars.TREEBOX_WIDTH + 5,
                    parentNodeY + UIVars.TREEBOX_HEIGHT / 2 - UIVars.TREEBOXLABEL_HEIGHT / 2 + 40,
                    UIVars.TREEBOXLABEL_WIDTH, UIVars.TREEBOXLABEL_HEIGHT);
            /* on ajoute la liste des occurences non gauche */
            lbOccurenceDiscrGauche.setToolTipText(listeOccurenceGauche);
            lbOccurenceDiscrGauche.setVisible(true);
            treeView.addLabel(lbOccurenceDiscrGauche);

            /* ajout du label de la valeur de la variable discrimante choisie */
            JLabel lbVariableDiscr = new JLabel("[" + variableDiscr + "]");
            lbVariableDiscr.setBounds(parentNodeX + UIVars.TREEBOX_WIDTH + 10,
                    parentNodeY + UIVars.TREEBOX_HEIGHT / 2 - UIVars.TREEBOXLABEL_HEIGHT / 2,
                    UIVars.TREEBOXLABEL_WIDTH, UIVars.TREEBOXLABEL_HEIGHT);
            lbVariableDiscr.setVisible(true);
            treeView.addLabel(lbVariableDiscr);




            /* appel récursif vers les fils */
            drawNode(parent.getLeftSon(), filsGaucheX, filsGaucheY);
            drawNode(parent.getRightSon(), filsDroitX, filsDroitY);

        } else {
            //System.out.println("developpé NON! Feuille !");
        }
    }

    /**
     * Clic sur un noeud
     */
    public static void clickOnTreeBox(Node noeud, Scission scission) {
        System.out.println("click sur un node:");
        System.out.println("noeud lvl = " + noeud.getLevel());
        System.out.println("paramatreScission = " + scission.toString());

        if (noeud.isDevelopped() == false) {
            /* on développe le noeud avec la scission */
            noeud.developp(scission);
            /* on affiche l'arbre à partir de la racine */
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
        int marge_vertical = UIVars.MARGIN_VERTICAL_ONE_DEVELOPPED; /* valeur par défaut */

        /* aucun fils n'est développé = feuille */
        if (node.getRightSon().isDevelopped() == false && node.getLeftSon().isDevelopped() == false) {
            marge_vertical = UIVars.MARGIN_VERTICAL_ONE_DEVELOPPED;
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
            marge_vertical = UIVars.MARGIN_VERTICAL_ONE_DEVELOPPED * 20;
            return marge_vertical + right;
        }

        /* fils gauche développé et fils droit = feuille */
        if (node.getLeftSon().isDevelopped() == true && node.getRightSon().isDevelopped() == false) {
            /* si l'arbre du fils gauche est mini, on n'agrandit pas */
            int left = getVerticalMargin2(node.getLeftSon());
//            if(left == UI.MARGIN_VERTICAL_ONE_DEVELOPPED)
//                marge_vertical = UI.MARGIN_VERTICAL_ONE_DEVELOPPED;
//            else
            marge_vertical = UIVars.MARGIN_VERTICAL_ONE_DEVELOPPED * 20;
            return marge_vertical + left;
        }

        /* fils droit et fils gauche développés */
        if (node.getRightSon().isDevelopped() == true && node.getLeftSon().isDevelopped() == true) {
            marge_vertical = UIVars.MARGIN_VERTICAL_BOTH_DEVELOPPED;
            return marge_vertical + getVerticalMargin2(node.getRightSon()) + getVerticalMargin2(node.getLeftSon());
        }

        return marge_vertical;

    }
}
