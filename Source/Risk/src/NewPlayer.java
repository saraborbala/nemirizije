import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPlayer frame = new NewPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewPlayer() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 285);
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
		Details.setBounds(48, 62, 366, 116);
		contentPane.add(Details);
		Details.setLayout(null);
		
		textField_Name = new JTextField();
		textField_Name.setToolTipText("G\u00E9peld be a k\u00EDv\u00E1nt nevet! :)");
		textField_Name.setBounds(160, 12, 136, 34);
		Details.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		lblNv.setHorizontalAlignment(SwingConstants.CENTER);
		lblNv.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNv.setBounds(58, 11, 98, 32);
		Details.add(lblNv);
		
		JLabel lblSzn = new JLabel("Sz\u00EDn:");
		lblSzn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSzn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSzn.setBounds(58, 63, 98, 27);
		Details.add(lblSzn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(java.awt.Color.BLACK);
		comboBox.setBorder(UIManager.getBorder("ComboBox.border"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Piros", "Z\u00F6ld", "K\u00E9k", "Narancs", "Lila"}));
		
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(160, 64, 137, 25);
		
		Details.add(comboBox);
		
		JButton btnNewGame = new JButton("Kezd\u0151dj\u00F6n a harc!");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Funkciók implementálása TODO
				
				//Bezárás
				dispose();
			}
		});
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewGame.setBounds(147, 199, 172, 34);
		contentPane.add(btnNewGame);

	}
}
