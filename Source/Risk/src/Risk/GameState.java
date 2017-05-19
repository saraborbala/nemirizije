package Risk;
import java.util.List;
import java.util.Vector;
/**
 * Soros kommunik�ci��rt felel�s oszt�ly
 * @author Tamaskaa
 * 	Vector<Territory> territories : ter�letek sorban
 *	Vector<Player> players : j�t�kosok �s adatai
 * Integer actPlayer : aktu�lis j�t�kos, azt n�zi meg, hogy mikor kattintha� a t�rk�p
 * int state : �llapov�ltoz�
 * List<Integer> defenderResult; : harcn�l a t�mad� eredm�nyei
 *	 List<Integer> attackerResult; : a v�dekez� eremd�nyei
 * int maxThrowNum; : ennyi dobhat a v�dekez� j�t�kos
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
