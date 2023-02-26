import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Board {

	private Player p1;
	private Player p2;
	private Player current;
	public static PlayablePosition[][] board = new PlayablePosition[8][8];
	Scanner scan_int = new Scanner(System.in);
	Scanner scan_string = new Scanner(System.in);
	Scanner scan_char = new Scanner(System.in);
	Scanner scan_line = new Scanner(System.in);
	static Scanner scan_string_static = new Scanner(System.in);

	// PLAY METHOD
	public void play() {
		while (validMove()) {
			drawBoard();
			takeTurn();
		}

		drawBoard();
		scan_int.close();
		scan_string.close();
		scan_char.close();
		scan_line.close();
		scan_string_static.close();
		isWinner();

	}

	// Board Constructors
	public Board(Player p1_, Player p2_, int start_) {
		p1 = p1_;
		p2 = p2_;

		// Set all board Position characters to empty & set unplayable positions
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				board[i][j] = new PlayablePosition(Position.EMPTY);

			}

		}

		switch (start_) {
		case 1:
			board[2][2].setPiece(Position.WHITE);
			board[2][3].setPiece(Position.WHITE);
			board[3][2].setPiece(Position.WHITE);
			board[3][3].setPiece(Position.WHITE);
			board[4][4].setPiece(Position.WHITE);
			board[4][5].setPiece(Position.WHITE);
			board[5][4].setPiece(Position.WHITE);
			board[5][5].setPiece(Position.WHITE);
			board[2][4].setPiece(Position.BLACK);
			board[2][5].setPiece(Position.BLACK);
			board[3][4].setPiece(Position.BLACK);
			board[3][5].setPiece(Position.BLACK);
			board[4][2].setPiece(Position.BLACK);
			board[4][3].setPiece(Position.BLACK);
			board[5][2].setPiece(Position.BLACK);
			board[5][3].setPiece(Position.BLACK);
			break;
		case 2:
			board[2][2].setPiece(Position.WHITE);
			board[3][3].setPiece(Position.WHITE);
			board[2][3].setPiece(Position.BLACK);
			board[3][2].setPiece(Position.BLACK);
			break;
		case 3:
			board[2][4].setPiece(Position.WHITE);
			board[3][5].setPiece(Position.WHITE);
			board[2][5].setPiece(Position.BLACK);
			board[3][4].setPiece(Position.BLACK);
			break;
		case 4:
			board[4][2].setPiece(Position.WHITE);
			board[5][3].setPiece(Position.WHITE);
			board[4][3].setPiece(Position.BLACK);
			board[5][2].setPiece(Position.BLACK);
			break;
		case 5:
			board[4][4].setPiece(Position.WHITE);
			board[5][5].setPiece(Position.WHITE);
			board[4][5].setPiece(Position.BLACK);
			board[5][4].setPiece(Position.BLACK);
			break;
		}

		board[7][3].setPiece(Position.UNPLAYABLE);
		board[7][4].setPiece(Position.UNPLAYABLE);

	}

	public Board(String save_file) {

		boolean valid = false;
		while (!valid) {
			try {
				File file = new File(save_file);
				Scanner myReader = new Scanner(file);

				// Set the fields in the constructor
				String player1 = myReader.nextLine();
				String player2 = myReader.nextLine();
				String currentPlayer = myReader.nextLine();
				String positions = myReader.nextLine();

				// Set the saved board
				int counter = 0;
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[i].length; j++) {
						board[i][j] = new PlayablePosition(positions.charAt(counter));
						counter++;

					}
				}

				// Set the saved names and current player
				p1 = new Player(player1, 'B');
				p2 = new Player(player2, 'W');
				if (currentPlayer.equals(p1.getName())) {
					current = p1;
				} else {
					current = p2;
				}
				myReader.close();
				valid = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found, please try again");
				System.out.println();
			}

		}

	}

	// DRAWBOARD METHOD
	public void drawBoard() {

		// Draw the board
		System.out.print(" ");

		for (int i = 0; i < 8; i++) {
			System.out.print(" " + (i + 1));
		}
		System.out.println();

		for (int row = 0; row < 8; row++) {

			for (int col = 0; col < 8; col++) {
				if (col == 0) {
					System.out.print(row + 1);
				}

				System.out.print(" " + (board[row][col].getPiece()));
			}
			System.out.println();
		}
	}

	// MAKEMOVE METHOD
	public void makeMove() {
		boolean hasMoved = false;

		do {
			// Prompt user to make a move
			System.out.print("Please make a move for " + current.getName() + " with the piece " + current.getColour()
					+ " Row[1-8] Column[1-8]");
			System.out.println();
			int row = scan_int.nextInt() - 1;
			int col = scan_int.nextInt() - 1;

			// Check if given position is playable
			if (board[row][col].canPlay(current, board, row, col) == false) {
				System.out.println("This position is unplayable, please choose a playable position");
				System.out.println();
			} else if (row > 8 || row < 0 || col > 8 || col < 0) {
				System.out.println("Please make a move between Row[1-8] Row[1-8]");
				System.out.println();
			} else if (board[row][col].canPlay(current, board, row, col) == true) {
				if (current.getColour() == 'B') {
					board[row][col].setPiece(Position.BLACK);
					board[row][col].convertAll(current, board, row, col);
					turn();
					hasMoved = true;
				} else if (current.getColour() == 'W') {
					board[row][col].setPiece(Position.WHITE);
					board[row][col].convertAll(current, board, row, col);
					turn();
					hasMoved = true;
				}
			} else {
				System.out.println("Please enter a valid integer");
				System.out.println();
			}

		} while (!hasMoved);

	}

	// VALIDMOVE METHOD
	public boolean validMove() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].canPlay(current, board, i, j) == true) {
					return true;
				}

			}
		}
		if (current.getColour() == p1.getColour()) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j].canPlay(p1, board, i, j) == true) {
						return true;
					}

				}
			}
		} else if (current.getColour() == p2.getColour()) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j].canPlay(p2, board, i, j) == true) {
						return true;
					}

				}
			}
		}
		return false;
	}

	// VALIDMOVECURRENT METHOD
	public boolean validMoveCurrent() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j].canPlay(current, board, i, j) == true) {

					return true;
				}

			}
		}
		return false;
	}

	// ISWINNER METHOD
	public void isWinner() {

		int blackCounter = 0;
		int whiteCounter = 0;

		// Count the white and black pieces

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getPiece() == Position.WHITE) {
					whiteCounter++;
				} else if (board[i][j].getPiece() == Position.BLACK) {
					blackCounter++;
				}
			}
		}

		// Display the winner or tie
		if (blackCounter == whiteCounter) {
			System.out.println("Tie, equality at " + blackCounter);
			System.out.println();
		}
		if (blackCounter > whiteCounter) {
			System.out.println(p1.getName() + "with the colour Black wins with " + blackCounter + " points!");
			System.out.println();
		}
		if (blackCounter < whiteCounter) {
			System.out.println(p2.getName() + "with the colour White wins with " + whiteCounter + " points!");
			System.out.println();
		}

	}

	// LOAD METHOD
	public static Board load() {

		// Prompt user for filename
		System.out.println("Please enter your filename");
		String filename = scan_string_static.next() + ".txt";

		// Retrieve board information and return the board

		return new Board(filename);

	}

	// SAVE METHOD
	private void save() {

		// Prompt user for filename in order to save

		System.out.println("Please enter your filename");
		String filename = scan_string.next() + ".txt";

		// Set a string with all the characters in the board, the player names and the
		// current player
		String positions = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				positions = positions.concat(Character.toString(board[i][j].getPiece()));
			}
		}

		try {

			FileWriter files = new FileWriter(filename);
			files.write(p1.getName());
			files.write(System.getProperty("line.separator"));
			files.write(p2.getName());
			files.write(System.getProperty("line.separator"));
			files.write(current.getName());
			files.write(System.getProperty("line.separator"));
			files.write(positions);

			files.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

	// TURN METHOD
	private void turn() {
		if (current == p1) {
			current = p2;
		} else {
			current = p1;
		}
	}

	// TAKETURN METHOD
	private void takeTurn() {

		if (validMoveCurrent()) {
			// Prompt user for turn choice
			System.out.println();
			System.out.println("The player " + current.getName() + " can choose to ");
			System.out.println("1. Save the game");
			System.out.println("2. Concede their turn");
			System.out.println("3. Make a move");

			int choice = scan_int.nextInt();

			switch (choice) {
			case 1:
				save();
				break;
			case 2:
				turn();
				break;
			case 3:
				makeMove();
				break;
			}
		} else {
			System.out.println();
			System.out.println("The player " + current + "cannot make a move, se they can choose to ");
			System.out.println("1. Save the game");
			System.out.println("2. Concede their turn");
			System.out.println("3. Forfeit their turn");

			int choice_no_move = scan_int.nextInt();

			switch (choice_no_move) {
			case 1:
				save();
				break;
			case 2:
				turn();
				break;
			case 3:
				forfeit();
				break;
			}
		}

	}

	// FORFEIT METHOD
	private void forfeit() {

		if (current == p1) {
			System.out.println("Player 1 has forfeited the game, Player 2 wins!");

			System.exit(0);
		} else if (current == p2) {
			System.out.println("Player 2 has forfeited the game, Player 1 wins!");
			System.exit(0);
		}
	}

	// Getters and setters for Position array

	public Player getCurrent() {
		return current;
	}

	public void setCurrent(Player current) {
		this.current = current;
	}

}
