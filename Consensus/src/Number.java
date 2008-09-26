
public class Number {
	
	private int number;
	
	private boolean isSended;
	
	public Number (int number) {
		
		this.number = number;
		this.isSended = false;
	}
	
	public void setSended() {
		
		this.isSended = true;
	}
	
	
	public boolean isSended() {
		
		return this.isSended;
	}

	public int getNumber() {
		return number;
	}
	
	
}
