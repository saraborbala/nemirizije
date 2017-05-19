
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;	
import java.util.Collections;//dobás
import java.util.List;
import java.util.Random;	//dobás
import java.util.Vector;	//vektorok
import java.util.concurrent.ThreadLocalRandom;
/**
 * Motor osztály, tárolja az egyes területeket, és a hozzájuk tartozó játékosokat
 * Ebben az osztályban vannak a játék menetéhez elengedhetetlen függvények implementálva
 * @author Tamaskaa
 *
 */

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
    private Player actPlayer;
    
    public void setActPlayer (Player actPlayer){
    	this.actPlayer = actPlayer;
    }
    public Player getActPlayer(){
    	return this.actPlayer;
    }
    
    
    
        
  ///  public Vector<Continent> continents = new Vector<Continent>();
    static public Vector<Territory> territories = new Vector<Territory>();
    public Territory aTerritory;	//tám ter
    public Territory dTerritory;	//véd ter7
    private AttackScreen aScreen;
    public void setAScreen( AttackScreen aScreen){
    	this.aScreen = aScreen;
    }
    List<Integer> attackerResult = new ArrayList<>();
    List<Integer> defenderResult = new ArrayList<>();
        
        
        static public Vector<Player> players = new Vector<Player>(2);
        public Player defender;	//vedekezo
        public Player aktiv;	
        public Player slPlayer;	//soronlevõ játekos,aktuális
  
        
        public int defNum = 0;	//védekezés-hány
        public int attNum = 0;	//támadás-hány
        public int iter = 0;
        
        public boolean drawn; //döntetlen-ilyen lehet: ??


        
     public Motor(){}
     
     //Initial players
     /**
      * Kezdeti játékosok beállítsa
      */
     public void initPlayers(){
    	 Player player1 = new Player("Player1", 0);
    	 Player player2 = new Player("Player2", 1);
    	 
    	 player1.setColor(Color.ORANGE);
    	 player2.setColor(Color.GREEN);
    	 players.addElement(player1);
    	 players.addElement(player2);   	 
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
     /**
      * Egységmozgatás ké terület között
      * @param from: kezdeti terület neve
      * @param to: cél területe neve
      * @param numberToMove: átvivendõ katonák.
      */
      public void moveUnits(String from, String to, int numberToMove){ 
    	 //A honnan terület egységeit csökkenteni a vivendõvel
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
     
      /**
       * A csata eredményének kiszámítása
       * Használja a dobások eredményét
       */
      public void upDateUnitsAfterAttack(){
    	  //Játékosok egységszámainak frissítése a csata függvényében
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
    				  
    				  //Egy egység átvitele
    				  //TODO: mindegyikre alkalmazza
    				  
    				  System.out.println(jgui.getLabelToName());
    			  }
    			  
    		  }  
    	  } 
    	  jgui.setAttackerLostUnits(0);
    	  jgui.setDefenderLostUnits(0);
    	  jgui.refreshMap();
      }
   	  /** Az egyes területekhez hozzárendel egy-egy játékost.
	  Összesen 41 terület van, 41/2 = 20 terület jut az elsõ játékosnak
	  Elv: egy listában 41 elem, shuffle és az elsõ 20 elemhez rendeljük az elsõ playert
	  **/
      public void assignPlayer(){
 
    	  int num = 4;
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
	  /**
	  Hozzárendeli az összes országhoz a szomszédait. Egyesével. 
	   Kontinensek:
	   1 - Észak-Amerika
	   2 - Dél-Amerika
	   3 - Európa
	   4 - Afrika
	   5 - Ázsia
	   6 - Ausztália és Óceánia
	  **/
      public void assignAdjacentsandContinent(){
    	  
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
    	  
        	//-------- Western Europe: kijavítani
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
    	  // Játékoshoz frissíti a
    	  for(Territory territories : territories){
    		  if(territories.getPlayer()==players.get(0)){
    			  // ha az elsõ játékosé
    			  players.get(0).setOccTerritory(territories);
    		  }
    		  else{
    			  players.get(1).setOccTerritory(territories);
    	  }
    	  }
      }
      /**
       * Minden területhez véletlen egység hozzárandelése az elején
       */
      public void assignArmies(){
    	  for(Territory territories : territories){
    		  territories.setArmies(ThreadLocalRandom.current().nextInt(1, 4 + 1));
    	  }
      }
      //-------------------------------------------
      

      public int getOwnership(int i){
                return territories.elementAt(i).getPlayer().getPlayerIndex();
         
      }
      public int numOfTerroitories(){
            int num = territories.size();
            return num;
        }   

//---------------------------------------------------------
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
	/**
	 * Fogadja a kommunikáció alapjául szolgáló GameState osztályt
	 * Az egyes gs.state-hez más akció hajtódik végre
	 * @param gs: GamesState osztály 
	 */
	public void GameStateRecieved(GameState gs){	
		switch(gs.state) {
		case 0: // kör vége
			//System.out.println(gs.msg);
			this.territories = gs.territories;
			this.players = gs.players;
			setPlayerTerritories();
			/*for (Player players : players){
				players.continentBonus();
			}*/
			jgui.actGUIPlayerIndex = gs.actPlayer;
			jgui.refreshMap();
			break;

		case 1:// Támadás alatt
			//System.out.println(gs.msg);
			this.territories = gs.territories;
			this.players = gs.players;		
			AttackScreen dScreen = new AttackScreen(jgui,this);
			dScreen.setBounds(32, 62, 765, 325);
			dScreen.setLocation(new Point(300,300));
			dScreen.setResizable(false);
			dScreen.setTitle("Megtámadtak!");
			dScreen.setVisible(true);
			
			this.attackerResult = gs.attackerResult;
			dScreen.setMaxThrowNum(gs.maxThrowNum);
			dScreen.attackdone = true;
			dScreen.refreshAttackScreen();
			break;
	
		case 2: // Védekezõ eremdényei jöttek
				//System.out.println(gs.msg);				
				this.defenderResult = gs.defenderResult;
				aScreen.attackdone = false;
				aScreen.refreshAttackScreen();
				break;
			
		case 3: //synch, szerver
				this.territories = gs.territories;
				this.players.set(1, gs.client);
				assignPlayer();
				jgui.refreshMap();
				// Kliens felé küldés egybõl
				GameState gs_tosend = new GameState();
				gs_tosend.state = 4; //synch
				gs_tosend.territories = this.territories;
				gs_tosend.players = this.players;
				sendGameState(gs_tosend);
				break;
				
		case 4: //Támadás vége
				this.territories = gs.territories;
				this.players = gs.players;
				jgui.refreshMap();	
				break;
		default: // ismeretlen állapot -> hiba
				System.err.println("Unknown GameState!");
				break;
			
		}
		}
	}
