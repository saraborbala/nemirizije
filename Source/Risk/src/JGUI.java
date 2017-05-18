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
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

@SuppressWarnings("serial")
public class JGUI extends JFrame implements java.io.Serializable{
	private Motor motor;
	private Point p1 = new Point(0, 0); 
	public int teszt = 1;
	private int availableUnits = 0;
	private Integer unitsToMoveNum = 0; 
	private Integer actualCircleUnits = 0;
	private String fromMove;
	private String toMove;
	JDesktopPane desktopPane = new JDesktopPane();
	private Integer attackerLostUnits = 0;
	private Integer defenderLostUnits = 0;
	
	Map<String, JLabel> labels = new HashMap<String,JLabel>();
	Map<String, JLabel> circles = new HashMap<String,JLabel>();
	Map<String, JLabel> toBeRefreshed = new HashMap<String,JLabel>();
	
	private String infoLabelText;
	private String labelFromName;
	private String labelToName;
	private boolean attackEnded = false;

	private Player actPlayer;
	private int unitToPlace = 0;
	private boolean movementDone;
	
	public void setInfoLabelText(String info){
		infoLabelText = info;
	}
	public String getInfoLabelText(){
		return infoLabelText;
	}
	
	public void setMovementDone(boolean state){
		movementDone = state;
	}
	public boolean getMovementDone(){
		return movementDone;
	}
	
	public void setUnitToPlace(int i){
		unitToPlace = i;
	}
	public int getUnitToPlace(){
		return unitToPlace;
	}
	
	public void setActPlayer(Player player){
		actPlayer = player;
	}

	public Integer actGUIPlayerIndex = 0; // 0, ha szerver, 1 ha kliens
 
	
	public void setAttackEnded(boolean value){
		attackEnded = value;
	}
	
	public String getLabelFromName(){
		return labelFromName;
	}
	public String getLabelToName(){
		return labelToName;
	}
	
	public int getUnitsToMove(){
		return unitsToMoveNum;
	}
	public int getAvailableUnits(){
		return availableUnits;
	}
	public Integer setAttackerLostUnits(int n){
		return attackerLostUnits = n;
	}
	public Integer setDefenderLostUnits(int n){
		return defenderLostUnits = n;
	}
	public Integer getAttackerLostUnits(){
		return attackerLostUnits;
	}
	public Integer getDefenderLostUnits(){
		return defenderLostUnits;
	}
	
	private enum StatusMove {
		STARTED, FIRST_SELECTED, BOTH_SELECTED, ATTACK_ENDED, PLACE_UNIT 
	}
	
	private StatusMove statusmove;
	public StatusMove getStatusMove(){
		return this.statusmove;
	}
	public void setStatusMove(StatusMove statusmove){
		this.statusmove = statusmove;
	}
	
	//TODO: hozzáadni a labelöket az országok közepére
	
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
	
	public JPanel contentPane;
		
