<<<<<<< HEAD
package risziko;

public class Continent {
	
	private String name;
	private Vector<Integer> territories; //kontinens ter�letei
	private int value;	//akkor m�s j�rjon pl ausztr�li��rt?
	
	
	//kons
	Continent(String n, Vector<Integer> t, int v){
		name = n;
		value = v;
		territories = t;
	}
	
	//getterek:
	public String getName(){
		return name;
	}

	public Vector<Integer> getTerritories(){
		return territories;
	}
	
	public int getValue(){
		return value;
	}
	
	//r�sze-e egy adott ter�let
	public boolean isTerritoryOf(Territory t){
		return (territories.contains(t.getId()));
	}
	
	//elfoglalta-e m�r adott valaki tutiba
	public boolean isContinentCaptured(Player p){
		Vector<Integer> tind = new Vector<Integer>(); 
		Vector<Territory> tjat = p.getOccupiedTerritories(); //adott j�t�kos ter�leteinek vektora
                
		for (int i = 0; i < tjat.size(); i++)
			tind.add(tjat.elementAt(i).getId());           //t1-be az adott j�t�kos ter�letindexeit bepakolja
		for (int j = 0; j < territories.size(); j++){	//v�gig a konti ter�letein
                    if(!tind.contains(territories.elementAt(j)))	//nincs elfoglalva, ha nem mind a ti�d
			return false;
		}	
	return true; 
	}

}
=======
package risziko;

public class Continent {
	
	private String name;
	private Vector<Integer> territories; //kontinens ter�letei
	private int value;	//akkor m�s j�rjon pl ausztr�li��rt?
	
	
	//kons
	Continent(String n, Vector<Integer> t, int v){
		name = n;
		value = v;
		territories = t;
	}
	
	//getterek:
	public String getName(){
		return name;
	}

	public Vector<Integer> getTerritories(){
		return territories;
	}
	
	public int getValue(){
		return value;
	}
	
	//r�sze-e egy adott ter�let
	public boolean isTerritoryOf(Territory t){
		return (territories.contains(t.getId()));
	}
	
	//elfoglalta-e m�r adott valaki tutiba
	public boolean isContinentCaptured(Player p){
		Vector<Integer> tind = new Vector<Integer>(); 
		Vector<Territory> tjat = p.getOccupiedTerritories(); //adott j�t�kos ter�leteinek vektora
                
		for (int i = 0; i < tjat.size(); i++)
			tind.add(tjat.elementAt(i).getId());           //t1-be az adott j�t�kos ter�letindexeit bepakolja
		for (int j = 0; j < territories.size(); j++){	//v�gig a konti ter�letein
                    if(!tind.contains(territories.elementAt(j)))	//nincs elfoglalva, ha nem mind a ti�d
			return false;
		}	
	return true; 
	}

}
>>>>>>> origin/master
