package games;

import java.io.PrintWriter;

public class Player {
	private String mName;
	private PrintWriter out;
	
	//Setters and getters
	public PrintWriter getOut() { return out; }
	public void setOut(PrintWriter out) { this.out = out; }
	public Player(String name){ mName = name; }
	public String getmName() { return mName; }
	public void setmName(String name) { mName = name; }
	//End of setters and getters
	
	public void sendMessageToPlayer(String sendThis) {
		out.println(sendThis);
	}
	
}
