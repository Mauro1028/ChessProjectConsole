package util;

public class Square {
    private boolean lightColor;
    private Piece occupant;
    public boolean isLightColor() {
        return lightColor;
    }
    public Piece getOccupant() { // gets Piece in this square location
        return occupant;
    }
    public void setOccupant(Piece occupant) { // setter for square to carry piece
        this.occupant = occupant;
    }
    public Square(boolean lightColor, Piece occupant) { // instances of Square
	this.lightColor = lightColor;
	this.occupant = occupant;
    }
}
