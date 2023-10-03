package util;
import game.*;
public class Pawn extends Piece{
    public Pawn(boolean white) {
	super(white);
    }
	@Override
	public boolean isLegal(Move move, Game game) { // checks if a piece of the same color is obstructing the square
		if (!super.isLegal(move, game)) {
			return false;
		}

		int rowDiff = move.getRow1() - move.getRow0();
		int colDiff = move.getCol1() - move.getCol0();

		// Can't move backwards
		if (rowDiff > 0 && white || rowDiff < 0 && !white) {
			return false;
		}
		if(Math.abs(rowDiff) == 2 && colDiff == 0) { // can only move 2 square forwards in the beginning position
			if (move.getRow0() == 6 || move.getRow0() == 1) {
				return true;
			} else {
				return false;
			}
		}
		if(Math.abs(colDiff) == 1 && Math.abs(rowDiff) == 1){ // can capture pieces diagonally from it
			if(game.getPiece(move.getRow0(), move.getCol0()) == null){
				return false;
			}else{
				return true;
			}
		}
		if(Math.abs(rowDiff) == 1 && colDiff == 0){ // can move 1 square forward
			return true;
		}
		else{
			return false;
		}
	}
    @Override
    public String toString() { // default toString
	return white?"\u2659":"\u265F";
    }
}
