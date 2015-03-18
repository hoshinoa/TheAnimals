import java.util.*;


public class OthelloLogic{

	public ArrayList<List<String>> board;
	public String BLACK = "B";
	public String WHITE = "W";
	public String EMPTY = ".";
	
	private int blackCount = 2;
	private int whiteCount = 2;
	private ArrayList flipCoords = new ArrayList<List<Integer>>();
	
	public OthelloLogic(){
		board = new ArrayList<List<String>>();
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
		board.add(Arrays.asList(".",".",".","W","B",".",".","."));
		board.add(Arrays.asList(".",".",".","B","W",".",".","."));
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
		board.add(Arrays.asList(".",".",".",".",".",".",".","."));
	}
	
	public void makeMove(int x, int y, String player){
		if (isLegalMove(x, y, player)){
			board.get(x-1).set(y-1, player);
			flipPieces(player);
			pieceCount();
		}
	}
	
	public void printBoard(){
		System.out.println("   1  2  3  4  5  6  7  8");
		for (int i = 0; i < 8; i++){
			System.out.print((i+1));
			for (int j = 0; j < 8; j++){
				System.out.print("  " + board.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println("Black: " + blackCount);
		System.out.println("White: " + whiteCount);
		System.out.println();
	}
	
	public boolean isGameOver(){
		if (isBoardFull()){
			pieceCount();
			printBoard();
			if (blackCount > whiteCount){
				System.out.println("Winner: Black");
			} else if (blackCount < whiteCount){
				System.out.println("Winner: White");
			} else{
				System.out.println("Tied");
			}
			return true;
		}	
		return false;
	}
	
	public boolean isLegalMove(int x, int y, String player){
		x--;
		y--;
		
		if (board.get(x).get(y) != EMPTY){
			return false;
		}
		
		boolean valid = false;
		flipCoords.clear();
		ArrayList coordsToFlip = new ArrayList<List>();
		
		for (int r = -1; r < 2; r++){
			for (int c = -1; c < 2; c++){
				ArrayList coords = new ArrayList<List<Integer>>();
				int count = 1;
				while (true){
					try{
						if (board.get(x+(r*count)).get(y+(c*count)) == EMPTY){
							break;
						} else if (board.get(x+(r*count)).get(y+(c*count)) != player){
							coords.add(Arrays.asList(x+(r*count),y+(c*count)));
							count++;
						} else if (board.get(x+(r*count)).get(y+(c*count)) == player && count > 1){
							coordsToFlip.addAll(coords);
							valid = true;
							break;
						} else if (board.get(x+(r*count)).get(y+(c*count)) == player && count == 1){
							break;
						}
					} catch (Exception e){
						break;
					}
				}
			}
		}
		flipCoords.addAll(coordsToFlip);
		return valid;
	}
	
	private void flipPieces(String player){
		for (int i = 0; i < flipCoords.size(); i++){
			board.get(Integer.parseInt(flipCoords.get(i).toString().substring(1,2))).set(Integer.parseInt(flipCoords.get(i).toString().substring(4,5)), player);
		}
	}
	
	private void pieceCount(){
		int bCount = 0;
		int wCount = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (board.get(i).get(j) == BLACK){
					bCount++;
				} else if (board.get(i).get(j) == WHITE){
					wCount++;
				}
			}
		}
		blackCount = bCount;
		whiteCount = wCount;
	}
	
	private boolean isBoardFull(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (board.get(i).get(j) == EMPTY){
					return false;
				}
			}
		}
		return true;
	}

}
