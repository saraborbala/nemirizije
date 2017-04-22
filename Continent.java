<<<<<<< HEAD
package risziko;

public class Continent {
	
	private String name;
	private Vector<Integer> territories; //kontinens területei
	private int value;	//akkor más járjon pl ausztráliáért?
	
	
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
	
	//része-e egy adott terület
	public boolean isTerritoryOf(Territory t){
		return (territories.contains(t.getId()));
	}
	
	//elfoglalta-e már adott valaki tutiba
	public boolean isContinentCaptured(Player p){
		Vector<Integer> tind = new Vector<Integer>(); 
		Vector<Territory> tjat = p.getOccupiedTerritories(); //adott játékos területeinek vektora
                
		for (int i = 0; i < tjat.size(); i++)
			tind.add(tjat.elementAt(i).getId());           //t1-be az adott játékos területindexeit bepakolja
		for (int j = 0; j < territories.size(); j++){	//végig a konti területein
                    if(!tind.contains(territories.elementAt(j)))	//nincs elfoglalva, ha nem mind a tiéd
			return false;
		}	
	return true; 
	}

}
=======
package risziko;

public class Continent {
	
	private String name;
	private Vector<Integer> territories; //kontinens területei
	private int value;	//akkor más járjon pl ausztráliáért?
	
	
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
	
	//része-e egy adott terület
	public boolean isTerritoryOf(Territory t){
		return (territories.contains(t.getId()));
	}
	
	//elfoglalta-e már adott valaki tutiba
	public boolean isContinentCaptured(Player p){
		Vector<Integer> tind = new Vector<Integer>(); 
		Vector<Territory> tjat = p.getOccupiedTerritories(); //adott játékos területeinek vektora
                
		for (int i = 0; i < tjat.size(); i++)
			tind.add(tjat.elementAt(i).getId());           //t1-be az adott játékos területindexeit bepakolja
		for (int j = 0; j < territories.size(); j++){	//végig a konti területein
                    if(!tind.contains(territories.elementAt(j)))	//nincs elfoglalva, ha nem mind a tiéd
			return false;
		}	
	return true; 
	}

}
>>>>>>> origin/master
