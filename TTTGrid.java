import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TTTGrid extends JComponent implements MouseListener {
	public static TTTCanvas canvas = new TTTCanvas();
	public static TTTGame game = new TTTGame();
	protected static String player = "p1";
	private String token;
	private int e1;
	private int e2;
	
	private static int [][]gridArray = new int[3][3];
	private static int num;
	
	//setup for finagling the grid
	private	int a = 90;
	private	int b = 190;
	private	int c = 290;
	private	int d = 390;

	public TTTGrid() {
		addMouseListener(this);
	}
	
	public String getPlayer() { 
		return player;
	}
	
	public void setPlayer(String newPlayer) { 
		player = newPlayer;
	}
	
	public void placeToken(String token, int xcoord, int ycoord, int e1, int e2) {
		Graphics g = getGraphics();
		Font f = new Font("Courier", Font.BOLD, 34);
		g.setFont(f);
		if (canvas.getStatus() == false && getArray(e1,e2) == 0 && (canvas.getTurnLabel().equals("Player 1's Turn") || canvas.getTurnLabel().equals("Player 2's Turn")) ) {
			addTurnNum();
			canvas.setScoreBoard();
			if (player.equals("p1")) { 
				token = "X";
				gridArray[e1][e2] = 1;
				player = "p2";
			}
			else if (player.equals("p2")) { 
				token = "O"; 
				gridArray[e1][e2] = 2;
				player = "p1";
			}
			g.drawString(token, xcoord, ycoord);
			game.startGame();
		}
		if (canvas.getTurnLabel().equals("Player 1's Turn") || canvas.getTurnLabel().equals("Player 2's Turn")) {
			canvas.setPlayerLabel("placeholder");
		}
	}

	@Override
	public void paint(Graphics g) {	
		//Drawing Board
		g.drawLine(b,a,b,d);
		g.drawLine(c,a,c,d);
		g.drawLine(a,b,d,b);
		g.drawLine(a,c,d,c);
	}

	public void createGrid() {
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.add(new TTTGrid());
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		TTTGrid grid = new TTTGrid();
		grid.createGrid();	
	}
	
	public void clearGrid() { 
		for (int i = 0; i < gridArray.length; ++i) { 
			for (int j = 0; j < gridArray[i].length; ++j) { 
				gridArray[i][j] = 0;
			}
		}
	}
	public static void printGrid() { 
		for (int i = 0; i < gridArray.length; ++i) { 
			for (int j = 0; j < gridArray[i].length; ++j) { 
				System.out.print(gridArray[i][j]);
			}
		}
		
	}

	public int getArray(int x, int y) { 
		return gridArray[x][y];
	}
	public int getTurnNum() { 
		return num;
	}
	public int addTurnNum() { 
		num++;
		return num;
	}
	public void setTurnNum(int newNum) { 
		num = newNum;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Index: empty = 0, x's = 1, o's = 2
		if (canvas.getStatus() == false) {
			int x = e.getX();
			int y = e.getY();
			if (x <= b && y <= b) { 
				placeToken(token, 130,150, 0, 0);
			}
			else if (x >= b && y <= b && x <= c) { 
				placeToken(token, 230,150, 0, 1);
			}
			else if (x >= c && y <= b) { 
				placeToken(token, 330,150, 0, 2);
			}
			else if (x <= b && y <= c && y >= b) { 
				placeToken(token, 130,250, 1, 0);
			}
			else if (x <= c && x >= b && y <= c && y >= b) { 
				placeToken(token, 230,250, 1, 1 );
			}
			else if (x >= c && y <= c && y >= b) { 
				placeToken(token, 330,250, 1, 2 );
			}
			else if (x <= b && y >= c) { 
				placeToken(token, 130,350, 2, 0 );
			}
			else if (x >= b && y >= c && x <= c) { 
				placeToken(token, 230,350, 2, 1 );
			}
			else if (x >= c && y >= c) { 
				placeToken(token, 330,350, 2, 2 );
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {
	}
}