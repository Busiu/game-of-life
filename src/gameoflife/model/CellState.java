package gameoflife.model;

/*
    This class is a container which contains more information about cell state. Besides isAlive information we have got
    number of cell's neighbours which is necessary to determine if cell will be alive in the next generation.
 */
public class CellState {

    /*
        gameoflife.model.Cell's interior state.
     */
    private final boolean isAlive;

    /*
        Number of cell's neighbours.
     */
    private final int nAliveNeighbours;

    public CellState(boolean isAlive, int nAliveNeighbours) {
        this.isAlive = isAlive;
        this.nAliveNeighbours = nAliveNeighbours;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getnAliveNeighbours() {
        return nAliveNeighbours;
    }
}
