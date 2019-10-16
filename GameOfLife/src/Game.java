import java.util.Scanner;

public class Game {

	public final static int SIZE = 20;

	boolean[][] board = new boolean[Game.SIZE][Game.SIZE];

	public static void main(String[] args) {

		Game game = new Game();

		// initial
		game.randomInitialization();
		game.setInitialState();
		

		Scanner input = new Scanner(System.in);

		while (true) {
			
			//clear
			Game.clearConsole();
			
			// print actual generation
			game.print();
			
			// wait for next
			String cmd = input.next();
			if(cmd.contains("exit"))
				break;
			
			//calculate next generation
			game.next();
			
		}

		input.close();

	}

	private void next() {
		boolean[][] next = new boolean[Game.SIZE][Game.SIZE];
		
		//starting at the second line
		for (int i=1; i<this.board.length-1; i++) {
			//starting at the second column
            for (int j=1; j<this.board[0].length-1; j++) {
                int surrounding = 0;
                if (this.board[i-1][j-1]) { surrounding++; } // up-left
                if (this.board[i-1][j])   { surrounding++; } // up
                if (this.board[i-1][j+1]) { surrounding++; } // up-right
                if (this.board[i][j-1])   { surrounding++; } // left
                if (this.board[i][j+1])   { surrounding++; } // right
                if (this.board[i+1][j-1]) { surrounding++; } // down-left
                if (this.board[i+1][j])   { surrounding++; } // down
                if (this.board[i+1][j+1]) { surrounding++; } // down-right
                
                // is a live cell?
                if (this.board[i][j]) {
                    // Cell is alive, Can the cell live? (2-3)
                    if ((surrounding == 2) || (surrounding == 3)) {
                    	next[i][j] = true;
                    } 
                } else {
                    // Cell is dead, will the cell be given birth? (3)
                    if (surrounding == 3) {
                    	next[i][j] = true;
                    }
                }
            }
        }
		this.board = next;
	}

	private void setInitialState() {
		
		// sequence
//		table[0][1] = true;
//		table[0][2] = true;
//		table[0][3] = true;

		// toad
//		table[4][3] = true;
//		table[4][4] = true;
//		table[4][5] = true;
//		table[5][2] = true;
//		table[5][3] = true;
//		table[5][4] = true;
		
		// Blinker
//		table[7][8] = true;
//		table[7][9] = true;
//		table[7][10] = true;
		
		// Glider
		board[1][2] = true;
		board[2][3] = true;
		board[3][1] = true;
		board[3][2] = true;
		board[3][3] = true;
		

	}

	private void print() {
		for (boolean[] x : board) {
			for (boolean y : x)
				System.out.print(y ? " #" : " ·");
			System.out.println();
		}
	}
	
	private void randomInitialization() {
		for (int x=0; x<Game.SIZE; x++ ) {
			for (int y=0; y<Game.SIZE; y++) {
				double r = Math.random() * 100;
				this.board[x][y] = (r < 40);
			}
		}
		
	}

	// Clear console/terminal
	public final static void clearConsole() {
		//a ugly way to clear the console...
		for(int i=0; i<=100; i++)
			System.out.println();
		
		//...because this doesn't work 
//		try {
//			final String os = System.getProperty("os.name");
//
//			if (os.contains("Windows")) {
//				Runtime.getRuntime().exec("cls");
//			} else {
//				Runtime.getRuntime().exec("clear");
//			}
//		} catch (final Exception e) {
//			// Handle any exceptions
//		}
	}

}
