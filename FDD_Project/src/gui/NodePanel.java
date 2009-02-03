package gui;

import cart.Node;
import cart.Scission;
import etl.DataInfos;
import etl.LoadCSV;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;

/**
 * @author  Romain
 */
public class NodePanel extends javax.swing.JPanel {

    /** sujet de l'affichage */
    private Node node;

    /** Creates new form TreeBox */
    public NodePanel(Node node) {
        this.node = node;
        initComponents();
        if(node.isDevelopped() == true){
               this.jButton1.setText("-");
               /* on affiche la variable discriminante choisie (de scission) */
               //this.criteresDiscrCB.setEnabled(false);
               this.criteresDiscrCB.removeAllItems();
               /* pour obtenir la scission d'un noeud, on regarde l'origine de la scission d'un de ses fils */
               this.criteresDiscrCB.addItem(new ScissionItem(   node.getRightSon().getOriginScission(),
                                                                0.0));
               this.criteresDiscrCB.setEnabled(false);
        }else{            
            /* on stocke dans un ArrayList pour pouvoir les trier */
            ArrayList<ScissionItem> comboboxItems = new ArrayList<ScissionItem>();
            for(Entry e : node.getChartDegrees().entrySet()){                
                comboboxItems.add(new ScissionItem((Scission)e.getKey(), (Double)e.getValue()));
            }
            /* on trie */
            Collections.sort(comboboxItems);
            /* on affiche la liste des scissions possibles */
            for(ScissionItem item : comboboxItems){
                criteresDiscrCB.addItem(item);
            }

            /* s'il n'y a plus rien à développer : population = 1 */
            if(comboboxItems.size() == 0){
                criteresDiscrCB.addItem("Aucun critère");
                jButton1.setEnabled(false);
                criteresDiscrCB.setEnabled(false);
            }
        }

        /* on affiche les informations utiles dans la TreeBox */
         this.jLabel1.setText(node.getData().getTargetVar());
         Double nbLignes = new Double(node.getData().getNbRow());
         Double nbLignesTotal = new Double(LoadCSV.data.getNbRow());
         Double repartition = round((nbLignes/nbLignesTotal)* 100,2);
         this.jProgressBar1.setValue(repartition.intValue());
         this.jProgressBar1.setString(node.getData().getNbRow() + " / " + LoadCSV.data.getNbRow() + " [" + repartition + " %]");


             for (DataInfos di : node.getData().getTabDataInfos()) {
                 if (di.getName().equals(node.getData().getTargetVar())) {
                      Double nbLignesTotalTB = new Double(node.getData().getNbRow());
                      //test
                      System.out.println("Nb occurence 1: " + node.getData().getListOccurence(di.getId()).length);


                    // occurence1
                    String occurence1 = (String)node.getData().getListOccurence(di.getId())[0];
                    int nbOccurence1 = node.getData().getNbOccurence(di.getId(), occurence1);                   
                    Double repartitionOccu1 = round((nbOccurence1/nbLignesTotalTB)*100, 2);
                    occurenceLabel1.setText(occurence1 + "  [" + nbOccurence1 + "] " + repartitionOccu1 + "%");

                    // occurence2 (s'il y en a une 2eme ou plusieurs)
                    if(node.getData().getListOccurence(di.getId()).length > 1){
                        String occurence2 = "";
                        int nbOccurence2 = 0;
                        String tooltip = "<html>"; // détails/infos supplémentaires

                        /* s'il n'y a que 2 types (cas binaire) */
                        if(node.getData().getListOccurence(di.getId()).length == 2){
                            occurence2 = (String)node.getData().getListOccurence(di.getId())[1];
                            nbOccurence2 = node.getData().getNbOccurence(di.getId(), occurence2);
                            Double repartitionOccu2 = round((nbOccurence2/nbLignesTotalTB)*100, 2);
                            occurenceLabel2.setText(occurence2 + "  [" + nbOccurence2 + "] " + repartitionOccu2 + "%");
                        }else{
                            /* il y a plusieurs types : donc non-<ocurrence1> */
                            occurence2 = "<html><b style='color:#ff0000'>¬</b> " + occurence1;
                            String occurenceSupp = "<html>";
                            int nbOccurenceSupp = 0;
                            for(int i=1; i<node.getData().getListOccurence(di.getId()).length; i++){
                                occurenceSupp = (String)node.getData().getListOccurence(di.getId())[i];
                                nbOccurenceSupp = node.getData().getNbOccurence(di.getId(), occurenceSupp);
                                nbOccurence2 += nbOccurenceSupp;
                                tooltip += "- <b>" + occurenceSupp + "</b> " + nbOccurenceSupp + "/" + nbLignesTotalTB + "  [" + round((nbOccurenceSupp/nbLignesTotalTB)*100, 2) + "%] <br />";
                            }
                            
                            Double repartitionOccu2 = round((nbOccurence2/nbLignesTotalTB)*100, 2);
                            occurenceLabel2.setText(occurence2 + "  [" + nbOccurence2 + "] " + repartitionOccu2 + "%" + "</html>");
                            tooltip += "</html>";
                           occurenceLabel2.setToolTipText(tooltip);
                        }

                        
                        
                    }else{
                        // on cache le label occurence2
                        occurenceLabel2.setVisible(false);
                    }
                    
                 }
             }

         this.jProgressBar1.setToolTipText(node.getData().getNbRow() + " / " + LoadCSV.data.getNbRow() + " [" + repartition + " %]");
    }

    /** on arrondi à x décimales */
    public double round(double what, int howmuch) {
       return (double) ((int) (what * Math.pow(10, howmuch) + .5)) / Math.pow(10, howmuch);
//        String pattern = "0.00";
//        DecimalFormat df = new DecimalFormat(pattern);
//        Double lol = Double.parseDouble(df.format(what));
//        return lol.doubleValue();

    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        criteresDiscrCB = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        occurenceLabel1 = new javax.swing.JLabel();
        occurenceLabel2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEtchedBorder())));

        jProgressBar1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jProgressBar1.setForeground(new java.awt.Color(255, 102, 255));
        jProgressBar1.setToolTipText("Répartition de la valeur cible");
        jProgressBar1.setValue(25);
        jProgressBar1.setName(""); // NOI18N
        jProgressBar1.setString("25 occurences");
        jProgressBar1.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 204));
        jLabel1.setText("CANCER");

        criteresDiscrCB.setFont(new java.awt.Font("Tahoma", 0, 9));
        criteresDiscrCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criteresDiscrCBActionPerformed(evt);
            }
        });

        jLabel2.setText("Critères discriminants");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        occurenceLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        occurenceLabel1.setText("occurence1");

        occurenceLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        occurenceLabel2.setText("occurence2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(criteresDiscrCB, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(occurenceLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                    .addComponent(occurenceLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occurenceLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(occurenceLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(criteresDiscrCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {criteresDiscrCB, jButton1});

    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jButton1.getText().equals("-")){
           node.setDevelopped(false);
           /* réaffiche l'arbre */
           TreeBuilder.drawRootNode();
       }else{
            /* retourne le parametre de scission de ce noeud ! */
            TreeBuilder.clickOnTreeBox(node, ((ScissionItem)criteresDiscrCB.getSelectedItem()).getScission());
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void criteresDiscrCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criteresDiscrCBActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_criteresDiscrCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox criteresDiscrCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel occurenceLabel1;
    private javax.swing.JLabel occurenceLabel2;
    // End of variables declaration//GEN-END:variables

}
