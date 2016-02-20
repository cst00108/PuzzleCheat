import java.text.*;

public class Runner{
	private static Piece[] pieces = new Piece[]{
			new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT)),
			new Piece(
			new Suits(Suits.CLUBS, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.OUT)),
			new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.SPADES, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.SPADES, Suits.OUT)),
			new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT)),
			new Piece(
			new Suits(Suits.CLUBS, Suits.OUT),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.HEARTS, Suits.OUT)),
			new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.SPADES, Suits.OUT)),
			new Piece(
			new Suits(Suits.SPADES, Suits.OUT),
			new Suits(Suits.HEARTS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT)),
			new Piece(
			new Suits(Suits.DIAMONDS, Suits.OUT),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.OUT)),
			new Piece(
			new Suits(Suits.HEARTS, Suits.OUT),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.CLUBS, Suits.IN),
			new Suits(Suits.DIAMONDS, Suits.OUT))};

	public static void main(String[] aaarg){
		Puzzle puzzle = new Puzzle(3, 3);
	
		for(int index=0; index<pieces.length; index++){
			pieces[index].setDirection();
			puzzle.addPiece(pieces[index]);
		}

		Piece[] correct = puzzle.getCorrect();

		NumberFormat formatter = new DecimalFormat("#,###,###");

		System.out.println(puzzle.printPiecesDirections(correct) + "Only took " +
				formatter.format(puzzle.getTrials()) + " combinations to check.");
	}
}
