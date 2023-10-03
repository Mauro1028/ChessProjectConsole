package util;

import game.*;

public class Rook extends Queen{
    public Rook(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
	if(!super.isLegal(move, game))
	    return false;
    // rules for Rook

    int rowDiff = move.getRow1() - move.getRow0();
    int colDiff = move.getCol1() - move.getCol0();

    if (Math.abs(rowDiff) > 0 && Math.abs(colDiff) > 0)
        return false;


	return true;
    }
    @Override
    public String toString() { // default toString
	return white?"\u2656":"\u265C";
    }
}
