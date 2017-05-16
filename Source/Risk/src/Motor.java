
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;	
import java.util.Collections;//dob�s
import java.util.Random;	//dob�s
import java.util.Vector;	//vektorok
import java.util.concurrent.ThreadLocalRandom;


public class Motor {
	private JGUI jgui;
	//allapotok-gameStates
    public static final int NEW_GAME = 0;	//�j j�t�k
    public static final int START_TURN = 1;	// ha minden j�t�kosnak van m�r ter�lete 
    public static final int EV_TERR_OW= 2;	//ha minden ter�let foglalt lesz 
    public static final int ACTIVE_TURN = 3;	//k�r kezd�s,�j k�r
    public static final int TURN_BONUS = 4;	//k�r b�nuszol�s-katonaoszt�sn�l-EZ NEM �LLAPOT
    public static final int ENOUGH_ARMIES = 5;	//megn�zi h el�g-e a katon�d a ter�leten
    public static final int ATTACKING = 6;	//a t�mad�s lehets�gess�g�nek megn�z�se->igen->7 
    public static final int ATTACK_PHASE = 7;//M�G NINCS MEG�RVA
    public static final int BATTLING = 8;	//csat�z�s-dob�s ut�n katon�k hal�la--
    public static final int CAPTURE = 9;	//elfoglal�s
    public static final int ATHELYEZES_HONNAN = 10;
    public static final int ATHELYEZES_HOVA = 11;
    
    public static final int GAME_OVER = 20;	//j�t�k v�ge---!! IDE M�G NEM NAGYON VAN SEMMI!!
    
    static private int gameState;
    private Player actPlayer;
    
    public void setActPlayer (Player actPlayer){
    	this.actPlayer = actPlayer;
    }
    public Player getActPlayer(){
    	return this.actPlayer;
    }
    
    
    
        
  ///  public Vector<Continent> continents = new Vector<Continent>();
    static public Vector<Territory> territories = new Vector<Territory>();
    public Territory aTerritory;	//t�m ter
    public Territory dTerritory;	//v�d ter
    
        
        
        static public Vector<Player> players = new Vector<Player>(2);
        public Player defender;	//vedekezo
        public Player aktiv;	
        public Player slPlayer;	//soronlev� j�tekos,aktu�lis
  
        
        public int defNum = 0;	//v�dekez�s-h�ny
        public int attNum = 0;	//t�mad�s-h�ny
        public int iter = 0;
        
        public boolean drawn; //d�ntetlen-ilyen lehet: ??


        
     public Motor(){
     
       
		//addPlayer ("Jatekos1");	//ezt sz�pen k�ne 
		//initalPlayer();	//kezd�j�t�kos- 0. 
		     
		loadMap();			//AHAAHAAHA
       
        distributeArmies();	//nulla katona          
	}
     
     static public boolean addPlayer(String nm){
         int size = players.size();
         if (size > 3)	//h�nyj�t�koslehet- max 3
             return false;		//ide k�ne vmi hiba�zi?! ha t�bbet akarunk csin�lni
         Player p = new Player(nm, size);
         players.add(p);							//beteszi az �j j�t�kost a list�ba
             return true;
     }
     
     public void initalPlayer(){
         slPlayer = players.elementAt(0);			//a 0 elem lesz a vektorb�l
     }
     
     public void loadMap() {
 		
 		// ja h�t ezt nem tom :(((
 		
       }
     
     //Initial players
     public void initPlayers(){
    	 Player player1 = new Player("Player1", 0);
    	 Player player2 = new Player("Player2", 1);
    	 
    	 player1.setColor(Color.ORANGE);
    	 player2.setColor(Color.GREEN);
    	 players.addElement(player1);
    	 players.addElement(player2);
    	 
    	 
     }
     
