import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
	private int throwCntrAtt = 0;
	private int throwCntrDef = 0;
	//Támadó dobásainak tárolása
	private List<Integer> attackerResult = new ArrayList<>();
	//Védekezõ dobásainak tárolása
	private List<Integer> defenderResult = new ArrayList<>();
	
	public List<Integer> getAttackerResult(){
		return attackerResult;
	}
	public List<Integer> getDefenderResult(){
		return defenderResult;
	}
	private JGUI jgui; 
	private Motor motor;
	private boolean AttackDice = true;
	private boolean DefDice = true;
	//
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttackScreen frame = new AttackScreen(jgui);
					frame.setVisible(true);
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	 
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
	private final JButton btnDefend = new JButton("V\u00E9dekez\u00E9s!");
	private final JLabel lblDefDice2 = new JLabel("");
	private final JLabel lblDefDice1 = new JLabel("");
	
	private void clearResults(){
		lblAttDice1.setIcon(null);
		lblAttDice2.setIcon(null);
		lblAttDice3.setIcon(null);
		lblDefDice1.setIcon(null);
		lblDefDice2.setIcon(null);
		throwCntrAtt = 0;
		throwCntrDef = 0;
		attackerResult.clear();
		defenderResult.clear();
		AttackDice = true;
		DefDice = true;
		
	}
	
	private void throwDiceAttack(){
		if(throwCntrAtt == 1){
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int Result = r.nextInt(High-Low) + Low;
				attackerResult.add(Result);
				switch(Result){
						case 1: {lblAttDice1.setIcon(Dice1icon); break;}
						case 2: {lblAttDice1.setIcon(Dice2icon); break;}
						case 3: {lblAttDice1.setIcon(Dice3icon); break;}
						case 4: {lblAttDice1.setIcon(Dice4icon); break;}
						case 5: {lblAttDice1.setIcon(Dice5icon); break;}
						case 6:	{lblAttDice1.setIcon(Dice6icon); break;}		
				}
		}
		
		else if(throwCntrAtt == 2){

				Random r = new Random();
				int Low = 1;
				int High = 7;
				int Result = r.nextInt(High-Low) + Low;
				attackerResult.add(Result);
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
		else if(throwCntrAtt == 3){
				AttackDice = false;
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int Result = r.nextInt(High-Low) + Low;;
				attackerResult.add(Result);
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
	
	private void throwDiceDef(){
		if(throwCntrDef == 1){
			Random r = new Random();
			int Low = 1;
			int High = 7;
			int Result = r.nextInt(High-Low) + Low;
			defenderResult.add(Result);
			switch(Result){
					case 1: {lblDefDice1.setIcon(Dice1icon); break;}
					case 2: {lblDefDice1.setIcon(Dice2icon); break;}
					case 3: {lblDefDice1.setIcon(Dice3icon); break;}
					case 4: {lblDefDice1.setIcon(Dice4icon); break;}
					case 5: {lblDefDice1.setIcon(Dice5icon); break;}
					case 6:	{lblDefDice1.setIcon(Dice6icon); break;}		
			}
	}
	
		else if(throwCntrDef == 2){
			Random r = new Random();
			int Low = 1;
			int High = 7;
			int Result = r.nextInt(High-Low) + Low;
			defenderResult.add(Result);
			switch(Result){
					case 1: {lblDefDice2.setIcon(Dice1icon); break;}
					case 2: {lblDefDice2.setIcon(Dice2icon); break;}
					case 3: {lblDefDice2.setIcon(Dice3icon); break;}
					case 4: {lblDefDice2.setIcon(Dice4icon); break;}
					case 5: {lblDefDice2.setIcon(Dice5icon); break;}
					case 6:	{lblDefDice2.setIcon(Dice6icon); break;}
					case 7:	{lblDefDice2.setText("baj");; break;}
				}
			DefDice = false;
			//calculateWinner();
		}
	}

	public void calculateWinner(){ 
		if((attackerResult != null) & (defenderResult != null)){
			Collections.sort(attackerResult);
			//System.out.println(attackerResult);		
			Collections.sort(defenderResult);
			if(attackerResult.get(2) - defenderResult.get(1) <= 0){
				jgui.setAttackerLostUnits(jgui.getAttackerLostUnits() + 1);
			}
			else{jgui.setDefenderLostUnits(jgui.getDefenderLostUnits() + 1);}
			
			if(attackerResult.get(1) - defenderResult.get(1) <= 0){
				jgui.setAttackerLostUnits(jgui.getAttackerLostUnits() + 1);
			}
			else{jgui.setDefenderLostUnits(jgui.getDefenderLostUnits() + 1);}
		}
		
    }
	/**
	 * Create the frame.
	 */
	public AttackScreen(JGUI jgui, Motor motor) {
		this.jgui = jgui;
		this.motor = motor;
		DiceRead();
		
		getContentPane().setBackground(Color.ORANGE);
		setBounds(100, 100, 785, 345);
		getContentPane().setLayout(null);
				
		//JPanel DiceScreen = new JPanel();
		
		//Kockák megjelenítése
		DiceScreen.setOpaque(true);		
		DiceScreen.setBounds(153, 23, 443, 220);
		getContentPane().add(DiceScreen);
		DiceScreen.setLayout(null);
		
		
		lblAttDice1.setBounds(10, 91, 66, 75);
		DiceScreen.add(lblAttDice1);
				
		
		lblAttDice2.setBounds(105, 91, 75, 75);
		DiceScreen.add(lblAttDice2);
		
		
		lblAttDice3.setBounds(60, 11, 75, 75);
		DiceScreen.add(lblAttDice3);
		lblDefDice2.setBounds(333, 91, 75, 75);
		
		DiceScreen.add(lblDefDice2);
		lblDefDice1.setBounds(248, 91, 75, 75);
		DiceScreen.add(lblDefDice1);
		
		
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
		
		JButton btnAttack = new JButton("T\u00E1mad\u00E1s!");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(AttackDice){
				throwDiceAttack();
				throwCntrAtt += 1;
				}
				//if()
				
				//lblAttDice1.setIcon(Dice1icon);
				//lblAttDice2.setIcon(Dice2icon);
			}
		});
		btnAttack.setBounds(153, 254, 133, 31);
		getContentPane().add(btnAttack);
		
		JButton btnEndAttack = new JButton("Támadás vége");
		btnEndAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblAttDice3.setIcon(Dice1icon);
				calculateWinner();
				motor.upDateUnitsAfterAttack();				
				//Gyõztes visszaadása
				//Territory tulajdonosának beállítása
				
				jgui.setAttackEnded(true);
				
				//Játékos megszerzett területének beállítása
				jgui.refreshMap();
				System.out.println(jgui.getAttackerLostUnits());
				
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
		btnClear.setBounds(327, 254, 93, 31);
		getContentPane().add(btnClear);
		
		btnDefend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DefDice){
				throwDiceDef();
				throwCntrDef += 1;
				
				if(throwCntrAtt == 3 & throwCntrDef == 2){
					//calculate result
					
				}
			}
			}
		});
		btnDefend.setBounds(463, 254, 133, 31);
		
		getContentPane().add(btnDefend);

	}
}
