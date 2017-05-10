import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AttackScreen extends JFrame {
	
	//Dice icons
	ImageIcon Dice1icon, Dice2icon, Dice3icon, Dice4icon, Dice5icon, Dice6icon ;
	int throwCntr = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttackScreen frame = new AttackScreen();
					frame.setVisible(true);
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Reading dice images
	public void DiceRead(){
	try{
		Dice1icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice1.png"));
		Dice2icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice2.png"));
		Dice3icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice3.png"));
		Dice4icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice4.png"));
		Dice5icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice5.png"));
		Dice6icon = new ImageIcon(AttackScreen.class.getResource("/Resized/Dice_images/Dice6.png"));
		
    }catch (Throwable e){
        e.printStackTrace();
    }
	}
	
	//Create labels
	JLabel lblAttDice1 = new JLabel("");
	JPanel DiceScreen = new JPanel();
	JLabel lblAttDice2 = new JLabel("");
	JLabel lblAttDice3 = new JLabel("");
	JPanel PlayerAttack = new JPanel();
	JPanel PlayerDeffend = new JPanel();
	JLabel lblWinner = new JLabel("");
	private final JButton btnClear = new JButton("T\u00F6rl\u00E9s");
	
	private void clearResults(){
		lblAttDice1.setIcon(null);
		lblAttDice2.setIcon(null);
		lblAttDice3.setIcon(null);
		throwCntr = 0;
	}
	
	private void blackJack(){
		if(throwCntr == 1){
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int Result = r.nextInt(High-Low) + Low;
					switch(Result){
						case 1: {lblAttDice1.setIcon(Dice1icon); break;}
						case 2: {lblAttDice1.setIcon(Dice2icon); break;}
						case 3: {lblAttDice1.setIcon(Dice3icon); break;}
						case 4: {lblAttDice1.setIcon(Dice4icon); break;}
						case 5: {lblAttDice1.setIcon(Dice5icon); break;}
						case 6:	{lblAttDice1.setIcon(Dice6icon); break;}		
				}
		}
		
		else if(throwCntr == 2){

				Random r = new Random();
				int Low = 1;
				int High = 7;
				int Result = r.nextInt(High-Low) + Low;
					switch(Result){
						case 1: {lblAttDice2.setIcon(Dice1icon); break;}
						case 2: {lblAttDice2.setIcon(Dice2icon); break;}
						case 3: {lblAttDice2.setIcon(Dice3icon); break;}
						case 4: {lblAttDice2.setIcon(Dice4icon); break;}
						case 5: {lblAttDice2.setIcon(Dice5icon); break;}
						case 6:	{lblAttDice2.setIcon(Dice6icon); break;}
						case 7:	{lblAttDice2.setText("baj");; break;}
					}
		}
		else if(throwCntr == 3){
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int Result = r.nextInt(High-Low) + Low;;
					switch(Result){
						case 1: {lblAttDice3.setIcon(Dice1icon); break;}
						case 2: {lblAttDice3.setIcon(Dice2icon); break;}
						case 3: {lblAttDice3.setIcon(Dice3icon); break;}
						case 4: {lblAttDice3.setIcon(Dice4icon); break;}
						case 5: {lblAttDice3.setIcon(Dice5icon); break;}
						case 6:	{lblAttDice3.setIcon(Dice6icon); break;}		
				}			
		}
	}
	/**
	 * Create the frame.
	 */
	public AttackScreen() {
		DiceRead();
		
		getContentPane().setBackground(Color.ORANGE);
		setBounds(100, 100, 785, 345);
		getContentPane().setLayout(null);
				
		//JPanel DiceScreen = new JPanel();
		DiceScreen.setOpaque(true);		
		DiceScreen.setBounds(153, 23, 443, 220);
		getContentPane().add(DiceScreen);
		DiceScreen.setLayout(null);
		
		//JLabel lblAttDice1 = new JLabel("");
		lblAttDice1.setBounds(10, 91, 66, 75);
		DiceScreen.add(lblAttDice1);
				
		//JLabel lblAttDice2 = new JLabel("");
		lblAttDice2.setBounds(105, 91, 75, 75);
		DiceScreen.add(lblAttDice2);
		
		//JLabel lblAttDice3 = new JLabel("");
		lblAttDice3.setBounds(60, 11, 75, 75);
		DiceScreen.add(lblAttDice3);
		
		
		//JPanel PlayerAttack = new JPanel();
		PlayerAttack.setBounds(10, 23, 133, 220);
		getContentPane().add(PlayerAttack);
		
		//JPanel PlayerDeffend = new JPanel();
		PlayerDeffend.setBounds(606, 23, 133, 220);
		getContentPane().add(PlayerDeffend);
		
		//JLabel lblWinner = new JLabel("");
		//lblWinner.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblWinner.setBackground(new Color(255, 255, 255));
		lblWinner.setBounds(221, 308, 309, 48);
		getContentPane().add(lblWinner);
		
		JButton btnThrowDice = new JButton("Dob\u00E1s!");
		btnThrowDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				blackJack();
				throwCntr += 1;
				//lblAttDice1.setIcon(Dice1icon);
				//lblAttDice2.setIcon(Dice2icon);
			}
		});
		btnThrowDice.setBounds(194, 254, 133, 31);
		getContentPane().add(btnThrowDice);
		
		JButton btnEndAttack = new JButton("T\u00E1mad\u00E1s v\u00E9ge");
		btnEndAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblAttDice3.setIcon(Dice1icon);
				
				
				//Gyõztes visszaadása
				//Territory tulajdonosának beállítása
				
				//Játékos megszerzett területének beállítása
				
				
				//Ablak bezárása
				dispose();	
			}
		});
		btnEndAttack.setBounds(606, 254, 133, 31);
		getContentPane().add(btnEndAttack);
		btnClear.setToolTipText("A dobott eredm\u00E9nyek t\u00F6rl\u00E9s\u00E9hez nyomd meg a gombot!");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearResults();
			}
		});
		btnClear.setBounds(384, 254, 133, 31);
		
		getContentPane().add(btnClear);

	}
}
