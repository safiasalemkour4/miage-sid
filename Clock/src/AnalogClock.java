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
	
	public void draw(ClockTimer clockTimer) {
	    
        int hour = clockTimer.getHour();
        int minute = clockTimer.getMinute();
        int second = clockTimer.getSecond();
        
        /* On cree une date avec les donnes de notre timer */
        Date date = new Date();
        date.setTime(hour*60*60*1000+minute*60*1000+second*1000); // (parametre : millisecond)
        
		/* On cree le format pour l'affichage de l'heure a aprtir de l'objet date */
		DateFormat dateFormat = new SimpleDateFormat("h:mm:ss a");
		
        System.out.println("Analogique : "+dateFormat.format(date));
    
	}

	/**
	 * Methode update
	 */
	
	public void update(Observable observable, Object arg1) {

            draw((ClockTimer)observable);
	}
}
