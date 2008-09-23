package oldVersion;

/**
 * Class Main
 * @author Arnaud Knobloch
 */

public class OldClockMain extends MyObservable {

	/**
	 * @param args les arguments du programmes (inutile ici)
	 */
	
	public static void main(String[] args) {

		/* Instance de l'Observable */ 
		OldClockTimer clockTimer = new OldClockTimer();
		
		/* Instances des Observers */
		OldDigitalClock digitalClock = new OldDigitalClock();
		OldAnalogClock analogClock = new OldAnalogClock();
		
		/* Ajout des Observers a l'Observable */
		clockTimer.attach(digitalClock);
		clockTimer.attach(analogClock);
		
		/* On lance le Timer */
		clockTimer.start();
		
	}

}
