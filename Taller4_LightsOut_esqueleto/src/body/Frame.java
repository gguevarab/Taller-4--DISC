package body;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

//El diagrama de clases esta en el paquete Diagrama. Deje una imagen en data por si acaso.

public class Frame extends JFrame{
	
	private JPanel pTopBar;
	private JPanel pSideBar;
	private JPanel pCanvas = new JPanel();
	private JPanel pBottomBar;
	private JCanvas canvas;
	private CSVToList fTop10;
	
	public Frame() {
		
		//Main settings of the JFrame
		this.setSize(900, 700);
		this.setTitle("Lights out");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//Bottom bar with the actual score and and player
		pBottomBar = new bottomBar();
		this.add(pBottomBar, BorderLayout.SOUTH);
		
		//Side bar with the buttons for new game, restart, top 10 and user change.
		pSideBar = new sideBar(this);
		this.add(pSideBar, BorderLayout.EAST);
		
		//Center Canvas (The grid with the game itself)
		pCanvas.setLayout(new BorderLayout());
		canvas = new JCanvas(this);
		pCanvas.add(canvas, BorderLayout.CENTER);
		this.add(pCanvas, BorderLayout.CENTER);	
		
		//Top bar with size and difficulty options
		pTopBar = new topBar(this);
		this.add(pTopBar, BorderLayout.NORTH);
		
		fTop10 = new CSVToList();
		
		this.setVisible(true);
		
	}
	
	public void newGame() {
		
		changePlayer();
		
		pCanvas.remove(canvas);
		canvas = new JCanvas(this);
		pCanvas.add(canvas);
		this.revalidate();
		this.repaint();
	}
	
	public void restartGame() {
		canvas.restartGame();
	}
	
	public void showTop10() {
		fTop10.showFrame();
	}
	
	public void changePlayer() {
		boolean loop = true;
		String playerName = "";
		try {
			while (loop) {
				playerName = JOptionPane.showInputDialog("Ingrese 3 letras: ").toUpperCase();
				if (playerName.length() == 3) {
					loop = false;
				}
			}
		} catch(Exception e) {
			
		}
		
		((bottomBar) pBottomBar).updateName(playerName);
	}

	public void updateScore(int score) {
		((bottomBar) pBottomBar).updateScore(score);
	}
	
	public int getCanvaSize() {
		return ((topBar) pTopBar).getCanvaSize();
	}
	
	public int getDifficulty() {
		return ((topBar) pTopBar).getDifficulty();
	}
	
	public void setWinPlayer(int score) {
		JOptionPane.showMessageDialog(this, "Felicidades! Has ganado!\nPuntaje total: " + Integer.toString(score),"Ganador", JOptionPane.INFORMATION_MESSAGE);
		try {
			fTop10.addRecord(score, ((bottomBar) pBottomBar).getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fTop10 = new CSVToList();
	}

	public static void main(String[] args) {
		FlatLightLaf.install();
		new Frame();
	}

}
