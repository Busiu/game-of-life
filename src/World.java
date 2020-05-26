import java.util.List;
import java.util.Map;

public class World {

    private Map<Position, Cell> worldMapState;
    private int width;
    private int height;

    public World(Map<Position, Cell> worldMap, int width, int height) {
        this.worldMapState = worldMap;
        this.width = width;
        this.height = height;
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
        List<Position> neighboursPositions = position.getNeighbours();
        int nAliveNeighbours = 0;

        for (Position neighbourPosition : neighboursPositions) {
            Cell neighbourCell = worldMapState.get(neighbourPosition);
            if (neighbourCell != null) {
                if (neighbourCell.isAlive()) {
                    nAliveNeighbours++;
                }
            }
        }

        return new CellState(cell.isAlive(), nAliveNeighbours);
    }
}
