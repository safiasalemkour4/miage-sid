/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author arnaud
 */
public class DisplayETL extends JFrame implements ActionListener {

    private JTextField textFieldInput;
    private JButton buttonInput;
    private JFileChooser fileChooserInput;

    public DisplayETL() {

        JPanel content = new JPanel();

        this.textFieldInput = new JTextField();
        this.textFieldInput.setColumns(10);
        this.textFieldInput.setEnabled(false);

        this.buttonInput = new JButton("Browse");
        this.buttonInput.addActionListener(this);

        this.fileChooserInput = new JFileChooser();
        this.fileChooserInput.setFileSelectionMode(JFileChooser.FILES_ONLY);

        content.add(textFieldInput);
        content.add(buttonInput);

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

            }
        }
    }

    public static void main(String[] args) {

        new DisplayETL();
    }
}
