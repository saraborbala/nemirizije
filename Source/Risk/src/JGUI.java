import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JGUI extends JFrame {
	
	JDesktopPane desktopPane = new JDesktopPane();
	
	Map<String, JLabel> labels = new HashMap<String,JLabel>();
	
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JGUI frame = new JGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Map a listenerekhez

	/**
	 * Create the frame.
	 */
	public JGUI() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1363, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		
		//Main panel
		JPanel Mainpanel = new JPanel();
		Mainpanel.setBorder(UIManager.getBorder("MenuBar.border"));
		contentPane.add(Mainpanel, BorderLayout.CENTER);
		Mainpanel.setLayout(null);
		
		//------------------------------------------------------------------------------------
		//Menüsor
		JPanel MenuBar = new JPanel();
		MenuBar.setBounds(21, 10, 344, 45);
		Mainpanel.add(MenuBar);
		MenuBar.setLayout(null);
		
		JMenuBar upperMenu = new JMenuBar();
		upperMenu.setBorderPainted(false);
		upperMenu.setBorder(UIManager.getBorder("InternalFrame.border"));
		upperMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		upperMenu.setMargin(new Insets(3, 3, 3, 3));
		upperMenu.setBounds(0, 0, 59, 34);
		MenuBar.add(upperMenu);
		
		JMenu Menu = new JMenu("Menu");
		
		//Elemek hozzáadása
		JMenuItem NewPlayer = new JMenuItem("Új játékos");
		NewPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPlayer frame = new NewPlayer();
				frame.setBounds(100, 100, 480, 285);
				frame.setLocation(new Point(300,300));
				frame.setResizable(false);
				//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				frame.setVisible(true);	
			}
		});
		JMenuItem NewGame = new JMenuItem("Új játék");
		JMenuItem Attack = new JMenuItem("Támadás");
		Attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttackScreen frame = new AttackScreen();
				frame.setBounds(32, 62, 765, 325);
				frame.setLocation(new Point(300,300));
				frame.setResizable(false);
				//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				frame.setVisible(true);	
			}
		});

		JMenuItem Exit = new JMenuItem("Kilépés");
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		upperMenu.add(Menu);
		Menu.add(NewPlayer);
		Menu.add(NewGame);
		Menu.add(Attack);
		Menu.add(Exit);
		
		// Menu vége
		//------------------------------------------------------------------------------------
	
		JLabel lblActCountryDet = new JLabel("");
		lblActCountryDet.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblActCountryDet.setHorizontalAlignment(SwingConstants.LEFT);
		lblActCountryDet.setBounds(661, 10, 261, 33);
		Mainpanel.add(lblActCountryDet);
		

		//----------------------------------------------------------------------------------------------------------------
		//Countries: 
		
		JLabel lblNwtTerritory = new JLabel("North West Territory");
		lblNwtTerritory.setToolTipText("North West Territory");
		lblNwtTerritory.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/America/Northwest Territory.png")));
		lblNwtTerritory.setBounds(210, 78, 155, 76);
		Mainpanel.add(lblNwtTerritory);
		String NorthWestTerritory = "North West Territory";
		labels.put(NorthWestTerritory, lblNwtTerritory);
		
		//North Africa
		JLabel lblNorthAfrica = new JLabel("");
		lblNorthAfrica.setToolTipText("North Africa");
		lblNorthAfrica.setBounds(512, 382, 80, 112);
		Mainpanel.add(lblNorthAfrica);
		String NorthAfrica = "North Africa";
		labels.put(NorthAfrica, lblNorthAfrica);
		
		//Egypt
		JLabel lblEgypt = new JLabel("");
		lblEgypt.setToolTipText("Egypt");
		lblEgypt.setBounds(585, 384, 80, 39);
		Mainpanel.add(lblEgypt);
		String Egypt = "Egypt";
		labels.put(Egypt, lblEgypt);
		
		//Congo
		JLabel lblCongo = new JLabel("");
		lblCongo.setToolTipText("Congo");
		lblCongo.setBounds(595, 500, 70, 39);
		Mainpanel.add(lblCongo);
		String Congo = "Congo";
		labels.put(Congo, lblCongo);
		
		JLabel lblSouthAfrica = new JLabel("");
		lblSouthAfrica.setToolTipText("South Africa");
		lblSouthAfrica.setBounds(595, 561, 92, 101);
		Mainpanel.add(lblSouthAfrica);
		String SouthAfrica = "South Africa";
		labels.put(SouthAfrica, lblSouthAfrica);
		
		//East Africa
		JLabel lblEastAfrica = new JLabel("");
		lblEastAfrica.setToolTipText("East Africa");
		lblEastAfrica.setBounds(646, 438, 70, 112);
		Mainpanel.add(lblEastAfrica);
		String EastAfrica = "East Africa";
		labels.put(EastAfrica, lblEastAfrica);
		
		//Madagascar
		JLabel lblMadagascar = new JLabel("");
		lblMadagascar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblActCountryDet.setText(lblMadagascar.getToolTipText());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblActCountryDet.setText("");
			}
		});
		lblMadagascar.setToolTipText("Madagascar");
		lblMadagascar.setBounds(704, 561, 40, 85);
		Mainpanel.add(lblMadagascar);
		String Madagascar = "Madagascar";
		labels.put(Madagascar, lblMadagascar);
				
		// Alberta
		JLabel lblAlberta = new JLabel("");
				
				//Kör rajzolás
				//public void paintComponent(Graphics g) 
				//	{						
				//		super.paintComponent(g);       
						//g.drawOval(10, 10, 20, 20);
				//		g.fillOval(34, 38, 20, 20);
						//TODO Set the color based on the actual players color!
						//g.setColor(c);
				//	}
			
		
		lblAlberta.setIcon(null);
		lblAlberta.setToolTipText("Alberta");
		lblAlberta.setBounds(220, 142, 89, 76);
		Mainpanel.add(lblAlberta);
		String Alberta = "Alberta";
		labels.put(Alberta, lblAlberta);
		
		// Eastern US
		JLabel lblEasternUS = new JLabel("");
		lblEasternUS.setToolTipText("Eastern US");
		lblEasternUS.setBounds(301, 249, 85, 68);
		Mainpanel.add(lblEasternUS);
		String EasternUS = "Eastern US";
		labels.put(EasternUS, lblEasternUS);
		
		//Brazil
		JLabel lblBrazil = new JLabel("");
		lblBrazil.setToolTipText("Brazil");
		lblBrazil.setBounds(368, 414, 102, 119);
		Mainpanel.add(lblBrazil);
		String Brazil = "Brazil";
		labels.put(Brazil, lblBrazil);
		
		//Venezuela		
		JLabel lblVenezuela = new JLabel("");
		lblVenezuela.setToolTipText("Venezuela");
		lblVenezuela.setBounds(301, 373, 112, 54);
		Mainpanel.add(lblVenezuela);
		String Venezuela = "Venezuela";
		labels.put(Venezuela, lblVenezuela);
		
		//Argentina
		JLabel lblArgentina = new JLabel("");
		lblArgentina.setToolTipText("Argentina");
		lblArgentina.setBounds(333, 516, 70, 146);
		Mainpanel.add(lblArgentina);
		String Argentina = "Argentina";
		labels.put(Argentina, lblArgentina);
		
		//Peru
		JLabel lblPeru = new JLabel("");
		lblPeru.setToolTipText("Peru");
		lblPeru.setBounds(301, 438, 85, 85);
		Mainpanel.add(lblPeru);
		String Peru = "Peru";
		labels.put(Peru, lblPeru);
				
		//Central America
		JLabel lblCentralAmerica = new JLabel("");
		lblCentralAmerica.setToolTipText("Central America");
		lblCentralAmerica.setBounds(224, 305, 85, 76);
		Mainpanel.add(lblCentralAmerica);
		String CentralAmerica = "Central America";
		labels.put(CentralAmerica, lblCentralAmerica);
		
		//Quebec
		JLabel lblQuebec = new JLabel("");
		lblQuebec.setToolTipText("Quebec");
		lblQuebec.setBounds(358, 153, 70, 76);
		Mainpanel.add(lblQuebec);
		String Quebec = "Quebec";
		labels.put(Quebec, lblQuebec);
		
		//Western US
		JLabel lblWesternUS = new JLabel("");
		lblWesternUS.setIcon(null);
		lblWesternUS.setToolTipText("Western US");
		lblWesternUS.setBounds(226, 206, 94, 101);
		Mainpanel.add(lblWesternUS);
		String WesternUS = "Western US";
		labels.put(WesternUS, lblWesternUS);
		
		//Greenland
		JLabel lblGreenland = new JLabel("");
		lblGreenland.setToolTipText("Greeland");
		lblGreenland.setForeground(new Color(255, 255, 255));
		lblGreenland.setBounds(375, 50, 135, 95);
		Mainpanel.add(lblGreenland);
		String Greeland = "Greenland";
		labels.put(Greeland, lblGreenland);
		
		//Ontario
		JLabel lblOntario = new JLabel("");
		lblOntario.setToolTipText("Ontario");
		lblOntario.setBounds(301, 153, 53, 76);
		Mainpanel.add(lblOntario);
		String Ontario = "Ontario";
		labels.put(Ontario, lblOntario);
				
		//Alasca
		JLabel lblAlasca = new JLabel("");
		lblAlasca.setToolTipText("Alasca");			
		lblAlasca.setIcon(null);
		lblAlasca.setBounds(139, 106, 89, 89);
		Mainpanel.add(lblAlasca);
		String Alasca = "Alasca";
		labels.put(Alasca, lblAlasca);
		
		//Országok vége
		//----------------------------------------------------------------------------------------------------------------
		
		//Térkép
		JLabel lblMap = new JLabel("");

		lblMap.setToolTipText("");
		lblMap.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/Full_map/map_resized_all_layer.png")));
		lblMap.setBounds(139, 56, 900, 606);
		Mainpanel.add(lblMap);
		//Térkép vége
		
		JLabel lblAktulisOrszg = new JLabel("Aktu\u00E1lisan kijel\u00F6lt orsz\u00E1g:");
		lblAktulisOrszg.setHorizontalAlignment(SwingConstants.CENTER);
		lblAktulisOrszg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAktulisOrszg.setBounds(465, 10, 191, 33);
		Mainpanel.add(lblAktulisOrszg);
		
		Panel PlayerStatus = new Panel();
		PlayerStatus.setBounds(1099, 10, 228, 523);
		Mainpanel.add(PlayerStatus);
		
				
		//Listenerek hozzáadása az országok labeljeihez
				
		for(Map.Entry<String, JLabel> entry : labels.entrySet()) {
		    String key = entry.getKey();
		    JLabel value = entry.getValue();
		    value.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblActCountryDet.setText(value.getToolTipText());
		    	}
				@Override
				public void mouseExited(MouseEvent arg0) {
					lblActCountryDet.setText("");
				}
		    });
		    // do what you have to do here

		}
	}
	
	
}
