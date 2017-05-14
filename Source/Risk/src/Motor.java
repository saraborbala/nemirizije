
import java.util.Arrays;	
import java.util.Collections;//dobás
import java.util.Random;	//dobás
import java.util.Vector;	//vektorok


public class Motor {
	private JGUI jgui;
	//allapotok-gameStates
    public static final int NEW_GAME = 0;	//új játék
    public static final int START_TURN = 1;	// ha minden játékosnak van már területe 
    public static final int EV_TERR_OW= 2;	//ha minden terület foglalt lesz 
    public static final int ACTIVE_TURN = 3;	//kör kezdés,új kör
    public static final int TURN_BONUS = 4;	//kör bónuszolás-katonaosztásnál-EZ NEM ÁLLAPOT
    public static final int ENOUGH_ARMIES = 5;	//megnézi h elég-e a katonád a területen
    public static final int ATTACKING = 6;	//a támadás lehetségességének megnézése->igen->7 
    public static final int ATTACK_PHASE = 7;//MÉG NINCS MEGÍRVA
    public static final int BATTLING = 8;	//csatázás-dobás után katonák halála--
    public static final int CAPTURE = 9;	//elfoglalás
    public static final int ATHELYEZES_HONNAN = 10;
    public static final int ATHELYEZES_HOVA = 11;
    
    public static final int GAME_OVER = 20;	//játék vége---!! IDE MÉG NEM NAGYON VAN SEMMI!!
    
    static private int gameState;

        
  ///  public Vector<Continent> continents = new Vector<Continent>();
    static public Vector<Territory> territories = new Vector<Territory>();
    public Territory aTerritory;	//tám ter
    public Territory dTerritory;	//véd ter
        
        
        static public Vector<Player> players = new Vector<Player>();
        public Player defender;	//vedekezo
        public Player aktiv;	
        public Player slPlayer;	//soronlevõ játekos,aktuális
  
        
        public int defNum = 0;	//védekezés-hány
        public int attNum = 0;	//támadás-hány
        public int iter = 0;
        
        public boolean drawn; //döntetlen-ilyen lehet: ??


        
     public Motor(){
     gameState = NEW_GAME;
        
		//addPlayer ("Jatekos1");	//ezt szépen kéne 
		//initalPlayer();	//kezdõjátékos- 0. 
		     
		loadMap();			//AHAAHAAHA
       
        distributeArmies();	//nulla katona          
	}
     
     static public boolean addPlayer(String nm){
         int size = players.size();
         if (size > 3)	//hányjátékoslehet- max 3
             return false;		//ide kéne vmi hibaüzi?! ha többet akarunk csinálni
         Player p = new Player(nm, size);
         players.add(p);							//beteszi az új játékost a listába
             return true;
     }
     
     public void initalPlayer(){
         slPlayer = players.elementAt(0);			//a 0 elem lesz a vektorból
     }
     
     public void loadMap() {
 		
 		// ja hát ezt nem tom :(((
 		
       }
     
     //elején katonaosztási:
     public void distributeArmies(){		
           int numberOfPlayers = players.size();
           int armies = 0;

           // gondolom, ha többen játszanak, úgy kellene, hogy több katona van az elején
           // és ha egyszer sokan akarják majd játszani, itt lehet majd állítani
           if(numberOfPlayers == 2)
               armies = 6;
           if(numberOfPlayers == 3)
               armies = 10;

           for(int i = 0; i < numberOfPlayers; i++)
               players.elementAt(i).addArmies(armies);		//minden játékos megkapja a katonáit
       }

     
     
     
      public String getTerritoryName(int id){
          return territories.elementAt(id).getName();
      }

      public String getPlayerName(int id){
    	  return territories.elementAt(id).getPlayer().getName();
      }

      public Player getCurrentPlayer(){
    	  return slPlayer;
      }
      
      public Vector<Player> getPlayers(){
    	  return players;
      }

      public Territory getTerritoryAt(int i){
    	  if(i > 0)
    		  return territories.elementAt(i);
    	  return null;
      }

      public int numOfArmiesOnTerritory(int i){
    	  return territories.elementAt(i).getArmies();
      }

