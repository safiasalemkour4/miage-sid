package oldVersion;

import java.util.ArrayList;


public class MyObservable {
	
	private ArrayList<MyObserver> listObserver = new ArrayList<MyObserver>();
	
	public void attach(MyObserver obs) {
		
		listObserver.add(obs);
	}
	
	public void detach(MyObserver obs) {
		
		listObserver.remove(obs);
	}
	
	public void notifyObservers() {
		
		for (MyObserver obs : listObserver) {
		
			obs.update(this);
		}
	}
	
	public void setChanged() {
		
	}
}
