import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Simulator {

    private ArrayList<World> worlds;
    private int currentWorld;

    public Simulator() {
        WorldInitializer worldInitializer = new WorldInitializer();
        this.worlds = new ArrayList<>();
        this.worlds.add(worldInitializer.initBulletWorld());
        this.currentWorld = 0;
    }

    public void simulateOneStep() {
        World world = worlds.get(currentWorld);
        Map<Position, Cell> newGrid = new HashMap<>();
        for (Position position : world.getGrid().keySet()) {
            CellState cellState = world.getCellState(position);
            if (cellState.isAlive() && cellState.getnAliveNeighbours() < 2) {
                newGrid.put(position, new Cell(false));
            }
            else if (cellState.isAlive() && cellState.getnAliveNeighbours() < 4) {
                newGrid.put(position, new Cell(true));
            }
            else if (cellState.isAlive()) {
                newGrid.put(position, new Cell(false));
            }
            else if (!cellState.isAlive() && cellState.getnAliveNeighbours() == 3) {
                newGrid.put(position, new Cell(true));
            }
            else {
                newGrid.put(position, new Cell(cellState.isAlive()));
            }
        }
        world.setGrid(newGrid);
        display(world);
    }

    private void display(World world) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(world);
    }
}
