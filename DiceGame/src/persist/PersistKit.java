/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import core.HighScore;

public abstract class PersistKit {

    private static PersistKit pesist_kit = null;
    
    public static final int JDBC = 1, SERIALIZABLE = 2, XML = 3;

    
    public abstract HighScore makeKit();

    public static PersistKit getInstance(int type) {
        if (pesist_kit == null) {
            if (type == JDBC) {
                pesist_kit = new JDBCKit();
            } else  if (type == SERIALIZABLE) {
                pesist_kit = new SerializableKit();
            } 
        }
        return pesist_kit;

    }
}
