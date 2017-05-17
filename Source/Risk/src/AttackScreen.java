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
	//T�mad� dob�sainak t�rol�sa
	private List<Integer> attackerResult = new ArrayList<>(0);
	//V�dekez� dob�sainak t�rol�sa
	private List<Integer> defenderResult = new ArrayList<>(0);
	
	public List<Integer> getAttackerResult(){
		return attackerResult;
	}
	public List<Integer> getDefenderResult(){
		return defenderResult;
	}
	
	public void setAttackerResult(List<Integer> attackerResult){
		this.attackerResult = attackerResult;
	}
	
	public void setDefenderResult(List<Integer> defenderResult){
		this.defenderResult = defenderResult;
	}
	private JGUI jgui; 
	private Motor motor;
	private boolean AttackDice = true;
	private boolean DefDice = true;
	private int maxThrowNum = 1;
	private boolean resultCalculable = false;
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
	public void setDiceIcon(Integer result, JLabel label){
		//Eredm�ny f�ggv�ny�ben az ikon kiv�laszt�sa �s be�ll�t�sa
		switch(result){
		case 1: {label.setIcon(Dice1icon); break;}
		case 2: {label.setIcon(Dice2icon); break;}
		case 3: {label.setIcon(Dice3icon); break;}
		case 4: {label.setIcon(Dice4icon); break;}
		case 5: {label.setIcon(Dice5icon); break;}
		case 6:	{label.setIcon(Dice6icon); break;}
		}
	}
	
	public void refreshAttackScreen(){
		if(!(attackerResult.isEmpty() & defenderResult.isEmpty())){
		//T�mad�
		setDiceIcon(attackerResult.get(0), lblAttDice1);
		setDiceIcon(attackerResult.get(1), lblAttDice2);
		setDiceIcon(attackerResult.get(2), lblAttDice3);
		//V�dekez�
		//setDiceIcon(defenderResult.get(0), lblDefDice1);
		//setDiceIcon(defenderResult.get(1), lblDefDice2);
		}
		else{System.out.println("Nincsenek eredm�nyek");}
	}
	
	private int generateRandom(){
		//Random sz�m 1-t�l 6-ig
		Random r = new Random();
		int low = 1;
		int high = 6;
		int result = r.nextInt(high-low) + low;
		return result;
	}
	
	private void setAttackerMaxThrowNum(){
		for(Territory territories : motor.territories){
			if(territories.getName().equals(jgui.getLabelFromName())){
				if(territories.getArmies() == 2){
					maxThrowNum = 1;
				}
				else if(territories.getArmies() == 3){
					maxThrowNum = 2;
				}
				else{
					maxThrowNum = 3;
				}
			}
		}
	}
	private void setDefenderMaxThrowNum(){
		for(Territory territories : motor.territories){
			if(territories.getName().equals(jgui.getLabelToName())){
				if(territories.getArmies() == 1){
					maxThrowNum = 1;
				}
				else{
					maxThrowNum = 2;
				}
			}
		}
	}
	
	private void throwDiceAttack(int maxThrowNum){
		if(throwCntrAtt == 1){
				attackerResult.add(generateRandom());
				setDiceIcon(attackerResult.get(0), lblAttDice1);
				if(maxThrowNum == 1){AttackDice = false;}
		}
		else if(throwCntrAtt == 2){
				attackerResult.add(generateRandom());
				setDiceIcon(attackerResult.get(1), lblAttDice2);
				if(maxThrowNum == 2){AttackDice = false;}
		}
		else if(throwCntrAtt == maxThrowNum){
				AttackDice = false;
				attackerResult.add(generateRandom());
				setDiceIcon(attackerResult.get(2), lblAttDice3);
				// H�l�zati kommunkik�ci�
				GameState gs = new GameState();
				gs.state = 1;
				gs.msg = "T�mad�s alatt";
				gs.territories = motor.territories;
				gs.players = motor.players;
				gs.defenderResult = this.defenderResult;
				gs.attackerResult = this.attackerResult;
				motor.sendGameState(gs);
		}
	}
	
	private void throwDiceDef(int maxThrowNum){
		if(throwCntrDef == 1){
			defenderResult.add(generateRandom());
			setDiceIcon(defenderResult.get(0), lblDefDice1);
	}
	
		else if(throwCntrDef == maxThrowNum){
			defenderResult.add(generateRandom());		
			setDiceIcon(defenderResult.get(1), lblDefDice2);
			DefDice = false;
			//calculateWinner();
		}
	}
	//Gy�ztes sz�m�t�sa: bukott egys�ges kisz�m�t�sa �s JGUI-ba �ll�t�s
	public void calculateWinner(){ 
		if(!(attackerResult.isEmpty() & defenderResult.isEmpty())){
			Collections.sort(attackerResult);
			System.out.println(attackerResult);		
			Collections.sort(defenderResult);
			System.out.println(defenderResult);		
			int maxSizeAttack = attackerResult.size();
			int maxSizeDef = defenderResult.size();
			
			if(attackerResult.get(maxSizeAttack -1) - defenderResult.get(maxSizeDef -1) <= 0){
				jgui.setAttackerLostUnits(jgui.getAttackerLostUnits() + 1);
			}
			else{jgui.setDefenderLostUnits(jgui.getDefenderLostUnits() + 1);}
			
			if(maxSizeAttack -1 != 0 & (maxSizeDef -1 != 0)){
			if(attackerResult.get(maxSizeAttack - 1) - defenderResult.get(maxSizeDef - 1) <= 0){
				jgui.setAttackerLostUnits(jgui.getAttackerLostUnits() + 1);
			}
			else{jgui.setDefenderLostUnits(jgui.getDefenderLostUnits() + 1);}
			}
		}
		
    }
	//Attackscreen friss�t�se
	
	
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
		
		//Kock�k megjelen�t�se
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
							setAttackerMaxThrowNum();
							if(maxThrowNum == 0){AttackDice = false;}
							if(AttackDice){
								throwDiceAttack(maxThrowNum);
								throwCntrAtt += 1;
							}
						}
					

		});
		btnAttack.setBounds(153, 254, 133, 31);
		getContentPane().add(btnAttack);
		
		JButton btnEndAttack = new JButton("T�mad�s v�ge");
		btnEndAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblAttDice3.setIcon(Dice1icon);
				//TODO: vizsg�lni, hogy vannak-e dob�sok
				if(!(attackerResult.isEmpty() & defenderResult.isEmpty())){
				calculateWinner();
				motor.upDateUnitsAfterAttack();	
				}
				//Gy�ztes visszaad�sa
				//Territory tulajdonos�nak be�ll�t�sa
				
				jgui.setAttackEnded(true);
				
				//J�t�kos megszerzett ter�let�nek be�ll�t�sa
				//jgui.refreshMap();
				//System.out.println(jgui.getAttackerLostUnits());
				
				//Ablak bez�r�sa
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
				setDefenderMaxThrowNum();
				if(DefDice){
				throwDiceDef(maxThrowNum);
				throwCntrDef += 1;
				
				if(throwCntrAtt == 3 & throwCntrDef == 2){
					//calculate result
					resultCalculable = true;
				}
			}
			}
		});
		btnDefend.setBounds(463, 254, 133, 31);
		
		getContentPane().add(btnDefend);

	}
}
