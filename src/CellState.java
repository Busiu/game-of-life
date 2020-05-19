public class CellState {

    private boolean isAlive;
    private int nAliveNeighbours;

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
