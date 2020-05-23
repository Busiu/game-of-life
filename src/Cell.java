public class Cell {

    private final boolean isAlive;
    private final static int[][] neighbours = {
            {-1,-1},
            {-1, 0},
            {-1, 1},
            { 0,-1},
            { 0, 1},
            { 1,-1},
            { 1, 0},
            { 1, 1}
    };

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public static int[][] getNeighbours() {
        return neighbours;
    }
}
