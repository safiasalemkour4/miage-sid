package cart;

import java.util.ArrayList;

import etl.Data;
import java.util.HashMap;
import java.util.Iterator;

public class Node {

    // Un noeud contient des donnees
    private Data data;
    // Scission qui a engendre ce noeud
    private Scission originScission;
    // Les scissions encore possible sur le noeud
    private ArrayList<Scission> possibleScissions;
    // Le fils gauche
    private Node leftSon;
    // Le fils droit
    private Node rightSon;
    // Le noeud a ete developpe ou non
    private boolean developped;
    // Niveau du noeud dans l'arbre
    private int level;
    // Si le noeud est feuille ou non
    private boolean finalNode;

    /**
     * On construit un noeud en lui donnant ses donnees, la scission qui a
     * permis sa creation, son niveau dans l'arbre
     *
     * @param data
     * @param origin
     * @param scissions
     * @param level
     */
    public Node(Data data, Scission origin, int level) {

        this.data = data;
        this.originScission = origin;
        this.possibleScissions = this.computePossibleScission();
        this.developped = false;
        this.level = level;
        this.finalNode = this.isFinalNode();
        // On ajoute le nouveau noeud a l'arbre
        Cart.tree.add(this);
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


        if (this.isFinalNode()) {
            System.out.println("Noeud Feuille");
        } else {
            // On recupere les deux tableaux de donnees issues de la discrimination par la scission
            Data[] tabData = scission.discriminate(this.data);

            // TODO calcul des scissions possibles pour les noeuds dils

            // On cree deux noeuds avec les donnees discriminees, la scission a l'origine de la
            // discrimination, le tableau des scissions possible pour le nouveau noeud et
            // on augmente le niveau du noeud de 1 par rapport a son pere.
            Node leftNode = new Node(tabData[0], scission, this.level + 1);
            Node rightNode = new Node(tabData[1], scission, this.level + 1);

            // On fait le lien entre les noeuds fils et le noeud pere
            this.leftSon = leftNode;
            this.rightSon = rightNode;
            // On ajoute les deux noeuds a l'arbre
            Cart.tree.add(this.leftSon);
            Cart.tree.add(this.rightSon);


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


    // TODO
    public double getDiscriminationDegree(Scission scission) {

        int idTarget = this.data.getTargetVarId();
        double rateTargetLeft;
        double rateTargetRight;
        double sumRateTarget = 0.0;
        // On recupere les deux ensembles qui ont ete discrimine par la scission
        Data possibleDataLeft = scission.discriminate(this.data)[0];
        Data possibleDataRight = scission.discriminate(this.data)[1];

        // On divise le nombre de ligne des donnees obtenues par le nombre de
        // ligne des donnees totales du noeud
        double rateNbLeft = possibleDataLeft.getNbRow() / this.data.getNbRow();
        double rateNbRight = possibleDataRight.getNbRow() / this.data.getNbRow();

        // On boucle sur toutes les valeurs que peut prendre la cible
        for (int i = 0; i < this.data.getListOccurence(idTarget).length; i++) {

            // On recupere la valeur courante
            String currentValueTarget = (String) this.data.getListOccurence(this.data.getTargetVarId())[i];
            // On recupere le nombre d'instance possedant cette valeur a gauche et droite
            rateTargetLeft = possibleDataLeft.getNbOccurence(idTarget, currentValueTarget);
            rateTargetRight = possibleDataRight.getNbOccurence(idTarget, currentValueTarget);
            // On somme la soustraction
            sumRateTarget += rateTargetLeft - rateTargetRight;

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

        ArrayList<Scission> tabScission = new ArrayList<Scission>();

        // TODO Calcul de toutes les possibilites de scission

        // On parcours la liste des colonnes
        for (int i = 0; i < this.data.getNbColumn(); i++) {

            // On ne s'interesse qu'aux variables non-cibles
            if (!this.data.isTargetVar(i)) {

                // Si la variable est de type string les scissions sont les
                // combinaisons entre les occurences avec d'un cote une valeur
                // et de l'autre le reste des valeurs
                if (this.data.isString(i)) {
                    
                    String[] listValue = (String[]) this.data.getListOccurence(i);

                    // On boucle sur chaque valeur de la liste pour l'isoler afin de créer
                    // une scission
                    for (int j = 0; j < listValue.length; j++) {

                        String leftCriteria = listValue[j];
                        ArrayList<String> rightCriteria = new ArrayList<String>();

                        // On boucle pour stocker les valeurs autres que le leftCriteria
                        for (int k = 0; k < listValue.length; k++) {
                            String value = listValue[k];
                            if (!value.equals(leftCriteria)) {
                                rightCriteria.add(value);
                            }

                        }
                        // On cree une nouvelle scission de type String sur la colonne
                        // en question et avec les criteres definis plus haut
                        Scission scission = new Scission(i, Scission.T_STRING);
                        scission.setCriteriaLeft(leftCriteria);
                        scission.setCriteriaRight(rightCriteria);
                        // On ajoute la scission aux scissions possible
                        possibleScissions.add(scission);

                    }


                } else {
                    // Si la colonne est numerique
                }

            }

        }

        return tabScission;

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

    public Node getLeftSon() {
        return this.leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public Node getRightSon() {
        return this.rightSon;
    }

    public void setRightSon(Node rightSon) {
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

    public boolean isFinalNode() {

        int indexCible = data.getTargetVarId();
        Object[] list = data.getListOccurence(indexCible);
        if (list.length == 1) {
            return true;
        } else {
            return false;
        }

    }

    public void setFinalNode(boolean finalNode) {
        this.finalNode = finalNode;
    }
}
