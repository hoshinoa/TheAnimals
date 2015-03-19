package games;

public class GamePiece {

	private String type;
	private String piece;
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
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	
}