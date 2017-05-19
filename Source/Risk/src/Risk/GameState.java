package Risk;
import java.util.List;
import java.util.Vector;
/**
 * Soros kommunikációért felelõs osztály
 * @author Tamaskaa
 * 	Vector<Territory> territories : területek sorban
 *	Vector<Player> players : játékosok és adatai
 * Integer actPlayer : aktuális játékos, azt nézi meg, hogy mikor kattinthaó a térkép
 * int state : állapováltozó
 * List<Integer> defenderResult; : harcnál a támadó eredményei
 *	 List<Integer> attackerResult; : a védekezõ eremdényei
 * int maxThrowNum; : ennyi dobhat a védekezõ játékos
 */ 
public class GameState implements java.io.Serializable{

	public int number = 1; // teszt
	public String msg = ""; // teszt
	public Vector<Territory> territories ;
	public Vector<Player> players;
	public Integer actPlayer;
	public int state;
	
	public List<Integer> defenderResult;
	public List<Integer> attackerResult;
	public int maxThrowNum;
	
	public int connected = 0;
	public int isGameOver = 0;
	private String errorMsg;
	public Player client;
	GameState(){}; 
}
