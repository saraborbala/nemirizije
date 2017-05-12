import java.util.Vector;



public class Territory{

		protected int id;
	    private int x;	//szomsz�doss�gok miatt kellenek?!
	    private int y;
		private String name;
		private Player player;
		private int armies;
		private int continent;
		private Vector<Integer> adjacents;	// minden ter�letnek a szomsz�dait elt�rolni
		
		//kons
		Territory(int i,String n, int c, int xh, int yh){
			id = i;
			x = xh;
		    y = yh;
			name = n;
			player = new Player(null,-1); 	
				//el�sz�r senki�: neve nincs, index ekkor legyen -1
			armies = 0; //elej�n sehol sincs katona
			continent = c; //ez m�g akkor k�ne a t�rk�pbe
			adjacents = new Vector<Integer>();         
		}
		
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
		
		 public void setAdjacent(Vector<Integer> a){
	            adjacents = a;
	    }
		
		//getterek:
	    public int getX(){
	            return x;
	    }

	    public int getY(){
	            return y;
	    }
		
		public int getId(){
			return id;
		}
		
		public String getName(){
			return name;
		}
		
		public Player getPlayer(){
			return player;
		}
		
		public int getArmies(){
			return armies;
		}
		
		public Vector<Integer> getAdjacents(){
			return adjacents;
		}
		
		public int getContinent() {
			return continent;
		}
		
		

		public boolean isAdjacent(Territory t){	//szomsz�dos-e
			return adjacents.contains(t.getId());
		}

	    public boolean isOccupied(){	//foglalt-e
	        if (player.getPlayerIndex() == -1)
	              return false;
	        return true;
	    }
	    
	    
	    //adott j�t�kos t�madhatja-e
	    public boolean isTamadhato(Player p){	//szomsz�dos ter�letem van ezzel
	    	//+ nem az eny�m kell legyen
	    	if ( !p.isMyTerritory(this) && ( p.getPlayerIndex() != player.getPlayerIndex()))
	    		return true;
	    	return false;
	    }
	    
		
	    
	    //katon�s cuma:
		public void addArmies(int n){
			armies+=n;
		}

	    public void addArmy(){
	        armies++;
	    }
		
		public void looseArmy(){
			armies--;
		}

	    public void looseArmies(int a){
	        armies -= a;
	    }
	    


}

