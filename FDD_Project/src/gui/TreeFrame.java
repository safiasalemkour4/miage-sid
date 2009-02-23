
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;


/**
 * @author  maxime
 */
public class TreeFrame extends javax.swing.JFrame {
    
    /** unique instance de l'UI */
    private static TreeFrame instance = null;
    /** Panel affichant l'arbre */
    private TreePanel treePanel;
    /** JScrollPane affichant le Panel contenant l'arbre */
    private TreeScrollPanel treeScrollPanel;

    /** Creates new form MyFrame */
    private TreeFrame() {
        initComponents();
        setSize(new Dimension(800,600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        /* initialisation de son contenu */
        treePanel = new TreePanel();
        treeScrollPanel = new TreeScrollPanel();

        /* initialisation du contentPane */
        this.setContentPane(content);

        /* on ajoute les élements au content */
        content.add(treeScrollPanel, BorderLayout.CENTER);

        /* on ajoute les élements au panneau avec les ascenceurs (zone d'affichage de l'arbre) */
        treeScrollPanel.add(treePanel);
        treeScrollPanel.setViewportView(treePanel);

        /* création de la barre d'outils */
        JToolBar toolBar = new JToolBar();
        toolBar.setBorder(new EtchedBorder());
        
        /* ajout des raccourcis */
         /* Import */
        ImageIcon iconImport = new ImageIcon("import.png");
        Action actionImport = new AbstractAction("Import", iconImport) {
            public void actionPerformed(ActionEvent e) {
                    ETLFrame.getInstance().setVisible(true);
            }
        };


        /* Intervalles */
        ImageIcon iconInterv = new ImageIcon("intervalles.png");
        Action actionInterv = new AbstractAction("Intervalles", iconInterv) {

            public void actionPerformed(ActionEvent e) {
                if(TreeIntervalFrame.getInstance() != null)
                    TreeIntervalFrame.getInstance().setVisible(true);
            }
        };

         /* Zoom + */
        ImageIcon iconZoomIn = new ImageIcon("zoom-in.png");
        Action actionZoomIn = new AbstractAction("Zoom In", iconZoomIn) {

            public void actionPerformed(ActionEvent e) {
                TreeFrame.getInstance().zoomIn();
            }
        };

        /* Zoom + */
        ImageIcon iconResize = new ImageIcon("resize.png");
        Action actionResize = new AbstractAction("Resize", iconResize) {

            public void actionPerformed(ActionEvent e) {
               TreeFrame.getInstance().resize();
            }
        };
        
         /* Zoom - */
        ImageIcon iconZoomOut = new ImageIcon("zoom-out.png");
        Action actionZoomOut = new AbstractAction("Zoom In", iconZoomOut) {

            public void actionPerformed(ActionEvent e) {
                 TreeFrame.getInstance().zoomOut();
            }
        };


        /* on ajoute les boutons à la barre d'outils */
        toolBar.add(actionImport);
        toolBar.addSeparator();
        toolBar.add(actionInterv);
        toolBar.addSeparator();
        toolBar.add(actionZoomIn);
        toolBar.add(actionResize);
        toolBar.add(actionZoomOut);


        /* on ajoute la barre d'outils à l'UI */
        content.add(toolBar, BorderLayout.NORTH);
        
    }


      // Instantiate a sample action with the NAME property of
        // "Download" and the appropriate SMALL_ICON property.
//        SampleAction exampleAction = new SampleAction("Download", new ImageIcon("action.gif"));
//        JButton exampleButton = new JButton(exampleAction);

//    class SampleAction extends AbstractAction {
//        // This is our sample action. It must have an actionPerformed() method,
//        // which is called when the action should be invoked.
//        public SampleAction(String text, Icon icon) {
//            super(text, icon);
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Action [" + e.getActionCommand() + "] performed!");
//        }
//    }

    public static TreeFrame getInstance() {
        if (TreeFrame.instance == null) {
            TreeFrame.instance = new TreeFrame();
        }
        return TreeFrame.instance;
    }

    public void prepapeTree(){
        /* initialisation de la JFrame au dimension max de l'écran */
        this.setExtendedState(Frame.MAXIMIZED_BOTH);        
    }

     /**
     * Ajoute un noeud (TreeBox) à l'arbre
     * @param tb noeud
     */
    public void addTreeBox(NodePanel tb){
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
        this.treeScrollPanel.getVerticalScrollBar().setValue((UIVars.TREEPANEL_WIDTH/2) - (this.getHeight()/2) + (UIVars.TREEBOX_HEIGHT/2)); // + taille fenetre /2
    }

    public void addLine(Line2D line){
        treePanel.addLine(line);
    }

    public void removeAllLines(){
        treePanel.removeAllLines();
    }

    public void zoomIn(){
        this.treePanel.zoomIn();
        this.treePanel.repaint();
    }

     public void zoomOut(){
        this.treePanel.zoomOut();
        this.treePanel.repaint();
    }

      public void resize(){
        this.treePanel.resize();
        this.treePanel.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmb = new javax.swing.JMenuBar();
        jm_file = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmi_quit = new javax.swing.JMenuItem();
        jm_about = new javax.swing.JMenu();
        jmi_about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iFouille de données (Ed. 2009) - \"Parce que nous ne nous contentons pas de fouiller, nous trouvons !\"");
        setFocusCycleRoot(false);

        jm_file.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Import ...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jm_file.add(jMenuItem1);

        jmi_quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmi_quit.setText("Quit");
        jmi_quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_quitActionPerformed(evt);
            }
        });
        jm_file.add(jmi_quit);

        jmb.add(jm_file);

        jm_about.setText("?");

        jmi_about.setText("About ...");
        jmi_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_aboutActionPerformed(evt);
            }
        });
        jm_about.add(jmi_about);

        jmb.add(jm_about);

        setJMenuBar(jmb);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jmi_quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_quitActionPerformed
    this.dispose();
}//GEN-LAST:event_jmi_quitActionPerformed

private void jmi_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_aboutActionPerformed
    JOptionPane.showMessageDialog(this, "Projet : Fouille de Données\n" +
	    "Par : \n" +
	    "	Florian COLLIGNON\n" +
	    "	Simon HASNE\n" +
	    "	Maxime HOEFFEL\n" +
	    "	Arnaud KNOBLOCH\n" +
	    "	Romain LAFOND\n" +
	    "	Eric NGUYEN VAN", 
	    "About : Miage SID 2009", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_jmi_aboutActionPerformed


private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    /* à l'import du fichier */
    ETLFrame.getInstance();
}//GEN-LAST:event_jMenuItem1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jm_about;
    private javax.swing.JMenu jm_file;
    private javax.swing.JMenuBar jmb;
    private javax.swing.JMenuItem jmi_about;
    private javax.swing.JMenuItem jmi_quit;
    // End of variables declaration//GEN-END:variables

}
