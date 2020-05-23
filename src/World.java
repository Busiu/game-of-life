import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World {

    private Map<Position, Cell> worldMapState;
    private int width;
    private int height;

    public World(char[][] pattern) {
        this.width = pattern[0].length;
        this.height = pattern.length;
        this.worldMapState = new HashMap<>();
        for (int yCoord = 0; yCoord < this.height; yCoord++) {
            for (int xCoord = 0; xCoord < this.width; xCoord++) {
                if (pattern[yCoord][xCoord] == 'X') {
                    this.worldMapState.put(new Position(xCoord, yCoord), new Cell(true));
                }
                else {
                    this.worldMapState.put(new Position(xCoord, yCoord), new Cell(false));
                }
            }
        }
    }

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.worldMapState = new HashMap<>();
        Random generator = new Random();
        for (int yCoord = 0; yCoord < this.height; yCoord++) {
            for (int xCoord = 0; xCoord < this.width; xCoord++) {
                if (generator.nextDouble() > 0.5) {
                    this.worldMapState.put(new Position(xCoord, yCoord), new Cell(true));
                }
                else {
                    this.worldMapState.put(new Position(xCoord, yCoord), new Cell(false));
                }
            }
        }
    }

    public Map<Position, Cell> getWorldMapState() {
        return worldMapState;
    }

    public void setWorldMapState(Map<Position, Cell> worldMapState) {
        this.worldMapState = worldMapState;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState getCellState(Position position) {
        Cell cell = worldMapState.get(position);
        int[][] cellNeighbours = Cell.getNeighbours();
        int nAliveNeighbours = 0;

        for (int i = 0; i < cellNeighbours.length; i++) {
            Cell neighbourCell = worldMapState.get(
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
        for (int yCoord = 0; yCoord < getHeight(); yCoord++) {
            for (int xCoord = 0; xCoord < getWidth(); xCoord++) {
                if (worldMapState.get(new Position(xCoord, yCoord)).isAlive()) {
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

