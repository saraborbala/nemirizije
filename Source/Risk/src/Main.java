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
					
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
				
			}
		});
		
	}

}