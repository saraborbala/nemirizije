package risziko;

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
    
    
    //dob�s
    public int dob(){		//nemlennikockadoboangol, ezt a kock�t majd a motorb�l kapja gondolom
    	return numOfKocka;
    }
	
    
    //j�t�kos ter�leteivel kapcsolatos cucc:
	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}
	
	public boolean isMyTerritory(Territory t){
		if (t.getPlayer() == this)			//am nem tom h thiselhetek-e am :(
				return true;
		return false;
	}
	
	//t�madhat-e a dr�ga-- ez nem tudom hogy van �ngolul bocsi
	public boolean isTamadhatoTerritory(Territory t){
				
		if (isAdjacent(t) && !isMyTerritory(t)) //szomszi �s nem a saj�tja
			return true;
		else
			//gondolom ide k�ne vmi hiba�zi: Ne legy�l csicska!- n�d help
			return false; //ha szomsz�dos de saj�t, vagy nem is szomsz�dos		
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
