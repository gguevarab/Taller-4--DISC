package body;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class JCanvas extends JPanel implements MouseListener{
	
	private Tablero tablero;
	private Rectangle[][] rectangles;
	private BufferedImage lightBulb;
	private Frame frame;
	private int rectangleWidth;
	private int rectangleHeight;
	private int sizeCanvas = 5;
	private boolean[][] lightsBool;
	private int difficulty = 0;
	private boolean winner = false;
	
	public JCanvas(Frame frame) {
		
		this.frame = frame;
		
		try {
			this.difficulty = frame.getDifficulty();
			sizeCanvas = frame.getCanvaSize();
		} catch(Exception e) {
			
		}
		
		tablero = new Tablero(sizeCanvas);
		tablero.desordenar(difficulty);
		frame.updateScore(getJugadas());
		
		if (difficulty == 0) {
			winner = true;
		}
		
		addMouseListener(this);
		
		try {
			lightBulb = ImageIO.read(new File("././data/luz.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rectangleWidth = (this.frame.getWidth()- 280) / sizeCanvas;
		rectangleHeight = (this.frame.getHeight() - 120) / sizeCanvas;
		
		this.rectangles = new Rectangle[sizeCanvas][sizeCanvas];
		for(int x = 0; x<sizeCanvas; x++) {
			for (int i = 0; i<sizeCanvas; i++) {
				this.rectangles[x][i] = new Rectangle(rectangleWidth*i + 2*i, rectangleHeight*x + 2*x, rectangleWidth, rectangleHeight);
			}
		}
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.drawImage(lightBulb, 200,200,null);
		//g2d.setColor(new Color(255, 242, 117));
		
		lightsBool = tablero.darTablero();
		
		for (int x = 0; x<sizeCanvas; x++) {
			for (int y = 0; y<sizeCanvas; y++) {
				if (lightsBool[x][y]) {
					g2d.setColor(new Color(255, 242, 117));
				}
				else {
					g2d.setColor(Color.BLACK);
				}
				Rectangle rect = rectangles[x][y];
				g2d.draw(rectangles[x][y]);
				g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
				int imageWidth = rect.width/2;
				int imageHeight = rect.height/2;
				int centerX = (int) (rect.getCenterX()) - imageWidth / 2; 
				int centerY = (int) (rect.getCenterY()) - imageHeight / 2;
				g2d.drawImage(lightBulb, centerX, centerY, imageWidth, imageHeight, null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = e.getY() / (rectangleHeight + 2);
		int col = e.getX() / (rectangleWidth + 2);
		
		if (row >= 0 && row < sizeCanvas && col >= 0 && col < sizeCanvas) {
            tablero.jugar(row, col);
            repaint();
        }
		
		frame.updateScore(getJugadas());
		
		if (tablero.tableroIluminado() && winner == false) {
			winner = true;
			frame.setWinPlayer(tablero.calcularPuntaje());
		}
	}
	
	public int getJugadas() {
		return tablero.darJugadas();
	}
	
	public void restartGame() {
		tablero.reiniciar();
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
