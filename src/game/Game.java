package game;
import java.io.PrintStream;
import java.util.*;
import util.*;
/*
Mauricio Cubillos 6349896
10/02/2023
FIU Fall 2023
G. Baroojeni
Game Class
This class prints out the board into the console and allows access to its variables through
getters and setters.
 */
public class Game {
	private String whitePlayer, blackPlayer;
	private ArrayList<Move> moves;
	private Square[][] board;
	private static final int BOARD_SIZE = 8;
	private static final String[] COLUMN_LABELS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    public Piece getPiece(int row, int col) {
	return board[row][col].getOccupant();
    }
    public boolean isWhiteTurn() {//specifies whose turn it is: white/player1 or black/player2
	return moves.size() % 2 == 0;
    }
    public String getPlayer1() {
        return whitePlayer;
    }
    public String getPlayer2() {
        return blackPlayer;
    }
    public Game(String whitePlayer, String blackPlayer) {//sets up the pieces on the board, initializes player's names.
	this.whitePlayer = whitePlayer;
	this.blackPlayer = blackPlayer;
	moves = new ArrayList<Move>();
	board = new Square[8][8];
	for(int row = 0; row < 8;row ++) {
        	for(int col = 0; col < 8; col++) {
        	    boolean white = (row + col) % 2 == 0;
        	    if (row == 1)
        		board[row][col] = new Square(white, new Pawn(false));
        	    else if (row == 6)
        		board[row][col] = new Square(white, new Pawn(true));
        	    else if (row == 7 || row == 0) {
        		if(col == 0 || col == 7)
        		    board[row][col] = new Square(white, new Rook(row == 7));
        		else if (col == 1 || col == 6)
        		    board[row][col] = new Square(white, new Knight(row == 7));
        		else if (col == 2 || col == 5)
        		    board[row][col] = new Square(white, new Bishop(row == 7));
        		else if (col == 3)
        		    board[row][col] = new Square(white, new Queen(row == 7));
        		else
        		    board[row][col] = new Square(white, new King(row == 7));
        	    }
        	    else
        		board[row][col] = new Square(white, null);
        	}
		}
    }
    public boolean move(Move move) {//makes a move, returns true if successful, false otherwise.
	Square src = board[move.getRow0()][move.getCol0()], dst = board[move.getRow1()][move.getCol1()];
	if(src.getOccupant() == null ||//empty src square
		!board[move.getRow0()][move.getCol0()].getOccupant().isLegal(move, this))//illegal move
	    return false;
	moves.add(move);
	dst.setOccupant(src.getOccupant());
	src.setOccupant(null);
	return true;
    }

	public ArrayList<Move> getMoves(){ // used for printing and for the undo command for its size
		return moves;
	}
	public void setMoves(ArrayList<Move> moves){ // the undo method in the main class uses this to change
		this.moves = moves;
	}
    @Override
    public String toString() { // used for print status to print the moves
	return moves.toString();
    }
	public void showBoard(PrintStream stream) {
		stream.println(blackPlayer + "\n__________________________________________________________\n  " + "\t" +
				String.join("\t", COLUMN_LABELS));
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = -1; col < BOARD_SIZE; col++) {
				if (col < 0)
					stream.print(String.format("%2d", BOARD_SIZE - row));
				else if (getPiece(row, col) != null)
					stream.print(getPiece(row, col));
				stream.print(col == -1 ? "  " : col != 7 ? "\t" : row != 7 ? "\n\n\n" : "\n");
			}
		}
		stream.println("__________________________________________________________\n" + whitePlayer);
	}
}