      public int getState(){
    	  return gameState;
      }

      public void setState(int state){
    	  gameState = state;
      }

      public void setAttack(int num) {
    	  attNum = num;
      }

      public void setDefend(int num){
    	  defNum = num;
      }
     
      

      //elején belepakoljuk a játékosokat a players-be, vektorban lesznek szépen
      public void nextPlayer(){
           if (slPlayer == players.lastElement()){
               slPlayer = players.elementAt(0);
                iter = 0;
           }
           else
               slPlayer = players.elementAt(++iter);
       }
      
      //ha valaki meghal- 3 játékosnál pl már kéne -	DE MOST AKKOR EZ FÖLÖS?!
      public void removePlayer(Player p){
          players.remove(p);
          players.trimToSize();	//lista kapacitását beállítja a lista méretéhez
          iter--;  
      }

      
 
      //KATONAOSZTOGATÁS:
     
      // kör eleji bónusz:
      public int turnBonus(){
          int bonus = 0;
          bonus = bonus + bonusFromTerritories();		//területekbõl adódó
          System.out.println("Bonusz a teruleteidbol: " + bonus);	//ez ko igy?
          //bonus = bonus + bonusFromContinents();		//kontinensekbõl adódó
          
          return bonus;
      }

      // hány területe van a soron levõ játékosnak
      public int bonusFromTerritories(){
         int terr = slPlayer.numOfTerritories();
         int bonus = TURN_BONUS*terr;
         
         return bonus;
      }

      
      // kontinensbõl származó bónusz-ha egy agészet elfoglal
 /*     public int bonusFromContinents(){
        int bonus = 0;
        int numberOfContinents = continents.size();
        
        // megnézni h vmelyik kontinens az adott játékosé-e
        for (int i = 0; i < numberOfContinents; i++){
        	boolean captured = continents.elementAt(i).isContinentCaptured(slPlayer); 
        	if (captured) // ha igen
        		{
        		bonus = bonus + continents.elementAt(i).getValue();
        		System.out.println("Bonusz a(z)" + continents.elementAt(i).getName() + "kontinensert: " + bonus );
        		}
        	else
            	System.out.println("Nincs kontinensed, nincs kontinens bonusz :C ");

        	}
        return bonus;
      }
*/
     //SD: egységmozgatás 
      public void moveUnits(String from, String to, int numberToMove){ 
    	 //A honnan terület egységeit csökkenteni a vivendõvel
    	  for(Territory territories : territories){	
    		  if(territories.getName().equals(from)){
    			  territories.setArmies(jgui.getAvailableUnits() - jgui.getUnitsToMove());	  
    		  }
    		  if(territories.getName().equals(to)){
    			  territories.setArmies(jgui.getUnitsToMove());
    		  }
    	  }
      }
     
      
      //JÁTÉKÁLLAPOT BEÁLLÍTÁS:- terület helye kell
      
