package protege;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
 * Protege name: reponseDisponibilite
 * @author ontology bean generator
 * @version 2008/11/24, 08:32:32
 */
public class ReponseDisponibilite implements Predicate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double prix;
	private Disque disque;

	public void setPrix(double value) { 
		this.prix = value;
	}

	public double getPrix() {
		return this.prix;
	}

	public Disque getDisque() {
		return disque;
	}

	public void setDisque(Disque disque) {
		this.disque = disque;
	}

}
