package Risk;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Szervert kezelõ oszály
 * Külön szálat nyit minden egyes indításkor
 * @author Tamaskaa
 *
 */

public class Server extends Network{
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ObjectOutputStream out = null; // GameState-té kasztolható
	private ObjectInputStream in = null; 
	
	
	// konstruktor
	Server(Motor motor) {
		super(motor);
	}

	private class ReceiverRunnable implements Runnable {
		/**
		 * Új szál nyitása, kliensre várás, adatok fogadása
		 */
		public void run() {
			//while(true){
			try {
				System.out.println("Waiting for Client");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected.");
			} catch (IOException e) {
				System.err.println("Accept failed.");
				// TODO: errorMsg-be tenni
				disconnect();
				return;
			}
			
			
			 try {
				out = new ObjectOutputStream(clientSocket.getOutputStream()); // csatorna indítás
				in = new ObjectInputStream(clientSocket.getInputStream());
				out.flush();  // memóriaszemét eltüntetés
			} catch (IOException e) {
				System.err.println("Error while getting streams.");
				disconnect();
				return;
			}
			 try{
				 while (true){
					 GameState received = (GameState) in.readObject();
					 motor.GameStateRecieved(received);
				 }
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Client disconnected!");
			} finally {
				disconnect();
			}
					 
		}//}
	}
	

	@Override
	void connect(String ip) {
		disconnect(); //ha véletlenül volt már kapcsolat
		if (this.serverSocket != null){
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			serverSocket = new ServerSocket(7555); // /7555-os port (elvileg legtöbbször nyitva)

			Thread recieve = new Thread(new ReceiverRunnable());
			recieve.start();
		} catch (IOException e) {
			System.err.println("Port no. 7555 is probably not open.");
		}
		
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException ex) {
			System.err.println("Could not disconnect");
		}
		
	}

	@Override
	/**
	 * GameState osztályú objektum küldése
	 * @param gs : küldendõ objektum
	 */
	void sendGameState(GameState gs) {
		if (out == null)
			return;
		System.out.println("Sending GameState");
		try{
			out.reset(); // 10 órás debuggolás eredménye :((((((((((	
			out.writeObject(gs);
			out.flush();
		}
	 catch (IOException ex) {
		System.err.println("Send error.");
	}
		
		
	}
}
