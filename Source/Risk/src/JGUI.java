import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.JButton;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

@SuppressWarnings("serial")
public class JGUI extends JFrame {
	
	private Point p1 = new Point(0, 0); 
	public int teszt = 1;
	JDesktopPane desktopPane = new JDesktopPane();
	
	Map<String, JLabel> labels = new HashMap<String,JLabel>();
	Map<String, JLabel> circles = new HashMap<String,JLabel>();
	Map<String, JLabel> toBeRefreshed = new HashMap<String,JLabel>();
	//TODO: hozz�adni a label�ket az orsz�gok k�zep�re
	
	public Map<String, JLabel> getLabels() {
        return labels;
    }
	public Map<String, JLabel> getCircles() {
        return circles;
    }
	
	public void refreshPlayerName(){
		for(Map.Entry<String, JLabel> toBeRefreshed : toBeRefreshed.entrySet()) {
		    //String labelkey = toBeRefreshed.getKey();
		    JLabel labelvalue = toBeRefreshed.getValue();
		    
		    if(labelvalue.getName().equals("lblPlayerName1")){
		    	labelvalue.setText(Motor.players.elementAt(0).getName());
		    }
		    if(labelvalue.getName().equals("lblPlayerName2")){
		    	labelvalue.setText(Motor.players.elementAt(0).getName());
		    }
		}
	}
	
