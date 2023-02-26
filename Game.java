import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

	public Game(Player p1, Player p2) {

	}

	public static void start() {

		// Set scanner
		Scanner scan = new Scanner(System.in);
		int start = 0;
		boolean valid = false;
		// Prompt user for Player names, and set them as Player Objects

		System.out.println("Please enter Player 1 and Player 2 names, respectively");
		String p1 = scan.next();
		String p2 = scan.next();
		Player player1 = new Player(p1, 'B');
		Player player2 = new Player(p2, 'W');

		// Prompt user for starting positions, and set the position
		while (!valid) {
			try {
				System.out.println("Please enter your starting position:");
				System.out.println("1. Four-by-four Starting Position");
				System.out.println("2. An offset Starting Position - Up-Left");
				System.out.println("3. An offset Starting Position - Up-Right");
				System.out.println("4. An offset Starting Position - Down-Left");
				System.out.println("5. An offset Starting Position - Down-Right");
				start = scan.nextInt();

				if (start > 5 || start < 1) {
					System.out.println("Please enter a valid integer");
				} else {
					valid = true;
				}

				// Create a board with arguments to set the starting position

			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
				scan.next();
			}
		}
		Board board = new Board(player1, player2, start);

		board.setCurrent(player1);

		board.play();
		scan.close();
	}

}
