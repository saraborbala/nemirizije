import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

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
		upperMenu.setBounds(0, 0, 112, 34);
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
		
		//Egységek mozgatás
		JMenu Move = new JMenu("Mozgatás");
		Move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//TODO: Egységmozgatáshoz szükséges pl üzenetek megjelenítése 
			}
		});
		upperMenu.add(Move);
		
		
		// Menu vége
		//------------------------------------------------------------------------------------
	
		JLabel lblActCountryPic = new JLabel("");
		lblActCountryPic.setBorder(null);
		lblActCountryPic.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblActCountryPic.setHorizontalAlignment(SwingConstants.LEFT);
		lblActCountryPic.setBounds(32, 438, 208, 224);
		Mainpanel.add(lblActCountryPic);
		
		JLabel lblActCntryName = new JLabel("");
		lblActCntryName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblActCntryName.setHorizontalAlignment(SwingConstants.LEFT);
		lblActCntryName.setBounds(32, 629, 208, 33);
		Mainpanel.add(lblActCntryName);
		

		//----------------------------------------------------------------------------------------------------------------
		//Countries: 
		
		JLabel lblNwtTerritory = new JLabel("North West Territory");
		lblNwtTerritory.setToolTipText("North West Territory");
		lblNwtTerritory.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/America/Northwest Territory.png")));
		lblNwtTerritory.setBounds(210, 78, 155, 76);
		Mainpanel.add(lblNwtTerritory);
		String NorthWestTerritory = "Northwest Territory";
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
		
		//Great Britain
		JLabel lblGreatBritain = new JLabel("");
		lblGreatBritain.setToolTipText("Great Britain");
		lblGreatBritain.setBounds(465, 206, 70, 68);
		Mainpanel.add(lblGreatBritain);
		String GreatBritain = "Great Britain";
		labels.put(GreatBritain, lblGreatBritain);
		
		//Iceland
		JLabel lblIceland = new JLabel("");
		lblIceland.setToolTipText("Iceland");
		lblIceland.setBounds(487, 153, 62, 42);
		Mainpanel.add(lblIceland);
		String Iceland = "Iceland";
		labels.put(Iceland, lblIceland);
		
		//Afganistan
		JLabel lblAfganistan = new JLabel("");
		lblAfganistan.setToolTipText("Afganistan");
		lblAfganistan.setBounds(716, 232, 92, 85);
		Mainpanel.add(lblAfganistan);
		String Afganistan = "Afganistan";
		labels.put(Afganistan, lblAfganistan);
		
		//Siberia
		JLabel lblSiberia = new JLabel("");
		lblSiberia.setToolTipText("Siberia");
		lblSiberia.setBounds(798, 78, 70, 112);
		Mainpanel.add(lblSiberia);
		String Siberia = "Siberia";
		labels.put(Siberia, lblSiberia);
		
		JLabel lblSiberia2 = new JLabel("");
		lblSiberia2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSiberia2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiberia2.setToolTipText("Siberia");
		lblSiberia2.setBounds(818, 159, 32, 76);
		Mainpanel.add(lblSiberia2);
		String Siberia2 = "Siberia2";
		labels.put(Siberia2, lblSiberia2);
		
		//Ural
		JLabel lblUral = new JLabel("");
		lblUral.setToolTipText("Ural");
		lblUral.setBounds(751, 124, 70, 128);
		Mainpanel.add(lblUral);
		String Ural = "Ural";
		labels.put(Ural, lblUral);
		
		//Russia
		JLabel lblRussia = new JLabel("");
		lblRussia.setToolTipText("Russia");
		lblRussia.setBounds(644, 109, 103, 208);
		Mainpanel.add(lblRussia);
		String Russia = "Russia";
		labels.put(Russia, lblRussia);
		
		//Scandinavia
		JLabel lblScandinavia = new JLabel("");
		lblScandinavia.setToolTipText("Scandinavia");
		lblScandinavia.setBounds(560, 106, 80, 104);
		Mainpanel.add(lblScandinavia);
		String Scandinavia = "Scandinavia";
		labels.put(Scandinavia, lblScandinavia);
		
		//Western Europe
		JLabel lblWesternEurope = new JLabel("");
		lblWesternEurope.setToolTipText("Western Europe");
		lblWesternEurope.setBounds(487, 285, 67, 76);
		Mainpanel.add(lblWesternEurope);
		String WesternEurope = "Western Europe";
		labels.put(WesternEurope, lblWesternEurope);
		
		//Northern Europe
		JLabel lblNorthernEurope = new JLabel("");
		lblNorthernEurope.setToolTipText("Northern Europe");
		lblNorthernEurope.setBounds(554, 215, 80, 76);
		Mainpanel.add(lblNorthernEurope);
		String NorthernEurope = "Northern Europe";
		labels.put(NorthernEurope, lblNorthernEurope);
		
		//Southern Europe
		JLabel lblSouthernEurope = new JLabel("");
		lblSouthernEurope.setToolTipText("Southern Europe");
		lblSouthernEurope.setBounds(564, 271, 70, 85);
		Mainpanel.add(lblSouthernEurope);
		String SouthernEurope = "Southern Europe";
		labels.put(SouthernEurope, lblSouthernEurope);
		
		//Middle East
		JLabel lblMiddleEast = new JLabel("");
		lblMiddleEast.setToolTipText("Middle East");
		lblMiddleEast.setBounds(644, 328, 112, 120);
		Mainpanel.add(lblMiddleEast);
		String MiddleEast = "Middle East";
		labels.put(MiddleEast, lblMiddleEast);
		
		//Siam
		JLabel lblSiam = new JLabel("");
		lblSiam.setToolTipText("Siam");
		lblSiam.setBounds(858, 373, 70, 85);
		Mainpanel.add(lblSiam);
		String Siam = "Siam";
		labels.put(Siam, lblSiam);
		
		//China
		JLabel lblChina1 = new JLabel("");
		lblChina1.setToolTipText("China");
		lblChina1.setBounds(818, 260, 32, 85);
		Mainpanel.add(lblChina1);
		String China = "China";
		labels.put(China, lblChina1);
		
		//India
		JLabel lblIndia = new JLabel("");
		lblIndia.setToolTipText("India");
		lblIndia.setBounds(766, 319, 90, 146);
		Mainpanel.add(lblIndia);
		String India = "India";
		labels.put(India, lblIndia);
		
		//Yakutsk
		JLabel lblYakutsk = new JLabel("");
		lblYakutsk.setToolTipText("Yakutsk");
		lblYakutsk.setBounds(864, 78, 70, 76);
		Mainpanel.add(lblYakutsk);
		String Yakutsk = "Yakutsk";
		labels.put(Yakutsk, lblYakutsk);
		
		//Kamchatka
		JLabel lblKamchatka = new JLabel("");
		lblKamchatka.setToolTipText("Kamchatka");
		lblKamchatka.setBounds(932, 78, 107, 128);
		Mainpanel.add(lblKamchatka);
		String Kamchatka = "Kamchatka";
		labels.put(Kamchatka, lblKamchatka);
		
		//Mongolia
		JLabel lblMongolia = new JLabel("");
		lblMongolia.setToolTipText("Mongolia");
		lblMongolia.setBounds(860, 232, 94, 59);
		Mainpanel.add(lblMongolia);
		String Mongolia = "Mongolia";
		labels.put(Mongolia, lblMongolia);
		
		//Japan
		JLabel lblJapan = new JLabel("");
		lblJapan.setToolTipText("Japan");
		lblJapan.setBounds(964, 215, 46, 112);
		Mainpanel.add(lblJapan);
		String Japan = "Japan";
		labels.put(Japan, lblJapan);
		
		//Irkutsk
		JLabel lblIrkutsk = new JLabel("");
		lblIrkutsk.setToolTipText("Irkutsk");
		lblIrkutsk.setBounds(854, 170, 53, 54);
		Mainpanel.add(lblIrkutsk);
		String Irkutsk = "Irkutsk";
		labels.put(Irkutsk, lblIrkutsk);
		
		
		JLabel lblChina2 = new JLabel("");
		lblChina2.setToolTipText("China");
		lblChina2.setBounds(818, 293, 126, 76);
		Mainpanel.add(lblChina2);
		String China2 = "China2";
		labels.put(China2, lblChina2);
		
		//Eastern Australia
		JLabel lblEasternAustralia = new JLabel("");
		lblEasternAustralia.setToolTipText("Eastern Australia");
		lblEasternAustralia.setBounds(950, 530, 89, 54);
		Mainpanel.add(lblEasternAustralia);
		String EasternAustralia = "Eastern Australia";
		labels.put(EasternAustralia, lblEasternAustralia);
		
		JLabel lblEasternAustralia2 = new JLabel("");
		lblEasternAustralia2.setToolTipText("Eastern Australia");
		lblEasternAustralia2.setBounds(989, 575, 46, 76);
		Mainpanel.add(lblEasternAustralia2);
		String EasternAustralia2 = "Eastern Australia2";
		labels.put(EasternAustralia2, lblEasternAustralia2);
		
		//Western Australia
		JLabel lblWesternAustralia = new JLabel("");
		lblWesternAustralia.setToolTipText("Western Australia");
		lblWesternAustralia.setBounds(885, 543, 102, 119);
		Mainpanel.add(lblWesternAustralia);
		String WesternAustralia = "Western Australia";
		labels.put(WesternAustralia, lblWesternAustralia);
		
		//New Guinea
		JLabel lblNewGuinea = new JLabel("");
		lblNewGuinea.setToolTipText("New Guinea");
		lblNewGuinea.setBounds(932, 463, 71, 45);
		Mainpanel.add(lblNewGuinea);
		String NewGuinea = "New Guinea";
		labels.put(NewGuinea, lblNewGuinea);
		
		//Indonezia
		JLabel lblIndonezia = new JLabel("");
		lblIndonezia.setToolTipText("Indonezia");
		lblIndonezia.setBounds(836, 469, 92, 76);
		Mainpanel.add(lblIndonezia);
		String Indonezia = "Indonezia";
		labels.put(Indonezia, lblIndonezia);
		
		//Országok vége
		//----------------------------------------------------------------------------------------------------------------
		
		//Térkép
		JLabel lblMap = new JLabel("");

		lblMap.setToolTipText("");
		lblMap.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/Full_map/map_resized_all_layer_AILL_resized900606.png")));
		lblMap.setBounds(139, 56, 900, 606);
		Mainpanel.add(lblMap);
		//Térkép vége
		
		JLabel lblAktulisOrszg = new JLabel("");
		lblAktulisOrszg.setHorizontalAlignment(SwingConstants.LEFT);
		lblAktulisOrszg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAktulisOrszg.setBounds(32, 415, 208, 33);
		Mainpanel.add(lblAktulisOrszg);
		
		JPanel PlayerStatus = new JPanel();
		PlayerStatus.setBounds(1098, 10, 239, 555);
		Mainpanel.add(PlayerStatus);
		PlayerStatus.setLayout(null);
		
		JLabel lblPlayerInd1 = new JLabel("New label");
		//TODO: Keret színének beállítása (második argumentum)- mindhárom kerethez
		lblPlayerInd1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerInd1.setBounds(10, 5, 219, 173);
		PlayerStatus.add(lblPlayerInd1);
		
		JLabel lblPlayerInd2 = new JLabel("New label");
		lblPlayerInd2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerInd2.setBounds(10, 189, 219, 173);
		PlayerStatus.add(lblPlayerInd2);
		
		JLabel lblPlayerInd3 = new JLabel("New label");
		lblPlayerInd3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerInd3.setBounds(10, 373, 219, 173);
		PlayerStatus.add(lblPlayerInd3);
		
				
		//Listenerek hozzáadása az országok labeljeihez
				
		for(Map.Entry<String, JLabel> entry : labels.entrySet()) {
		    String key = entry.getKey();
		    JLabel value = entry.getValue();
		    String path = "/Resized/"+ key.toString() +".png";
		    value.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblAktulisOrszg.setText("Aktuálisan kijelölt ország:");
		    		lblActCntryName.setText(value.getToolTipText());
		    		lblActCountryPic.setIcon(new ImageIcon(JGUI.class.getResource(path)));
		    		
					repaint();
		    	}
				@Override
				public void mouseExited(MouseEvent arg0) {
					lblActCountryPic.setText("");
					lblAktulisOrszg.setText("");
					lblActCountryPic.setIcon(null);
					lblActCntryName.setText("");
				}
		    });
		    // do what you have to do here

		}
	}
}
