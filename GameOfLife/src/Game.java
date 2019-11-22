import java.util.Scanner;

/**
 * Game of life - CICCC
 * @author alexkayser
 *
 */
public class Game {

	//public final static int SIZE = 20;
	public final static int XSIZE = 20;
	public final static int YSIZE = 30;

	//the main game board
	boolean[][] board = new boolean[Game.XSIZE][Game.YSIZE];

	/**
	 * Starts the game and controls steps
	 * @param args
	 */
	public static void main(String[] args) {

		Game game = new Game();

		// initial
		//game.randomInitialization();
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
			
			//if typed "random" on console it fills a percentage of cells on the board.
			if("random".equalsIgnoreCase(cmd)) {
				System.out.print("Percentage [1-100]: ");
				int percentage = input.nextInt();
				game.randomInitialization(percentage);
			}
			
		}

		input.close();

	}

	private void next() {
		
		//temporary hidden board
		boolean[][] next = new boolean[Game.XSIZE][Game.YSIZE];
		
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

	/**
	 * Set initial 
	 */
	private void setInitialState() {

		// toad
		board[14][3] = true;
		board[14][4] = true;
		board[14][5] = true;
		board[15][2] = true;
		board[15][3] = true;
		board[15][4] = true;
		
		// Blinker
		board[7][13] = true;
		board[7][14] = true;
		board[7][15] = true;
		
		// Glider
		board[1][2] = true;
		board[2][3] = true;
		board[3][1] = true;
		board[3][2] = true;
		board[3][3] = true;
		

	}

	/**
	 * Prints the board using characters
	 */
	private void print() {
		for (boolean[] x : board) {
			for (boolean y : x)
				System.out.print(y ? " #" : " ·");
			System.out.println();
		}
	}
	
	/**
	 * It fill out a percentage of the board
	 * @param percentage 
	 */
	private void randomInitialization(int percentage) {
		// iterate lines
		for (int horiz=0; horiz<Game.XSIZE; horiz++ ) {
			//iterate colunms
			for (int vert=0; vert<Game.YSIZE; vert++) {
				//calculate a random number between 1 and 100
				double r = Math.random() * 100;
				boolean before = this.board[horiz][vert];
				//if this number is less then the percentage, change to true
				//if the position already is true keeps true (conditional OR)
				boolean turnOn = before || (r < percentage);
				this.board[horiz][vert] = turnOn;
			}
		}
		
	}

	/**
	 * trying to clear the console, not in the best way yet.
	 */
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
