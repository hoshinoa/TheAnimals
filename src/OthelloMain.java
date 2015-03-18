
public class OthelloMain{

	public static void main(String[] args){
		OthelloLogic o = new OthelloLogic();
		o.printBoard();
//		System.out.println(o.isLegalMove(2, 2, "B"));
//		System.out.println(o.isLegalMove(3, 3, "B"));
//		System.out.println(o.isLegalMove(3, 5, "B"));
//		System.out.println(o.isLegalMove(4, 4, "B"));
//		System.out.println(o.isLegalMove(3, 4, "B"));
		o.makeMove(3, 4, "B");
		o.printBoard();
		System.out.println(o.isGameOver());
	}

}
