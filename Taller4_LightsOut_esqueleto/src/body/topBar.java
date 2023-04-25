package body;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class topBar extends JPanel{
	
	private int canvaSize;
	private int difficulty;
	private Frame frame;
	private ButtonGroup group = new ButtonGroup();
	
	public topBar(Frame frame) {
		
		this.frame = frame;
		
		//Colors
		Color cTopbar = new Color(97,144,255);
		
		//Sizes
		String[] sizes = {"5x5", "6x6", "7x7", "8x8", "9x9"};
		JComboBox<String> sizeSelect = new JComboBox(sizes);
				
		
		//Adding elements		
		//Radio Buttons Top bar
		JRadioButton rbEasy = new JRadioButton("Easy");
		rbEasy.setActionCommand("Easy");
		JRadioButton rbInter = new JRadioButton("Intermediate");
		rbInter.setActionCommand("Intermediate");
		JRadioButton rbHard = new JRadioButton("Hard");
		rbHard.setActionCommand("Hard");
				
		
		//Top Bar
		this.setBackground(cTopbar);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				
		this.add(Box.createHorizontalGlue());
		this.add(new JLabel("Tamaño: "));
		this.add(sizeSelect);
				
		String tempSize = (String) sizeSelect.getSelectedItem();
		canvaSize = Character.getNumericValue(tempSize.charAt(0));
				
		this.add(new JLabel("Difficultad: "));
		group.add(rbEasy);
		group.add(rbInter);
		group.add(rbHard);
		rbEasy.setSelected(true);
		this.add(rbEasy);
		this.add(rbInter);
		this.add(rbHard);
		this.add(Box.createHorizontalGlue());
		
		this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		sizeSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComboBox<String> combo = (JComboBox<String>) e.getSource();
			     String selectedOption = (String) combo.getSelectedItem();
			     canvaSize = Character.getNumericValue(selectedOption.charAt(0));
			}
		});
		
	}
	
	public int getDifficulty() {
		if (group.getSelection() != null) {
			String selectedOption = group.getSelection().getActionCommand();
			if (selectedOption == "Easy") {
				difficulty = 10;
			}else if (selectedOption == "Intermediate") {
				difficulty = 20;
			}else if (selectedOption == "Hard") {
				difficulty = 50;
			} else {
				difficulty = 0;
			}
		}
		
		return difficulty;
	}
	
	public int getCanvaSize() {
		return canvaSize;
	}
	
}
