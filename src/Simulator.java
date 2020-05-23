import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {

    private List<World> worlds;
    private int currentWorldIndex;

    public Simulator() {
        WorldInitializer worldInitializer = new WorldInitializer();
        this.worlds = new ArrayList<>();
        this.worlds.add(worldInitializer.initBulletWorld());
        this.worlds.add(worldInitializer.initWorld1());
        this.worlds.add(worldInitializer.initWorld2());
        this.worlds.add(worldInitializer.initWorld3());
        this.currentWorldIndex = 0;
    }

    public Map<Position, Cell> simulateOneStep() {
        World world = worlds.get(currentWorldIndex);
        Map<Position, Cell> newWorldMapState = new HashMap<>();
        for (Position position : world.getWorldMapState().keySet()) {
            CellState cellState = world.getCellState(position);
            if (cellState.isAlive() && cellState.getnAliveNeighbours() < 2) {
                newWorldMapState.put(position, new Cell(false));
            }
            else if (cellState.isAlive() && cellState.getnAliveNeighbours() < 4) {
                newWorldMapState.put(position, new Cell(true));
            }
            else if (cellState.isAlive()) {
                newWorldMapState.put(position, new Cell(false));
            }
            else if (!cellState.isAlive() && cellState.getnAliveNeighbours() == 3) {
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

    public void moveToTheNextWorld() {
        if (currentWorldIndex < worlds.size() - 1) {
            currentWorldIndex++;
        }
    }
}
