import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;




@SuppressWarnings("serial")
public class NewPlayer extends JFrame {
	private JPanel contentPane;
	private JTextField textField_Name;

	private Motor motor;
	String playerColorStr;
	Color playerColor;
	public NewPlayer(Motor motor) {
		this.motor = motor;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPlayer = new JLabel("\u00DAj j\u00E1t\u00E9kos hozz\u00E1ad\u00E1sa");
		lblNewPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPlayer.setBounds(118, 11, 224, 26);
		contentPane.add(lblNewPlayer);
		
		JPanel Details = new JPanel();
		Details.setBounds(10, 62, 455, 180);
		contentPane.add(Details);
		Details.setLayout(null);

		textField_Name = new JTextField();
		textField_Name.setToolTipText("G\u00E9peld be a k\u00EDv\u00E1nt nevet! :)");
		textField_Name.setBounds(253, 12, 136, 34);
		Details.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblName = new JLabel("N\u00E9v:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(108, 11, 98, 32);
		Details.add(lblName);
		
		JLabel lblColor = new JLabel("Sz\u00EDn:");
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblColor.setBounds(108, 63, 98, 27);
		Details.add(lblColor);
		
		JComboBox comboBoxColor = new JComboBox();
		comboBoxColor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				playerColorStr = comboBoxColor.getSelectedItem().toString();
				//A kiv�lasztott mez�nek megfel�l�en contentPane �s details label h�tter�nek �ll�t�sa
				//playerColor-ba a sz�n be�ll�tva
				switch(playerColorStr){
					case("Piros") 	: contentPane.setBackground(Color.RED.darker()); playerColor = Color.RED.darker(); break;
					case("Z�ld") 	: contentPane.setBackground(Color.GREEN.darker()); playerColor = Color.GREEN; break;
					case("Narancs")	: contentPane.setBackground(Color.ORANGE); playerColor = Color.ORANGE; break;
					case("K�k") 	: contentPane.setBackground(Color.CYAN); playerColor = Color.CYAN; break;
					case("Magenta") : contentPane.setBackground(Color.MAGENTA.darker()); playerColor = Color.MAGENTA.darker(); break;			
				}
				Details.setBackground(contentPane.getBackground());
			}
		});
 
		comboBoxColor.setForeground(java.awt.Color.BLACK);
		comboBoxColor.setBorder(UIManager.getBorder("ComboBox.border"));
		comboBoxColor.setModel(new DefaultComboBoxModel(new String[] {"Piros", "Z�ld", "K�k", "Narancs", "Magenta"}));
		
        comboBoxColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxColor.setBounds(253, 64, 137, 25);
		Details.add(comboBoxColor);
		
		JLabel lblClientServer = new JLabel("Kliens vagy szerver:");
		lblClientServer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClientServer.setBounds(91, 101, 136, 34);
		Details.add(lblClientServer);
		
		//Kliens vagy szerver v�laszthat�s�g
		JRadioButton rdbtnServer = new JRadioButton("Szerver");
		rdbtnServer.setEnabled(true);
		rdbtnServer.setOpaque(false);
		rdbtnServer.setBounds(338, 109, 90, 23);
		Details.add(rdbtnServer);
		rdbtnServer.setBackground(contentPane.getBackground());
		
		//Szerver ne legyen v�laszthat�, ha m�r van szerver
		//TODO van-e szerver felt�tel? 


		boolean isServerAvailable = false;

		/*boolean isServerAvailable = true;

		/*boolean isServerAvailable = true;

>>>>>>> origin/master
		if(isServerAvailable){
			rdbtnServer.setEnabled(false);
		}*/
		
		JRadioButton rdbtnClient = new JRadioButton("Kliens");
		rdbtnClient.setOpaque(false);
		rdbtnClient.setBounds(253, 109, 83, 23);
		Details.add(rdbtnClient);
		rdbtnClient.setBackground(contentPane.getBackground());
		
		//Radio gombok k�z�l csak az egyik legyen v�laszthat�
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnServer);
		group.add(rdbtnClient);
				
		JButton btnNewGame = new JButton("Kezd\u0151dj\u00F6n a harc!");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Funkci�k implement�l�sa TODO
				
				//Kliens vagy szerver? 
				
				if(rdbtnServer.isSelected()){
					motor.startServer();
					//jgui.actPlayer = motor.players.get(0);
				}
				if(rdbtnClient.isSelected()){
					 motor.startClient();
					 //jgui. = motor.players.get(1);
					 
				}
				
				//�j j�t�kos l�trehoz�sa n�v alapj�n
				//String newPlayerName = textField_Name.getText();
				//Motor.addPlayer(newPlayerName);
				/*int playerNum = Motor.players.size();
				String playerName = textField_Name.getText();				
				Player newPlayer = new Player(playerName, playerNum+1);*/
				
					
				
				//Csak 2 j�t�kos adhat� �sszesen hozz�
				/*if(playerNum < 2){
				motor.players.add(newPlayer);
				}*/
				
				// J�t�kos sz�n�nek be�ll�t�sa -> PlayerColor v�ltoz�ban a sz�n
				/*for(Player player : Motor.players){
					if(player.getName() == playerName){
						player.setColor(playerColor);
					}
				}*/
				//Bez�r�s
				dispose();			
			}
		});
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewGame.setBounds(147, 253, 172, 34);
		contentPane.add(btnNewGame);
	}
}
