/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persist;

import core.HighScore;

public class JDBCKit extends PersistKit {


    public HighScore makeKit() {
        HighScore hs = HighScore.getInstance(PersistKit.JDBC);
        hs.load();
        return hs;
    }

}
