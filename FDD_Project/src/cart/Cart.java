package cart;

import java.util.ArrayList;

import etl.Data;

public class Cart {

    // L'arbre des noeuds
    public static ArrayList<Node> tree;
    

    public Cart(Data data) {

        // On cree le tableau representant l'arbre
        Cart.tree = new ArrayList<Node>();     
        // On cree le noeud racine (n'a pas de scission d'origine et de niveau 0)
        Node rootNode = new Node(data, null, "root",  0);
        // On ajoute la racine de l'arbre
        Cart.tree.add(rootNode);
    }

    /**
     * Fonction qui developpe l'arbre
     */
    public void developTree() {

        //Cart.tree.get(0).extendNode();

    }

    
}
