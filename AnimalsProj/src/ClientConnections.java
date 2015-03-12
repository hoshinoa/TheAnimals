import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnections {
	
	//private Client[] clients;
	private static ArrayList<Client> clients;
	
	public ClientConnections(){
		//clients = new Client[100]; //arbitrary initial length. should we switch to ArrayList or something?
		clients = new ArrayList<Client>();
	}
	
	/**Precondition:
	 * Postcondition:
	 *
	 */
	public static boolean populateClients(Client newClient){
		return clients.add(newClient);
	}
	
	/**Precondition:
	 * Postcondition:
	 *
	 */
	public static void closeConnections(){
		for(Client c : clients){
			c.disconnect();
		}
	}
	
	public class ConnectionRequestHandler implements Runnable{
		private Socket _socket = null;
		private PrintWriter _out = null;
		private BufferedReader _in = null;
 
		
		public ConnectionRequestHandler(Socket socket) {
			_socket = socket;
		}
 
		/**Precondition:
		 * Postcondition:
		 *
		 */
		public void run() {
			System.out.println("Client connected to socket: " + _socket.toString());
 
			try {
				_out = new PrintWriter(_socket.getOutputStream(), true);
				_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
 
				String inputLine, outputLine;
				Logic logic = new Logic();
				outputLine = logic.processInput(null);
				_out.println(outputLine);
 
				//Read from socket and write back the response to client. 
				while ((inputLine = _in.readLine()) != null) {
					outputLine = logic.processInput(inputLine);
					if(outputLine != null) {
						_out.println(outputLine);
						if (outputLine.equals("exit")) {
							System.out.println("Server is closing socket for client:" + _socket.getLocalSocketAddress());
							break;
						}
					} else {
						System.out.println("OutputLine is null!!!");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally { //In case anything goes wrong we need to close our I/O streams and sockets.
				try {
					_out.close();
					_in.close();
					_socket.close();
				} catch(Exception e) { 
					System.out.println("Couldn't close I/O streams");
				}
			}
		}
 
	}
	
	public class Logic {
		
		/**Precondition:
		 * Postcondition:
		 *
		 */
		public String processInput (String input) {
			return "Client said:" + input;
		}
	}

}
