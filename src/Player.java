import java.util.*;

public class Player extends CardCollection{
	private final String name;
	private final boolean isRobot;
	
	public Player(String name,boolean isRobot) {
		super();
		
		this.name = name;
		this.isRobot = isRobot;
	}
	
	/**
	 * The method returns the name of the player.
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The method helps to search does identical card exist in player's cards. Different declared Card object is allowed.
	 * @return boolean
	 */
	public boolean contains(Card card) {		
		return (super.search(card.getValue()).length > 0 && super.search(card.getSuit()).length > 0) ? true : false;
	}
	
	/**
	 * The method automatically gives possible cards. Only for robot.
	 * @param playerList The Players list
	 * @param currentTop Current Top Card in Game
	 * @return Card array, allowed empty
	 */
	public Card[] auto(TreeMap<Integer, Player> playerList, Card currentTop) {
		Card[] result = new Card[super.getSize()];
		List<Card> nResult = new ArrayList<Card>();
		int resultSize = 0;
		
		if (isRobot) {
			if (currentTop.getValue() != 1) {
				Card[] vCard = super.search(currentTop.getValue());
				
				if (vCard.length > 0) {
					result[resultSize] = vCard[0];
					resultSize++;
				}
			}
			
			if (resultSize == 0) {
				Card[] sCard = super.search("*" + currentTop.getSuit().charAt(1));
				
				if (sCard.length > 0) {
					result[resultSize] = sCard[0];
					resultSize++;
				}
			}
			
			// find Q
			for (Card fc : result) {
				if (fc!= null) {
					if (fc.getValue() != 1) {
						nResult.add(fc);
					}
					
					if (fc.getValue() == 12) {
						for (Card rc : super.search(12)) {
							if (rc.getSuit().charAt(1) != fc.getSuit().charAt(1)) nResult.add(rc);
						}
					}
					
					break;
				}
			}
			
			//find A
			Card[] aCard = super.search(1);
			if (aCard.length > 0) nResult.add(aCard[0]);
		}  
		
		return nResult.toArray(new Card[0]);
	}
	
	/**
	 * The method interacts with human player, requiring human to input.
	 * @param playerList The Players list
	 * @param currentTop Current top card in game
	 * @return Card array, allowed empty
	 */
	public Card[] run(TreeMap<Integer, Player> playerList, Card currentTop) {
		ArrayList<Card> result = new ArrayList<Card>();
		
		if (isRobot) {
			Collections.addAll(result, this.auto(playerList, currentTop));
		} else {
			//User mode: Input card
			System.out.println("===================");
			System.out.println("[" + name + "] I have " + super.toString() + ".");
			
			int nullCount = 0;
			
			while (result.size() == 0) {
				if (nullCount == 3) break;
				
				String input = Utils.input("> ");

				if (input.length() == 0) nullCount++;
				
				while (input.indexOf(",") != -1 || input.length() != 0) {
					
					
					String frag = input.substring(0, (input.indexOf(",") == -1) ? input.length():input.indexOf(","));
					input = (input.indexOf(",") != -1) ? input.substring(input.indexOf(",") + 1):"";
					
					String encodedCode = Card.convertToCode(frag);
					Card possibleCard = new Card(encodedCode.substring(0, 2), Integer.parseInt(encodedCode.substring(2)));
					
					if (this.contains(possibleCard)) {
						result.add(possibleCard);
					}
				}
			}
		}
		
		return result.toArray(new Card[0]);
	}
	
	/**
	 * List of the hand card
	 */
	public String toString() {
		return super.toString();
	}
}
