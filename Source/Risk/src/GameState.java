import java.util.List;
import java.util.Vector;

public class GameState implements java.io.Serializable{
	// Csak publikus osztályváltozók, ide jön minden ami szükséges arra, hogy a GUI felépüljön, illetve
	// néhány szerver mûködéséshez fontos dolog
	// Az errorMsg private egyedül
	
	// Attack attack;
	// Map map;
	// stb. kérlek írjátok be mi kell még
	public int number = 1; // teszt
	public String msg = ""; // teszt
	public Vector<Territory> territories ;
	public Vector<Player> players;
	public Integer actPlayer;
	public int state;
	
	public List<Integer> defenderResult;
	public List<Integer> attackerResult;
	
	public int connected = 0;
	public int isGameOver = 0;
	private String errorMsg;
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	GameState(){}; 

}

//TODO
//Átküldeni: Motorból: players, territories