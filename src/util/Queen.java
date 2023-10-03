package util;

import game.*;

public class Queen extends Piece{
    public Queen(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
	if(!super.isLegal(move, game))
	    return false;
	//rules for queen only!
	int rowDiff = move.getRow1() - move.getRow0();
	int colDiff = move.getCol1() - move.getCol0();
	if (rowDiff == 0){//moves horizontally
	    for(int i = 1; i < Math.abs(colDiff);i++)
		if(colDiff > 0 && game.getPiece(move.getRow0(), move.getCol0() + i) != null//to the right
		|| colDiff < 0 && game.getPiece(move.getRow0(), move.getCol1() + i) != null) {//to the left
			return false;
		}
	    return true;
	}
	if (colDiff == 0){//moves vertically
	    for(int i = 1; i < Math.abs(rowDiff);i++)
		if(rowDiff > 0 && game.getPiece(move.getRow0() + i, move.getCol0()) != null//moves down
		|| rowDiff < 0 && game.getPiece(move.getRow1() + i, move.getCol0()) != null) {//moves up
			return false;
		}
	    return true;
	}
		if(Math.abs(rowDiff) == Math.abs(colDiff)) { // moves diagonally
			int stepRow = rowDiff > 0 ? 1 : -1;
			int stepCol = colDiff > 0 ? 1 : -1;

			for (int i = 1; i < Math.abs(rowDiff); i++) {
				int rowToCheck = move.getRow0() + i * stepRow;
				int colToCheck = move.getCol0() + i * stepCol;

				if (game.getPiece(rowToCheck, colToCheck) != null) {
					return false; // There's an obstruction in the diagonal path
				}
			}
			return true; // No obstructions, diagonal move is legal
		}
	return false;
    }
    @Override
    public String toString() { // default toString
	return white?"\u2655":"\u265B";
    }
}
