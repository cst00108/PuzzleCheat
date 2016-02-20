public class Piece{
	private static int counter = 0;
	private int pieceNumber = 0;
	private Suits top = null;
	private Suits bottom = null;
	private Suits left = null;
	private Suits right = null;
	private int side = 0;

	public Piece(Suits top, Suits bottom, Suits left, Suits right){
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;

		this.pieceNumber = ++counter;
	}

	public int getNumber(){
		return this.pieceNumber;
	}

	public int rotate(){
		Suits temp = top;
		
		top = left;
		left = bottom;
		bottom = right;
		right = temp;

		this.side = (this.side + 1) % 4;

		return this.getSide();
	}

	public int getSide(){
		return this.side;
	}

	//n,e,s,w = out,out,in,in
	public void setDirection(){
		if(top.isOut() && right.isOut()){
			//it's the right way
			this.side = 0;
		} else {
			rotate();
			setDirection();
		}
	}

	//left.fits(right, TRUE)
	//top.fits(bottom, FALSE)
	public boolean fits(Piece piece, boolean horizontal){
		if(horizontal){
			return Suits.isMatch(this.right, piece.left);
		} else {
			return Suits.isMatch(this.bottom, piece.top);
		}
	}
}
