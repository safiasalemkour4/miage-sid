/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicegame;

import core.Die;
import core.Player;
import gui.DiceGameForm;
import gui.DieView;
import gui.PlayerView;

/**
 *
 * @author arnaud
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Die die = new Die();
        Player player = new Player();
        
        DieView dieView = new DieView();
        PlayerView playerView = new PlayerView();
        
        die.addObserver(dieView);
        player.addObserver(playerView);
                
        new DiceGameForm(dieView, playerView);
    }

}
