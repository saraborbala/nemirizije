package Risk;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Szervert kezel� osz�ly
 * K�l�n sz�lat nyit minden egyes ind�t�skor
 * @author Tamaskaa
 *
 */

public class Server extends Network{
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ObjectOutputStream out = null; // GameState-t� kasztolhat�
	private ObjectInputStream in = null; 
	
	
	// konstruktor
	Server(Motor motor) {
		super(motor);
	}

	private class ReceiverRunnable implements Runnable {
		/**
		 * �j sz�l nyit�sa, kliensre v�r�s, adatok fogad�sa
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
				out = new ObjectOutputStream(clientSocket.getOutputStream()); // csatorna ind�t�s
				in = new ObjectInputStream(clientSocket.getInputStream());
				out.flush();  // mem�riaszem�t elt�ntet�s
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
		disconnect(); //ha v�letlen�l volt m�r kapcsolat
		if (this.serverSocket != null){
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			serverSocket = new ServerSocket(7555); // /7555-os port (elvileg legt�bbsz�r nyitva)

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
	 * GameState oszt�ly� objektum k�ld�se
	 * @param gs : k�ldend� objektum
	 */
	void sendGameState(GameState gs) {
		if (out == null)
			return;
		System.out.println("Sending GameState");
		try{
			out.reset(); // 10 �r�s debuggol�s eredm�nye :((((((((((	
			out.writeObject(gs);
			out.flush();
		}
	 catch (IOException ex) {
		System.err.println("Send error.");
	}
		
		
	}
}
