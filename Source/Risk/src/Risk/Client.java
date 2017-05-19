package Risk;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
/**
 * Klienst implement�l� oszt�ly
 * �j sz�mban fut
 * @author Tamaskaa
 *
 */
public class Client extends Network{
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	

	Client(Motor motor) {
		super(motor);
	}
	
	// Sz�lkezel� subclass
	private class ReceiverRunnable implements Runnable {
		/**
		 * �j sz�l nyit�sa
		 * Adatok fogad�s szervert�l
		 */
		public void run() {
			System.out.println("Waiting for input..."); 
			try {
				while (true) {
					GameState received = (GameState) in.readObject();
					motor.GameStateRecieved(received);
				}	
			} catch (Exception ex) { // exepction kil�p a while (true)b�l
				System.out.println(ex.getMessage());
				System.err.println("Server disconnected!");
			} finally {
				disconnect();
			}
		}
	}
	 
	
	@Override
	void connect(String ip) {
		// TODO Auto-generated method stub
		disconnect();
		try {
			socket = new Socket(ip, 7555);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();

			Thread recive = new Thread(new ReceiverRunnable());
			recive.start();
		} catch (UnknownHostException e) { // csatalkoz�skor m�s hib�k gener�l�dnak, mint fut�skor
			System.err.println("There is no host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!"); 
			// TODO: GameState errorMsg v�ltoz�j�ba k�ldeni a hib�t
			// felugr� ablak,
			// networkos r�sz nem tartalmaaz guit, csak kommunik�ci�s
		}
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException ex) {
			System.err.println("Error while closing conn."); 
			// TODO: errorMsgbe tenni
		}
	}
	@Override
	void sendGameState(GameState gs) {
		if (out == null)
			return;
		System.out.println("Sending GameState");
		try{
			out.reset();
			out.writeObject(gs);
			out.flush();
		}
	 catch (IOException ex) {
		System.err.println("Send error.");
		// TODO errorMsg
	}
	}
}
