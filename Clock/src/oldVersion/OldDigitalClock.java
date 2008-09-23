package oldVersion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class DigitalClock
 * @author Arnaud Knobloch
 */

public class OldDigitalClock implements MyObserver {

	/**
	 * Constructeur
	 */
	public OldDigitalClock() {
		
	}
	
	/**
	 * Methode draw
	 * @param clockTimer
	 */
	
	public void draw(OldClockTimer clockTimer) {
	    
        int hour = clockTimer.getHour();
        int minute = clockTimer.getMinute();
        int second = clockTimer.getSecond();
        
        /* On cree une date avec les donnes de notre timer */
        Date date = new Date();
        date.setTime(hour*60*60*1000+minute*60*1000+second*1000-1000*60*60);
        
		/* On cree le format pour l'affichage de l'heure a aprtir de l'objet date */
		DateFormat dateFormat = new SimpleDateFormat("HH'h':mm'm':ss's'");
		
        System.out.println("Digital : "+dateFormat.format(date)+"\n");
    
	}

	/**
	 * Methode update
	 */
	
	public void update(MyObservable observable) {

		draw((OldClockTimer)observable);
	}
}