 /*     public void gamePhaseSetup(int x, int y){

         // int valasztottTerritory = getMapLocation(x,y);
          int numberOfOccTerr = 0; 
          
          
          //új játék kezdéskor:
          if (getState() == NEW_GAME){
           int n = numOfTerroitories();

           //ha kiválasztotta a területet
          // if(valasztottTerritory != -1){ 
        	   //és ha még senkié a terület
              //if(getOwnership(valasztottTerritory) == -1) 
            	  //akkor most a jelenlegi játékos bebirtokolja, mint állat
                 //   initialOccupyTerritories(valasztottTerritory);
              
                //mennyi területke van bebbirtokolva-e?
                for(int i = 0; i < n; i++){
                	//ha be van birtokolva
                     if (getOwnership(i) != -1) 
                    	 numberOfOccTerr++; 
                }               
           } 

           //ha minden terület foglalt- ekkor már csak egymás közt megy a csihipuhi 
           if (numberOfOccTerr == n){
              setState(EV_TERR_OW);
           }
        } 
          
          
          //ha már minden terület valakié:
          if(getState() == EV_TERR_OW){
        	  //létezõt választ:
              if(valasztottTerritory != -1){
            	  //ha a sajátját
                if(getOwnership(valasztottTerritory) == slPlayer.getPlayerIndex()){
                	//bool fgv
                    occupyTerritory(territories.elementAt(valasztottTerritory));
                    nextPlayer();
                }
              }
              
              //minden játékosra
              for(int i = 0; i < players.size(); i++){
            	  // ha katonátlan, akkor kapjon területet->elfoglalt területek nõnek
                 if (players.elementAt(i).getNumberOfArmies() == 0)
                	 numberOfOccTerr++;
                }
              	//ha már minden játékosnak van elfoglalva egy területe-kör indul
                if (numberOfOccTerr == players.size()){
                    setState(START_TURN);	
                }
          } 

      }
      
      
      //elfoglalhatja-e a játékos a területet
      public boolean occupyTerritory(Territory t){
          //van-e elég katonácskája
          if(slPlayer.getNumberOfArmies() > 0)       	  
                      return true;
          else
            System.out.println(slPlayer.getName() + " nincs katonád :C");
          return false;
        } 

      
      public void gamePhaseActive(int x, int y){

          int terri = getMapLocation(x,y);
                   
          if(getState() == START_TURN){
              slPlayer.addArmies(turnBonus());    
           }
          
          
          if(getState() == ATHELYEZES_HONNAN){
              if(terri != -1){
            	  //ha a sajátja a terület
                    if(getOwnership(terri) == slPlayer.getPlayerIndex()){
                    	//van-e katona ottan (min 2)
                    	if(territories.elementAt(terri).getArmies() > 2)
                    	{
                        setState(ATHELYEZES_HOVA);
                        aTerritory = territories.elementAt(terri);
                    	}
                    }
              }
          }


          if(getState() == ATHELYEZES_HOVA){
        	  //ha  terület
              if(terri != -1){
            	  //hova terület
                dTerritory = territories.elementAt(terri);
                //ha a soron levõ játékosé a terület
                if(getOwnership(terri) == slPlayer.getPlayerIndex())	
                	//ha szomszédos, is a honnannal
                      if(aTerritory.isAdjacent(dTerritory)){
                    	  
                    	  //IDE KELL MÉG EGY KATONAMOZGATÁS
                          
                      }
              }
          }
        
          //támadolhatsz e -katonád elég van e
          if(getState() == ENOUGH_ARMIES){
            if (terri != -1)

                if(getOwnership(terri) == slPlayer.getPlayerIndex()){
                	//ha van elég embered
                    if(territories.elementAt(terri).getArmies() > 1){
                    	setState(ATTACKING);
                        aTerritory = territories.elementAt(terri);	//támadott ter = terri
                    }
                    //ha viszont nem elég
                    else
                    	System.out.println("sajna keves a katonad :(");
                    	//INNEN KÉNE OLYAN, HOGY ÁTHELYEZGETHESSEN?
                    
                }
          }
          
          
          //tiéd e -szomszéd-e
          if(getState() == ATTACKING){
        	  // egy terület
              if(terri != -1){
            	  //terület védelme
                Territory d = territories.elementAt(terri);
                //ha sajátod
                if(getOwnership(terri) == slPlayer.getPlayerIndex())
                	//legyen vmi hiaüzi hogy az a te területed, buta vagy
                        System.out.println("Butasag ez");
                //ha szomszédos
                      if(aTerritory.isAdjacent(d)){
                    	  //megtámadott állapott
                          setState(ATTACK_PHASE);
                          dTerritory = d;	//védekezni való ter legyen ez a terület
                          defender = d.getPlayer();	//védekezõ játékos meg a gazdája
                      }
                      //ha nem szomszédos
                      else 
                    	  System.out.println("Nem szomszédos");
              }

          }
   */       
          //maga a támadás, mikor szomszédeos,nemsaját,megemberisvan
          //if(getState() == ATTACK_PHASE){
           

        	  ///JAJ
        	  // INNEN LEGYEN RÖGTÖN CSATA?
        //  }

        
     // }
      
      
      
