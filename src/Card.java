import java.util.*;
import java.lang.*;

public class Card {
	private final String suit;
	private final int value;
	
	private final static HashMap<String, String> suitStrLib = new HashMap<String, String>(){{
        put("RS", " ♤");
        put("RH", "♡");
        put("RD", "♢");
        put("RC", "♧");
        put("BS", "♠");
        put("BH", "♥");
        put("BD", "♦");
        put("BC", "♣");
    }};
    
    private final static HashMap<Integer, String> valueStrLib = new HashMap<Integer, String>(){{
        put(1, "A");
        put(2, "2");
        put(3, "3");
        put(4, "4");
        put(5, "5");
        put(6, "6");
        put(7, "7");
        put(8, "8");
        put(9, "9");
        put(10, "10");
        put(11, "J");
        put(12, "Q");
        put(13, "K");
    }};
	
	/**
	 * The constructor helps to create the Card object.
	 * @param suit The description of suit.
	 * @param value The value of the card. 
	 * @precondition suit can only be the combination of B or R with type which are S, H, D, C.
	 * @precondition value The value should between 1 to 13.
	 */
	public Card(String suit, int value) {
		if (suit.charAt(0) != 'R' && suit.charAt(0) != 'B') throw new IllegalArgumentException("Card's color must be either black(\"B\") or Red(\"R\").");
		
		if (!Arrays.asList(new String[] {"S", "H", "D", "C"}).contains("" + suit.charAt(1))) throw new IllegalArgumentException("Card's types are only spade(S), Heart(H), Diamond(D), or Club(C).");
		
		if (value < 1 || value > 13) throw new IllegalArgumentException("Card's value must be betweeb 1 to 13.");
		
		this.suit = suit.substring(0, 2);
		this.value = value;
	}
	
	/**
	 * The getSuit method returns the suit of the card in a decoded way.
	 * @return String The decoded suit value
	 * @postcondition String The combination of B or R with type which are S, H, D, C.
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * The getValue method returns the value of the card in a decoded way.
	 * @return int The decoded card value.
	 * @postcondition int An integer between 1 to 13.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * The suitEqualTo method helps to compare the suit types between two cards.
	 * @param other The another Card object
	 * @return boolean equal(true) or not equal(false)
	 * @precondition Card A completed Card object
	 * @postcondition boolean
	 */
	public boolean suitEqualTo(Card other) {
		if (other == null) return false;
		
		return suit.charAt(1) == other.getSuit().charAt(1);
	}
	
	/**
	 * The valueEqualTo method helps to compare the values between two cards.
	 * @param other The another Card object
	 * @return boolean equal(true) or not equal(false)
	 * @precondition Card A completed Card object
	 * @postcondition boolean
	 */
	public boolean valueEqualTo(Card other) {
		if (other == null) return false;
		
		return value == other.getValue();
	}
	
	/**
	 * The colorEqualTo method helps to compare the color between two cards.
	 * @param other The another Card object
	 * @return boolean equal(true) or not equal(false)
	 * @precondition Card A completed Card object
	 * @postcondition boolean
	 */
	public boolean colorEqualTo(Card other) {
		if (other == null) return false;
		
		return suit.charAt(0) == other.getSuit().charAt(0);
	}
	
	/**
	 * The toString method textualizes the card suit and value in icon or real poker card representation.
	 * @postcondition String
	 */
	public String toString() {
		return Card.textualization(suit, value);
	}
	
	/**
	 * The private method, textualization, helps to convert decoded card value into a real poker card representation.
	 * @param suit The suit of the card
	 * @param value The value of the card
	 * @return String The converted poker card representation
	 * @postcondition String
	 * @precondition A valid card suit and value
	 */
	public static String textualization(String suit, int value) {
		
		return Card.suitStrLib.get(suit) + Card.valueStrLib.get(value);
	}
	
	/**
	 * The method helps to convert icon text to card code.
	 * @param icon The icon text
	 * @return corresponding card code
	 */
	public static String convertToCode(String icon) {
		return Utils.getKeyByValue(Card.suitStrLib, icon.substring(0, 1)) + Utils.getKeyByValue(Card.valueStrLib, icon.substring(1));
	}
}
