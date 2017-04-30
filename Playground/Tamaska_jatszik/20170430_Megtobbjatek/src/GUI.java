/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Engine engine;
	
	GUI(Engine engine){ // konstruktor
		//super("SzoftechTutor");
		this.engine = engine;
		setSize(500, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Start");

		JMenuItem menuItem = new JMenuItem("Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.startClient();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.startServer();
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);

		/*menuItem = new JMenuItem("Clear");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.points.clear();
				drawPanel.repaint();
			}
		});
		menuBar.add(menuItem);*/

		menuItem = new JMenuItem("KÜDD");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState gs = new GameState();
				gs.msg = "Helloka!";
				engine.sendGameState(gs);
			}
		});
		menuBar.add(menuItem);

		setJMenuBar(menuBar);

		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(30, 30, 200, 200);
		inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
		inputPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				GameState gs = new GameState();
				gs.msg = "Helloka!";
				engine.sendGameState(gs);
			}
		});
		add(inputPanel);
		
		setVisible(true);
	}
}
