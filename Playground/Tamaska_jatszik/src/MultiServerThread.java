import java.net.*;
import java.io.*;
 
public class MultiServerThread extends Thread 
{
	private Socket serverSocket = null;
	private Socket clientSocket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
 
    public MultiServerThread(Socket socket) 
    {
    super("MultiServer Thread");
    this.serverSocket = socket;
    }
 
    public void run() 
    {
 
    	
    	try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			out.flush();  // memóriaszemét eltüntetés
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.err.println("Client disconnected!");
		}
    	
            try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    }
}