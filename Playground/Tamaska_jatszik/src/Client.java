import java.io.*;
import java.net.*;
 
public class Client
{
    public static void main(String[] args) throws IOException 
    {
 
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null; 
 
        try 
        {
        	socket = new Socket("localhost", 7555);
            out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) 
        {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }
 
        System.out.println("Waiting for points...");
		try {
			while (true) {
				bolygok received = (bolygok) in.readObject();
				received.display();
			}
		} catch (Exception ex) { // exepction kilép a while (true)ból
			System.out.println(ex.getMessage());
			System.err.println("Server disconnected!");
		} finally {
			///disconnect();
		}
 
        out.close();
        in.close();
        socket.close();
    }
}