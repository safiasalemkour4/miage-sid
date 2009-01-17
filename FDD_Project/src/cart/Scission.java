package cart;

import etl.*;
import java.util.ArrayList;

public class Scission {

    public static final int T_STRING = 1,  T_NUMERIC = 2,  T_BINARY = 3;

    // L'id de la colonne qui est utilis� par la scission
    private int idColumnCriteria;
    // S'il s'agit d'une scission numeric ou string
    private int typeScission;
    // Si le critere est sur un String quels sont les criteres pour les fils
    // droit et gauche
    private String criteriaLeft;
    private ArrayList<String> criteriaRight;
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
        // 2 tableau à renvoyer : Scission gauche (données avec crière) et Scission droite (autres données)
        Data[] tabNode = new Data[2];
        //Donnée contenu à gauche
        String[][] leftData = null, rightData = null;
        // Toutes les données possible de la colonne ciblée
        Object[] tab = data.getListOccurence(idColumnCriteria);
        // La colonne à tester
        int column = idColumnCriteria;

        int i = 0;
        int j = 0;
        //Tableau temporaire contenant les données à traiter
        String[][] tempData = data.getTabDataValues();

        if (this.isString() || this.isBinary()) {
            //construction des tableaux
            leftData = new String[data.getNbOccurence(column, criteriaLeft)][data.getNbColumn()];
            rightData = new String[data.getNbRow() - data.getNbOccurence(column, criteriaLeft)][data.getNbColumn()];

            // Séparation des données
            for (int row = 0; row < data.getNbRow() - 1; row++) {
                
                // Si donnée corespond au critère gauche mettre dans le tableau Data[0] sinon Data[1]
                if (criteriaLeft.compareTo(data.getValue(row, column)) == 0) {
                    leftData[i] = tempData[row];
                    i++;
                } else {
                    rightData[j] = tempData[row];
                    j++;
                }
            }
        }

        DataInfos[] di = data.getTabDataInfos();
        Data ltab = new Data(leftData, di);
        Data rtab = new Data(rightData, di);
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

    public ArrayList<String> getCriteriaRight() {
        return this.criteriaRight;
    }

    public void setCriteriaRight(ArrayList<String> criteriaRight) {
        this.criteriaRight = criteriaRight;
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
