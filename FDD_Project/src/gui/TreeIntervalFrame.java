package gui;

import etl.Data;
import etl.LoadCSV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import javax.swing.JDialog;

/**
 *
 * @author Romain
 */
public class TreeIntervalFrame extends JDialog {

    /** instance de l'UI */
    private static TreeIntervalFrame instance = null;
    /** liste des colonnes numériques */
    final ArrayList<String> intervalColumns;
    /** map associant pour une colonne numérique renseignée les intervalles saisies */
    HashMap<Integer, ArrayList<Integer>> intervalsMap;


    public static TreeIntervalFrame getInstance(){
            if(instance == null && LoadCSV.getData() != null){
                instance = new TreeIntervalFrame(LoadCSV.getData());
            }
            return instance;
    }

    /** 
     * Contructeur de la fenetre TreeInterval
     */
    private TreeIntervalFrame(Data data) {       
        setTitle("Saisies des intervalles des colonnes de type numérique.");
        //setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
        setModal(true);

        this.intervalsMap = new HashMap<Integer, ArrayList<Integer>>();
        this.intervalColumns = new ArrayList<String>();

        /* on récupère toutes les colonnes */
        for (int col = 0; col < data.getNbColumn(); col++) {
            if (data.isNumeric(col)) {
                System.out.println("La colonne " + col + "[ " + data.getColumnName(col) + "] est de type numérique.");
                intervalColumns.add("(" + col + ")  " + data.getColumnName(col) + " [" + data.getMinValue(col) + "-" + data.getMaxValue(col) + "]");
            }
        }

        initComponents();

        Object[][] dataModel = new Object[intervalColumns.size()][1];
        for (int col = 0; col < intervalColumns.size(); col++) {
            dataModel[col][0] = intervalColumns.get(col);
        }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                dataModel,
                new String[]{
                    "Colonnes numériques", "Intervalles"
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        // Set the first visible column to 100 pixels wide
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
       
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonValider = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setBackground(new java.awt.Color(255, 204, 255));
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Colonnes numériques", "Intervalles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 102, 255));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);

        buttonValider.setText("Valider");
        buttonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValiderActionPerformed(evt);
            }
        });

        jButton2.setText("Fermer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(buttonValider, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValiderActionPerformed
        /* on va remplir la map contenant pour une colonne numérique renseignée les intervalles saisies */
        for (int col = 0; col < intervalColumns.size(); col++) {
            /* nom de la colonne */
            String nomColonne = (String) jTable1.getValueAt(col, 0);
            /* colonne sur laquelle l'utilisateur a posé des intervalles */
            String dataColonne = (String) jTable1.getValueAt(col, 1);

            if (dataColonne != null && dataColonne.trim().length() > 0) {
                /* on regarde le numéro de colonne */
                int numColonne = Integer.parseInt(nomColonne.substring(nomColonne.indexOf("(") + 1, nomColonne.indexOf(")")));
                
                /* on récupère les intervalles */
                StringTokenizer st = new StringTokenizer(dataColonne, ";");
                /* accès aux tokens */
                ArrayList<Integer> intervalsList = new ArrayList<Integer>();
                while (st.hasMoreTokens()) {
                    Integer interval = Integer.parseInt(st.nextToken());
                    intervalsList.add(interval);
                }

                /* on associe les intervalles au numéro de la colonne */
                this.intervalsMap.put(numColonne, intervalsList);
                this.buttonValider.setEnabled(false);
                this.jTable1.setEnabled(false);
            }
        }

        /* affichage test de la map d'intervalles */
        System.out.println("Affichage de la map d'intervalles");
        for(Entry colonne: intervalsMap.entrySet()){
            System.out.print("Colonne " + colonne.getKey() + " ----->    ");
            for(Integer unInterval : (ArrayList<Integer>)colonne.getValue()){
                System.out.print("[" + unInterval + "]");
            }
            System.out.println();
        }

        this.setVisible(false);

}//GEN-LAST:event_buttonValiderActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Retourne la map des intervalles des colonnes numériques
     * @return map des intervalles des colonnes numériques
     */
    public HashMap<Integer, ArrayList<Integer>> getIntervalsMap(){
        return intervalsMap;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonValider;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}