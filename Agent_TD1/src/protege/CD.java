package protege;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: CD
* @author ontology bean generator
* @version 2008/11/4, 10:58:26
*/
public class CD implements Concept {

   /**
* Protege name: title
   */
   private String title;
   
   public void setTitle(String value) { 
    this.title=value;
   }
   
   public String getTitle() {
     return this.title;
   }

   /**
* Protege name: price
   */
   private List price = new ArrayList();
   
   public void addPrice(Float elem) { 
     List oldList = this.price;
     price.add(elem);
   }
   
   public boolean removePrice(Float elem) {
     List oldList = this.price;
     boolean result = price.remove(elem);
     return result;
   }
   public void clearAllPrice() {
     List oldList = this.price;
     price.clear();
   }
   public Iterator getAllPrice() {return price.iterator(); }
   public List getPrice() {return price; }
   public void setPrice(List l) {price = l; }

   /**
* Protege name: nbTrack
   */
   private int nbTrack;
   public void setNbTrack(int value) { 
    this.nbTrack=value;
   }
   public int getNbTrack() {
     return this.nbTrack;
   }

   /**
* Protege name: inPromotion
   */
   private boolean inPromotion;
   public void setInPromotion(boolean value) { 
    this.inPromotion=value;
   }
   public boolean getInPromotion() {
     return this.inPromotion;
   }

}
