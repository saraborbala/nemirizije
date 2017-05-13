/* 
 * Abstract Network oszt�ly
 * Ebb�l sz�rmazik le a kliens �s a szerver is
 */
public abstract class Network {
	protected Motor motor;
	
	// Konstruktor
	Network(Motor motor){
		this.motor = motor;
	}
	abstract void connect (String ip);
	abstract void disconnect();
	
	abstract void sendGameState(GameState gs);
}
