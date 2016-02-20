public class Puzzle{
	private Piece[] pieces = null;
	private int pieceIndex = 0;
	private Piece[][] piecesMatrix = null;  //think I'll scrap
	private Piece[][] combinations = null;
	private int height = 0;
	private int width = 0;
	private long trials = 0;

	public Puzzle(int width, int height){
		this.height = height;
		this.width = width;

		int totalPieces = height * width;
		
		this.pieces = new Piece[totalPieces];
		this.piecesMatrix = new Piece[height][width];
		
		this.combinations =
				new Piece[Puzzle.exclamationMark(totalPieces)][totalPieces];
	}


	public void rotatePieces(){
		this.rotatePieces(pieces.length - 1);
	}

	
	private void rotatePieces(int piecesIndex){
		if(pieces[piecesIndex].rotate() == 0){
			rotatePieces(piecesIndex-1);
		}
	}


	public String printCombination(int combinationsIndex){
		StringBuffer toReturn = new StringBuffer();

		for(int piecesIndex = 0;
				piecesIndex < pieces.length;
				piecesIndex++){

			toReturn.append(this.combinations[combinationsIndex][piecesIndex].getNumber() + " ");
		}

		return toReturn.toString();
	}


	public String printCombinations(){
		StringBuffer toReturn = new StringBuffer();

		for(int combinationsIndex = 0;
				combinationsIndex < this.combinations.length;
				combinationsIndex++){

			toReturn.append(printCombination(combinationsIndex) + "\n");
		}

		return toReturn.toString();
	}
	

	//get every order in which to try pieces
	private void getCombinations(
			Piece[] piecesConceredWith,
			int rangeStart, int rangeEnd){

		int rangeTotal = rangeEnd - rangeStart;
		int dividedRange = rangeTotal / piecesConceredWith.length;
		
		for(int concernedWithIndex=0;
				concernedWithIndex<piecesConceredWith.length;
				concernedWithIndex++){

			for(int fill=0; fill<dividedRange; fill++){
				int combinationsIndex = 
						concernedWithIndex * dividedRange + rangeStart + fill;
				int recursiveIndex = pieces.length - piecesConceredWith.length;
				
				this.combinations[combinationsIndex][recursiveIndex] =
						piecesConceredWith[concernedWithIndex];
			}

			if(piecesConceredWith.length > 1){
				Piece[] newPiecesConcernedWith = Puzzle.throwAway(
						piecesConceredWith[concernedWithIndex], piecesConceredWith);
				int newRangeStart = concernedWithIndex * dividedRange + rangeStart;

				getCombinations(newPiecesConcernedWith,
						newRangeStart, newRangeStart + dividedRange);
			}
		}
	}


	private static Piece[] throwAway(Piece thisOne, Piece[] fromHere){
		Piece[] toReturn = new Piece[fromHere.length-1];
		int toReturnIndex = 0;

		for(int fromHereIndex=0; fromHereIndex<fromHere.length; fromHereIndex++){
			if(fromHere[fromHereIndex] != thisOne){
				toReturn[toReturnIndex++] = fromHere[fromHereIndex];
			}
		}

		return toReturn;
	}
	
	private static int exclamationMark(int value){
		if(value == 2){
			return 2;
		}
		
		return value * exclamationMark(value - 1);
	}

	
	public void addPiece(Piece piece){
		this.pieces[pieceIndex++] = piece;

		//if there all there, get all the possible 
		if(this.pieces[this.pieces.length-1] != null){
//			System.out.println("getting combinations");
			this.getCombinations(pieces, 0, combinations.length);
		}
	}


	public void addPieceToMatrix(Piece piece){
		for(int heightIndex=0; heightIndex<this.height; heightIndex++){
			for(int widthIndex=0; widthIndex<this.width; widthIndex++){
				
				if(piecesMatrix[heightIndex][widthIndex] != null){
					continue;
				}
				
				piecesMatrix[heightIndex][widthIndex] = piece;
				return;
			}
		}
	}


	private void setPiecesToMatrix(){
		int arrayIndex = 0;

		for(int heightIndex=0; heightIndex<this.height; heightIndex++){
			for(int widthIndex=0; widthIndex<this.width; widthIndex++){
				piecesMatrix[heightIndex][widthIndex] = pieces[arrayIndex++];
			}
		}
	}


	public static String printPiecesDirections(Piece[] pieces){
		StringBuffer toReturn = new StringBuffer();

		for(int index=0; index<pieces.length; index++){
			toReturn.append("Piece Number:  " + pieces[index].getNumber() + "\n");
			toReturn.append("Piece Side:    " + pieces[index].getSide() + "\n\n");
		}

		return toReturn.toString();
	}
	

	public long getTrials(){
		return trials;
	}


	public Piece[] getCorrect(){
		long status = -1;
		
		for(int timesThroughtCombinations = 0;
				true;
				timesThroughtCombinations++){
			
			for(int combinationsIndex = 0;
					combinationsIndex < this.combinations.length;
					combinationsIndex++){
				
				this.trials++;
				Piece[] pieces = this.combinations[combinationsIndex];

				if(this.isCorrect(pieces)){
					return pieces;
				}
			}

			//only display some of the time
			if(timesThroughtCombinations/10 > status){
				//shorter instruction time
				status++;  //status = timesThroughtCombinations/10000000;
				
				System.out.println((this.combinations.length*(timesThroughtCombinations+1)));
			}

			this.rotatePieces();
		}

//		return null;
	}


	//this is cool, but had to draw out
	private boolean isCorrect(Piece[] pieces){
		for(int index=0; index<pieces.length; index++){
			if((index+1) % this.width != 0){
				if(!pieces[index].fits(pieces[index+1], true)){
					return false;
				}
			}
		}

		for(int index=0; index<pieces.length-this.width; index++){
			if(!pieces[index].fits(pieces[this.width+index], false)){
				return false;
			}
		}

		return true;
	}

	
	public boolean isCorrectMatrix(){
		this.setPiecesToMatrix();

		//check if pieces match sideways
		for(int heightIndex=0; heightIndex<this.height; heightIndex++){
			for(int widthIndex=0; widthIndex<this.width-1; widthIndex++){
				if(piecesMatrix[heightIndex][widthIndex].fits(
						piecesMatrix[heightIndex][widthIndex+1], true)){
					continue;
				}

				return false;
			}
		}

		//see if pieces fit up and down
		for(int widthIndex=0; widthIndex<this.width; widthIndex++){
			for(int heightIndex=0; heightIndex<this.height-1; heightIndex++){
				if(piecesMatrix[heightIndex][widthIndex].fits(
						piecesMatrix[heightIndex+1][widthIndex], false)){
					continue;
				}

				return false;
			}
		}

		return true;
	}
	

	public static void main(String[] aaarg){
		System.out.println(exclamationMark(9));
		Puzzle puzzle = new Puzzle(2, 2);
	
		Piece[] pieces = new Piece[]{
				new Piece(
				new Suits(Suits.CLUBS, Suits.OUT),
				new Suits(Suits.SPADES, Suits.IN),
				new Suits(Suits.CLUBS, Suits.IN),
				new Suits(Suits.DIAMONDS, Suits.OUT)),
				new Piece(
				new Suits(Suits.CLUBS, Suits.IN),
				new Suits(Suits.SPADES, Suits.OUT),
				new Suits(Suits.HEARTS, Suits.IN),
				new Suits(Suits.SPADES, Suits.OUT)),
				new Piece(
				new Suits(Suits.SPADES, Suits.OUT),
				new Suits(Suits.DIAMONDS, Suits.IN),
				new Suits(Suits.DIAMONDS, Suits.IN),
				new Suits(Suits.HEARTS, Suits.OUT)),
				new Piece(
				new Suits(Suits.HEARTS, Suits.IN),
				new Suits(Suits.CLUBS, Suits.OUT),
				new Suits(Suits.DIAMONDS, Suits.IN),
				new Suits(Suits.HEARTS, Suits.OUT))};

		for(int index=0; index<pieces.length; index++){
			pieces[index].setDirection();
			puzzle.addPiece(pieces[index]);
		}

		Piece[] correct = puzzle.getCorrect();
		
		System.out.println(puzzle.printPiecesDirections(correct) + "Only took " + puzzle.getTrials() + " combinations to check.");

/*
		for(int index=0; index<pieces.length; index++){
			System.out.println(pieces[index].getPieceNumber());
		}

		Piece[] peez = Puzzle.throwAway(pieces[1], pieces);

		for(int index=0; index<peez.length; index++){
			System.out.println(peez[index].getPieceNumber());
		}
*/
	}
}
