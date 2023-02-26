import java.util.Scanner;

public class OthelloDriver {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to Othello! Select your choice:");
		System.out.println("1. Load a Game");
		System.out.println("2. Quit");
		System.out.println("3. Start a New Game");

		int choice = scan.nextInt();

		if (choice == 1) {
			Board loadedBoard = Board.load();
			loadedBoard.play();
		}
		if (choice == 2) {
			System.exit(0);
		}
		if (choice == 3) {
			Game.start();
		}

		scan.close();

	}

}
