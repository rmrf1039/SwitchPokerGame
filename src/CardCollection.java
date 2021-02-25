import java.util.*;

public class CardCollection {
	private ArrayList<Card> collection;
	
	/**
	 * The constructor initializes internal variable.
	 */
	public CardCollection() {
		collection = new ArrayList<Card>();
	}
	
	/**
	 * The getSize method returns the size of the card collection.
	 * @return int the size of the card collection
	 * @postcondition int An integer between 0 to 52
	 */
	public int getSize() {
		return collection.size();
	}
	
	/**
	 * The addCard methods helps to add a Card object into the card collection.
	 * @param card A completed Card object
	 * @precondition Card A completed Card object
	 */
	public void addCard(Card card) {
		collection.add(card);
	}
	
	/**
	 * The addCard methods helps to add a Card object into the card collection.
	 * @param card A Card array
	 * @precondition Card A Card array, empty allowed
	 */
	public void addCard(Card[] card) {
		Collections.addAll(collection, card);
	}
	
	/**
	 * The addCard methods helps to add a Card object into the card collection in a required index.
	 * @param card A Card array
	 * @param index The index to insert the card in.
	 * @precondition 0 <= index
	 */
	public void addCard(Card card, int index) {
		collection.add(index, card);
	}
	
	/**
	 * The removeCard method removes card from the card collection by index.
	 * @param index The index of the array that wants to remove
	 * @return Card A completed Card object that just removed from the collection
	 * @precondition int An integer between 0 to the the current card size - 1, ex: 0~51(full)
	 * @postcondition Card
	 */
	public Card removeCard(int index) {
		if (index < 0 && index > this.getSize() - 1) throw new IllegalArgumentException("The index is out of bound to the cardCollection array.");
		
		Card removeCard = collection.get(index);
		
		collection.remove(index);
		
		return removeCard;
	}
	
	/**
	 * The removeCard method removes card from the card collection by Card object.
	 * @param card The identical Card object that wants to remove
	 */
	public Card removeCard(Card card) {
		Iterator<Card> itr = collection.iterator();  
		
		while(itr.hasNext()){
		    Card c = itr.next();
		    
		    if (c.getSuit().equals(card.getSuit()) && c.getValue() == card.getValue()) itr.remove();
		}
		
		return card;
	}
	
	/**
	 * The clearButFirst method remove all the card but the first card
	 */
	public void clearButFirst() {
		collection.subList(1, collection.size()).clear();
	}
	
	/**
	 * The method helps to reveal the top card from the collection.
	 * @return A signed Card object
	 */
	public Card getTopCard() {
		return collection.get(0);
	}
	
	/**
	 * The method gives card from current collection and remove them after giving.
	 * @param num The amount of given cards
	 * @return Card array, allowed empty
	 */
	public Card[] getCard(int num) {
		Card[] result = new Card[num];
		
		for (int i = 0; i < num; i++) {
			if (this.getSize() == 0) break;
			
			result[i] = this.removeCard(0);
		}
		
		return result;
	}
	
	/**
	 * The method get all the card from the collection except of the top one.
	 * @return Card array, allowed empty
	 */
	public Card[] getAllCardButFirst() {
		return collection.subList(1, collection.size()).toArray(new Card[0]);
	}
	
	/**
	 * The method searches the existence of card.
	 * @param card The card wants to search
	 * @return true if the card exists; false if not exist
	 * @precondition The Card object must from the card, identical but declared differently is no acceptable
	 */
	public boolean contains(Card card) {
		return collection.contains(card);
	}
	
	/**
	 * The method searches all the exist card in the collection with the same suit.
	 * @param suit The suit wants to search
	 * @return Card array, allowed empty
	 */
	public Card[] search(String suit) {
		ArrayList<Card> result = new ArrayList<Card>(collection);
		String color = suit.substring(0, 1);
		String type = suit.substring(1, 2);
		
		if (this.getSize() > 0) {
			if (!color.equals("*")) {
				Iterator<Card> itr = result.iterator();  
				
				while(itr.hasNext()){
				    Card c = itr.next();
				    
				    if (!color.equals("" + c.getSuit().charAt(0))) itr.remove();
				}
			}
			
			if (!type.equals("*")) {
				Iterator<Card> itr = result.iterator();  
				
				while(itr.hasNext()){
				    Card c = itr.next();
				    
				    if (c == null) break;
				    
				    if (!type.equals("" + c.getSuit().charAt(1))) itr.remove();
				}
			}
		}
		
		return result.toArray(new Card[0]);
	}
	
	/**
	 * The method searches all the exist card in the collection with the same values.
	 * @param value The value of card wants to search
	 * @return Card array, allowed empty
	 */
	public Card[] search(int value) {
		ArrayList<Card> result = new ArrayList<Card>();
		
		for (Card c : collection) {
			if (c == null) break;
			
			if (c.getValue() == value) result.add(c);
		}
		
		return result.toArray(new Card[0]);
	}
	
	/**
	 * The toString method prints all the card with icons in the card collection to a string.
	 * @postcondition String
	 */
	public String toString() {
		return collection.toString();
	}
}
