package protege;


import jade.content.Predicate;

/**
 * Protege name: validerAchat
 * @author ontology bean generator
 * @version 2008/11/24, 08:32:32
 */
public class ValiderAchat implements Predicate {

	/**
	 * Protege name: reponse
	 */
	private boolean reponse;
	private int qté;
	private Disque disc;

	public void setReponse(boolean value) { 
		this.reponse=value;
	}
	public boolean getReponse() {
		return this.reponse;
	}
	public int getQté() {
		return qté;
	}
	public void setQté(int qté) {
		this.qté = qté;
	}
	public Disque getDisc() {
		return disc;
	}
	public void setDisc(Disque disc) {
		this.disc = disc;
	}



}
