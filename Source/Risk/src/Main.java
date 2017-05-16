import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		Motor motor = new Motor();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JGUI frame = new JGUI(motor);
					frame.setVisible(true);
					frame.setTitle("Rizik�");
					motor.setGUI(frame);
					motor.initialTestPlayer();
					motor.assignPlayer();
					motor.assignAdjacentsandContinent();			
					motor.setPlayerTerritories();
					motor.assignArmies();
					/*for(Territory territories : motor.territories){
						System.out.println(territories.getName());
						System.out.println(territories.getArmies());
					}*/
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
				
			}
		});
		
	}

}