	public JGUI(Motor motor) {
		
		this.motor = motor;	// motor és GUI összekapcsolása
		
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
		
		 
		
		//Indikátor a játékosok egységeinek száma függvényében
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
		upperMenu.setBounds(0, 0, 58, 34);
		MenuBar.add(upperMenu);
		
		JMenu Menu = new JMenu("Menu");
		
		//Elemek hozzáadása
		JMenuItem NewPlayer = new JMenuItem("Új játékos");
		NewPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NewPlayer frame = new NewPlayer(motor);
							frame.setVisible(true);
							frame.setBounds(100, 100, 481, 338);
							frame.setLocation(new Point(300,300));
							frame.setResizable(false);
							//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
							frame.setVisible(true);	
						} 
						catch (Exception e) {
						e.printStackTrace();
						}
					}
				});

			}
		});
		
		//
		JMenuItem Exit = new JMenuItem("Kilépés");
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		upperMenu.add(Menu);
		Menu.add(NewPlayer);
		Menu.add(Exit);
		
		//Egységek mozgatás
		JGUI jgui = this;
		
		// Menu vége
		//------------------------------------------------------------------------------------
		//Felhasznált változók
		Color actualColor = Color.BLACK;
		String actualColorStr;
		 
		//Országok nevei
		
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
		
		//--Országnév vége
	
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
		//Tulajdonos indikátorok az országokhoz
		//Fõpanel
		JPanel MapPanel = new JPanel();
		MapPanel.setOpaque(false);
		MapPanel.setBounds(137, 58, 925, 604);
		Mainpanel.add(MapPanel);
		MapPanel.setLayout(null);
		//Alasca indikátor
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
			
		lblWestUSInd.setIcon(null);
		lblWestUSInd.setBounds(118, 182, 30, 30);	
		//TODO: Mapben leimplementálni, ez a pilot
		//lblWestUSInd.setText("25");
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
		circles.put(WesternEurope, lblWesternEUInd);
		
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
		
		//Országok vége
		//----------------------------------------------------------------------------------------------------------------
		
		//Térkép
		JLabel lblMap = new JLabel("");

		lblMap.setToolTipText("");
		lblMap.setIcon(new ImageIcon(JGUI.class.getResource("/Resized/Full_map/map_resized_final.png")));
		lblMap.setBounds(139, 56, 900, 606);
		Mainpanel.add(lblMap);
		//Térkép vége
		
		//Aktuális ország kijelzése
		JLabel lblAktulisOrszg = new JLabel("");
		lblAktulisOrszg.setHorizontalAlignment(SwingConstants.LEFT);
		lblAktulisOrszg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAktulisOrszg.setBounds(32, 415, 208, 33);
		Mainpanel.add(lblAktulisOrszg);
		
		//Országok tulajdonosainak kijelzése
		//Országok és középpontok összerendelése
		
		
		//----------------------------------
		
		//Játékosok státuszai
		JPanel PlayerStatus = new JPanel();
		PlayerStatus.setBounds(1098, 10, 239, 555);
		Mainpanel.add(PlayerStatus);
		PlayerStatus.setLayout(null);
		
		JLabel lblPlayerName1 = new JLabel("New label");
		lblPlayerName1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlayerName1.setHorizontalAlignment(SwingConstants.CENTER);
		//TODO: Keret színének beállítása (második argumentum)- mindhárom kerethez
		lblPlayerName1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerName1.setBounds(10, 5, 219, 35);
		PlayerStatus.add(lblPlayerName1);
		toBeRefreshed.put("lblPlayerName1", lblPlayerName1);
		//Név beállítása
		
		
		JLabel lblPlayerName2 = new JLabel("New label");
		lblPlayerName2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlayerName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPlayerName2.setBounds(10, 189, 219, 35);
		PlayerStatus.add(lblPlayerName2);
		toBeRefreshed.put("lblPlayerName2", lblPlayerName2);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInfo.setBounds(10, 262, 219, 116);
		PlayerStatus.add(lblInfo);
		toBeRefreshed.put("InfoLabel", lblInfo);
		
		JPanel bonusUnitPanel = new JPanel();
		bonusUnitPanel.setVisible(false);
		bonusUnitPanel.setBounds(10, 430, 219, 125);
		PlayerStatus.add(bonusUnitPanel);
		bonusUnitPanel.setLayout(null);
		
		JLabel lblBonusUnitText = new JLabel("Ennyi elemet helyezek le:");
		lblBonusUnitText.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonusUnitText.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBonusUnitText.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblBonusUnitText.setBounds(10, 0, 199, 31);
		bonusUnitPanel.add(lblBonusUnitText);
		
		JLabel lblBonusUnitNum = new JLabel("");
		lblBonusUnitNum.setBounds(40, 34, 46, 31);
		bonusUnitPanel.add(lblBonusUnitNum);
		
		JButton btnUnitAdd = new JButton("+");
		btnUnitAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bonusUnitAvailable = 0;
				for(Territory territories : motor.territories){
					if(territories.getName().equals(labelFromName)){
						Player tempplayer = territories.getPlayer();
						bonusUnitAvailable = tempplayer.getBonusUnit();
					}		
				}
				if(getUnitToPlace() < bonusUnitAvailable){
				setUnitToPlace(getUnitToPlace() +1);
				Integer actarmy = getUnitToPlace();
				lblBonusUnitNum.setText(actarmy.toString());
				}
			}
		});
		btnUnitAdd.setBounds(100, 32, 41, 35);
		bonusUnitPanel.add(btnUnitAdd);
		
		JButton btnUnitMin = new JButton("-");
		btnUnitMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bonusUnitAvailable = 0;
				for(Territory territories : motor.territories){
					if(territories.getName().equals(labelFromName)){
						Player tempplayer = territories.getPlayer();
						bonusUnitAvailable = tempplayer.getBonusUnit();
					}		
				}
				if(getUnitToPlace() <= bonusUnitAvailable){
				if((getUnitToPlace() - 1) > 0){
				setUnitToPlace(getUnitToPlace() - 1);
				}
				Integer actarmy = getUnitToPlace();
				lblBonusUnitNum.setText(actarmy.toString());}
				else{lblBonusUnitNum.setText("0");}
			}
		});
		btnUnitMin.setBounds(151, 32, 41, 35);
		bonusUnitPanel.add(btnUnitMin);
		
		JButton btnUnitDone = new JButton("K\u00E9sz!");
		btnUnitDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bonusUnitAvailable = 0;
				for(Territory territories : motor.territories){	 
					if(territories.getName().equals(labelFromName)){
						territories.setArmies(territories.getArmies() + getUnitToPlace() );
						
						//Bonus értékének aktualizálása
						territories.getPlayer().setBonusUnit(territories.getPlayer().getBonusUnit() - getUnitToPlace());
						bonusUnitAvailable = territories.getPlayer().getBonusUnit();
						setUnitToPlace(0);
					}
				}
				lblBonusUnitNum.setText("0");
				
				if(bonusUnitAvailable == 0){
				statusmove = StatusMove.STARTED;
				bonusUnitPanel.setVisible(false);
				refreshMap();
				}
				else{
				bonusUnitPanel.setVisible(false);
				statusmove = StatusMove.PLACE_UNIT;
				refreshMap();	
				}
				
			}
		});
		btnUnitDone.setBounds(68, 76, 89, 35);
		bonusUnitPanel.add(btnUnitDone);
		
		JLabel lblBonusinfo = new JLabel("B\u00F3nusz egys\u00E9g:");
		lblBonusinfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBonusinfo.setBounds(10, 391, 219, 28);
		PlayerStatus.add(lblBonusinfo);
		toBeRefreshed.put("bonusNum", lblBonusinfo);

		if((Motor.players.size() == 2)){
			Color playerInd1Color;
			Color playerInd2Color;
			float widthPercentage = ((float)(Motor.players.get(0).getNumberOfArmies())) / ((float)(Motor.players.get(1).getNumberOfArmies()) + (float)(Motor.players.get(0).getNumberOfArmies()));
			playerInd1Color = Motor.players.get(0).getColor();
			playerInd2Color = Motor.players.get(1).getColor();
			//Szélesség kiszámítása
			int width = (int) (300*widthPercentage);		
			Player1Units.setBounds(414, 10, (width), 20);
			Player1Units.setBackground(playerInd1Color);
			Player2Units.setBackground(playerInd2Color);
			Player2Units.setBounds((414+(width)), 10, (300-width), 20);
			
			//Nevek beállítása
			lblPlayerName1.setText(Motor.players.elementAt(0).getName());
			lblPlayerName2.setText(Motor.players.elementAt(1).getName());
		}
