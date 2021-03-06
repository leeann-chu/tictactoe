/* Name: LeeAnn Chu
 * Netid: lchu7
 * Assignment: Project 3 - Tic-Tac-Toe
 * Lab section: TR 12:30pm
 * 
 * I did not collaborate with anyone on this assignment.
 */

public class TTTGame extends TTTCanvas {
	public static TTTGrid grid = new TTTGrid();
	public static TTTCanvas canvas = new TTTCanvas();
	private static int[] top = new int[3], mid = new int[3], bot = new int[3], left = new int[3], center = new int[3], right = new int[3], lDiag = new int[3], rDiag = new int[3];
	public static int won;
	public static int draws;
	public static int xs;
	public static int os;
	
	public static void main(String[] args) {
		new TTTCanvas().setVisible(true);	
		canvas.setScoreBoard();
	}
	
	public void startGame() { 
		if (grid.getTurnNum() >= 5 && grid.getTurnNum() <= 9) {
			countArrays();
		}
	}
	public static void countArrays() { 
		//Top
		for (int i = 0; i < 3; i++) { 
			top[i] = grid.getArray(0,i);
		}
		countNum(top);
		//Middle
		for (int i = 0; i < 3; i++) { 
			mid[i] = grid.getArray(1,i);
		}
		countNum(mid);
		//Bottom
		for (int i = 0; i < 3; i++) { 
			bot[i] = grid.getArray(2,i);
		}
		countNum(bot);
		//Left
		for (int i = 0; i < 3; i++) { 
			left[i] = grid.getArray(i,0);
		}
		countNum(left);
		//center
		for (int i = 0; i < 3; i++) { 
			center[i] = grid.getArray(i,1);
		}
		countNum(center);
		//right
		for (int i = 0; i < 3; i++) { 
			right[i] = grid.getArray(i,2);
		}
		
		countNum(right);
		//left diagonal		
		for (int i = 0; i < 3; i++) { 
			lDiag[i] = grid.getArray(i,i);
		}
		countNum(lDiag);
		//right diagonal
		int j = 2;
		for (int i = 0; i < 3; i++) { 
			rDiag[i] = grid.getArray(i,j);
			j = j-1;
		}
		countNum(rDiag);
		
	}
	
	public static void countNum(int[] newArray) {
		//counts how many times a number appears in the roll
		int j = 0;
		while (j < newArray.length) {
			int total = 0;
			for (int i = 0; i < 3; i++) { 
				if (newArray[i] == j) { 
					total++;
				}
			}
			if (j > 0) { 
				if (total == 3 && j == 1) { 
					won = 1;
					canvas.setStatus(true);
					
				}
				else if (total == 3 && j == 2) { 
					won = 2;
					canvas.setStatus(true);
				}
			}
			j++;
		}
	}
	
	public int getWon() { 
		return won;
	}
	public void addDraws() { 
		draws++;
	}
	public void addXs() { 
		xs++;
	}
	public void addOs() { 
		os++;
	}
	public String getDraws() { 
		return Integer.toString(draws);
	}
	public String getXs() { 
		return Integer.toString(xs);
	}
	public String getOs() { 
		return Integer.toString(os);
	}
}