     //elej�n katonaoszt�si:
     public void distributeArmies(){		
           int numberOfPlayers = players.size();
           int armies = 0;

           // gondolom, ha t�bben j�tszanak, �gy kellene, hogy t�bb katona van az elej�n
           // �s ha egyszer sokan akarj�k majd j�tszani, itt lehet majd �ll�tani
           if(numberOfPlayers == 2)
               armies = 6;
           if(numberOfPlayers == 3)
               armies = 10;

           for(int i = 0; i < numberOfPlayers; i++)
               players.elementAt(i).addArmies(armies);		//minden j�t�kos megkapja a katon�it
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
     
      

      //elej�n belepakoljuk a j�t�kosokat a players-be, vektorban lesznek sz�pen
      public void nextPlayer(){
           if (slPlayer == players.lastElement()){
               slPlayer = players.elementAt(0);
                iter = 0;
           }
           else
               slPlayer = players.elementAt(++iter);
       }
      
      //ha valaki meghal- 3 j�t�kosn�l pl m�r k�ne -	DE MOST AKKOR EZ F�L�S?!
      public void removePlayer(Player p){
          players.remove(p);
          players.trimToSize();	//lista kapacit�s�t be�ll�tja a lista m�ret�hez
          iter--;  
      }

      
 
      //KATONAOSZTOGAT�S:
     
      // k�r eleji b�nusz:
      public int turnBonus(){
          int bonus = 0;
          bonus = bonus + bonusFromTerritories();		//ter�letekb�l ad�d�
          System.out.println("Bonusz a teruleteidbol: " + bonus);	//ez ko igy?
          //bonus = bonus + bonusFromContinents();		//kontinensekb�l ad�d�
          
          return bonus;
      }

      // h�ny ter�lete van a soron lev� j�t�kosnak
      public int bonusFromTerritories(){
         int terr = slPlayer.numOfTerritories();
         int bonus = TURN_BONUS*terr;
         
         return bonus;
      }

      
      // kontinensb�l sz�rmaz� b�nusz-ha egy ag�szet elfoglal
 /*     public int bonusFromContinents(){
        int bonus = 0;
        int numberOfContinents = continents.size();
        
        // megn�zni h vmelyik kontinens az adott j�t�kos�-e
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
     //SD: egys�gmozgat�s 
      public void moveUnits(String from, String to, int numberToMove){ 
    	 //A honnan ter�let egys�geit cs�kkenteni a vivend�vel
    	  for(Territory territories : territories){	
    		  if(territories.getName().equals(from)){
    			  
    			  territories.setArmies(jgui.getAvailableUnits() - numberToMove);
    			  
    			  
    		  }
	    		  if(territories.getName().equals(to)){
	    			  int actArmy = territories.getArmies();
	    			  territories.setArmies(actArmy + numberToMove);
	    		  }
    		  }  
      }
     
      //Gy�ztes kisz�m�t�sa 
      public void upDateUnitsAfterAttack(){
    	  //J�t�kosok egys�gsz�mainak friss�t�se a csata f�ggv�ny�ben
    	  Player attacker = null;
    	  boolean conqueared = false;
    	  String labelFromStr = jgui.getLabelFromName();
    	  String labelToStr = jgui.getLabelToName();
    	  System.out.println(labelFromStr);
    	  System.out.println(labelToStr);
    	  int attackerLostUnits = jgui.getAttackerLostUnits();
    	  int defenderLostUnits = jgui.getDefenderLostUnits();
    	  for(Territory territories : territories){	
    		  if(territories.getName().equals(labelFromStr)){
    			  attacker = territories.getPlayer();
    			  int actarmy = territories.getArmies();
    			  territories.setArmies(actarmy - attackerLostUnits);
    			  
    		  }
    	  }  
    	  for(Territory territories : territories){  
    		  if(territories.getName().equals(labelToStr)){
    			  int actarmy = territories.getArmies();
    			  territories.setArmies(actarmy - defenderLostUnits);
    			  
    			  if(territories.getArmies() <= 0){
    				  //Elfoglalva
    				  //conqueared = true;
    				  territories.setArmies(0);
    				  territories.setPlayer(attacker);
    				  System.out.println(labelFromStr + "\n" + labelToStr );
    				  moveUnits(labelFromStr, labelToStr, 1);
    				  /*while(territories.getArmies() < 1){
    					  territories.addArmies(1);
    				  }*/
    				  
    				  //Egy egys�g �tvitele
    				  //TODO: mindegyikre alkalmazza
    				  
    				  System.out.println(jgui.getLabelToName());
    			  }
    			  
    		  }  
    		  //if(conqueared){
    			  
    			  /*if(territories.getName().equals(labelFromStr)){
    				  //Eredeti ter�letr�l egy egys�g levon�sa
    				  //int actarmy = territories.getArmies();
        			  //territories.setArmies(actarmy - 1);
        			  
        		  }*/
    		  //}
    	  } 
    	  jgui.setAttackerLostUnits(0);
    	  jgui.setDefenderLostUnits(0);
    	  jgui.refreshMap();
      }
    	  
    	  
      
      //J�T�K�LLAPOT BE�LL�T�S:- ter�let helye kell
      
 /*     public void gamePhaseSetup(int x, int y){

         // int valasztottTerritory = getMapLocation(x,y);
          int numberOfOccTerr = 0; 
          
          
          //�j j�t�k kezd�skor:
          if (getState() == NEW_GAME){
           int n = numOfTerroitories();

           //ha kiv�lasztotta a ter�letet
          // if(valasztottTerritory != -1){ 
        	   //�s ha m�g senki� a ter�let
              //if(getOwnership(valasztottTerritory) == -1) 
            	  //akkor most a jelenlegi j�t�kos bebirtokolja, mint �llat
                 //   initialOccupyTerritories(valasztottTerritory);
              
                //mennyi ter�letke van bebbirtokolva-e?
                for(int i = 0; i < n; i++){
                	//ha be van birtokolva
                     if (getOwnership(i) != -1) 
                    	 numberOfOccTerr++; 
                }               
           } 

           //ha minden ter�let foglalt- ekkor m�r csak egym�s k�zt megy a csihipuhi 
           if (numberOfOccTerr == n){
              setState(EV_TERR_OW);
           }
        } 
          
          
          //ha m�r minden ter�let valaki�:
          if(getState() == EV_TERR_OW){
        	  //l�tez�t v�laszt:
              if(valasztottTerritory != -1){
            	  //ha a saj�tj�t
                if(getOwnership(valasztottTerritory) == slPlayer.getPlayerIndex()){
                	//bool fgv
                    occupyTerritory(territories.elementAt(valasztottTerritory));
                    nextPlayer();
                }
              }
              
              //minden j�t�kosra
              for(int i = 0; i < players.size(); i++){
            	  // ha katon�tlan, akkor kapjon ter�letet->elfoglalt ter�letek n�nek
                 if (players.elementAt(i).getNumberOfArmies() == 0)
                	 numberOfOccTerr++;
                }
              	//ha m�r minden j�t�kosnak van elfoglalva egy ter�lete-k�r indul
                if (numberOfOccTerr == players.size()){
                    setState(START_TURN);	
                }
          } 

      }
      
      
      //elfoglalhatja-e a j�t�kos a ter�letet
      public boolean occupyTerritory(Territory t){
          //van-e el�g katon�csk�ja
          if(slPlayer.getNumberOfArmies() > 0)       	  
                      return true;
          else
            System.out.println(slPlayer.getName() + " nincs katon�d :C");
          return false;
        } 

      
      public void gamePhaseActive(int x, int y){

          int terri = getMapLocation(x,y);
                   
          if(getState() == START_TURN){
              slPlayer.addArmies(turnBonus());    
           }
          
          
          if(getState() == ATHELYEZES_HONNAN){
              if(terri != -1){
            	  //ha a saj�tja a ter�let
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
        	  //ha  ter�let
              if(terri != -1){
            	  //hova ter�let
                dTerritory = territories.elementAt(terri);
                //ha a soron lev� j�t�kos� a ter�let
                if(getOwnership(terri) == slPlayer.getPlayerIndex())	
                	//ha szomsz�dos, is a honnannal
                      if(aTerritory.isAdjacent(dTerritory)){
                    	  
                    	  //IDE KELL M�G EGY KATONAMOZGAT�S
                          
                      }
              }
          }
        
          //t�madolhatsz e -katon�d el�g van e
          if(getState() == ENOUGH_ARMIES){
            if (terri != -1)

                if(getOwnership(terri) == slPlayer.getPlayerIndex()){
                	//ha van el�g embered
                    if(territories.elementAt(terri).getArmies() > 1){
                    	setState(ATTACKING);
                        aTerritory = territories.elementAt(terri);	//t�madott ter = terri
                    }
                    //ha viszont nem el�g
                    else
                    	System.out.println("sajna keves a katonad :(");
                    	//INNEN K�NE OLYAN, HOGY �THELYEZGETHESSEN?
                    
                }
          }
          
          
          //ti�d e -szomsz�d-e
          if(getState() == ATTACKING){
        	  // egy ter�let
              if(terri != -1){
            	  //ter�let v�delme
                Territory d = territories.elementAt(terri);
                //ha saj�tod
                if(getOwnership(terri) == slPlayer.getPlayerIndex())
                	//legyen vmi hia�zi hogy az a te ter�leted, buta vagy
                        System.out.println("Butasag ez");
                //ha szomsz�dos
                      if(aTerritory.isAdjacent(d)){
                    	  //megt�madott �llapott
                          setState(ATTACK_PHASE);
                          dTerritory = d;	//v�dekezni val� ter legyen ez a ter�let
                          defender = d.getPlayer();	//v�dekez� j�t�kos meg a gazd�ja
                      }
                      //ha nem szomsz�dos
                      else 
                    	  System.out.println("Nem szomsz�dos");
              }

          }
   */       
          //maga a t�mad�s, mikor szomsz�deos,nemsaj�t,megemberisvan
          //if(getState() == ATTACK_PHASE){
           

        	  ///JAJ
        	  // INNEN LEGYEN R�GT�N CSATA?
        //  }

        
     // }
      
      
      
    /*  public int getMapLocation(int x, int y){
          int xx;
          int yy;
          int size = 10;//ide kell majd vmi k�z�s!!!!-Strau mondj okost
          for (int i = 0; i < territories.size(); i++){
                xx = territories.elementAt(i).getX();
                yy = territories.elementAt(i).getY();
              if (Math.abs(xx - x) <= size || Math.abs(xx - x) <= size ){
                  if (Math.abs(yy - y) <= size || Math.abs(yy - y) <= size){
                        return i;
                  } 
              }
          } 
          // ha nincs is ilyen ter�let
          return -1;
      }
      */
        
      
      //CSATA:/
