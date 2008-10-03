/**
 * Number class
 * Represent the object (message) that processes will exchange in the system
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Number {
	
	/** The value of the number */
	private int value;
	
	/** Boolean represent the stat of the number (sended or not) */
	private boolean isSended;
	
	/**
	 * Constructor of a Number
	 * @param value the value of the number
	 */
	
	public Number (int value) {
		
		this.value = value;
		this.isSended = false;
	}
	
	/**
	 * Methode setSended
	 * Switch the stat of the number to "sended"
	 */
	
	public void setSended() {
		
		this.isSended = true;
	}
	
	/**
	 * Getter Send
	 * @return true if the number was sent
	 */
	
	public boolean isSended() {
		
		return this.isSended;
	}

	/**
	 * Getter Value
	 * @return the value of the number
	 */
	
	public int getValue() {
		
		return this.value;
	}
}
