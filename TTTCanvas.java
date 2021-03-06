import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

public class TTTCanvas extends JFrame {
	
	private final JPanel topPanel = new JPanel();
	private final JPanel newGamePanel = new JPanel();
	private final JPanel turnIndicatorPanel = new JPanel();
	private final JPanel scoreBoard = new JPanel();
	private final JPanel paintPanel = new JPanel();
	private final JPanel blank = new JPanel();
	private final JPanel blank2 = new JPanel();
	private final JPanel blank3 = new JPanel();
	private final JPanel blank4 = new JPanel();
	
	private static JLabel turnLabel = new JLabel("Start New Game");
	private static JLabel scoreLabel = new JLabel("ScoreBoard");
	
	public static boolean newGame = true;
	public static int q = 0;
	
	Container cp;
	public static TTTGrid grid = new TTTGrid();
	public static TTTGame game = new TTTGame();
	
	public TTTCanvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//------------------JPanel Setup------------------//	
		cp = getContentPane();		
		cp.setPreferredSize(new Dimension(480,580));
//		topPanel.setBackground(new Color(10,245,215));
		blank.setPreferredSize(new Dimension(70,50));
		blank2.setPreferredSize(new Dimension(70,50));
		blank3.setPreferredSize(new Dimension(70,50));
		
		//Top Panel	 
	    topPanel.setLayout(new GridLayout(1,3,60,0));
	    topPanel.add(newGamePanel);
	    topPanel.add(turnIndicatorPanel);
	    topPanel.add(scoreBoard);
	    add(topPanel, BorderLayout.NORTH);
	    
	    //Sub Panels
	    newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
	    newGamePanel.add(blank);
	    JButton button = new JButton("New Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newGame) {
					button.setText("Clear Board");
					turnLabel.setText("Player 1's Turn");
					newGame = false;
					game.startGame();
				}
				else {
					//Button = Reset Game
					button.setText("New Game");
					turnLabel.setText("Start New Game");
					repaint();
					grid.clearGrid();
					grid.setPlayer("p1");
					grid.setTurnNum(0);
					newGame = true;
				}
			} 
		});
		newGamePanel.add(button);
		
		turnIndicatorPanel.setLayout(new BoxLayout(turnIndicatorPanel, BoxLayout.Y_AXIS));
		turnIndicatorPanel.add(blank2);
	    turnIndicatorPanel.add(turnLabel);
	    
	    scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));
	    scoreBoard.add(blank3);
	    scoreBoard.add(scoreLabel);
	    
	    //Grid
	    paintPanel.add(new TTTGrid());
	    paintPanel.setLayout(new BoxLayout(paintPanel, BoxLayout.Y_AXIS));
	    add(paintPanel, BorderLayout.CENTER);    
	    pack();
	}
	
	public static void setPlayerLabel(String turn) { 
		if (newGame == false && grid.getTurnNum() >= 9) { 
			turnLabel.setText("It's a Draw!");
			game.addDraws();
			setScoreBoard();
		}
		else if (newGame == false && grid.getPlayer().equals("p1")) { 
			turnLabel.setText("Player 1's Turn");
		}
		else if (newGame == false && grid.getPlayer().equals("p2")) { 
			turnLabel.setText("Player 2's Turn");
		}
		else if (newGame == true && (game.getWon() == 1)) { 
			turnLabel.setText("X Won!");
			game.addXs();
			setScoreBoard();
			setStatus(false);
		}
		else if (newGame == true && game.getWon() == 2) { 
			turnLabel.setText("O Won!");
			game.addOs();
			setScoreBoard();
			setStatus(false);
		}
	}
	
	public boolean getStatus() { 
		return newGame;
	}
	public static void setStatus(boolean status) { 
		newGame = status;
	}
	public static String getTurnLabel() { 
		return turnLabel.getText();
	}
	public static void setScoreBoard() { 
		scoreLabel.setText("<html>Player 1's Wins: " + game.getXs() + "<br>Player 2's Wins: " + game.getOs() + "<br>Draws: " + game.getDraws() + "</html>");
	}
	
}
