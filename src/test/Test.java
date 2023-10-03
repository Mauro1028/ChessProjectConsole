package test;
import game.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
Mauricio Cubillos 6349896
10/02/2023
FIU Fall 2023
G. Baroojeni
Test Class
This class prints out the board into the console and allows access to its variables through
getters and setters.
 */
public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Game game = new Game(null, null);
		boolean newGameCreated = false;

		// while loop asking commands from the user
		while (true) {
			System.out.println("Please use the following commands:" +
					"\nCreate a new game by typing: new game (whiteplayer) (blackplayer)" +
					"\nmy (startPos)(endPos) eg. d2d4\ncp (startPos)(capturePos)" +
					"\nTo undo a move type: undo \nFor chess game stats: print status" +
					"\nTo quit: quit\n");
			System.out.print("\nPlease Enter Command Here:");

			String command = sc.nextLine();
			String[] commands = command.split(" ");

			if (commands[0].equals("new") && commands[1].equals("game")) { // create game command for creating board
				if (commands.length != 4) {
					System.out.println("Invalid Input");
					continue;
				}
				game = newGame(commands[2], commands[3]);
				newGameCreated = true;
			}

			else if (commands[0].equals("mv") && newGameCreated && commands.length == 2) { // move command for moving piece to empty square
				Move move = new Move(commands[1]);
				if(checkMove(commands[1]))
					System.out.println("Incorrect format");
				else {
					if (move(move, game)) {
						if (!game.isWhiteTurn())
							System.out.println(game.getPlayer1() + " moves: " + commands[1]);
						else System.out.println(game.getPlayer2() + " moves: " + commands[1]);
					} else {
						System.out.println("Illegal Move");
					}
				}
			}

			else if (commands[0].equals("cp") && newGameCreated) { // capture piece command
				Move move = new Move(commands[1]);
				if(captureMove(move, game)){
					if(game.isWhiteTurn())
						System.out.println(game.getPlayer1() + " captures: " + commands[1]);
					else System.out.println(game.getPlayer2() + " captures: " + commands[1]);
				}	else
					System.out.println("Illegal Move");
			}

			else if (command.equals("undo")) { // undo command that allows user ot backtrack movements
				game = undo(game, game.getPlayer1(), game.getPlayer2());
			}

			else if (command.equals("print status") && newGameCreated){
				System.out.println("List of moves");
				printStatus(game);
				System.out.println();
			}

			else if (command.equals("quit")) { // quit command for breaking the loop
				break;
			}
			else System.out.println("Invalid input");

			if (newGameCreated && !command.equals("print status")) {
				game.showBoard(System.out); // Print the updated board
				if(game.isWhiteTurn())
					System.out.println("It is " + game.getPlayer1() + "'s turn to move!");
				else
					System.out.println("It is " + game.getPlayer2() + "'s turn to move!");
			}
			else // ignores updated board and prints current board with list of moves
				continue;
			System.out.println();
		}
	}

	public static Game newGame(String whitePlayer, String blackPlayer) { // method creates new game from user input
		Game newGame = new Game(whitePlayer, blackPlayer);
		return newGame;
	}

	public static boolean move(Move move, Game newGame) { // method meant for piece moving into empty squares
		if(newGame.getPiece(move.getRow1(), move.getCol1()) == null) {
			if (newGame.move(move))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public static boolean captureMove(Move move, Game newGame) { // method meant only for capturing pieces
		if (newGame.getPiece(move.getRow1(), move.getCol1()) != null){
			if(newGame.move(move))
				return true;
			else
				return false;
		}else
			return false;
	}

	private static boolean checkMove(String move){
		// Check if the length of the command is correct
		// Look over to see if the format is correct
		if (move.length() != 4 || !isValidChar(move.charAt(0)) ||
				!Character.isDigit(move.charAt(1)) ||
				!isValidChar(move.charAt(2)) ||
				!Character.isDigit(move.charAt(3))){
			return true;
		}
		else return false;
	}

	private static Game undo(Game game, String player1, String player2){ // undoes the user's movement to the previous
		ArrayList<Move> moves = game.getMoves();
		moves.remove(moves.size()-1);
		game.setMoves(moves);
		Game newGame = new Game(player1, player2);
		for (Move i : moves){
			newGame.move(i);
		}
		return newGame;
	}

	private static boolean isValidChar(char c) { // checks if character within move command follows chess coords
		return (c >= 'a' && c <= 'h') || (c >= '1' && c <= '8');
	}

	private static void printStatus(Game game){ // prints the board followed by a list of moves done in the game
		game.showBoard(System.out);
		System.out.println("List of moves");
		System.out.println(game.toString());
	}

}
