package body;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class bottomBar extends JPanel {
	
	private JLabel lScore = new JLabel("0");
	private JLabel lPlayer = new JLabel("Ingrese un nombre para registro");
	
	public bottomBar() {
		//Bottom Bar
		this.setBackground(Color.GREEN);
		this.setLayout(new GridLayout(1,4));
						
		this.add(new JLabel("Jugadas"));
		this.add(lScore);
		this.add(new JLabel("Jugador"));
		this.add(lPlayer);
	}
	
	public void updateScore(int score) {
		lScore.setText(Integer.toString(score));
	}
	
	public void updateName(String name) {
		lPlayer.setText(name);
	}
	
	public String getName() {
		return lPlayer.getText();
	}

}
