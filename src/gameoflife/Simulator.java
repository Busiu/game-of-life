package gameoflife;

import gameoflife.model.Cell;
import gameoflife.model.CellState;
import gameoflife.model.Position;
import gameoflife.model.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This class simulates our worlds. It contains a list of worlds so single instance is able to simulate multiple worlds
    in order.
 */
public class Simulator {

    /*
        List of our worlds to simulate.
     */
    private final List<World> worlds;

    /*
        Index defining which world from the list is being simulated now.
     */
    private int currentWorldIndex;

    public Simulator(List<World> worlds) {
        this.worlds = worlds;
        this.currentWorldIndex = 0;
    }

    /*
        This method simulates one step of a world -> creates new generation of cells.
     */
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

    /*
        This method returns state of current world which is used in gameoflife.Displayer.
     */
    public Map<Position, Cell> getStateOfCurrentWorldMap() {
        return worlds.get(currentWorldIndex).getWorldMapState();
    }

    /*
        This method enables to start simulate a new world from the list.
     */
    public boolean moveToTheNextWorld() {
        if (currentWorldIndex < worlds.size() - 1) {
            currentWorldIndex++;
            return true;
        }
        return false;
    }
}
