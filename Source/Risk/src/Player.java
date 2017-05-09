import java.awt.Color;
import java.util.Vector;


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
    
    
    //játékos területeivel kapcsolatos cucc:
	public boolean isMyTerritory(Territory t){
		if (t.getPlayer() == this)			//am nem tom h thiselhetek-e am :(
				return true;
		return false;
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

