import java.awt.EventQueue;
/**
 * Main oszt�ly
 * Elind�tja a GUI �s a Motort
 * �sszekapcsolja a GUIT �s a hozz�tartoz� Motort
 * Inicializ�lja a j�t�kot: kiosztja a ter�leteket, be�ll�tja az aktu�lis j�t�kost
 * @author Tamaskaa
 *
 */
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
					motor.initPlayers(); // j�tkosok l�trehoz�sa
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
