package util;

import game.*;

public class Knight extends Piece{
    public Knight(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
	if(!super.isLegal(move, game)) {
        return false;
    }
	//rules for knight only!
        //eg. e4 position can be moved to d6, d2, c5, c3,
        // f6, f2, g5, g3
        int rowDiff = Math.abs(move.getRow1() - move.getRow0());
        int colDiff = Math.abs(move.getCol1() - move.getCol0());
        if(rowDiff == 2 || rowDiff == 1){
            if(colDiff == 2 || colDiff == 1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
	return white?"\u2658":"\u265E";
    }
}
