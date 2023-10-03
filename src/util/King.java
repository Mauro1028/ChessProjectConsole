package util;

import game.*;

public class King extends Piece{

    public King(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) { // checks if a piece of the same color is obstructing the square
	if(!super.isLegal(move, game))
	    return false;

    int rowDiff = move.getRow1() - move.getRow0();
    int colDiff = move.getCol1() - move.getCol0();

    if(Math.abs(rowDiff) * Math.abs(colDiff) != 1){ // simple rule for king moving one square any direction
        return false;
    }else{
        return true;
    }
	//rules for king only!
    }
    @Override
    public String toString() { // default toString
	return white?"\u2654":"\u265A";
    }
}
