
/**
 * Class Main
 * @author Arnaud Knobloch
 */

public class ClockMain {

	/**
	 * @param args les arguments du programmes (inutile ici)
	 */
	
	public static void main(String[] args) {

		/* Instance de l'Observable */ 
		ClockTimer clockTimer = new ClockTimer();
		
		/* Instances des Observers */
		DigitalClock digitalClock = new DigitalClock();
		AnalogClock analogClock = new AnalogClock();
		
		/* Ajout des Observers a l'Observable */
		clockTimer.addObserver(digitalClock);
		clockTimer.addObserver(analogClock);
		
		/* On lance le Timer */
		clockTimer.start();
		
	}

}
