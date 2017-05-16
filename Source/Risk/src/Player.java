import java.awt.Color;
import java.util.Vector;


public class Player implements java.io.Serializable {

	private String name;	//j�cc� neve
	private int index;		//indexe
	private Color color;
	private Vector<Territory> occupiedTerritories;	//elfoglalt ter�letk�i
	private int armies;		//mennyi katon�ja van �sszesen
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
		// kontinens b�nusz, minden k�r v�g�n meg kell h�vni,
		// a b�nuszv�ltoz�t �ll�tja annak f�ggv�ny�ben, mennyi kontinense van
		// B�nuszok: 
		// �szak-Amerika(1) -> 5
		// D�l-Amerika(2)	-> 3
		// Eur�pa(3)		-> 5 
		// Afrika(4)		-> 4
		// �zsia(5)			-> 7
		// Auszt�lia �s �ce�nia(6) -> 3
		// Elv: tudjuk h�ny darab kell az adott kontinesb�l, nem kell k�l�n kontinens oszt�ly
		
		
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
	
	
	//ton�s cs�kken-n�
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

