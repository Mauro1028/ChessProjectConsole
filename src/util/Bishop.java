package util;

import game.*;

public class Bishop extends Queen{
    public Bishop(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) { // checks if a piece of the same color is obstructing the square
	if(!super.isLegal(move, game))
	    return false;
	//rules for bishop only!
	int colDiff = move.getRow1() - move.getRow0();
    int rowDiff = move.getCol1() - move.getCol0();
    if (Math.abs(rowDiff) == Math.abs(colDiff)) // can only move diagonal
        return true;
    else
        return false;
    }
    @Override
    public String toString() { // default toString
	return white?"\u2657":"\u265D";
    }
}
