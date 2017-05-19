import java.util.Vector;
/**
 * Egyes ter�leteke t�rol� oszt�ly
 * T�rolja a nev�t �s a szomsz�dait
 * @author Tamaskaa
 *
 */
public class Territory implements java.io.Serializable{
		private String name;
		private Player player;
		private int armies;
		private int continent;
		private Vector<String> adjacents;	// minden ter�letnek a szomsz�dait elt�rolni
		
		//kons	
		Territory(String n){
			
			name = n;
			player = new Player(null,-1); 
		}
		
		//setterek:
		public void setPlayer(Player p){
			player = p;
		}

		public void setArmies(int a){
			armies = a;
		}

		public void setContinent(int c) {
			continent = c;
		}
		
		 public void setAdjacent(Vector<String> a){
	            adjacents = a;
	    }
		
		//getterek:
		public String getName(){
			return name;
		}
		
		public Player getPlayer(){
			return player;
		}
		
		public int getArmies(){
			return armies;
		}
		
		public Vector<String> getAdjacents(){
			return adjacents;
		}
		
		public int getContinent() {
			return continent;
		}
		

		public boolean isAdjacent(String name){	//szomsz�dos-e
			return adjacents.contains(name);
		}

	    public boolean isOccupied(){	//foglalt-e
	        if (player.getPlayerIndex() == -1)
	              return false;
	        return true;
	    }
	    	    
	    //katon�s cuma:
		public void addArmies(int n){
			armies+=n;
		}
 
}

