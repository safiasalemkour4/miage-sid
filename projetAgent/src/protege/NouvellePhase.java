package protege;



import jade.content.Predicate;

/**
* Protege name: nouvellePhase
* @author ontology bean generator
* @version 2008/11/24, 08:32:32
*/
public class NouvellePhase implements Predicate {

   /**
* Protege name: numeroPhase
   */
	public final static int PHASE_PROD = 1;
	public final static int PHASE_ACHAT = 2;
   private int numeroPhase;
   public void setNumeroPhase(int value) { 
    this.numeroPhase=value;
   }
   public int getNumeroPhase() {
     return this.numeroPhase;
   }

}
