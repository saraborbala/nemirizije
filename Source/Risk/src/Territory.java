import java.util.Vector;
/**
 * Egyes területeke tároló osztály
 * Tárolja a nevét és a szomszédait
 * @author Tamaskaa
 *
 */
public class Territory implements java.io.Serializable{
		private String name;
		private Player player;
		private int armies;
		private int continent;
		private Vector<String> adjacents;	// minden területnek a szomszédait eltárolni
		
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
		

		public boolean isAdjacent(String name){	//szomszédos-e
			return adjacents.contains(name);
		}

	    public boolean isOccupied(){	//foglalt-e
	        if (player.getPlayerIndex() == -1)
	              return false;
	        return true;
	    }
	    	    
	    //katonás cuma:
		public void addArmies(int n){
			armies+=n;
		}
 
}

