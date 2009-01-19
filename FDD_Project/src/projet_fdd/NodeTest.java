package projet_fdd;

import cart.*;
import java.util.ArrayList;

import etl.Data;
import java.util.HashMap;
import java.util.Iterator;

public class NodeTest {

    // Coté du noeud (droit ou gauche )
    String side;
    // Un noeud contient des donnees
    private Data data;
    // Scission qui a engendre ce noeud
    private Scission originScission;
    // Les scissions encore possible sur le noeud
    private ArrayList<Scission> possibleScissions;
    // Le fils gauche
    private NodeTest leftSon;
    // Le fils droit
    private NodeTest rightSon;
    // Le noeud a ete developpe ou non
    private boolean developped;
    // Niveau du noeud dans l'arbre
    private int level;
    // Si le noeud est feuille ou non
    private boolean finalNodeTest;

    /**
     * On construit un noeud en lui donnant ses donnees, la scission qui a
     * permis sa creation et son niveau dans l'arbre
     *
     * @param data
     * @param origin
     * @param scissions
     * @param level
     */
    public NodeTest(Data data, Scission origin, String side, int level) {
        this.data = data;
        this.originScission = origin;
        // On calcul les scissions possible pour le noeud
        this.possibleScissions = new ArrayList<Scission>();
        //this.computePossibleScission();
        this.developped = false;
        this.level = level;
        this.side = side;
        //this.finalNodeTest = this.isFinalNodeTest();
        // On ajoute le nouveau noeud a l'arbre
        //Cart.tree.add(this);
    }

    /**
     * Fonction qui etend un noeud en utilisant la scission passee
     * en parametre.Va creer deux noeuds fils avec les deux nouveaux
     * sous ensemble de donnees.
     */
    public void developp(Scission scission) {

        // - verifier si feuille
        // - creation des deux noeuds fils avec les sous-ensembles des donnees
        // - insertion des fils dans l'arbre


        if (this.isFinalNodeTest()) {
            System.out.println("Noeud Feuille");
        } else {
            // On recupere les deux tableaux de donnees issues de la discrimination par la scission
            Data[] tabData = scission.discriminate(this.data);

            // On cree deux noeuds avec les donnees discriminees, la scission a l'origine de la
            // discrimination, le tableau des scissions possible pour le nouveau noeud et
            // on augmente le niveau du noeud de 1 par rapport a son pere.
            NodeTest leftNodeTest = new NodeTest(tabData[0], scission, "left", this.level + 1);
            NodeTest rightNodeTest = new NodeTest(tabData[1], scission, "right", this.level + 1);

            // On fait le lien entre les noeuds fils et le noeud pere
            this.leftSon = leftNodeTest;
            this.rightSon = rightNodeTest;
            // On ajoute les deux noeuds a l'arbre
            //Cart.tree.add(this.leftSon);
            //Cart.tree.add(this.rightSon);
            // Le noeud est maintenant developpe
            this.developped = true;


        }
    }

    /**
     * Fonction qui calcule tous les degres de discrimination des scissions que peut
     * effectuer le noeud sur son ensemble de donnees. Elle renvoie une HashMap
     * ayant pour cle la scission et comme valeur son degre.
     * @return
     */
    public HashMap<Scission, Double> getChartDegrees() {


        HashMap<Scission, Double> map = new HashMap<Scission, Double>();
        for (Iterator<Scission> it = possibleScissions.iterator(); it.hasNext();) {

            Scission scission = it.next();
            map.put(scission, getDiscriminationDegree(scission));

        }

        return map;

    }

    /**
     * Fonction calculant le degre de discrimination de la scission
     * passee en parametre sur les donnees du noeud
     * @param scission
     * @return double
     */
    public double getDiscriminationDegree(Scission scission) {

        int idTarget = this.data.getTargetVarId();
        double rateTargetLeft;
        double rateTargetRight;
        double sumRateTarget = 0.0;
        // On recupere les deux ensembles qui ont ete discrimine par la scission
        Data possibleDataLeft = scission.discriminate(this.data)[0];
        Data possibleDataRight = scission.discriminate(this.data)[1];
        System.out.print("Calcul de la scission sur le critere : "+scission.getCriteriaLeft()+"\n");
        System.out.print("--nbRowLeft: " + possibleDataLeft.getNbRow() + " ;nbRowRight: " + possibleDataRight.getNbRow()+"\n");
        // On divise le nombre de ligne des donnees obtenues par le nombre de
        // ligne des donnees totales du noeud
        double rateNbLeft = possibleDataLeft.getNbRow() / this.data.getNbRow();
        double rateNbRight = possibleDataRight.getNbRow() / this.data.getNbRow();

        // On boucle sur toutes les valeurs que peut prendre la cible
        for (int i = 0; i < this.data.getListOccurence(idTarget).length; i++) {

            // On recupere la valeur courante
            String currentValueTarget = (String) this.data.getListOccurence(this.data.getTargetVarId())[i];
            System.out.println(";valeurcourante Cible: "+currentValueTarget+"\n");
            System.out.println(";nb de cible à gauche: "+possibleDataLeft.getNbOccurence(idTarget, currentValueTarget)+"\n");
            // On recupere le nombre d'instance possedant cette valeur a gauche et droite
            rateTargetLeft = possibleDataLeft.getNbOccurence(idTarget, currentValueTarget);
            rateTargetRight = possibleDataRight.getNbOccurence(idTarget, currentValueTarget);
            //System.out.print("--rateTargetLeft: " + rateTargetLeft + " ;rateTargetRight: " + rateTargetRight);
            // On somme la soustraction en valeur absolue
            sumRateTarget += Math.abs(rateTargetLeft - rateTargetRight);



        }

        double totalDegree = 2 * rateNbLeft * rateNbRight * sumRateTarget;
        return totalDegree;
    }

