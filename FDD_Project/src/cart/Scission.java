package cart;

import etl.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scission {

    public static final int T_STRING = 1,  T_NUMERIC = 2,  T_BINARY = 3;

    // L'id de la colonne qui est utilis� par la scission
    private int idColumnCriteria;
    // S'il s'agit d'une scission numeric ou string
    private int typeScission;
    // Si le critere est sur un String quel est le critere pour le fils droit
    private String criteriaLeft;
    // Si le critere est numeric on defini la valeur discriminatoire
    private int criteriaInterval;

    public Scission(int idColumn, int type) {
        this.idColumnCriteria = idColumn;
        this.typeScission = type;
    }

    /**
     * Fonction qui separe un ensemble de donnees en deux ensembles discrimines
     * par le critere de la scission
     *
     * @param node
     */
    public Data[] discriminate(Data data) {
        /*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Scission.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        // 2 tableau à renvoyer : Scission gauche (données avec crière) et Scission droite (autres données)
        Data[] tabNode = new Data[2];
        //Donnée contenu à gauche
        String[][] leftData = null, rightData = null;

        // La colonne à tester
        int column = idColumnCriteria;

        int i = 0;
        int j = 0;
        //Tableau temporaire contenant les données à traiter
        String[][] tempData = data.getTabDataValues();
//System.out.println("nbLigneLeft: "+(data.getNbRow() - data.getNbOccurence(column, criteriaLeft)));
        //Si c'est un critère binaire ou un critère en chaine de caractères
        if (this.isString() || this.isBinary()) {
//            System.out.println("nb row data = " + data.getNbRow());
//            System.out.println("data.getNbOccurence(column,criteriaLeft) = " + data.getNbOccurence(column,criteriaLeft));
//            System.out.println("nb row left = " + (data.getNbRow() - data.getNbOccurence(column, criteriaLeft)));

            //construction des tableaux selon criteriaLeft
            leftData = new String[data.getNbOccurence(column, criteriaLeft)][data.getNbColumn()];
            rightData = new String[data.getNbRow() - data.getNbOccurence(column, criteriaLeft)][data.getNbColumn()];
            
            // Séparation des données
            for (int row = 0; row < data.getNbRow(); row++) {

                // Si donnée corespond au critère gauche mettre dans le tableau Data[0] sinon Data[1]
                if (criteriaLeft.compareTo(data.getValue(row, column)) == 0) {
                    leftData[i] = tempData[row];
                    i++;
                } else {
                    rightData[j] = tempData[row];
                    j++;
                }
            }
        } //Sinon si c'est un critère numérique
        else {
            //construction des tableaux selon criteriaInterval
            leftData = new String[data.getNbOccurenceBefore(column, criteriaInterval)][data.getNbColumn()];
            rightData = new String[data.getNbRow() - data.getNbOccurenceBefore(column, criteriaInterval)][data.getNbColumn()];
            
            // Séparation des données
            for (int row = 0; row < data.getNbRow(); row++) {

                // Si donnée corespond au critère gauche mettre dans le tableau Data[0] sinon Data[1]
                if (criteriaInterval < Integer.parseInt(data.getValue(row, column))) {
                    leftData[i] = tempData[row];
                    i++;
                } else {
                    rightData[j] = tempData[row];
                    j++;
                }
            }
        }
        //System.out.println("Scission sur la colonne " + this.idColumnCriteria + " et critereLeft = " + this.getCriteriaLeft() + " et critereNum = " + this.getCriteriaInterval());
        DataInfos[] diLeft = data.getNewDataInfo(leftData, data.getTabDataInfos());
        //System.out.println("dileft: "+diLeft[0].getName());
        Data ltab = new Data(leftData, diLeft);
        DataInfos[] diright = data.getNewDataInfo(rightData, data.getTabDataInfos());
        Data rtab = new Data(rightData, diright);



        tabNode[0] = ltab;
        tabNode[1] = rtab;

        // Verification des données data (à faire....)


        // TODO Separer un tableau en deux tableaux dapres le critere
        return tabNode;

    }

    public int getIdColumnCriteria() {
        return this.idColumnCriteria;
    }

    public void setIdColumnCriteria(int idColumnCriteria) {
        this.idColumnCriteria = idColumnCriteria;
    }

    public int getTypeScission() {
        return this.typeScission;
    }

    public void setTypeScission(int typeScission) {
        this.typeScission = typeScission;
    }

    public String getCriteriaLeft() {
        return this.criteriaLeft;
    }

    public void setCriteriaLeft(String criteriaLeft) {
        this.criteriaLeft = criteriaLeft;
    }

    public int getCriteriaInterval() {
        return this.criteriaInterval;
    }

    public void setCriteriaInterval(int criteriaInterval) {
        this.criteriaInterval = criteriaInterval;
    }

    public boolean isNumeric() {
        return (this.typeScission == T_NUMERIC);
    }

    public boolean isString() {
        return (this.typeScission == T_STRING);
    }

    public boolean isBinary() {
        return (this.typeScission == T_BINARY);
    }

    @Override
    public String toString() {

        String st = "";

        st += "Scission ";
        switch (this.getTypeScission()) {
            case Scission.T_STRING:
                st += "de type String (critere:" + this.getCriteriaLeft() + ")\n";
                break;
            case Scission.T_NUMERIC:
                st += "de type Numerique (critere:" + this.getCriteriaInterval() + ")\n";
                break;
            case Scission.T_BINARY:
                st += "de type Binaire (critere:" + this.getCriteriaLeft() + ")\n";
                break;
        }

        st += "Sur la colonne " + this.getIdColumnCriteria() + "\n";
        
        return st;
    }
}
