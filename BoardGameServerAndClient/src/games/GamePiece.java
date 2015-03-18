package games;

public class GamePiece {

	private String type;
	private char piece;
	private int value;
	
	public String getType() {
		return type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
	public char getPiece() {
		return piece;
	}
	public void setPiece(char piece) {
		this.piece = piece;
	}
	
}