    /**
     * Cette fonction calcule toutes les possibilites de scission qu'offre
     * l'ensemble de donnees. Renvoi un tableau de scission
     *
     * @param data
     * @return
     */
    public ArrayList<Scission> computePossibleScission() {



        // TODO Calcul de toutes les possibilites de scission

        // On parcours la liste des colonnes
        for (int i = 0; i < this.data.getNbColumn(); i++) {

            // On ne s'interesse qu'aux variables non-cibles
            if (!this.data.isTargetVar(i)) {

                // Si la variable est de type string les scissions sont les
                // combinaisons entre les occurences avec d'un cote une valeur
                // et de l'autre le reste des valeurs

                if (this.data.isString(i)) {

                    Object[] listValue = this.data.getListOccurence(i);

                    // On boucle sur chaque valeur de la liste pour l'isoler afin de créer
                    // une scission
                    for (int j = 0; j < listValue.length; j++) {

                        String leftCriteria = (String) listValue[j];
                        ArrayList<String> rightCriteria = new ArrayList<String>();

                        // On boucle pour stocker les valeurs autres que le leftCriteria
                        for (int k = 0; k < listValue.length; k++) {
                            String value = (String) listValue[k];
                            if (!value.equals(leftCriteria)) {
                                rightCriteria.add(value);
                            }

                        }
                        // On cree une nouvelle scission de type String sur la colonne
                        // en question et avec les criteres definis plus haut
                        Scission scission = new Scission(i, Scission.T_STRING);
                        scission.setCriteriaLeft(leftCriteria);
                        // On ajoute la scission aux scissions possible
                        possibleScissions.add(scission);

                    }


                } else {
                    if (this.data.isNumeric(i)) {
                        // Si la colonne est numerique
//                    int[] listBound = this.data.getBoundaries();
//                    for (int j = 0; j < listBound.length; j++) {
//                        int currentBound = listBound[j];
//                        Scission scission = new Scission(i, Scission.T_NUMERIC);
//                        scission.setCriteriaInterval(currentBound);
//                        possibleScissions.add(scission);
//
//                    }
                    } else {
                        // Si la colonne est de type binaire on ne stocke qu'une seule scission
                        Object[] listValue = this.data.getListOccurence(i);
                        String leftCriteria = (String) listValue[0];
                        String right = (String) listValue[1];
                        ArrayList<String> rightCriteria = new ArrayList<String>();
                        rightCriteria.add(right);
                        Scission scission = new Scission(i, Scission.T_BINARY);
                        scission.setCriteriaLeft(leftCriteria);
                        // On ajoute la scission aux scissions possible
                        possibleScissions.add(scission);
                    }
                }

            }

        }

        return this.possibleScissions;

    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Scission getOriginScission() {
        return this.originScission;
    }

    public void setOriginScission(Scission originScission) {
        this.originScission = originScission;
    }

    public ArrayList<Scission> getPossibleScissions() {
        return this.possibleScissions;
    }

    public void setPossibleScissions(ArrayList<Scission> possibleScissions) {
        this.possibleScissions = possibleScissions;
    }

    public NodeTest getLeftSon() {
        return this.leftSon;
    }

    public void setLeftSon(NodeTest leftSon) {
        this.leftSon = leftSon;
    }

    public NodeTest getRightSon() {
        return this.rightSon;
    }

    public void setRightSon(NodeTest rightSon) {
        this.rightSon = rightSon;
    }

    public boolean isDevelopped() {
        return this.developped;
    }

    public void setDevelopped(boolean developped) {
        this.developped = developped;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isFinalNodeTest() {

        int indexCible = data.getTargetVarId();
        Object[] list = data.getListOccurence(indexCible);
        if (list.length == 1) {
            return true;
        } else {
            return false;
        }

    }

    public void setFinalNodeTest(boolean finalNodeTest) {
        this.finalNodeTest = finalNodeTest;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {

        String st = "";

        st += "\nLe noeud " + this.side + this.level + " possede " + this.data.getNbRow() + " lignes. \n";
        if (this.level == 0) {
            st += "Il s'agit du noeud racine\n";
        } else {
            st += this.getOriginScission().toString();
        }
        st += "Developpe? : " + this.developped + "\n";
        st += "Nombre de scissions possible : " + this.possibleScissions.size() + "\n";
        st += "Scission possible : \n";
        for (Iterator<Scission> it = possibleScissions.iterator(); it.hasNext();) {
            Scission scission = it.next();
            st += "- " + scission + "\n";
        }

        return st;
    }
}
