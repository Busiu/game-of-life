import java.util.HashMap;
import java.util.Map;

public class World {

    private Map<Position, Cell> grid;
    private int width;
    private int height;

    public World(char[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        this.grid = new HashMap<>();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (grid[y][x] == 'X')
                    this.grid.put(new Position(x, y), new Cell(true));
                else
                    this.grid.put(new Position(x, y), new Cell(false));
            }
        }
    }

    public Map<Position, Cell> getGrid() {
        return grid;
    }

    public void setGrid(Map<Position, Cell> grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState getCellState(Position position) {
        Cell cell = grid.get(position);
        int[][] cellNeighbours = Cell.getNeighbours();
        int nAliveNeighbours = 0;

        for (int i = 0; i < cellNeighbours.length; i++) {
            Cell neighbourCell = grid.get(
                    new Position(position.getX() + cellNeighbours[i][0], position.getY() + cellNeighbours[i][1])
            );
            if (neighbourCell == null) {
                continue;
            }
            else if (neighbourCell.isAlive()) {
                nAliveNeighbours++;
            }
        }

        return new CellState(cell.isAlive(), nAliveNeighbours);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (grid.get(new Position(x, y)).isAlive()) {
                    result.append("X ");
                }
                else {
                    result.append(". ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}

