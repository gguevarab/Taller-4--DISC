package body;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class sideBar extends JPanel{

	private Frame frame;
	
	public sideBar(Frame frame) {
		
		this.frame = frame;
		
		//Color
		Color cRightbar = new Color(220,220,220);
				
				
		//Side Panel Elements
		JButton bIniciar = new JButton("Nueva Partida");
		bIniciar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		JButton bRestart = new JButton("Reiniciar");
		bRestart.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		JButton bTop10 = new JButton("Top 10");
		bTop10.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		JButton bCJugador = new JButton("Cambiar Jugador");
		bCJugador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
				
		//Side Panel
		this.setBackground(cRightbar);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(250,700));
				
		this.add(Box.createVerticalGlue());
		this.add(bIniciar);
		this.add(bRestart);
		this.add(bTop10);
		this.add(bCJugador);
		this.add(Box.createVerticalGlue());
				
		this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		bIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.newGame();
			}
		});
		
		bRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.restartGame();
			}
		});
		
		bTop10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.showTop10();
			}
		});
		
		bCJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePlayer();
			}
			
		});
		
	}
	
}
