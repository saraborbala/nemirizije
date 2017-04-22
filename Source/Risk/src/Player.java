package risziko;

public class Player {

	private String name;	//jáccó neve
	private int index;		//indexe
	private Color color;
	private Vector<Territory> occupiedTerritories;	//elfoglalt területkéi
	private int armies;		//mennyi katonája van összesen
	
	//kons
	Player(String n, int i) {
		name = n;
		index = i;
		occupiedTerritories = new Vector<Territory>();
		armies = 10; //mennyivel kezd?
	}
	
	
	//getterek:
	public int getPlayerIndex(){
		return index;
	}
	
	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getNumberOfArmies(){
		return armies;
	}
	
	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}
	
	//katonás csökken-nõ
	public void addArmies(int a){
		armies += a;
	}

    public void addArmy(){
        armies++;
    }

    public void looseArmies(int a){
        armies -= a;
    }
        
    public void looseArmy(){
        armies--;
    }
    
    
    //dobás
    public int dob(){		//nemlennikockadoboangol, ezt a kockát majd a motorból kapja gondolom
    	return numOfKocka;
    }
	
    
    //játékos területeivel kapcsolatos cucc:
	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}
	
	public boolean isMyTerritory(Territory t){
		if (t.getPlayer() == this)			//am nem tom h thiselhetek-e am :(
				return true;
		return false;
	}
	
	//támadhat-e a drága-- ez nem tudom hogy van ángolul bocsi
	public boolean isTamadhatoTerritory(Territory t){
				
		if (isAdjacent(t) && !isMyTerritory(t)) //szomszi és nem a sajátja
			return true;
		else
			//gondolom ide kéne vmi hibaüzi: Ne legyél csicska!- níd help
			return false; //ha szomszédos de saját, vagy nem is szomszédos		
	}

	public int numOfTerritories(){
		return occupiedTerritories.size();
	}
	
	public void occupyTerritory(Territory t){
		occupiedTerritories.add(t);
	} //kíváló területfoglalás
	
	public void looseTerritory(Territory t){
		occupiedTerritories.remove(t);	//kiszedni a területvektorából
		// vektor méretét csökkenteni nem árt gondolom
	}	//itt meg elveszti
	
	
	
	
}
