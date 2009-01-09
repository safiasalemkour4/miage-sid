/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etl;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author arnaud
 */
public class DisplayETL extends JFrame implements ActionListener {

    
    private String pathFile;
    private JTextField textFieldInput;
    private JButton buttonInput,  buttonOk,  buttonLoad;
    private JFileChooser fileChooserInput;
    JPanel content;
    JComboBox comboHeader;

    public DisplayETL() {

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

        this.setContentPane(content);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

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
                Logger.getLogger(DisplayETL.class.getName()).log(Level.SEVERE, null, ex);
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


            try {
                LoadCSV.LoadCSVData(this.pathFile, comboHeader.getSelectedIndex());
            } catch (IOException ex) {
                Logger.getLogger(DisplayETL.class.getName()).log(Level.SEVERE, null, ex);
            }

             System.out.println("");
            System.out.println("--------- Header --------- ");

            DataInfos[] tabDataInfos = LoadCSV.data.getTabDataInfos();

            for (int i = 0; i < tabDataInfos.length; i++) {

                System.out.println("- " + tabDataInfos[i]);

            }
                         System.out.println("");
            System.out.println("--------- DATA --------- ");
            
            String [][] tabDataValues = LoadCSV.data.getTabDataValues();
            
            for (int i = 0; i < tabDataValues.length; i++) {
                    for (int j = 0; j < tabDataValues[0].length; j++) {
                        System.out.print(tabDataValues[i][j]+", ");
                    }
                    System.out.println("");
            }
        }
        
    }

    public static void main(String[] args) {

        new DisplayETL();
    }
}
