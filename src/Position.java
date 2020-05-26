import java.util.ArrayList;
import java.util.List;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Position> getNeighbours() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(x - 1, y - 1));
        positions.add(new Position(x, y - 1));
        positions.add(new Position(x + 1, y - 1));
        positions.add(new Position(x - 1, y));
        positions.add(new Position(x + 1, y));
        positions.add(new Position(x - 1, y + 1));
        positions.add(new Position(x, y + 1));
        positions.add(new Position(x + 1, y + 1));
        return positions;
    }

    @Override
    public int hashCode() {
        return 1000000 * x + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}
