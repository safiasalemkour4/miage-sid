/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import core.HighScore;

/**
 *
 * @author arnaud
 */
public class SerializableKit extends PersistKit {

    @Override
    public HighScore makeKit() {
        HighScore hs = HighScore.getInstance(PersistKit.SERIALIZABLE);
        hs.load();
        return hs;
    }
}
