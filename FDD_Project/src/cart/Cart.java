package cart;

import java.util.ArrayList;

import etl.Data;
import etl.DataInfos;

public class Cart {

	// L'arbre des noeuds
	public static ArrayList<Node> tree;
	// Les Scissions possible sur l'ensemble de donnees
	public static ArrayList<Scission> possibleScissions;

	public Cart(Data data) {

		// On cree le tableau representant l'arbre
		Cart.tree = new ArrayList<Node>();
		// On calcule les differentes scissions possible
		Cart.possibleScissions = this.computePossibleScission(data);
		// On cree le noeud racine (n'a pas de scission d'origine et de niveau
		// 0)
		Node rootNode = new Node(data, null, Cart.possibleScissions, 0);
		// On ajoute la racine de l'arbre
		Cart.tree.add(rootNode);
	}

	/**
	 * Fonction qui developpe l'arbre
	 */
	public void developTree() {

		Cart.tree.get(0).extendNode();

	}

	/**
	 * Cette fonction calcule toutes les possibilites de scission qu'offre
	 * l'ensemble de donnees. Renvoi un tableau de scission
	 * 
	 * @param data
	 * @return
	 */
	public ArrayList<Scission> computePossibleScission(Data data) {

		ArrayList<Scission> tabScission = new ArrayList<Scission>();

		// TODO Calcul de toutes les possibilites de scission

		// On parcours la liste des colonnes
		for (int i = 0; i < data.getTabDataInfos().length; i++) {

			DataInfos currentColumn = data.getTabDataInfos()[i];

			// On ne s'interesse qu'aux variables non-cibles
			if (!currentColumn.isTargetVar()) {

				// Si la variable est de type string les scissions sont les
				// combinaisons entre les occurences
				if (currentColumn.isString()) {

				} else {

				}

			}

		}

		return tabScission;

	}

}
