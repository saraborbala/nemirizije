import java.awt.Color;
import java.util.Vector;


public class Player implements java.io.Serializable {

	private String name;	//jáccó neve
	private int index;		//indexe
	private Color color;
	private Vector<Territory> occupiedTerritories;	//elfoglalt területkéi
	private int armies;		//mennyi katonája van összesen
	private int bonusUnit;
	
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
	public void setName(String name){
		this.name = name; 
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
	
	public int getBonusUnit(){
		return bonusUnit;
	}
	public void setBonusUnit(int n){
		bonusUnit = 0;
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
	
	public void continentBonus(){
		// kontinens bónusz, minden kör végén meg kell hívni,
		// a bónuszváltozót állítja annak függvényében, mennyi kontinense van
		// Bónuszok: 
		// Észak-Amerika(1) -> 5
		// Dél-Amerika(2)	-> 3
		// Európa(3)		-> 5 
		// Afrika(4)		-> 4
		// Ázsia(5)			-> 7
		// Ausztália és Óceánia(6) -> 3
		// Elv: tudjuk hány darab kell az adott kontinesbõl, nem kell külön kontinens osztály
		
		
		int nAmerica_count = 0;
		int sAmerica_count = 0;
		int europe_count = 0;
		int asia_count = 0;
		int africa_count = 0;
		int australia_count = 0;
		bonusUnit = 0;
		
		for(Territory territories : occupiedTerritories){
			switch (territories.getContinent()){
				case 1:  nAmerica_count++; break;
				case 2:  sAmerica_count++; break;
				case 3:  europe_count++; break;
				case 4:  asia_count++; break;
				case 5:  africa_count++; break;
				case 6:  australia_count++; break;
			}
		}
		if (nAmerica_count == 8)
			bonusUnit+=5;
		if (sAmerica_count == 4)
			bonusUnit+=3;
		if (europe_count == 7)
			bonusUnit+=5;
		if (asia_count == 12)
			bonusUnit+=7;
		if (africa_count == 6)
			bonusUnit+=4;
		if (australia_count == 4)
			bonusUnit+=3;
		
	}
	
	//----------------------------------
	
	
	//tonás csökken-nõ
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

