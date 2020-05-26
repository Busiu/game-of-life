import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {

    private List<World> worlds;
    private int currentWorldIndex;

    public Simulator(List<World> worlds) {
        this.worlds = worlds;
        this.currentWorldIndex = 0;
    }

    public Map<Position, Cell> simulateOneStep() {
        World world = worlds.get(currentWorldIndex);
        Map<Position, Cell> newWorldMapState = new HashMap<>();

        for (Position position : world.getWorldMapState().keySet()) {
            CellState cellState = world.getCellState(position);
            boolean isAlive = cellState.isAlive();
            int nAliveNeighbours = cellState.getnAliveNeighbours();

            if (isAlive && nAliveNeighbours < 2) {
                newWorldMapState.put(position, new Cell(false));
            }
            else if (isAlive && nAliveNeighbours < 4) {
                newWorldMapState.put(position, new Cell(true));
            }
            else if (isAlive) {
                newWorldMapState.put(position, new Cell(false));
            }
            else if (nAliveNeighbours == 3) {
                newWorldMapState.put(position, new Cell(true));
            }
            else {
                newWorldMapState.put(position, new Cell(cellState.isAlive()));
            }
        }
        world.setWorldMapState(newWorldMapState);

        return getStateOfCurrentWorldMap();
    }

    public int getHeightOfCurrentWorldMap() {
        return worlds.get(currentWorldIndex).getHeight();
    }

    public int getWidthOfCurrentWorldMap() {
        return worlds.get(currentWorldIndex).getWidth();
    }

    public Map<Position, Cell> getStateOfCurrentWorldMap() {
        return worlds.get(currentWorldIndex).getWorldMapState();
    }

    public boolean moveToTheNextWorld() {
        if (currentWorldIndex < worlds.size() - 1) {
            currentWorldIndex++;
            return true;
        }
        return false;
    }
}
