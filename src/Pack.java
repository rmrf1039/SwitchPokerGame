import java.util.*;

public class Pack extends CardCollection{
	/**
	 * The constructor initializes internal variable.
	 */
	public Pack() {
		super();
		
		String[] suits = {"BS", "RH", "RD", "BC"};
		
		for (String s : suits) {
			for (int v = 1; v <= 13; v++) {
				super.addCard(new Card(s, v));
			}
		}
		
		this.shuffle();
		
		//Move Black Club to the top.
		super.removeCard(new Card("BC", 3));
		super.addCard(new Card("BC", 3), 0);
	}
	
	/**
	 * The shuffle method randomizes the order of cards.
	 */
	public void shuffle() {
		Random rand = new Random(); 
        
        for (int i = 0; i < this.getSize() * 5; i++) {        	
        	super.addCard(super.removeCard(rand.nextInt(this.getSize())));
		}
	}
	
	/**
	 * The toString method prints all the card with icons in the pack to a string.
	 * @postcondition String
	 */
	public String toString() {
		return super.toString();
	}
}