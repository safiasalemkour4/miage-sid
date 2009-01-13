package cart;

import java.util.ArrayList;

import etl.Data;

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
	 * permis sa creation, les scissions qu'il peut encore effectuer, son niveau
	 * dans l'arbre
	 * 
	 * @param data
	 * @param origin
	 * @param scissions
	 * @param level
	 */
	public Node(Data data, Scission origin, ArrayList<Scission> scissions,
			int level) {

		this.data = data;
		this.originScission = origin;
		this.possibleScissions = scissions;
		this.developped = false;
		this.level = level;
		this.finalNode = this.isFinalNode();
		// On ajoute le nouveau noeud � l'arbre
		Cart.tree.add(this);
	}

	/**
	 * Fonction qui etend un noeud. Va choisir parmi les scissions possibles
	 * laquelle discrimine le plus. Va creer deux noeuds fils.
	 */
	public void extendNode() {

		// TODO :
		// - verifier si feuille
		// - Etendre un noeud en calculant les valeurs des differentes scissions
		// et choisir le Max (ve
		// - puis creation des deux noeuds fils
		// - insertion des fils dans l'arbre
		// - appel de la meme fonction sur les deux fils
	}

	// TODO
	public double getDiscriminationDegree(Scission scission) {

		// On recupere les deux ensembles qui ont ete discrimine par la scission
		Data possibleDataLeft = scission.discriminate(this.data)[0];
		Data possibleDataRight = scission.discriminate(this.data)[1];

		// On divise le nombre de ligne des donnees obtenues par le nombre de
		// ligne des donnees totales du noeud
		double rateNbLeft = possibleDataLeft.getNbRow()
				/ this.data.getNbRow();
		double rateNbRight = possibleDataRight.getNbRow()
				/ this.data.getNbRow();


		double rateTargetLeft;
		double rateTargetRight;

		return 0.0;
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
		// TODO Regarder si toutes les instances de data ont la meme variable
		// cible
        String target = this.data.getTargetVar();
        
		return this.finalNode;
	}

	public void setFinalNode(boolean finalNode) {
		this.finalNode = finalNode;
	}

}
