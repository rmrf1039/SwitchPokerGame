import java.util.*;

public class Game {
	private TreeMap<Integer, Player> players;
	private Pack pack;
	private CardCollection trash;
	
	public Game(int playerNum) {
		this.players = new TreeMap<Integer, Player>();
		this.pack = new Pack();
		this.trash = new CardCollection();
		
		System.out.println("========== Welcome to the APCS Switch Poker Game ==========\n");
		
		String playerName = "";
		
		do {
			playerName = Utils.input("Please enter your username: ");
		} while (playerName.length() == 0);
		
		for (int i = 0; i < playerNum; i++) {
			this.players.put(i, new Player((i == 0) ? playerName:"Robot " + i, (i == 0) ? false : true));
		}
	}
	
	/**
	 * The method helps to give all player 7 cards first and automatically transfer club 3 from player to trash.
	 */
	public void initialize() {	
		for (Player p : players.values()) {
			p.addCard(pack.getCard(7));
		}
		
		for (Player p : players.values()) {
			Card BC3 = new Card("BC", 3);
			
			if (p.contains(BC3)) this.playerShowsCard(new Card[] {p.removeCard(BC3)});
		}

		System.out.println("\n========== Dealing completed. The Game Stars! ==========");
		System.out.println(this.getGameStatus());
	}
	
	/**
	 * The method add Card(s) to trash
	 * @param cards Card array
	 */
	public void playerShowsCard(Card[] cards) {
		trash.addCard(cards);
	}
	
	/**
	 * The method ensures the pack has enough cards to give.
	 * @param num The amount of cards requires to return
	 * @return Card array
	 */
	public Card[] giveCard(int num) {
		//if the pack only has one card, move card from trash to pack and shuffle
	    if (pack.getSize() < num) {
	    	pack.addCard(trash.getAllCardButFirst());
	    	pack.shuffle();
	    	trash.clearButFirst();
	    }
	    
	    return pack.getCard(num);
	}
	
	/**
	 * The method announces the current top card.
	 * @return String
	 */
	public String getGameStatus() {
		return "[INFO] The top card is " + trash.getTopCard() + " now.";
	}
	
	/**
	 * The method reverses current Players list in order.
	 * @param currentPlayer The player as the reference point
	 */
	private void reversePlayerList(Player currentPlayer) {
		TreeMap<Integer, Player> tempPlayer = new TreeMap<Integer, Player>();
		int currentIndex = 0;
		
		for (Map.Entry<Integer, Player> entry : players.entrySet()) {
            if (!entry.getValue().equals(currentPlayer)) {
            	currentIndex++;
            } else {
            	break;
            }
        }
		
		int newPos = 0;
		
		for (int i = currentIndex - 1; i >= 0; i--) {
			Integer key = players.keySet().toArray(new Integer[players.size()])[i];
			Player value = players.get(key);
			
			tempPlayer.put(newPos, value);
			newPos++;
		}
		
		
		for (int i = players.size() - 1; i >= currentIndex; i--) {
			Integer key = players.keySet().toArray(new Integer[players.size()])[i];
			Player value = players.get(key);
			
			tempPlayer.put(newPos, value);
			newPos++;
		}
		
		players = tempPlayer;
	}
	
	/**
	 * Game core
	 */
	public void run() {
		int cardAdding = 0;
		boolean skip = false;
		boolean reverseOnce = false;
		
		while (true) {
			for (Map.Entry<Integer, Player> p : players.entrySet()) {
				if (reverseOnce) {
					reverseOnce = false;
					break;
				}
				
			    Player player = p.getValue();
			    
			    if (cardAdding != 0) {
			    	System.out.println("[INFO] " + player.getName() + " receives " + (2 * cardAdding) + " cards from pervious player.");
			    	player.addCard(this.giveCard(2 * cardAdding));
			    	cardAdding = 0;
			    }
			    
			    if (!skip) {
			    	Card[] throwedCards = player.run(players, trash.getTopCard());
			    	
			    	if (throwedCards.length == 0) {
			    		System.out.println("[INFO] " + player.getName() + " has no card.");
				    	player.addCard(this.giveCard(1));
				    	
				    	continue;
				    }
			    	
			    	//Check game rule
		    		int invalids = 0;
		    		int nAllow = throwedCards.length;
		    		boolean AOnce = false;
		    		boolean firstAppear = false;
		    		Card QAppear = null;
		    		
			    	for (Card c : throwedCards) {
			    		if (!AOnce && c.getValue() == 1) {
			    			AOnce = true;
			    			continue;
			    		}
			    		
			    		if (QAppear != null) {		
			    			if (QAppear.getValue() != 1 && QAppear.getValue() != 12) {
			    				invalids++;
			    				nAllow -= 2;
			    			}
			    			
			    			QAppear = null;
			    			continue;
			    		}
			    		
			    		if (c.getValue() == 12 && c.suitEqualTo(trash.getTopCard())) {
			    			QAppear = c;
			    			continue;
			    		}
			    		
			    		if (!firstAppear && (c.valueEqualTo(trash.getTopCard()) || c.suitEqualTo(trash.getTopCard()))) {
			    			continue;
			    		}
			    		
			    		firstAppear = true;
			    		nAllow--;
			    	}
			    	
			    	//Punishment
			    	invalids += throwedCards.length - nAllow;
			    	
			    	if (invalids > 0) {
				    	System.out.println("[God] You throw invalid card(s). Your turn is not valid. For punishment, you receive " + invalids + " card.");
				    	player.addCard(this.giveCard(invalids));
				    	
				    	continue;
			    	}
			    	
			    	//Apply card functions
			    	System.out.println("[" + player.getName() + "] Showing " + Arrays.asList(throwedCards).toString());
			    	
			    	for (Card c : throwedCards) {
				    	trash.addCard(c, 0);
				    	player.removeCard(c);
				    	
				    	switch (c.getValue()) {
					    	case 2:
					    		cardAdding += 1;
					    		break;
					    		
					    	case 11: 
					    		this.reversePlayerList(player);
					    		System.out.println("[INFO] The direction has been reversed.");
					    		reverseOnce = true;
					    		break;
					    	
					    	case 13:
					    		skip = true;
					    		break;
				    	}
				    	
				    }
			    	
			    	if (player.getSize() == 1) {
			    		System.out.println("[" + player.getName() + "] UNO!");
			    	} else if (player.getSize() == 0) {
			    		System.out.println("[System] Congratulations! Player, " + player.getName() + ", won the game.");
			    		System.exit(0);
			    	}
			    	
			    	System.out.println(this.getGameStatus());
			    } else {
			    	System.out.println("[INFO] " + player.getName() + " has been skipped. Moving on!");
			    	skip = false;
			    	
			    	continue;
			    }
			}
		}
	}
}
