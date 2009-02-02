package gui;

import etl.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*****************************************************************************************************
 *   					    ~ Data Mining Project (Miage Nancy - SID - 2008/2009) ~             	 *
 *****************************************************************************************************
 *    CLASS  	******		DisplayETL.java                                                          *
 *****************************************************************************************************
 *    			******																				 *
 * DESCRIPTION  ******		JFrame permettant de charger les donnees                 				 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Romain Lafond, Maxime Hoeffel, Simon Hasne, Eric Nguyen-Van,             *
 *              ******      Florian Collignon, Arnaud Knobloch                 						 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class ETLFrame extends JFrame implements ActionListener {

    /** instance de l'UI */
    private static ETLFrame instance = null;

    /* Le chemin du fichier source */
    private String pathFile;

    /* Le composant JTextField */
    private JTextField textFieldInput;

    /* Les composants JButton */
    private JButton buttonInput, buttonOk, buttonLoad;

    /* Le composant JFileChooser */
    private JFileChooser fileChooserInput;

    /* Le composant JComboBox */
    private JComboBox comboHeader;

    /* Le JPanel principal */
    private JPanel content;

    /**
     * Constructeur
     */
    private ETLFrame() {

        content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel input = new JPanel();

        this.textFieldInput = new JTextField();
        this.textFieldInput.setColumns(10);
        this.textFieldInput.setEnabled(false);

        this.buttonInput = new JButton("Browse");
        this.buttonInput.addActionListener(this);

        this.buttonOk = new JButton("Ok");
        this.buttonOk.addActionListener(this);

        this.fileChooserInput = new JFileChooser();
        this.fileChooserInput.setFileSelectionMode(JFileChooser.FILES_ONLY);

        this.buttonLoad = new JButton("Load");
        this.buttonLoad.addActionListener(this);

        input.add(textFieldInput);
        input.add(buttonInput);
        input.add(buttonOk);

        content.add(input, BorderLayout.NORTH);

        this.setTitle("ETL - Loading Data");
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setContentPane(content);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Retourne l'instance unique de l'UI
     * @return
     */
    public static ETLFrame getInstance(){
        if(instance == null)
            instance = new ETLFrame();
        return instance;
    }

    /**
     * Methode actionPerformed
     * @param e l'action (ici le clique)
     */
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonInput) {

            int fileChooserReturn = this.fileChooserInput.showOpenDialog(this);

            if (fileChooserReturn == JFileChooser.APPROVE_OPTION) {

                File file = this.fileChooserInput.getSelectedFile();

                this.textFieldInput.setText(file.getName());
                this.pathFile = file.getPath();
            }
        }

        if (e.getSource() == buttonOk) {

            try {
                LoadCSV.LoadCSVHeader(this.pathFile);
            } catch (IOException ex) {
                Logger.getLogger(ETLFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            JPanel header = new JPanel();

            comboHeader = new JComboBox(LoadCSV.header);
            comboHeader.addActionListener(this);

            header.add(comboHeader);
            header.add(buttonLoad);

            content.add(header, BorderLayout.CENTER);

            this.pack();
        }

        if (e.getSource() == buttonLoad) {

            this.setAlwaysOnTop(false);
            this.setVisible(false);

            try {
                LoadCSV.LoadCSVData(this.pathFile, comboHeader.getSelectedIndex());
            } catch (IOException ex) {
                Logger.getLogger(ETLFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Mehode Main
     * @param args la liste des arguments
     */
    
    public static void main(String[] args) {

        new ETLFrame();
    }
}
