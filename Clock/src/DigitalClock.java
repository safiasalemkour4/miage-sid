import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


public class DigitalClock implements Observer {

	public DigitalClock() {
		
	}
	
	public void draw(ClockTimer clockTimer) {
	    
        int hour = clockTimer.getHour();
        int minute = clockTimer.getMinute();
        int second = clockTimer.getSecond();
        
        Date date = new Date();
        
        date.setTime(hour*60*60*1000+minute*60*1000+second*1000);
       /// date.setHours(hour);
        //date.setMinutes(minute);
        //date.setSeconds(second);
        
		/* On cree le format pour l'affichage de l'heure a aprtir de l'objet date */
		DateFormat dateFormat = new SimpleDateFormat("HH'h':mm'm':ss's'");
		
        System.out.println("Digital : "+dateFormat.format(date)+"\n");
    
	}

	public void update(Observable observable, Object arg1) {

            draw((ClockTimer)observable);
	}

}
