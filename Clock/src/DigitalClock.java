import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Class DigitalClock
 * @author Arnaud Knobloch
 */

public class DigitalClock implements Observer {

	/**
	 * Constructeur
	 */
	public DigitalClock() {
		
	}
	
	/**
	 * Methode draw
	 * @param clockTimer
	 */
	
	public void draw(Date date) {
        
		/* On cree le format pour l'affichage de l'heure a aprtir de l'objet date */
		DateFormat dateFormat = new SimpleDateFormat("HH'h':mm'm':ss's'");
		
        System.out.println("Digital : "+dateFormat.format(date)+"\n");
    
	}

	/**
	 * Methode update
	 */
	
	public void update(Observable observable, Object obj) {

        draw((Date)obj);
}
}
