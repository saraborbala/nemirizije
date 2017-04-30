/* 
 * Abstract Network oszt�lx
 * Ebb�l sz�rmazik le a kliens �s a szerver is
 */
public abstract class Network {
	protected Engine engine;
	
	// Konstruktor
	Network(Engine engine){
		this.engine = engine;
	}
	abstract void connect (String ip);
	abstract void disconnect();
	
	abstract void sendGameState(GameState gs);
}
