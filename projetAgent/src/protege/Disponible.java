package protege;

import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: disponible
* @author ontology bean generator
* @version 2008/11/24, 08:32:32
*/
public class Disponible implements Predicate {

   /**
* Protege name: qte
   */
   private int qte;
   public void setQte(int value) { 
    this.qte=value;
   }
   public int getQte() {
     return this.qte;
   }

   /**
* Protege name: disque
   */
   private Disque disque;
   public void setDisque(Disque value) { 
    this.disque=value;
   }
   public Disque getDisque() {
     return this.disque;
   }

}
