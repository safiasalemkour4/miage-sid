package oldVersion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class MyObservable {
	
	private ArrayList<MyObserver> listObserver = new ArrayList<MyObserver>();
	
	public void attach(MyObserver obs) {
		
		listObserver.add(obs);
	}
	
	public void detach(MyObserver obs) {
		
		listObserver.remove(obs);
	}
	
	public void notifyObservers() {
		
		Iterator<MyObserver> listIt = listObserver.iterator();
		
		/* Avec Iterator --> NULL */
		while (listIt.hasNext()) {
			listIt.next().update(this);
		}
		
		/*
		for (MyObserver obs : listObserver) {
		
			obs.update(this);
		}*/
	}
	
	public void setChanged() {
		
	}
}
