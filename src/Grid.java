import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class Grid {

	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	//Video Link: https://youtu.be/t9CNYiWYzRg
	
	//contructors
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		
		bombGrid = new boolean[10][10];
		countGrid = new int[10][10];
		
		createBombGrid();
		createCountGrid();
		
		
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(countGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public Grid(int rows, int columns) {
		
		this.numRows = rows;
		this.numColumns = columns;
		numBombs = 25;
		
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		
		createBombGrid();
		createCountGrid();
		
	}
	
	public Grid(int rows, int columns, int bombs) {

		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = bombs;
		
		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int[numRows][numColumns];
		
		createBombGrid();
		createCountGrid();
	}
	
	//getters
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean[][] getBombGrid() {
		boolean[][] cloneBombGrid = new boolean[numRows][numColumns];
		for (int i=0;i<numRows;i++) {
			for (int j=0; j<numColumns;j++) {
				cloneBombGrid[i][j] = bombGrid[i][j];
			}
		}
		return cloneBombGrid;
	}
	
	public int[][] getCountGrid() {
		int[][] cloneCountGrid = new int[numRows][numColumns];
		for (int i=0;i<numRows;i++) {
			for (int j=0; j<numColumns;j++) {
				cloneCountGrid[i][j] = countGrid[i][j];
			}
		}
		return cloneCountGrid;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row, int column) {
		//return countGrid[row][column];
		int count = 0;
		boolean notLeftSide = column > 0;
		boolean notRightSide = column < (numColumns - 1);
		if(isBombAtLocation(row, column))count++;

		// Check for bombs above
		if(row > 0) {
			if(isBombAtLocation(row - 1, column))
				count++; // Above

			if(notLeftSide)
				if(isBombAtLocation(row - 1, column - 1))
					count++; // Top Left

			if(notRightSide)
				if(isBombAtLocation(row - 1, column + 1))
					count++; // Top Right
		}

		// Check for bombs adjacent
		if(notLeftSide)
			if(isBombAtLocation(row, column - 1)) {
				count++; // Left
			}

		if(notRightSide)
			if(isBombAtLocation(row, column + 1)) {
				count++; // Right
			}

		// Check for bombs underneath
		if(row < (numRows - 1)) {
			if(isBombAtLocation(row + 1, column))
				count++; // Below

			if(notLeftSide)
				if(isBombAtLocation(row + 1, column - 1))
					count++; // Bottom Left

			if(notRightSide)
				if(isBombAtLocation(row + 1, column + 1))
					count++; // Bottom Right
		}

		return count;
	}
	
	public void createBombGrid() {
		
		for (int i=0; i<numRows;i++) {
			for (int j=0; j<numColumns;j++) {
				bombGrid[i][j] = false;
			}
		}
		
		//Random rand = new Random();
		/*int row = rand.nextInt(numRows-1);
		int column = rand.nextInt(numColumns-1);
		for (int i=0;i<=numBombs;i++) {
			while (bombGrid[row][column] == true) {
				row = rand.nextInt(numRows-1);
				column = rand.nextInt(numColumns-1);
			}
			bombGrid[row][column] = true;
		} */
		
		for (int i=0; i< numBombs; i++) {
			int row = (int) ((Math.random()* numRows));
			int column = (int) (Math.random()* numColumns);
			while(bombGrid[row][column] == true) {
				row = (int) ((Math.random()* numRows));
				column = (int) (Math.random()* numColumns);
			}
			bombGrid[row][column] = true;
		}
	}
	
	public void createCountGrid() {
		
		for(int row = 0; row < bombGrid.length; row++) {
			for(int col = 0; col < bombGrid[row].length; col++) {
				countGrid[row][col] = getCountAtLocation(row, col);
			}
		}
		
		
		
	}
	
	
	
}















