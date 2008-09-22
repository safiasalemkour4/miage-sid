import java.util.Calendar;
import java.util.Observable;


public class ClockTimer extends Observable {

	private int hour, minute, second;

	public ClockTimer() {

		/* On cree l'objet Date */

		Calendar calendar = Calendar.getInstance();
		 
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		this.minute = calendar.get(Calendar.MINUTE);
		
		this.second = calendar.get(Calendar.SECOND);

	}

	public void start() {

		new Timer(this);

	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public class Timer extends Thread {

		private ClockTimer clockTimer;

		public Timer(ClockTimer clockTimer) {

			this.clockTimer = clockTimer;
			this.start();
		}

		/**
		 * Methode Run
		 */

		public void run() {

			while (true) {

				try {

					setChanged();

					Thread.sleep(1000);

					this.clockTimer.notifyObservers();

					if (this.clockTimer.getSecond()<59) {

						this.clockTimer.setSecond(this.clockTimer.getSecond()+1);

					} else {

						this.clockTimer.setSecond(0);

						if (this.clockTimer.getMinute()<59) {

							this.clockTimer.setMinute(this.clockTimer.getMinute()+1);

						} else {

							this.clockTimer.setMinute(0);

							if (this.clockTimer.getHour()<23) {

								this.clockTimer.setHour(this.clockTimer.getHour()+1);

							} else {

								this.clockTimer.setHour(0);
							}
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