    /*  public int getMapLocation(int x, int y){
          int xx;
          int yy;
          int size = 10;//ide kell majd vmi közös!!!!-Strau mondj okost
          for (int i = 0; i < territories.size(); i++){
                xx = territories.elementAt(i).getX();
                yy = territories.elementAt(i).getY();
              if (Math.abs(xx - x) <= size || Math.abs(xx - x) <= size ){
                  if (Math.abs(yy - y) <= size || Math.abs(yy - y) <= size){
                        return i;
                  } 
              }
          } 
          // ha nincs is ilyen terület
          return -1;
      }
      */
        
      
      //CSATA:/
/*public void Battle(){

           
           Integer[] attack = new Integer[attNum];
           Integer[] defend = new Integer[defNum];
           //Dice-kocka :D
           Random attDice = new Random();


            // megjegyezni minden dobás értékét
            for (int i = 0; i < attNum; i++)
                attack[i] = attDice.nextInt(6) + 1;
           for (int i = 0; i < defNum; i++)
               defend[i] = attDice.nextInt(6) + 1;
           

             //egyel dobnak:
            if(attNum == 1){
               System.out.println("elso dobas: tamado:"+attack[0] + " vedo: " +defend[0]);
                if(attack[0] > defend[0])
          //          dTerritory.looseArmy();
                else
            //        aTerritory.looseArmy();
            }
            
            //többel dobnak:
             if(attNum > 1){ 
               
            	// !! MEGNÉZNI HOGYAN DÖNTÜNK !!!!
            	 // DOBÁSOK ÖSSZEGE KELL? MERT AKKOR NEM IS BIZTI, KELL TÖMB
            	 
             }

             //ha a védekezõ elveszti katonáit->elfoglalás állapot, támadóé lesz a terület
             if(dTerritory.getArmies() == 0){
                 setState(CAPTURE);
                 dTerritory.setPlayer(slPlayer);
             }
             //ha meg a támadó veszít--AKKOR MI LESZ A TERÜLETÉVEL?
             if(aTerritory.getArmies() == 0)
                 setState(ACTIVE_TURN);

             aktiv = slPlayer;

      }*/

      
      //ELFOGLALÁS:

// TODO ha armies == 0, swap player
      public void capture(){
          int armies = defNum;
          Territory d = dTerritory;	//védõ
          Territory a = aTerritory;	//támadó
          defender.looseTerritory(d);
          aktiv.occupyTerritory(d);

          if(defender.getOccupiedTerritories().size() == 0){
              System.out.println(defender.getName()+" meghotal");
              removePlayer(defender);
              if(players.size() == 1){
                  System.out.print(aktiv.getName()+" nyertel");
              }
          }


         // a.looseArmies(armies);
         // d.addArmies(armies);

          setState(ACTIVE_TURN);
          
          //ezeket vissza kell állítni
          defNum = 0;
          attNum = 0;
          dTerritory = null;
          aTerritory = null;
      }
      


      public int getOwnership(int i){
                return territories.elementAt(i).getPlayer().getPlayerIndex();
         
      }

      
      public int numOfTerroitories(){
            int num = territories.size();
            return num;
        }

     

      
      
      public void initialOccupyTerritories(int id){


          if(slPlayer.getNumberOfArmies() > 0){
            Territory t = territories.elementAt(id);
            t.setPlayer(slPlayer);
            slPlayer.occupyTerritory(t);
            //t.addArmy();
            slPlayer.looseArmy();
            nextPlayer();
          }
          else
            System.out.println(slPlayer.getName() + " nincs tobb katona");


      }

     // Szerver-kommunikációhoz tartozó függvények
      // motor és gui összekapcsolása
  void setGUI(JGUI jgui){
  		this.jgui = jgui;
  	}  
      	
    private Network net = null;
 	private String ip = "localhost"; // teszt, majd rendes IP-t is meg lehet adni (elvileg)
 	void startServer(){
		if (net != null)
			net.disconnect();
		net = new Server(this);
		net.connect(ip);
	}
	
	void startClient(){
		if (net != null)
			net.disconnect();
		net = new Client(this);
		net.connect(ip);
	}
	
	public void sendGameState(GameState gs){
		if (net == null)
			return;
		net.sendGameState(gs);
	}
	public void GameStateRecieved(GameState gs){
		//TODO error handling
		System.out.println(gs.msg);
		jgui.revalidate();
		
	}


}