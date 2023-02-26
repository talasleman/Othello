
public class PlayablePosition extends Position {

	public PlayablePosition(char piece_) {
		piece = piece_;
	}

	@Override
	public boolean canPlay(Player current, PlayablePosition[][] board, int row, int col) {
		char opposite = ' ';

		// Check if current is white or black in order to compare to opposite colour
		if (current.getColour() == Position.WHITE) {
			opposite = Position.BLACK;
		} else if (current.getColour() == Position.BLACK) {
			opposite = Position.WHITE;
		}

		// Check if position is playable
		if (Board.board[row][col].piece == Position.EMPTY) {
			// Vertical down
			if (checkPlay(current, board, row, col, 1, 0, opposite, 1, 0) == true) {
				return true;
			}
			// Vertical up
			else if (checkPlay(current, board, row, col, -1, 0, opposite, -1, 0) == true) {
				return true;
			}
			// Horizontal right
			else if (checkPlay(current, board, row, col, 0, 1, opposite, 0, 1) == true) {
				return true;
			}
			// Horizontal left
			else if (checkPlay(current, board, row, col, 0, -1, opposite, 0, -1) == true) {
				return true;
			}
			// Diagonal down right
			else if (checkPlay(current, board, row, col, 1, 1, opposite, 1, 1) == true) {
				return true;
			}
			// Diagonal down left
			else if (checkPlay(current, board, row, col, 1, -1, opposite, 1, -1) == true) {
				return true;
			}
			// Diagonal up right
			else if (checkPlay(current, board, row, col, -1, 1, opposite, -1, 1) == true) {
				return true;
			}
			// Diagonal up left
			else if (checkPlay(current, board, row, col, -1, -1, opposite, -1, -1) == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkPlay(Player current, PlayablePosition[][] board, int row, int col, int valuerow, int valuecol,
			char opposite, int counterrow, int countercol) {
		// Vertical down
		int counter = 1;
		if ((row + valuerow < 8) && (row + valuerow >= 0) && (col + valuecol < 8) && (col + valuecol >= 0)
				&& (Board.board[row + valuerow][col + valuecol].piece == opposite)) {
			counter = 1;
			if (valuerow == 0 && valuecol != 0) {
				while ((col + (countercol * counter) < 8) && (col + (countercol * counter) >= 0)
						&& (Board.board[row][col + (countercol * counter)].piece == opposite)) {
					counter++;
				}
				if ((col + (countercol * counter) < 8) && (col + (countercol * counter) >= 0)
						&& (Board.board[row][col + (countercol * (counter))].piece == current.getColour())) {
					return true;
				} else {
					return false;
				}
			} else if (valuecol == 0 && valuerow != 0) {
				counter = 1;
				while ((row + (counterrow * counter) < 8) && (row + (counterrow * counter) >= 0)
						&& (Board.board[row + (counterrow * counter)][col].piece == opposite)) {
					counter++;

				}
				if ((row + (counterrow * counter) < 8) && (row + (counterrow * counter) >= 0)
						&& (Board.board[row + (counterrow * (counter))][col].piece == current.getColour())) {
					return true;
				} else {
					return false;
				}
			} else if (valuecol != 0 && valuerow != 0) {
				counter = 1;
				while ((row + (counterrow * counter) < 8) && (row + (counterrow * counter) >= 0)
						&& (col + (countercol * counter) < 8) && (col + (countercol * counter) >= 0)
						&& (Board.board[row + (counterrow * counter)][col
								+ (countercol * counter)].piece == opposite)) {
					counter++;

				}
				if ((col + (countercol * counter) < 8) && (col + (countercol * counter) >= 0)
						&& (row + (counterrow * counter) < 8) && (row + (counterrow * counter) >= 0)
						&& (Board.board[row + (counterrow * (counter))][col + countercol * (counter)].piece == current
								.getColour())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void convertAll(Player current, PlayablePosition[][] board, int row, int col) {
		char opposite = ' ';

		// Check if current is white or black in order to compare to opposite colour
		if (current.getColour() == Position.WHITE) {
			opposite = Position.BLACK;
		} else if (current.getColour() == Position.BLACK) {
			opposite = Position.WHITE;
		}

		if (checkPlay(current, board, row, col, 1, 0, opposite, 1, 0) == true) {

			convert(current, board, row, col, 1, 0, opposite, 1, 0);
		}
		// Vertical up
		if (checkPlay(current, board, row, col, -1, 0, opposite, -1, 0) == true) {

			convert(current, board, row, col, -1, 0, opposite, -1, 0);
		}
		// Horizontal right
		if (checkPlay(current, board, row, col, 0, 1, opposite, 0, 1) == true) {

			convert(current, board, row, col, 0, 1, opposite, 0, 1);
		}
		// Horizontal left
		if (checkPlay(current, board, row, col, 0, -1, opposite, 0, -1) == true) {

			convert(current, board, row, col, 0, -1, opposite, 0, -1);
		}
		// Diagonal down right
		if (checkPlay(current, board, row, col, 1, 1, opposite, 1, 1) == true) {

			convert(current, board, row, col, 1, 1, opposite, 1, 1);
		}
		// Diagonal down left
		if (checkPlay(current, board, row, col, 1, -1, opposite, 1, -1) == true) {

			convert(current, board, row, col, 1, -1, opposite, 1, -1);
		}
		// Diagonal up right
		if (checkPlay(current, board, row, col, -1, 1, opposite, -1, 1) == true) {

			convert(current, board, row, col, -1, 1, opposite, -1, 1);
		}
		// Diagonal up left
		if (checkPlay(current, board, row, col, -1, -1, opposite, -1, -1) == true) {

			convert(current, board, row, col, -1, -1, opposite, -1, -1);
		}

	}

	@Override
	public void convert(Player current, PlayablePosition[][] board, int row, int col, int valuerow, int valuecol,
			char opposite, int counterrow, int countercol) {

		int counter = 1;

		// Convert all valid pieces
		if (valuerow == 0 && valuecol != 0) {
			counter = 1;
			while (Board.board[row][col + (valuecol * counter)].piece != current.getColour()) {
				Board.board[row][col + (valuecol * counter)].piece = (current.getColour());
				counter++;
			}
		}

		else if (valuerow != 0 && valuecol == 0) {
			counter = 1;
			while (Board.board[row + (valuerow * counter)][col].piece != current.getColour()) {
				Board.board[row + (valuerow * counter)][col].piece = (current.getColour());
				counter++;
			}
		}

		else if (valuerow != 0 && valuecol != 0) {
			counter = 1;
			while (Board.board[row + (valuerow * counter)][col + (valuecol * counter)].piece != current.getColour()) {
				Board.board[row + (valuerow * counter)][col + (valuecol * counter)].piece = (current.getColour());
				counter++;
			}
		}

	}
}
