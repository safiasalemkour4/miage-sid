/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persist;

import core.PersistKit;

/**
 *
 * @author arnaud
 */
public class SerializableKit extends PersistKit {

    private HightScoreSerializable hightScoreScreen;
    
    @Override
    public void makeKit() {
        
        this.hightScoreScreen = new HightScoreSerializable();
    }

}
