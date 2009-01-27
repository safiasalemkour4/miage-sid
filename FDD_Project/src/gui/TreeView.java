
package gui;


import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Romain
 */
public class TreeView extends JFrame {

    /** Panel affichant l'arbre */
    private TreePanel treePanel;
    /** JScrollPane affichant le Panel contenant l'arbre */
    private TreeScrollPanel treeScrollPanel;


    /**
     * Constructeur
     */
    public TreeView(){
        super();

        /* initialisation de la JFrame */
        setSize(1024,768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Arbre de discrimant [ultra dev.team #1 @ MIAGE SID 2009 ]");

        /* initialisation de son contenu */
        treePanel = new TreePanel();
        treeScrollPanel = new TreeScrollPanel();

        /* initialisation du contentPane */
        this.setContentPane(treeScrollPanel);        
        treeScrollPanel.add(treePanel);
        treeScrollPanel.setViewportView(treePanel);

        this.setVisible(true);
    }

    /**
     * Ajoute un noeud (TreeBox) à l'arbre
     * @param tb noeud
     */
    public void addTreeBox(TreeBox tb){
        this.treePanel.add(tb);
    }

    /**
     * Ajoute le label permettant d'afficher une des deux variables d'une scission d'un noeud
     * @param label a afficher
     */
    public void addLabel(JLabel label){
        this.treePanel.add(label);
    }

    /**
     * On efface tous les TreeBox de l'arbre, ce qui revient à "reset" l'arbre
     */
    public void removeAllTreeBox(){
        this.treePanel.removeAll();
    }

    public void centerOnRootNode(){
        System.out.println("taillou: " +  this.getHeight());
        this.treeScrollPanel.getVerticalScrollBar().setValue((UI.TREEPANEL_WIDTH/2) - (this.getHeight()/2) + (UI.TREEBOX_HEIGHT/2)); // + taille fenetre /2
    }

    public void addLine(Line2D line){
        treePanel.addLine(line);
    }

    public void removeAllLines(){
        treePanel.removeAllLines();
    }

}
