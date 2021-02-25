
public class Test {
	public static void testCard() {
		Card BS1 = new Card("BS", 1);
		Card BS2 = new Card("RS", 2);
		
		System.out.println(BS1.suitEqualTo(BS2)); //true
		System.out.println(BS1.colorEqualTo(BS2)); //false
		System.out.println(BS1.valueEqualTo(BS2)); //false
	}
	
	public static void testCollection() {
		Card BS1 = new Card("BS", 1);
		Card BS2 = new Card("BS", 2);
		Card RS3 = new Card("RS", 3);
		Card RD4 = new Card("RD", 4);
		
		CardCollection cardCollection = new CardCollection();
		
		cardCollection.addCard(BS1);
		cardCollection.addCard(BS2);
		cardCollection.addCard(RS3);
		cardCollection.addCard(RD4);
		
		cardCollection.removeCard(1); //remove by index
		cardCollection.removeCard(BS1); //remove by Card object identity
		
		cardCollection.contains(BS2);
		
		System.out.println(cardCollection); //list all card in the collection
		System.out.println(cardCollection.getSize()); //get collection's size
	}
	
	public static void testPack() {
		Pack pack = new Pack();
		
		pack.shuffle();
		
		System.out.println(pack);
		System.out.println(pack.removeCard(0));
		System.out.println(pack);
		System.out.println(pack.getSize());
	}
	
	public static void testPlayer() {
		Player p1 = new Player("Edwin", true);
		Card BS1 = new Card("BS", 12);
		Card BS2 = new Card("BS", 4);
		Card RS3 = new Card("RH", 3);
		Card RD4 = new Card("RD", 3);
		
		p1.addCard(new Card[] {BS1, BS2, RS3, RD4});
		System.out.println(p1);
	}
	
	public static void testGame() {
		Game game = new Game(4);
		game.initialize();
		game.run();
	}
}
