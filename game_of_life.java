public class game_of_life {
	public static class LifeGrid {	
		final int HEIGHT; // chieu cao cua mang choi
		final int WIDTH; // chieu rong cua mang choi
		final int DEAD_CELL = 0; // 2 trang thai cua te bao
		final int LIVE_CELL = 1;
		int[][] grid;
		int[][] copyGrid;
		// tao mang grid chua cac te bao, mac dinh tat ca deu chet
		public LifeGrid(int numRows, int numCols) {
			HEIGHT = numRows;
			WIDTH = numCols;
			grid = new int[HEIGHT][WIDTH];
			copyGrid = new int[HEIGHT][WIDTH];
		}
		// tra ve so hang
		public int numRow() {
			return HEIGHT;
		}
		// tra ve so cot
		public int numCols() {
			return WIDTH;
		}
		// cai dat cac te bao song theo danh sach
		public void configure(int[][] coordList) {
			// xoa het grid
			for(int i = 0; i < HEIGHT; i++) {
				for(int j = 0; j < WIDTH; j++) {
					this.clearCell(i, j);
				}
			}
			// dat cac te bao o vi tri theo danh sach thanh song
			for (int[] coord : coordList) {
				this.setCell(coord[0], coord[1]);
			}
		}
		// chuyen te bao thanh chet
		public void clearCell(int row, int col) {
			this.grid[row][col] = DEAD_CELL;
		}
		// dat te bao thanh song
		public void setCell(int row, int col) {
			this.grid[row][col] = LIVE_CELL;
		}
		// kiem tra te bao song hat khong
		public boolean isLiveCell(int row, int col) {
			return this.grid[row][col] == LIVE_CELL;
		}
		public int numLiveNeighbors(int row, int col) {
			int neighbors = 0;
			int up = row - 1;
			int down = row + 1;
			int left = col - 1;
			int right = col + 1;
			if (left >= 0) { // kiem tra 3 hang xom ben trai
				neighbors += copyGrid[row][left];
				if (up >= 0) {
					neighbors += copyGrid[up][left];
				}
				if (down < HEIGHT) {
					neighbors += copyGrid[down][left];
				}
			}
			if (right < WIDTH) {
				neighbors += copyGrid[row][right];
				if (up >= 0) {
					neighbors += copyGrid[up][right];
				}
				if (down < HEIGHT) {
					neighbors += copyGrid[down][right];
				}
			}
			if (up >= 0) { // dem hang xom phia tren
				neighbors += copyGrid[up][col];
			}
			if (down < HEIGHT) { // dem hang xom phia duoi
				neighbors += copyGrid[down][col];
			}
			return neighbors;
		}
		public void copyGrid() { // sao chep grid sang copyGrid de dung dem hang xom
			for(int i = 0; i < HEIGHT; i++) {
				for(int j = 0; j < WIDTH; j++) {
					copyGrid[i][j] = grid[i][j];
				}
			}
		}
		// kiem tra tat ca cac te bao cho the he sau va cap nhat lai grid
		public void evolve() {
			int neighbors = 0;
			copyGrid();
			for(int i = 0; i < HEIGHT; i++) {
				for(int j = 0; j < WIDTH; j++) {
					neighbors = 0;
					neighbors = numLiveNeighbors(i, j);
					if (grid[i][j] == LIVE_CELL) {
						if (neighbors == 2 || neighbors == 3) {
							grid[i][j] = LIVE_CELL;
						}
						if (neighbors < 2 || neighbors > 3) {
							grid[i][j] = DEAD_CELL;
						}
					} else if (grid[i][j] == DEAD_CELL) {
						if (neighbors == 3) {
							grid[i][j] = LIVE_CELL;
						}
					}
				}
			}
		}
	}
	public static void draw(int[][] grid) {
		for(int[] row : grid) {
			for (int i : row) {
	            System.out.print(i);
	            System.out.print(" ");
	        }
	        System.out.println();
        }	
	}
	public static void main(String[] args) {
		int[][] config = {{1,1}, {1,2}, {2,2}, {3,2}};
		int width = 5;
		int height = 5;
		int num_gens = 8;
		LifeGrid gameGrid = new LifeGrid(height, width);
		gameGrid.configure(config);
		System.out.println("Ban dau: ");
		draw(gameGrid.grid);
		for(int i = 0; i < num_gens; i++) {
			gameGrid.evolve();
			System.out.println("The he thu " + (i + 1) + ":");
			draw(gameGrid.grid);
		}
	} 
}
