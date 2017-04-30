/////////////////////////////
// PLAYGROUND
////////////////////////////
public class Engine {
	
	private Network net = null;
	private GUI gui;
	private String ip = "localhost"; // teszt, majd rendes IP-t is meg lehet adni
	Engine(){} // konstruktor
	
	void setGUI(GUI gui){
		this.gui = gui;
	}
	
	void startServer(){
		if (net != null)
			net.disconnect();
		net = new Server(this);
		net.connect(ip);
	}
	
	void startClient(){
		if (net != null)
			net.disconnect();
		net = new Client(this);
		net.connect(ip);
	}
	
	public void sendGameState(GameState gs){
		if (net == null)
			return;
		net.sendGameState(gs);
	}
	public void GameStateRecieved(GameState gs){
		//TODO error handling
		System.out.println(gs.msg);
	}
	
}