//----HÁLÓZAT----------------------------------------------------------------------------	
		JButton btnNewButton = new JButton("K\u00F6r v\u00E9ge");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(Player players : motor.players){
					if(players.getPlayerIndex() == actGUIPlayerIndex){
					players.setBonusUnit(2);
					}
				}
				
				// Kör vége gomb, átküldi az összes változtatást
				GameState gs = new GameState();
				// Átállítjuk az aktuális playert
				if (actGUIPlayerIndex == 0){
					actGUIPlayerIndex = 1;
				}
				else{
					actGUIPlayerIndex = 0;
				}
				if(motor.getActPlayer().getPlayerIndex() == 0){
					gs.actPlayer = 1;
				}
				else{
					gs.actPlayer = 0;
				}			
				refreshMap();
				gs.msg = "Kör vége!";
				gs.state = 0; // new_turn
				gs.territories = motor.territories;
				gs.players = motor.players;
				motor.sendGameState(gs);
			}
		});
//----HÁLÓZAT-----------------------------------------------------------------------------
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(1135, 606, 166, 76);
		Mainpanel.add(btnNewButton);
		
		JPanel movementPanel = new JPanel();
		movementPanel.setBounds(343, 660, 611, 35);
		Mainpanel.add(movementPanel);
		movementPanel.setLayout(null);
		
		JLabel lblUnitNum = new JLabel("");
		lblUnitNum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnitNum.setBounds(330, 0, 41, 35);
		movementPanel.add(lblUnitNum);
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if(availableUnits > unitsToMoveNum){
				if(jgui.getAvailableUnits() - unitsToMoveNum >= 2){
					unitsToMoveNum += 1;
					lblUnitNum.setText(unitsToMoveNum.toString());
				}
				//}
			}
		});
		btnNewButton_1.setBounds(381, 0, 41, 35);
		movementPanel.add(btnNewButton_1);
		
		JButton button = new JButton("-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(unitsToMoveNum > 0){
					unitsToMoveNum -= 1;
					lblUnitNum.setText(unitsToMoveNum.toString());
				}
					
			}
		});
		button.setBounds(425, 0, 41, 35);
		movementPanel.add(button);
		
		
		//Szöveg a mozgatáshoz/támadáshoz
		JLabel lblMoveOrAtt = new JLabel("Ennyi egys\u00E9get mozgatok:");
		lblMoveOrAtt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoveOrAtt.setBounds(152, 0, 168, 35);
		movementPanel.add(lblMoveOrAtt);
		
		//Kész gomb
		JButton btnDoneMoveSelect = new JButton("Kész!");
		btnDoneMoveSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(unitsToMoveNum);
				
				motor.moveUnits(labelFromName, labelToName, unitsToMoveNum);
				
				for(Territory territories : motor.territories){	 
					if(territories.getName().equals(labelFromName)){
						Integer army = territories.getArmies();
						fromMove = army.toString();
					}
					if(territories.getName().equals(labelToName)){
						Integer army = territories.getArmies();
						toMove = army.toString();
					}
					actualCircleUnits = territories.getArmies();
						
						//System.out.println(territories.getArmies());
						for(Map.Entry<String, JLabel> circleitem : circles.entrySet()) {
							String circlekey 	= circleitem.getKey();
							JLabel circlevalue 	= circleitem.getValue();
							if(circlekey.equals(labelFromName)){					
								circlevalue.setText(fromMove);
							}
							if(circlekey.equals(labelToName)){
								circlevalue.setText(toMove);
							}		
							//circlevalue.setText("13");	
						}					
				}
				setMovementDone(true);
				
				lblInfo.setText("");
				lblUnitNum.setText("0");
				unitsToMoveNum = 0;
				//System.out.println(x);
				movementPanel.setVisible(false);
			}
		});
		btnDoneMoveSelect.setBounds(476, 0, 89, 35);
		movementPanel.add(btnDoneMoveSelect);
		
		movementPanel.setVisible(false);
		//listenerek	
		//Játékos indikátor körök listenerje  
		for(Map.Entry<String, JLabel> circleitem : circles.entrySet()) {
			String circlekey = circleitem.getKey();
		    JLabel circlevalue = circleitem.getValue();
		    String circlepath = "/Resized/"+ circlekey.toString() +".png";
		    //Tooltip beállítása
		    circlevalue.setToolTipText(circlekey);
		    //Territories feltöltése
		    Territory actualTerritory  = new Territory(circlekey);
		    motor.territories.add(actualTerritory);

    		//TODO: Mapben leimplementálni, ez a pilot
    		circlevalue.setText(actualCircleUnits.toString());
    		circlevalue.setHorizontalTextPosition(lblWestUSInd.CENTER);
    		//------
		    //Indikátorok listenerjei
    		statusmove = StatusMove.STARTED;
    		    		
    		circlevalue.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblAktulisOrszg.setText("Aktuálisan kijelölt ország:");
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

				public void mouseClicked(MouseEvent arg0) {
					Player labelFromPlayer = null;
					Player labelToPlayer = null;
					boolean isAdjacent = false;
					
					switch(statusmove){
						case STARTED: {
							//actGUIPlayerIndex
							if(motor.players.get(actGUIPlayerIndex).getBonusUnit() != 0){
								statusmove = StatusMove.PLACE_UNIT;
							}
							
							labelFromName = circlekey;
							for(Territory territories : motor.territories){	
							isMyTurn();		
								if(territories.getName().equals(labelFromName)){
									if(territories.getPlayer().getPlayerIndex() == actGUIPlayerIndex){
											labelFromPlayer = territories.getPlayer();
											setInfoLabelText("");
											refreshMap();
											statusmove = StatusMove.FIRST_SELECTED;
									}
										else{
											setInfoLabelText("Nem a te területed!");
											refreshMap();
											System.out.println("Nem a te területed!");
											}
								}
							}

							break;
						}
						case FIRST_SELECTED: {						
							labelToName = circlekey;
							for(Territory territories : motor.territories){
								if(territories.getName().equals(circlekey)){
									isAdjacent = territories.isAdjacent(labelFromName);
								}
							}
							} 
						if(isAdjacent){
							for(Territory territories : motor.territories){	
					    		  if(territories.getName().equals(labelFromName)){
					    			  availableUnits = territories.getArmies();
					    			  labelFromPlayer = territories.getPlayer();
					    		  }
					    		  if(territories.getName().equals(labelToName)){
					    			  labelToPlayer = territories.getPlayer();
					    		  }
							}
							if(!(labelFromPlayer.equals(labelToPlayer))){
								//ATTACK
								//Create attackframe
								if(availableUnits != 1){
								AttackScreen frame = new AttackScreen(jgui, motor);
								motor.setAScreen(frame);
								frame.setBounds(32, 62, 765, 325);
								frame.setLocation(new Point(300,300));
								frame.setResizable(false);
								frame.setTitle("Támadás");
								frame.setVisible(true);	
								}
								else{
									setInfoLabelText("<html>Legalább 2 egység<br>szükséges!</html>");
									refreshMap();
								}
							}
							else{//MOVE
								movementPanel.setVisible(true);
							}
	
							statusmove = StatusMove.BOTH_SELECTED;
							//System.out.println(statusmove);
							statusmove = StatusMove.STARTED;
							
							break;
							}
						case PLACE_UNIT:{

							for(Territory territories : motor.territories){	
							isMyTurn();		
							if(territories.getName().equals(labelFromName)){
								if(territories.getName().equals(labelFromName)){
									labelFromPlayer = territories.getPlayer();	
								}
								if(territories.getPlayer().getPlayerIndex() == actGUIPlayerIndex){
										labelFromPlayer = territories.getPlayer();
										setInfoLabelText("");
										refreshMap();
										statusmove = StatusMove.FIRST_SELECTED;
								}
									else{
										setInfoLabelText("Nem a te területed!");
										refreshMap();
										System.out.println("Nem a te területed!");
										}
							}
							labelFromName = circlekey;								
							}
						
							
							
							bonusUnitPanel.setVisible(true);
						}
						default:
							break;
						
						}
				}
				
				
		    });			
    		
		//Listenerek hozzáadása az országok labeljeihez		
		for(Map.Entry<String, JLabel> labelitem : labels.entrySet()) {
		    String labelkey = labelitem.getKey();
		    JLabel labelvalue = labelitem.getValue();
		    String path = "/Resized/"+ labelkey.toString() +".png";
		    labelvalue.setToolTipText(labelkey);
		    
		    labelvalue.addMouseListener(new MouseAdapter() {
		    	public void mouseEntered(MouseEvent e) {
		    		lblAktulisOrszg.setText("Aktuálisan kijelölt ország:");
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
		
		//listener vége -----------------------------------------------------------------------------		
	}
}

	private boolean isMyTurn(){
		if(motor.getActPlayer() != null){
		return (actGUIPlayerIndex == motor.getActPlayer().getPlayerIndex());
		}
		else {return true;}
	}
	// ------------------ Adatok frissítése serveren keresztül
	public void refreshMap(){ // void? 		
			for(Map.Entry<String, JLabel> circleitem : circles.entrySet()) {
				String circlekey = circleitem.getKey();
				JLabel circlevalue = circleitem.getValue();
				
				for(Territory territories : motor.territories){
					if(territories.getName().equals(circlekey)){
						Integer actarmy = territories.getArmies();
						circlevalue.setText(actarmy.toString());
					}
				}	
			}
			//RefreshLabels///////////////////////////////////////////////////////
		    for(Map.Entry<String, JLabel> infoitem : toBeRefreshed.entrySet()) {
				String infoitemkey = infoitem.getKey();
				JLabel infoitemvalue = infoitem.getValue();
				infoitemvalue.setText(infoitemvalue.getText());
				
				if(infoitemkey.equals("lblPlayerName1")){
					infoitemvalue.setText(motor.players.get(0).getName());
					infoitemvalue.setBorder(new EtchedBorder(EtchedBorder.LOWERED, motor.players.get(0).getColor(), motor.players.get(0).getColor()));
				}
				if(infoitemkey.equals("lblPlayerName2")){
					infoitemvalue.setText(motor.players.get(1).getName());
					infoitemvalue.setBorder(new EtchedBorder(EtchedBorder.LOWERED, motor.players.get(1).getColor(), motor.players.get(1).getColor()));
				}
				if(infoitemkey.equals("InfoLabel")){
					infoitemvalue.setText(getInfoLabelText());
					if(!isMyTurn()){
						infoitemvalue.setBackground(Color.RED);
						infoitemvalue.setText("Nem te jössz!");
					}
					
				}
				
		    }
		    //////////////////////////////////////////////////////////////////////
			if(!isMyTurn()){
				contentPane.setVisible(false);
			}
			else{
				contentPane.setVisible(true);
			}
			
	
			//--------------------------
			for(Map.Entry<String, JLabel> circleitem : circles.entrySet()) {
			String circlekey = circleitem.getKey();
		    JLabel circlevalue = circleitem.getValue();
				

		    for(Territory territories : motor.territories){
		    	if(territories.getPlayer().getPlayerIndex() != -1){	
	    		//Ha egyezik a kör labelje az adott terület nevével	
		    		if(territories.getName().equals(circlekey))
			    	{
			    		Player actualPlayer;		    		
			    		actualPlayer = territories.getPlayer();
			    		int colorID = 0; 
			    		//Játékos színe alapján colorID beállítása a következõ sw.c-hez
						if(actualPlayer.getColor().equals(Color.RED.darker())) 		{colorID = 1;}
						if(actualPlayer.getColor().equals(Color.CYAN))				{colorID = 2;}
						if(actualPlayer.getColor().equals(Color.GREEN))				{colorID = 3;}
						if(actualPlayer.getColor().equals(Color.ORANGE))			{colorID = 4;}
						if(actualPlayer.getColor().equals(Color.MAGENTA.darker()))	{colorID = 5;}
							
						//Megfelelõ színû kör kiválasztása és beállítása
						switch(colorID){
							case 1: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/red_dot.PNG"))); break;}
							case 2: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/blue_dot.PNG"))); break;}
							case 3: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/green_dot.PNG"))); break;}
							case 4: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/orange_dot.PNG"))); break;}
							case 5: {circlevalue.setIcon(new ImageIcon(JGUI.class.getResource("/Indicators/magenta_dot.PNG"))); break;}		
							default: {circlevalue.setIcon(null);}
						}	    
						    
				    }
		    		
			    }
	
		  
		}
	}
	}
}
