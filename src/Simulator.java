import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Simulator {

    private ArrayList<World> worlds;
    private Synchronizer synchronizer;
    private final static int nIters = 1000;

    public Simulator() {
        WorldInitializer worldInitializer = new WorldInitializer();
        this.worlds = new ArrayList<>();
        this.worlds.add(worldInitializer.initBulletWorld());

        this.synchronizer = new Synchronizer(30);
    }

    public void start() {
        for (int i = 0; i < worlds.size(); i++) {
            simulate(worlds.get(i));
        }
    }

    private void simulate(World world) {
        // Temporary moving to the next world
        for (int i = 0; i < nIters; i++) {
            display(world);
            synchronizer.startMeasureTime();
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
            synchronizer.synchronize();
        }
    }

    private void display(World world) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(world);
    }
}
