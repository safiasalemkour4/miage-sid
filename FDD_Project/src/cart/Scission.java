package cart;

import etl.Data;

public class Scission {

	public static final int T_STRING = 1, T_NUMERIC = 2, T_BINARY = 3;

	// L'id de la colonne qui est utilisé par la scission
	private int idColumnCriteria;
	// S'il s'agit d'une scission numeric ou string
	private int typeScission;
	// Si le critere est sur un String qu'elle sont les criteres pour les fils
	// droit et gauche
	private String[] criteriaLeft;
	private String[] criteriaRight;
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

		Data[] tabNode = new Data[2];
		// TODO Separer les un tableau en deux tableaux dapres le critere
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

	public String[] getCriteriaLeft() {
		return this.criteriaLeft;
	}

	public void setCriteriaLeft(String[] criteriaLeft) {
		this.criteriaLeft = criteriaLeft;
	}

	public String[] getCriteriaRight() {
		return this.criteriaRight;
	}

	public void setCriteriaRight(String[] criteriaRight) {
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

}
