/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import core.HighScore;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arnaud
 */
public class HighScoreSerializable extends HighScore {

    public void HightScoreJDBC() {
    }

    @Override
    public void save() {
        FileOutputStream outStream = null;
        {
            ObjectOutputStream persistance = null;
            try {
                outStream = new FileOutputStream("highscore.data");
                persistance = new ObjectOutputStream(outStream);
                persistance.writeObject(this);
                persistance.flush();
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HighScoreSerializable.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    outStream.close();
                    persistance.close();
                } catch (IOException ex) {
                }

            }
        }

    }

    @Override
    public void load() {
        FileInputStream input = null;
        {
            ObjectInputStream persistance = null;
            try {
                input = new FileInputStream("highscore.data");
                persistance = new ObjectInputStream(input);
                HighScoreSerializable hs = (HighScoreSerializable) persistance.readObject();
                this.setListHighScore(hs.getListHighScore());

            } catch (IOException ex) {
                Logger.getLogger(HighScoreSerializable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HighScoreSerializable.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                    persistance.close();
                } catch (IOException ex) {
                    Logger.getLogger(HighScoreSerializable.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
