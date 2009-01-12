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
public class ScreenKit extends PersistKit {

    private HightScoreScreen hightScoreScreen;
    
    @Override
    public void makeKit() {
        
        this.hightScoreScreen = new HightScoreScreen();
    }

}
