public class GameState implements java.io.Serializable{
	// Csak publikus oszt�lyv�ltoz�k, ide j�n minden ami sz�ks�ges arra, hogy a GUI fel�p�lj�n, illetve
	// n�h�ny szerver m�k�d�s�shez fontos dolog
	// Az errorMsg private egyed�l
	
	// Attack attack;
	// Map map;
	// stb. k�rlek �rj�tok be mi kell m�g
	public int number = 1; // teszt
	public String msg = ""; // teszt
	
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