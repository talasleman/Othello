
public abstract class Position {

	protected char piece;
	public static final char UNPLAYABLE = '*';
	public static final char EMPTY = ' ';
	public static final char BLACK = 'B';
	public static final char WHITE = 'W';

	public abstract boolean canPlay(Player current, PlayablePosition[][] board, int row, int col);

	public abstract boolean checkPlay(Player current, PlayablePosition[][] board, int row, int col, int valuerow,
			int valuecol, char opposite, int counterrow, int countercol);
	
	public abstract void convertAll(Player current, PlayablePosition[][] board, int row, int col);
	
	public abstract void convert(Player current, PlayablePosition[][] board, int row, int col, int valuerow, int valuecol,
			char opposite, int counterrow, int countercol);

	public char getPiece() {
		return piece;
	}

	public void setPiece(char piece) {
		this.piece = piece;
	}
}
