import java.util.*;
import java.text.*;

class Testing{

	private Piece piece1 = new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT));

	private Piece piece2 = new Piece(
			new Suits(Suits.CLUBS, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.OUT));

	private Piece piece3 = new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.SPADES, Suits.OUT));

	private Piece piece4 = new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT));

	private Piece piece5 = new Piece(
			new Suits(Suits.CLUBS, Suits.OUT),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.OUT));

	private Piece piece6 = new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.SPADES, Suits.OUT));

	private Piece piece7 = new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT));

	private Piece piece8 = new Piece(
			new Suits(Suits.DIAMONDS, Suits.OUT),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.OUT));

	private Piece piece9 = new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT));

	public static void main(String[] aaarg){
//		List<Piece> pieces = new ArrayList<Piece>();
/*
jay@betterish:~/programming/java/SuitsPuzzle$ gcj -C Testing.java
Testing.java:60: error: ':' expected.
                List<Piece> pieces = new ArrayList<Piece>();
                       ^
1 error
*/
		NumberFormat formatter = new DecimalFormat("#,###,###");
    	System.out.println(formatter.format(-1234567890.345));


		List pieces = new ArrayList();

		Testing testing = new Testing();
		
		pieces.add(testing.piece1);
		pieces.add(testing.piece2);
		pieces.add(testing.piece3);

		((Piece)pieces.get(0)).setDirection();


		pieces.clear();
		pieces.add(new Piece[]{testing.piece1, testing.piece2});
		Piece pees[] = ((Piece[])pieces.get(0));
		Piece pee = pees[0];
		
		Piece pieces2[] = new Piece[Integer.MAX_VALUE];
		System.out.println(pieces2.length);
		pieces2 = new Piece[Long.MAX_VALUE];
	}
} 
