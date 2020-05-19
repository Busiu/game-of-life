public class World {

    private Cell[][] grid;
    private int width;
    private int height;

    public World(int width, int height) {
        this.grid = new Cell[width][height];
        this.width = width;
        this.height = height;
    }

    public World(char[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        this.grid = new Cell[this.width][this.height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (grid[y][x] == 'X')
                    this.grid[y][x] = new Cell(true);
                else
                    this.grid[y][x] = new Cell(false);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState getCellState(int y, int x) {
        boolean isAlive = grid[y][x].isAlive();
        int nAliveNeighbours = 0;

        if (y - 1 >= 0) {
            if (x - 1 >= 0) {
                if (grid[y - 1][x - 1].isAlive())
                    nAliveNeighbours++;
            }
            if (grid[y - 1][x].isAlive())
                nAliveNeighbours++;
            if (x + 1 < getWidth()) {
                if (grid[y - 1][x + 1].isAlive())
                    nAliveNeighbours++;
            }
        }
        if (x - 1 >= 0) {
            if (grid[y][x - 1].isAlive())
                nAliveNeighbours++;
        }
        if (x + 1 < getWidth()) {
            if (grid[y][x + 1].isAlive())
                nAliveNeighbours++;
        }
        if (y + 1 < getHeight()) {
            if (x - 1 >= 0) {
                if (grid[y + 1][x - 1].isAlive())
                    nAliveNeighbours++;
            }
            if (grid[y + 1][x].isAlive())
                nAliveNeighbours++;
            if (x + 1 < getWidth()) {
                if (grid[y + 1][x + 1].isAlive())
                    nAliveNeighbours++;
            }
        }

        return new CellState(isAlive, nAliveNeighbours);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x].isAlive())
                    result.append("X ");
                else
                    result.append(". ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}