/*public void Battle(){

           
           Integer[] attack = new Integer[attNum];
           Integer[] defend = new Integer[defNum];
           //Dice-kocka :D
           Random attDice = new Random();


            // megjegyezni minden dob�s �rt�k�t
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
            
            //t�bbel dobnak:
             if(attNum > 1){ 
               
            	// !! MEGN�ZNI HOGYAN D�NT�NK !!!!
            	 // DOB�SOK �SSZEGE KELL? MERT AKKOR NEM IS BIZTI, KELL T�MB
            	 
             }

             //ha a v�dekez� elveszti katon�it->elfoglal�s �llapot, t�mad�� lesz a ter�let
             if(dTerritory.getArmies() == 0){
                 setState(CAPTURE);
                 dTerritory.setPlayer(slPlayer);
             }
             //ha meg a t�mad� vesz�t--AKKOR MI LESZ A TER�LET�VEL?
             if(aTerritory.getArmies() == 0)
                 setState(ACTIVE_TURN);

             aktiv = slPlayer;

      }*/

      
      //ELFOGLAL�S:

      public void assignPlayer(){
    	  // Az egyes ter�letekhez hozz�rendel egy-egy j�t�kost.
    	  // �sszesen 41 ter�let van, 41/2 = 20 ter�let jut az els� j�t�kosnak
    	  //Elv: egy list�ban 41 elem, shuffle �s az els� 20 elemhez rendelj�k az els� playert
    	  int num = 41;
    	  ArrayList<Integer> list = new ArrayList<Integer>();
          for (int i=0; i<num; i++) {
              list.add(new Integer(i));
          }
          Collections.shuffle(list);
          for (int i=0; i<21; i++) {
        	  territories.get(i).setPlayer(players.get(0));
          }
          for (int i=21; i<=num; i++) {
        	  territories.get(i).setPlayer(players.get(1));
          }
      }
      
      public void assignAdjacentsandContinent(){
    	  //Hozz�rendeli az �sszes orsz�ghoz a szomsz�dait. Egyes�vel. Eretv�gok.
    	  // Nagyon cs�nya, k�rlet el se olvasd
    	  // Kontinensek:
    	  // 1 - �szak-Amerika
    	  // 2 - D�l-Amerika
    	  // 3 - Eur�pa
    	  // 4 - Afrika
    	  // 5 - �zsia
    	  // 6 - Auszt�lia �s �ce�nia
    	  
    	  
    	  //-------- Alasca
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Alasca")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Kamchatka");
					adjs.add("Northwest Territory");
					adjs.add("Alberta");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
    	  //-------- Northwest Territory
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Northwest Territory")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Alasca");
					adjs.add("Greenland");
					adjs.add("Alberta");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
    	  //-------- Quebec
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Quebec")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Eastern US");
					adjs.add("Ontario");
					adjs.add("Alberta");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
    	  
    	  //-------- Ontario
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Ontario")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Quebec");
					adjs.add("Northwest Territory");
					adjs.add("Alberta");
					adjs.add("Western US");
					adjs.add("Eastern US");	
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
    	//-------- Alberta
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Alberta")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Alasca");
					adjs.add("Northwest Territory");
					adjs.add("Ontario");
					adjs.add("Western US");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
      	//-------- Western US
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Western US")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Central America");
					adjs.add("Eastern US");
					adjs.add("Ontario");
					adjs.add("Alberta");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
    	//-------- Eastern US
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Eastern US")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Central America");
					adjs.add("Western US");
					adjs.add("Ontario");
					adjs.add("Quebec");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
			}
    	  
        	//-------- Greenland
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Greenland")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Northwest Territory");
  					adjs.add("Quebec");
  					adjs.add("Ontario");
  					adjs.add("Iceland");					
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(1);break; //:(
  				}
  			}
      	  
      	//-------- Central America
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Central America")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Western US");
					adjs.add("Eastern US");
					adjs.add("Venezuela");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(1);break; //:(
				}
    	  }
    	  
    	//-------- Venezuela
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Venezuela")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Central America");
					adjs.add("Peru");
					adjs.add("Brazil");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(2);break; //:(
				}
			}
    	  
      	//-------- Peru
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Peru")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Venezuela");
					adjs.add("Argentina");
					adjs.add("Brazil");					
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(2);break; //:(
				}
			}
    	  
    	//-------- Brazil
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Brazil")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Venezuela");
					adjs.add("Argentina");
					adjs.add("Peru");
					adjs.add("North Africa");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(2);break; //:(
				}
			}
    	  
    	//-------- Argentina
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Argentina")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Brazil");
					adjs.add("Peru");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(2);break; //:(
				}
			}
    	  
    	//-------- Iceland
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Iceland")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Greenland");
					adjs.add("Scandinavia");
					adjs.add("Great Britain");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(3);break; //:(
				}
			}
    	  
      	//-------- Great Britain
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Great Britain")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Iceland");
					adjs.add("Scandinavia");
					adjs.add("Western Europe");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(3);break; //:(
				}
			}
    	  
        	//-------- Western Europe: kijav�tani
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Western Europe")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Great Britain");
  					adjs.add("Northern Europe");
  					adjs.add("Southern Europe");
  					adjs.add("North Africa");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(3);break; //:(
  				}
  			}
      	  
      	//-------- Scandinavia
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Scandinavia")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Iceland");
					adjs.add("Russia");
					adjs.add("Great Britain");
					adjs.add("Northern Europe");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(3);break; //:(
				}
			}
    	  
    	//-------- Northern Europe
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Northern Europe")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Iceland");
					adjs.add("Russia");
					adjs.add("Great Britain");
					adjs.add("Scandinavia");
					adjs.add("Southern Europe");
					adjs.add("Western Europe");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(3);break; //:(
				}
			}
    	  
        	//-------- Southern Europe
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Southern Europe")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Western Europe");
  					adjs.add("Russia");
  					adjs.add("Egypt");
  					adjs.add("Northern Europe");
  					adjs.add("Middle East");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(3);break; //:(
  				}
  			}
      	  
      	//-------- Russia
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("Russia")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Southern Europe");
					adjs.add("Scandinavia");
					adjs.add("Northern Europe");
					adjs.add("Middle East");
					adjs.add("Ural");
					adjs.add("Afganistan");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(3);break; //:(
				}
			}
    	  
        	//-------- Egypt
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Egypt")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Southern Europe");
  					adjs.add("North Africa");
  					adjs.add("East Africa");
  					adjs.add("Middle East");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(4);break; //:(
  				}
  			}
    	  
      	//-------- North Africa
    	  for ( int i =0; i <41; i++ ){
				if (territories.get(i).getName().equals("North Africa")){
					Vector<String> adjs = new Vector<String>();
					adjs.add("Southern Europe");
					adjs.add("Egypt");
					adjs.add("East Africa");
					adjs.add("Western Europe");
					adjs.add("Congo");
					adjs.add("Brazil");
					territories.get(i).setAdjacent(adjs);
					territories.get(i).setContinent(4);break; //:(
				}
			}
    	  
        //-------- East Africa
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("East Africa")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Middle East");
  					adjs.add("Egypt");
  					adjs.add("North Africa");
  					adjs.add("Congo");
  					adjs.add("Madagascar");
  					adjs.add("South Africa");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(4);break; //:(
  				}
  			}
      	  
      	//-------- Congo
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Congo")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("North Africa");
  					adjs.add("East Africa");
  					adjs.add("South Africa");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(4);break; //:(
  				}
  			}
      	  
        	//-------- South Africa
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("South Africa")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Congo");
  					adjs.add("East Africa");
  					adjs.add("Madagascar");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(4);break; //:(
  				}
  			}
      	   
      	//-------- Madagascar
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Madagascar")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("East Africa");
  					adjs.add("South Africa");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(4);break; //:(
  				}
  			}
      	  
      	//-------- Middle East
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Middle East")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("East Africa");
  					adjs.add("Egypt");
  					adjs.add("Russia");
  					adjs.add("Southern Europe");
  					adjs.add("India");
  					adjs.add("Afganistan");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Afganistan
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Afganistan")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("China");
  					adjs.add("Russia");
  					adjs.add("Ural");
  					adjs.add("India");
  					adjs.add("Middle East");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Ural
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Ural")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("China");
  					adjs.add("Russia");
  					adjs.add("Afganistan");
  					adjs.add("Siberia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Siberia
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Siberia")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("China");
  					adjs.add("Yakutsk");
  					adjs.add("Irkutsk");
  					adjs.add("Ural");
  					adjs.add("Mongolia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Yakutsk
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Yakutsk")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Kamchatka");
  					adjs.add("Siberia");
  					adjs.add("Irkutsk");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
        //-------- Kamchatka
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Kamchatka")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Yakutsk");
  					adjs.add("Siberia");
  					adjs.add("Irkutsk");
  					adjs.add("Alasca");
  					adjs.add("Japan");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Japan
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Japan")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Kamchatka");
  					adjs.add("Mongolia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
          //-------- Irkutsk
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Irkutsk")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Yakutsk");
  					adjs.add("Siberia");
  					adjs.add("Kamchatka");
  					adjs.add("Mongolia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- Mongolia
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Mongolia")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Yakutsk");
  					adjs.add("Siberia");
  					adjs.add("Kamchatka");
  					adjs.add("Japan");
  					adjs.add("China");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- China
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("China")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Ural");
  					adjs.add("Siberia");
  					adjs.add("Mongolia");
  					adjs.add("Afganistan");
  					adjs.add("Siam");
  					adjs.add("India");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
      	//-------- India
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("India")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("China");
  					adjs.add("Middle East");
  					adjs.add("Afganistan");
  					adjs.add("Siam");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
        //-------- Siam
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Siam")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("China");
  					adjs.add("India");
  					adjs.add("Indonezia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(5);break; //:(
  				}
  			}
      	  
          //-------- Indonezia
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Indonezia")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Siam");
  					adjs.add("New Guinea");
  					adjs.add("Western Australia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(6);break; //:(
  				}
  			}
      	  
      	//-------- New Guinea
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("New Guinea")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Eastern Australia");
  					adjs.add("Indonezia");
  					adjs.add("Western Australia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(6);break; //:(
  				}
  			}
      	  
      	//-------- Western Australia
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Western Australia")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Eastern Australia");
  					adjs.add("New Guinea");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(6);break; //:(
  				}
  			}
      	  
        	//-------- Eastern Australia
      	  for ( int i =0; i <41; i++ ){
  				if (territories.get(i).getName().equals("Eastern Australia")){
  					Vector<String> adjs = new Vector<String>();
  					adjs.add("Western Australia");
  					adjs.add("Indonezia");
  					territories.get(i).setAdjacent(adjs);
  					territories.get(i).setContinent(6);break; //:(
  				}
  			} 	  
      }
      
      public void setPlayerTerritories(){
    	  for(Territory territories : territories){
    		  if(territories.getPlayer()==players.get(0)){
    			  // ha az els� j�t�kos�
    			  players.get(0).setOccTerritory(territories);
    		  }
    		  else{
    			  players.get(1).setOccTerritory(territories);
    	  }
    	  }
      }
      
      public void assignArmies(){
    	  for(Territory territories : territories){
    		  territories.setArmies(ThreadLocalRandom.current().nextInt(1, 4 + 1));
    		  //territories.setArmies(1 + (int)(Math.random() * 4));
    	  }
      }
      //-------------------------------------------
      
      public void capture(){
          int armies = defNum;
          Territory d = dTerritory;	//v�d�
          Territory a = aTerritory;	//t�mad�
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
          
          //ezeket vissza kell �ll�tni
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
//---------------------------------------------------------
     // Szerver-kommunik�ci�hoz tartoz� f�ggv�nyek
      // motor �s gui �sszekapcsol�sa
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
		if(gs.state == 0){
			System.out.println(gs.msg);
			this.territories = gs.territories;
			this.players = gs.players;
			setPlayerTerritories();
			for (Player players : players){
				players.continentBonus();
			}
			jgui.actGUIPlayerIndex = gs.actPlayer;
			jgui.refreshMap();
		}
		if(gs.state == 1){ // T�mad�s alatt
			System.out.println(gs.msg);
			this.territories = gs.territories;
			this.players = gs.players;
			AttackScreen aScreen = new AttackScreen(jgui,this);
			aScreen.refreshAttackScreen();
			
			
		}
	}
}