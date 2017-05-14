import java.awt.Color;
import java.util.Vector;


public class Player {

	private String name;	//j�cc� neve
	private int index;		//indexe
	private Color color;
	private Vector<Territory> occupiedTerritories;	//elfoglalt ter�letk�i
	private int armies;		//mennyi katon�ja van �sszesen
	
	//kons
	Player(String n, int i) {
		name = n;
		index = i;
		occupiedTerritories = new Vector<Territory>();
		armies = 10; //mennyivel kezd?
		color = Color.BLACK;
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
	

	//SD
	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}
	
	//Strau - setterek
	public void setColor(Color playercolor){
		color = playercolor;
	}
	
	public void setOccTerritory(Territory territory){
		occupiedTerritories.add(territory);
	}
	
	//----------------------------------
	
	//katon�s cs�kken-n�
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
    
    
    //j�t�kos ter�leteivel kapcsolatos cucc:
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
	} //k�v�l� ter�letfoglal�s
	
	public void looseTerritory(Territory t){
		occupiedTerritories.remove(t);	//kiszedni a ter�letvektor�b�l
		// vektor m�ret�t cs�kkenteni nem �rt gondolom
	}	//itt meg elveszti
	
	
	
}

