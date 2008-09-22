
public class ClockMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClockTimer clockTimer = new ClockTimer();
		
		DigitalClock digitalClock = new DigitalClock();
		AnalogClock analogClock = new AnalogClock();
		
		clockTimer.addObserver(digitalClock);
		clockTimer.addObserver(analogClock);
		
		clockTimer.start();
		
	}

}
