import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		Motor motor = new Motor();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JGUI frame = new JGUI(motor);
					frame.setVisible(true);
					frame.setTitle("Rizikó");
					motor.setGUI(frame);
					motor.initPlayers(); // játkosok létrehozása
					//motor.assignPlayer();
					motor.assignAdjacentsandContinent();			
					motor.setPlayerTerritories();
					motor.assignArmies();
					frame.refreshMap();
					for(Territory territories : motor.territories){
						System.out.println(territories.getName());
						System.out.println(territories.getPlayer());
					}
					motor.setActPlayer(motor.players.get(0));
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
				
			}
		});
		
	}

}