	private JPanel contentPane;
		
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JGUI frame = new JGUI();
					frame.setVisible(true);						
					frame.repaint();
					frame.revalidate();
					frame.setTitle("Rizik�");
					NewPlayer newPlayerframe = new NewPlayer();
					newPlayerframe.setVisible(true);
					newPlayerframe.setTitle("�j j�t�kos hozz�ad�sa");
					
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
			}
		});
	}*/


	/**
	 * Create the frame.
	 */
	public JGUI() {
			
		//Teszt j�t�kos
		Player newPlayer1 = new Player("tesztname", 0);
		//Player newPlayer2 = new Player("tesztname2", 1);
		
		Territory newTerritory1 = new Territory(1, "Alasca", 2, 4, 5);
		Territory newTerritory2 = new Territory(1, "Congo", 2, 4, 5);
		
		newTerritory1.setPlayer(newPlayer1);
		//newTerritory2.setPlayer(newPlayer2);
		
		Motor.territories.add(newTerritory1);
		Motor.territories.add(newTerritory2);
		
		newPlayer1.setOccTerritory(newTerritory1);
		newPlayer1.setColor(Color.GREEN);
		
		newPlayer1.addArmies(0);
		//newPlayer2.addArmies(10);
		
		Motor.players.add(newPlayer1);
		
		//newPlayer2.setOccTerritory(newTerritory2);
		//newPlayer2.setColor(Color.RED);
		//Motor.players.add(newPlayer2);	
		
		
		
		//-------------		
		
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
		
		
		
		//Indik�tor a j�t�kosok egys�geinek sz�ma f�ggv�ny�ben
		JLabel Player1Units = new JLabel("");
		Player1Units.setOpaque(true);
		Player1Units.setForeground(Color.RED);
		//Player1Units.setBackground(Color.RED);
		//Player1Units.setBounds(414, 10, 150, 20);
		Mainpanel.add(Player1Units);
		
		JLabel Player2Units = new JLabel("");
		//Player2Units.setBackground(Color.BLUE);
		Player2Units.setOpaque(true);
		//Player2Units.setBounds(564, 10, 150, 20);
		Mainpanel.add(Player2Units);
		
		//------------------------------------------------------------------------------------
		//Men�sor
		JPanel MenuBar = new JPanel();
		MenuBar.setBounds(21, 10, 344, 45);
		Mainpanel.add(MenuBar);
		MenuBar.setLayout(null);
		
		JMenuBar upperMenu = new JMenuBar();
		upperMenu.setBorderPainted(false);
		upperMenu.setBorder(UIManager.getBorder("InternalFrame.border"));
		upperMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		upperMenu.setMargin(new Insets(3, 3, 3, 3));
		upperMenu.setBounds(0, 0, 167, 34);
		MenuBar.add(upperMenu);
		
		JMenu Menu = new JMenu("Menu");
		
		//Elemek hozz�ad�sa
		JMenuItem NewPlayer = new JMenuItem("�j j�t�kos");
		NewPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPlayer NewPlayerFrame = new NewPlayer();
				NewPlayerFrame.setBounds(100, 100, 481, 338);
				NewPlayerFrame.setLocation(new Point(300,300));
				NewPlayerFrame.setResizable(false);
				//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				NewPlayerFrame.setVisible(true);				
			}
		});
		
		//
		JMenuItem NewGame = new JMenuItem("�j j�t�k");
		//TODO: Megoldand� feladatok: ter�letek sz�toszt�sa j�t�kosok k�z�tt, random. 
		//�j j�t�k kezd�se: kor�bbit reseteli
		
		JMenuItem Attack = new JMenuItem("T�mad�s");
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
		

		JMenuItem Exit = new JMenuItem("Kil�p�s");
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
		
		//Egys�gek mozgat�s
		JMenu Move = new JMenu("Mozgat�s");
		Move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//TODO: Egys�gmozgat�shoz sz�ks�ges pl �zenetek megjelen�t�se 
			}
		});
		upperMenu.add(Move);
		
		JMenu AttackMenu = new JMenu("T�mad�s");	
		AttackMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AttackScreen frame = new AttackScreen();
				frame.setBounds(32, 62, 765, 325);
				frame.setLocation(new Point(300,300));
				frame.setResizable(false);
				//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				frame.setVisible(true);	
			}
		});
		upperMenu.add(AttackMenu);
		
		// Menu v�ge
		//------------------------------------------------------------------------------------
		//Felhaszn�lt v�ltoz�k
		Color actualColor = Color.BLACK;
		String actualColorStr;
		 
		//Orsz�gok nevei
		
		String NorthAfrica = "North Africa";
		String NorthWestTerritory = "Northwest Territory";
		String Egypt = "Egypt";
		String SouthAfrica = "South Africa";
		String EastAfrica = "East Africa";
		String Madagascar = "Madagascar";
		String Alberta = "Alberta";
		String EasternUS = "Eastern US";
		String Brazil = "Brazil";
		String Venezuela = "Venezuela";
		String Argentina = "Argentina";
		String Peru = "Peru";
		String CentralAmerica = "Central America";
		String Quebec = "Quebec";
		String WesternUS = "Western US";
		String Greeland = "Greenland";
		String Ontario = "Ontario";
		String Alasca = "Alasca";
		String GreatBritain = "Great Britain";
		String Iceland = "Iceland";
		String Afganistan = "Afganistan";
		String Siberia = "Siberia";
		String Siberia2 = "Siberia2";
		String Ural = "Ural";
		String Russia = "Russia";
		String Scandinavia = "Scandinavia";
		String WesternEurope = "Western Europe";
		String NorthernEurope = "Northern Europe";
		String SouthernEurope = "Southern Europe";
		String MiddleEast = "Middle East";
		String Siam = "Siam";
		String China = "China";
		String India = "India";
		String Yakutsk = "Yakutsk";
		String Kamchatka = "Kamchatka";
		String Mongolia = "Mongolia";
		String Japan = "Japan";
		String Irkutsk = "Irkutsk";
		String China2 = "China2";
		String EasternAustralia = "Eastern Australia";
		String EasternAustralia2 = "Eastern Australia2";
		String WesternAustralia = "Western Australia";
		String NewGuinea = "New Guinea";
		String Indonezia = "Indonezia";
		//String Canada = "Canada";
		String Congo = "Congo";
		
		//--Orsz�gn�v v�ge
	
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
		//Tulajdonos indik�torok az orsz�gokhoz
		//F�panel
		JPanel MapPanel = new JPanel();
		MapPanel.setOpaque(false);
		MapPanel.setBounds(137, 58, 925, 604);
		Mainpanel.add(MapPanel);
		MapPanel.setLayout(null);
		//Alasca indik�tor
		JLabel lblAlascaInd = new JLabel("");
		lblAlascaInd.setBounds(33, 63, 30, 30);
		MapPanel.add(lblAlascaInd);
		circles.put(Alasca, lblAlascaInd);
		
		JLabel lblNWTInd = new JLabel("");
		lblNWTInd.setBounds(118, 63, 30, 30);
		MapPanel.add(lblNWTInd);
		circles.put(NorthWestTerritory, lblNWTInd);
		
		JLabel lblAlbertaInd = new JLabel("");
		lblAlbertaInd.setBounds(110, 114, 30, 30);
		MapPanel.add(lblAlbertaInd);
		circles.put(Alberta, lblAlbertaInd);
		
		JLabel lblWestUSInd = new JLabel("");
		lblWestUSInd.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/blue_dot.PNG")));
		lblWestUSInd.setBounds(118, 182, 30, 30);	
		//TODO: Mapben leimplement�lni, ez a pilot
		lblWestUSInd.setText("25");
		lblWestUSInd.setHorizontalTextPosition(lblWestUSInd.CENTER);
		MapPanel.add(lblWestUSInd);
		circles.put(WesternUS, lblWestUSInd);
		
		JLabel lblEastUS = new JLabel("");
		lblEastUS.setBounds(186, 206, 30, 30);
		MapPanel.add(lblEastUS);
		circles.put(EasternUS, lblEastUS);
		
		JLabel lblCentralAmInd = new JLabel("");
		lblCentralAmInd.setBounds(118, 268, 30, 30);
		MapPanel.add(lblCentralAmInd);
		circles.put(CentralAmerica, lblCentralAmInd);
		
		JLabel lblOntarioInd = new JLabel("");
		lblOntarioInd.setBounds(174, 124, 30, 30);
		MapPanel.add(lblOntarioInd);
		circles.put(Ontario, lblOntarioInd);
		
		JLabel lblQuebecInd = new JLabel("");
		lblQuebecInd.setBounds(242, 124, 30, 30);
		MapPanel.add(lblQuebecInd);
		circles.put(Quebec, lblQuebecInd);
		
		JLabel lblGreenlandInd = new JLabel("");
		lblGreenlandInd.setBounds(298, 28, 30, 30);
		MapPanel.add(lblGreenlandInd);
		circles.put(Greeland, lblGreenlandInd);
		
		JLabel lblIcelandInd = new JLabel("");
		lblIcelandInd.setBounds(368, 98, 30, 30);
		MapPanel.add(lblIcelandInd);
		circles.put(Iceland, lblIcelandInd);
		
		JLabel lblGreatBritainInd = new JLabel("");
		lblGreatBritainInd.setBounds(353, 182, 30, 30);
		MapPanel.add(lblGreatBritainInd);
		circles.put(GreatBritain, lblGreatBritainInd);
		
		JLabel lblWesternEUInd = new JLabel("");
		lblWesternEUInd.setBounds(368, 268, 30, 30);
		MapPanel.add(lblWesternEUInd);
		circles.put(WesternUS, lblWesternEUInd);
		
		JLabel lblScandinaviaInd = new JLabel("");
		lblScandinaviaInd.setBounds(452, 84, 30, 30);
		MapPanel.add(lblScandinaviaInd);
		circles.put(Scandinavia, lblScandinaviaInd);
		
		JLabel lblRussiaInd = new JLabel("");
		lblRussiaInd.setBounds(535, 135, 30, 30);
		MapPanel.add(lblRussiaInd);
		circles.put(Russia, lblRussiaInd);
		
		JLabel lblNorthernEUInd = new JLabel("");
		lblNorthernEUInd.setBounds(445, 184, 30, 30);
		MapPanel.add(lblNorthernEUInd);
		circles.put(NorthernEurope, lblNorthernEUInd);
		
		JLabel lblSouthernEUInd = new JLabel("");
		lblSouthernEUInd.setBounds(452, 243, 30, 30);
		MapPanel.add(lblSouthernEUInd);
		circles.put(SouthernEurope, lblSouthernEUInd);
		
		JLabel lblVenezuelaInd = new JLabel("");
		lblVenezuelaInd.setBounds(186, 323, 30, 30);
		MapPanel.add(lblVenezuelaInd);
		circles.put(Venezuela, lblVenezuelaInd);
		
		JLabel lblBrazilInd = new JLabel("");
		lblBrazilInd.setBounds(264, 384, 30, 30);
		MapPanel.add(lblBrazilInd);
		circles.put(Brazil, lblBrazilInd);
		
		JLabel lblPeruInd = new JLabel("");
		lblPeruInd.setBounds(198, 410, 30, 30);
		MapPanel.add(lblPeruInd);
		circles.put(Peru, lblPeruInd);
		
		JLabel lblArgentinaInd = new JLabel("");
		lblArgentinaInd.setBounds(212, 491, 30, 30);
		MapPanel.add(lblArgentinaInd);
		circles.put(Argentina, lblArgentinaInd);
		
		JLabel lblNorthAfricaInd = new JLabel("");
		lblNorthAfricaInd.setBounds(405, 364, 30, 30);
		MapPanel.add(lblNorthAfricaInd);
		circles.put(NorthAfrica, lblNorthAfricaInd);
		
		JLabel lblCongoInd = new JLabel("");
		lblCongoInd.setBounds(483, 445, 30, 30);
		MapPanel.add(lblCongoInd);
		circles.put(Congo, lblCongoInd);
		
		JLabel lblSouthAfricaInd = new JLabel("");
		lblSouthAfricaInd.setBounds(483, 525, 30, 30);
		MapPanel.add(lblSouthAfricaInd);
		circles.put(SouthAfrica, lblSouthAfricaInd);
		
		JLabel lblMadagascarInd = new JLabel("");
		lblMadagascarInd.setBounds(566, 535, 30, 30);
		MapPanel.add(lblMadagascarInd);
		circles.put(Madagascar, lblMadagascarInd);
		
		JLabel lblEastAfricaInd = new JLabel("");
		lblEastAfricaInd.setBounds(515, 394, 30, 30);
		MapPanel.add(lblEastAfricaInd);
		circles.put(EastAfrica, lblEastAfricaInd);
		
		JLabel lblEgyptInd = new JLabel("");
		lblEgyptInd.setBounds(483, 333, 30, 30);
		MapPanel.add(lblEgyptInd);
		circles.put(Egypt, lblEgyptInd);
		
		JLabel lblMiddleEastInd = new JLabel("");
		lblMiddleEastInd.setBounds(550, 293, 30, 30);
		MapPanel.add(lblMiddleEastInd);
		circles.put(MiddleEast, lblMiddleEastInd);
		
		JLabel lblIndiaInd = new JLabel("");
		lblIndiaInd.setBounds(661, 293, 30, 30);
		MapPanel.add(lblIndiaInd);
		circles.put(India, lblIndiaInd);
		
		JLabel lblAfganistanInd = new JLabel("");
		lblAfganistanInd.setBounds(612, 206, 30, 30);
		MapPanel.add(lblAfganistanInd);
		circles.put(Afganistan, lblAfganistanInd);
		
		JLabel lblUralInd = new JLabel("");
		lblUralInd.setBounds(627, 114, 30, 30);
		MapPanel.add(lblUralInd);
		circles.put(Ural, lblUralInd);
		
		JLabel lblChinaInd = new JLabel("");
		lblChinaInd.setBounds(724, 253, 30, 30);
		MapPanel.add(lblChinaInd);
		circles.put(China, lblChinaInd);
		
		JLabel lblSiamInd = new JLabel("");
		lblSiamInd.setBounds(739, 323, 30, 30);
		MapPanel.add(lblSiamInd);
		circles.put(Siam, lblSiamInd);
		
		JLabel lblSiberiaInd = new JLabel("");
		lblSiberiaInd.setBounds(676, 63, 30, 30);
		MapPanel.add(lblSiberiaInd);
		circles.put(Siberia, lblSiberiaInd);
		
		JLabel lblYakutskInd = new JLabel("");
		lblYakutskInd.setBounds(749, 43, 30, 30);
		MapPanel.add(lblYakutskInd);
		circles.put(Yakutsk, lblYakutskInd);
		
		JLabel lblIrkutskInd = new JLabel("");
		lblIrkutskInd.setBounds(739, 124, 30, 30);
		MapPanel.add(lblIrkutskInd);
		circles.put(Irkutsk, lblIrkutskInd);
		
		JLabel lblMongoliaInd = new JLabel("");
		lblMongoliaInd.setBounds(749, 190, 30, 30);
		MapPanel.add(lblMongoliaInd);
		circles.put(Mongolia, lblMongoliaInd);
		
		JLabel lblKamchatkaInd = new JLabel("");
		lblKamchatkaInd.setBounds(821, 52, 30, 30);
		MapPanel.add(lblKamchatkaInd);
		circles.put(Kamchatka, lblKamchatkaInd);
		
		JLabel lblJapanInd = new JLabel("");
		lblJapanInd.setBounds(841, 206, 30, 30);
		MapPanel.add(lblJapanInd);
		circles.put(Japan, lblJapanInd);
		
		JLabel lblIndonesiaInd = new JLabel("");
		lblIndonesiaInd.setBounds(739, 445, 30, 30);
		MapPanel.add(lblIndonesiaInd);
		circles.put(Indonezia, lblIndonesiaInd);
		
		JLabel lblNewGuineaInd = new JLabel("");
		lblNewGuineaInd.setBounds(815, 415, 30, 30);
		MapPanel.add(lblNewGuineaInd);
		circles.put(NewGuinea, lblNewGuineaInd);
		
		JLabel lblWesternAustraliaInd = new JLabel("");
		lblWesternAustraliaInd.setBounds(773, 525, 30, 30);
		MapPanel.add(lblWesternAustraliaInd);
		circles.put(WesternAustralia, lblWesternAustraliaInd);
		
		JLabel lblEasternAustraliaInd = new JLabel("");
		lblEasternAustraliaInd.setBounds(821, 491, 30, 30);
		MapPanel.add(lblEasternAustraliaInd);
		circles.put(EasternAustralia, lblEasternAustraliaInd);
		
		//Countries: 
		JLabel lblNwtTerritory = new JLabel("");
		lblNwtTerritory.setBounds(210, 100, 155, 54);
		Mainpanel.add(lblNwtTerritory);
		labels.put(NorthWestTerritory, lblNwtTerritory);
		
		//North Africa
		JLabel lblNorthAfrica = new JLabel("");
		lblNorthAfrica.setBounds(512, 382, 80, 112);
		Mainpanel.add(lblNorthAfrica);
		labels.put(NorthAfrica, lblNorthAfrica);
		
		//Egypt
		JLabel lblEgypt = new JLabel("");
		lblEgypt.setBounds(585, 384, 80, 39);
		Mainpanel.add(lblEgypt);
		labels.put(Egypt, lblEgypt);
		
		//Congo
		JLabel lblCongo = new JLabel("");
		lblCongo.setBounds(595, 500, 70, 39);
		Mainpanel.add(lblCongo);
		labels.put(Congo, lblCongo);
		
		JLabel lblSouthAfrica = new JLabel("");
		lblSouthAfrica.setBounds(595, 561, 92, 101);
		Mainpanel.add(lblSouthAfrica);
		labels.put(SouthAfrica, lblSouthAfrica);
		
		//East Africa
		JLabel lblEastAfrica = new JLabel("");
		lblEastAfrica.setBounds(646, 438, 70, 112);
		Mainpanel.add(lblEastAfrica);
		labels.put(EastAfrica, lblEastAfrica);
		
		//Madagascar
		JLabel lblMadagascar = new JLabel("");
		lblMadagascar.setBounds(704, 561, 40, 85);
		Mainpanel.add(lblMadagascar);
		labels.put(Madagascar, lblMadagascar);
				
		// Alberta
		JLabel lblAlberta = new JLabel("");
		lblAlberta.setBounds(220, 142, 89, 76);
		Mainpanel.add(lblAlberta);
		labels.put(Alberta, lblAlberta);
		
		// Eastern US
		JLabel lblEasternUS = new JLabel("");
		lblEasternUS.setBounds(301, 249, 85, 68);
		Mainpanel.add(lblEasternUS);
		labels.put(EasternUS, lblEasternUS);
		
		//Brazil
		JLabel lblBrazil = new JLabel("");
		lblBrazil.setBounds(368, 414, 102, 119);
		Mainpanel.add(lblBrazil);
		labels.put(Brazil, lblBrazil);
		
		//Venezuela		
		JLabel lblVenezuela = new JLabel("");
		lblVenezuela.setBounds(301, 373, 112, 54);
		Mainpanel.add(lblVenezuela);
		labels.put(Venezuela, lblVenezuela);
		
		//Argentina
		JLabel lblArgentina = new JLabel("");
		lblArgentina.setBounds(333, 516, 70, 146);
		Mainpanel.add(lblArgentina);
		labels.put(Argentina, lblArgentina);
		
		//Peru
		JLabel lblPeru = new JLabel("");
		lblPeru.setBounds(301, 438, 85, 85);
		Mainpanel.add(lblPeru);
		labels.put(Peru, lblPeru);
				
		//Central America
		JLabel lblCentralAmerica = new JLabel("");
		lblCentralAmerica.setBounds(224, 305, 85, 76);
		Mainpanel.add(lblCentralAmerica);
		labels.put(CentralAmerica, lblCentralAmerica);
		
		//Quebec
		JLabel lblQuebec = new JLabel("");
		lblQuebec.setBounds(358, 153, 70, 76);
		Mainpanel.add(lblQuebec);
		labels.put(Quebec, lblQuebec);
		
		//Western US
		JLabel lblWesternUS = new JLabel("");
		lblWesternUS.setBounds(226, 206, 94, 101);
		Mainpanel.add(lblWesternUS);
		labels.put(WesternUS, lblWesternUS);
		
		//Greenland
		JLabel lblGreenland = new JLabel("");
		lblGreenland.setForeground(new Color(255, 255, 255));
		lblGreenland.setBounds(375, 50, 135, 95);
		Mainpanel.add(lblGreenland);
		labels.put(Greeland, lblGreenland);
		
		//Ontario
		JLabel lblOntario = new JLabel("");
		lblOntario.setBounds(301, 153, 53, 76);
		Mainpanel.add(lblOntario);
		labels.put(Ontario, lblOntario);
				
		//Alasca
		JLabel lblAlasca = new JLabel("");		
		lblAlasca.setBounds(139, 106, 89, 89);
		Mainpanel.add(lblAlasca);
		labels.put(Alasca, lblAlasca);
		
		//Great Britain
		JLabel lblGreatBritain = new JLabel("");
		lblGreatBritain.setBounds(465, 206, 70, 68);
		Mainpanel.add(lblGreatBritain);
		labels.put(GreatBritain, lblGreatBritain);
		
		//Iceland
		JLabel lblIceland = new JLabel("");
		lblIceland.setBounds(487, 153, 62, 42);
		Mainpanel.add(lblIceland);
		labels.put(Iceland, lblIceland);
		
		//Afganistan
		JLabel lblAfganistan = new JLabel("");
		lblAfganistan.setBounds(716, 232, 92, 85);
		Mainpanel.add(lblAfganistan);
		labels.put(Afganistan, lblAfganistan);
		
		//Siberia
		JLabel lblSiberia = new JLabel("");
		lblSiberia.setBounds(798, 78, 70, 112);
		Mainpanel.add(lblSiberia);
		labels.put(Siberia, lblSiberia);
		
		JLabel lblSiberia2 = new JLabel("");
		lblSiberia2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSiberia2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiberia2.setBounds(818, 159, 32, 76);
		Mainpanel.add(lblSiberia2);
		labels.put(Siberia2, lblSiberia2);
		
		//Ural
		JLabel lblUral = new JLabel("");
		lblUral.setBounds(751, 124, 70, 128);
		Mainpanel.add(lblUral);
		labels.put(Ural, lblUral);
		
		//Russia
		JLabel lblRussia = new JLabel("");
		lblRussia.setBounds(644, 109, 103, 208);
		Mainpanel.add(lblRussia);
		labels.put(Russia, lblRussia);
		
		//Scandinavia
		JLabel lblScandinavia = new JLabel("");
		lblScandinavia.setBounds(560, 106, 80, 104);
		Mainpanel.add(lblScandinavia);
		labels.put(Scandinavia, lblScandinavia);
		
		//Western Europe
		JLabel lblWesternEurope = new JLabel("");
		lblWesternEurope.setBounds(487, 285, 67, 76);
		Mainpanel.add(lblWesternEurope);
		labels.put(WesternEurope, lblWesternEurope);
		
		//Northern Europe
		JLabel lblNorthernEurope = new JLabel("");
		lblNorthernEurope.setBounds(554, 215, 80, 76);
		Mainpanel.add(lblNorthernEurope);
		labels.put(NorthernEurope, lblNorthernEurope);
		
		//Southern Europe
		JLabel lblSouthernEurope = new JLabel("");
		lblSouthernEurope.setBounds(564, 271, 70, 85);
		Mainpanel.add(lblSouthernEurope);
		labels.put(SouthernEurope, lblSouthernEurope);
		
		//Middle East
		JLabel lblMiddleEast = new JLabel("");
		lblMiddleEast.setBounds(644, 328, 112, 120);
		Mainpanel.add(lblMiddleEast);
		labels.put(MiddleEast, lblMiddleEast);
		
		//Siam
		JLabel lblSiam = new JLabel("");
		lblSiam.setBounds(858, 373, 70, 85);
		Mainpanel.add(lblSiam);
		labels.put(Siam, lblSiam);
		
		//China
		JLabel lblChina1 = new JLabel("");
		lblChina1.setBounds(818, 260, 32, 85);
		Mainpanel.add(lblChina1);
		labels.put(China, lblChina1);
		
		//India
		JLabel lblIndia = new JLabel("");
		lblIndia.setBounds(766, 319, 90, 146);
		Mainpanel.add(lblIndia);
		labels.put(India, lblIndia);
		
		//Yakutsk
		JLabel lblYakutsk = new JLabel("");
		lblYakutsk.setBounds(864, 78, 70, 76);
		Mainpanel.add(lblYakutsk);
		labels.put(Yakutsk, lblYakutsk);
		
		//Kamchatka
		JLabel lblKamchatka = new JLabel("");
		lblKamchatka.setBounds(932, 78, 107, 128);
		Mainpanel.add(lblKamchatka);
		labels.put(Kamchatka, lblKamchatka);
		
		//Mongolia
		JLabel lblMongolia = new JLabel("");
		lblMongolia.setBounds(860, 232, 94, 59);
		Mainpanel.add(lblMongolia);
		labels.put(Mongolia, lblMongolia);
		
		//Japan
		JLabel lblJapan = new JLabel("");
		lblJapan.setBounds(964, 215, 46, 112);
		Mainpanel.add(lblJapan);
		labels.put(Japan, lblJapan);
		
		//Irkutsk
		JLabel lblIrkutsk = new JLabel("");
		lblIrkutsk.setBounds(854, 170, 53, 54);
		Mainpanel.add(lblIrkutsk);
		labels.put(Irkutsk, lblIrkutsk);
		
		
		JLabel lblChina2 = new JLabel("");
		lblChina2.setBounds(818, 293, 126, 76);
		Mainpanel.add(lblChina2);
		labels.put(China2, lblChina2);
		
		//Eastern Australia
		JLabel lblEasternAustralia = new JLabel("");
		lblEasternAustralia.setBounds(950, 530, 89, 54);
		Mainpanel.add(lblEasternAustralia);
		labels.put(EasternAustralia, lblEasternAustralia);
		
		JLabel lblEasternAustralia2 = new JLabel("");
		lblEasternAustralia2.setBounds(989, 575, 46, 76);
		Mainpanel.add(lblEasternAustralia2);
		labels.put(EasternAustralia2, lblEasternAustralia2);
		
		//Western Australia
		JLabel lblWesternAustralia = new JLabel("");
		lblWesternAustralia.setBounds(885, 543, 102, 119);
		Mainpanel.add(lblWesternAustralia);
		labels.put(WesternAustralia, lblWesternAustralia);
		
		//New Guinea
		JLabel lblNewGuinea = new JLabel("");
		lblNewGuinea.setBounds(932, 463, 71, 45);
		Mainpanel.add(lblNewGuinea);
		labels.put(NewGuinea, lblNewGuinea);
		
		//Indonezia
		JLabel lblIndonezia = new JLabel("");
		lblIndonezia.setBounds(836, 469, 92, 76);
		Mainpanel.add(lblIndonezia);
		labels.put(Indonezia, lblIndonezia);
		
		//Orsz�gok v�ge
		//----------------------------------------------------------------------------------------------------------------
		
		//T�rk�p
		JLabel lblMap = new JLabel("");

		lblMap.setToolTipText("");
		lblMap.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/Full_map/map_resized_final.png")));
		lblMap.setBounds(139, 56, 900, 606);
		Mainpanel.add(lblMap);
		//T�rk�p v�ge
		
		//Aktu�lis orsz�g kijelz�se
		JLabel lblAktulisOrszg = new JLabel("");
		lblAktulisOrszg.setHorizontalAlignment(SwingConstants.LEFT);
		lblAktulisOrszg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAktulisOrszg.setBounds(32, 415, 208, 33);
		Mainpanel.add(lblAktulisOrszg);
		
		//Orsz�gok tulajdonosainak kijelz�se
		//Orsz�gok �s k�z�ppontok �sszerendel�se
		
		
		//----------------------------------
		
		//J�t�kosok st�tuszai
		JPanel PlayerStatus = new JPanel();
		PlayerStatus.setBounds(1098, 10, 239, 555);
		Mainpanel.add(PlayerStatus);
		PlayerStatus.setLayout(null);
		
		JLabel lblPlayerName1 = new JLabel("New label");
		//TODO: Keret sz�n�nek be�ll�t�sa (m�sodik argumentum)- mindh�rom kerethez
		lblPlayerName1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerName1.setBounds(10, 5, 219, 35);
		PlayerStatus.add(lblPlayerName1);
		toBeRefreshed.put("lblPlayerName1", lblPlayerName1);
		//N�v be�ll�t�sa
		
		
		JLabel lblPlayerName2 = new JLabel("New label");
		lblPlayerName2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerName2.setBounds(10, 189, 219, 35);
		PlayerStatus.add(lblPlayerName2);
		toBeRefreshed.put("lblPlayerName2", lblPlayerName2);
		
		//TODO: Megoldani, hogy a j�t�kosok indexe 0 �s 1 legyen
		//Sz�less�gek be�ll�t�sa a j�t�kosok egys�geinek f�ggv�ny�ben
		//TODO: fusson le, amikor 2 j�t�kos �rkezik
		
		
		if((Motor.players.size() == 2)){
			Color playerInd1Color;
			Color playerInd2Color;
			float widthPercentage = ((float)(Motor.players.get(0).getNumberOfArmies())) / ((float)(Motor.players.get(1).getNumberOfArmies()) + (float)(Motor.players.get(0).getNumberOfArmies()));
			playerInd1Color = Motor.players.get(0).getColor();
			playerInd2Color = Motor.players.get(1).getColor();
			//Sz�less�g kisz�m�t�sa
			int width = (int) (300*widthPercentage);
					
			System.out.println(widthPercentage);
			System.out.println(width);
			System.out.println(Motor.players.get(0).getNumberOfArmies());
			System.out.println(Motor.players.get(1).getNumberOfArmies());
					
			Player1Units.setBounds(414, 10, (width), 20);
			Player1Units.setBackground(playerInd1Color);
			Player2Units.setBackground(playerInd2Color);
			Player2Units.setBounds((414+(width)), 10, (300-width), 20);
			
			//Nevek be�ll�t�sa
			lblPlayerName1.setText(Motor.players.elementAt(0).getName());
			lblPlayerName2.setText(Motor.players.elementAt(1).getName());
		}
		
		JButton btnNewButton = new JButton("K\u00F6r v\u00E9ge");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(1135, 606, 166, 76);
		Mainpanel.add(btnNewButton);
		
		//listenerek
		
		//J�t�kos indik�tor k�r�k listenerje  
		for(Map.Entry<String, JLabel> circleitem : circles.entrySet()) {
			String circlekey = circleitem.getKey();
		    JLabel circlevalue = circleitem.getValue();
		    String circlepath = "/Resized/"+ circlekey.toString() +".png";
		    //Tooltip be�ll�t�sa
		    circlevalue.setToolTipText(circlekey);
		    //Territories felt�lt�se
		    Territory actualTerritory  = new Territory(circlekey);
		    //actualTerritory.setPlayer(newPlayer1);
		    Motor.territories.add(actualTerritory);
		    
		    //teszt
		    
		    //Ter�lethez tartoz� j�t�kos kikeres�se
		    
		    //J�t�kos sz�ne alapj�n be�ll�t�s
		    
		    //Teszt S�ra k�dja alapj�n - indik�torok sz�n�nek be�ll�t�sa
		    for(Territory territories : Motor.territories){	    		    		    	
		    	//Ha tartozik a ter�lethez j�t�kos
		    	if(territories.getPlayer().getPlayerIndex() != -1){	
		    		//Ha egyezik a k�r labelje az adott ter�let nev�vel	
		    		if(territories.getName().equals(circlekey))
			    	{
			    		Player actualPlayer;

			    		actualPlayer = territories.getPlayer();
			    		actualColor = actualPlayer.getColor();
			    		System.out.println(circlekey);
			    		
			    		int colorID = 0; 
			    		//J�t�kos sz�ne alapj�n colorID be�ll�t�sa a k�vetkez� sw.c-hez
						if(actualColor.equals(Color.RED.darker())) 		{colorID = 1;}
						if(actualColor.equals(Color.CYAN))				{colorID = 2;}
						if(actualColor.equals(Color.GREEN))				{colorID = 3;}
						if(actualColor.equals(Color.ORANGE))			{colorID = 4;}
						if(actualColor.equals(Color.MAGENTA.darker()))	{colorID = 5;}
						
						//Megfelel� sz�n� k�r kiv�laszt�sa �s be�ll�t�sa
					    switch(colorID){
							case 1: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/red_dot.PNG"))); break;}
							case 2: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/blue_dot.PNG"))); break;}
							case 3: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/green_dot.PNG"))); break;}
							case 4: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/orange_dot.PNG"))); break;}
							case 5: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/magenta_dot.PNG"))); break;}		
							default: {circlevalue.setIcon(null);}
					    }	    
					    System.out.println(circlevalue);
			    		}
			    }
		    }
		    
		    //circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/blue_dot.PNG")));
    		//TODO: Mapben leimplement�lni, ez a pilot
    		circlevalue.setText("25");
    		circlevalue.setHorizontalTextPosition(lblWestUSInd.CENTER);
    		//------
		    //Indik�torok listenerjei
    		circlevalue.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblAktulisOrszg.setText("Aktu�lisan kijel�lt orsz�g:");
		    		lblActCntryName.setText(circlevalue.getToolTipText());
		    		lblActCountryPic.setIcon(new ImageIcon(JGUI.class.getResource(circlepath)));
		    		
		    		//repaint();
		    	}
				public void mouseExited(MouseEvent arg0) {
					lblActCountryPic.setText("");
					lblAktulisOrszg.setText("");
					lblActCountryPic.setIcon(null);
					lblActCntryName.setText("");
					
				}
		    });		
		
		//Listenerek hozz�ad�sa az orsz�gok labeljeihez		
		for(Map.Entry<String, JLabel> labelitem : labels.entrySet()) {
		    String labelkey = labelitem.getKey();
		    JLabel labelvalue = labelitem.getValue();
		    String path = "/Resized/"+ labelkey.toString() +".png";
		    labelvalue.setToolTipText(labelkey);
		    
		    labelvalue.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblAktulisOrszg.setText("Aktu�lisan kijel�lt orsz�g:");
		    		lblActCntryName.setText(labelvalue.getToolTipText());
		    		lblActCountryPic.setIcon(new ImageIcon(JGUI.class.getResource(path)));

		    		//repaint();

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

		};
		
		//listener v�ge -----------------------------------------------------------------------------
		MapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {		
				if((Motor.players.size() == 2)){
					Color playerInd1Color;
					Color playerInd2Color;
					float widthPercentage = ((float)(Motor.players.get(0).getNumberOfArmies())) / ((float)(Motor.players.get(1).getNumberOfArmies()) + (float)(Motor.players.get(0).getNumberOfArmies()));
					playerInd1Color = Motor.players.get(0).getColor();
					playerInd2Color = Motor.players.get(1).getColor();
					//Sz�less�g kisz�m�t�sa
					int width = (int) (300*widthPercentage);
							
					System.out.println(widthPercentage);
					System.out.println(width);
					System.out.println(Motor.players.get(0).getNumberOfArmies());
					System.out.println(Motor.players.get(1).getNumberOfArmies());
							
					Player1Units.setBounds(414, 10, (width), 20);
					Player1Units.setBackground(playerInd1Color);
					Player2Units.setBackground(playerInd2Color);
					Player2Units.setBounds((414+(width)), 10, (300-width), 20);
					
					//Nevek be�ll�t�sa
					lblPlayerName1.setText(Motor.players.elementAt(0).getName());
					lblPlayerName2.setText(Motor.players.elementAt(1).getName());	
					
				}
			}
		});
		
		}
		
		
		
		//////TESZT
		for(Player players : Motor.players){
		System.out.println(players.getName());
		}
	}
	
}
