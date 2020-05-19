import java.util.ArrayList;

public class Simulator {

    private ArrayList<World> worlds;
    private Synchronizer synchronizer;

    public Simulator() {
        WorldInitializer worldInitializer = new WorldInitializer();
        this.worlds = new ArrayList<>();
        this.worlds.add(worldInitializer.initBulletWorld());

        this.synchronizer = new Synchronizer(30);
    }

    public void start() {
        for (int i = 0; i < worlds.size(); i++)
            simulate(worlds.get(i));
    }

    private void simulate(World world) {
        // Temporary moving to the next world
        for (int i = 0; i < 1000; i++) {
            display(world);
            synchronizer.startMeasureTime();
            Cell[][] newGrid = new Cell[world.getWidth()][world.getHeight()];
            for (int y = 0; y < world.getHeight(); y++) {
                for (int x = 0; x < world.getWidth(); x++) {

                    CellState cellState = world.getCellState(y, x);
                    if (cellState.isAlive() && cellState.getnAliveNeighbours() < 2)
                        newGrid[y][x] = new Cell(false);
                    else if (cellState.isAlive() && cellState.getnAliveNeighbours() < 4)
                        newGrid[y][x] = new Cell(true);
                    else if (cellState.isAlive())
                        newGrid[y][x] = new Cell(false);
                    else if (!cellState.isAlive() && cellState.getnAliveNeighbours() == 3)
                        newGrid[y][x] = new Cell(true);
                    else
                        newGrid[y][x] = new Cell(cellState.isAlive());
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
