
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Class AnalogClock
 * @author Arnaud Knobloch
 */

public class AnalogClock implements Observer {

	/**
	 * Constructeur
	 */
	public AnalogClock() {
		
	}
	
	/**
	 * Methode draw
	 * @param clockTimer
	 */
	
	public void draw(Date date) {
        
		/* On cree le format pour l'affichage de l'heure a aprtir de l'objet date */
		DateFormat dateFormat = new SimpleDateFormat("h:mm:ss a");
		
        System.out.println("Analogique : "+dateFormat.format(date));
    
	}

	/**
	 * Methode update
	 */
	
	public void update(Observable observable, Object obj) {

            draw((Date)obj);
	}
}
