public class Suits{
	public static final int HEARTS = 1;
        public static final int CLUBS = 2;
        public static final int SPADES = 3;
        public static final int DIAMONDS = 4;
	
        public static final int OUT = 1;
        public static final int IN = -1;
	
	private int suit = 0;


	public Suits(int suit, int direction){
		this.suit = suit * direction;
	}

	/*
	public int getValue(){
		return this.suit;
	}
	*/
	
	public static boolean isMatch(Suits piece1, Suits piece2){
		if(piece1.suit+piece2.suit == 0){
			return true;
		}
		
		return false;
	}

	//lazy
	public boolean isOut(){
		if(suit > 0){
			return true;
		}
		
		return false;
	}
}
