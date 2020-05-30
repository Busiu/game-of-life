/*
    This class is a model representation of a single cell.
 */
public class Cell {

    /*
        Cell's interior state.
     */
    private final boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
