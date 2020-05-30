import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
    This class loads world pattern from given file. It returns world which is ready to simulate.
 */
public class WorldLoader {

    public World loadWorld(File file) throws IOException, FileFormatException {
        Map<Position, Cell> worldMap = new HashMap<>();

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        int worldWidth;
        int worldHeight;
        try {
            worldWidth = Integer.parseInt(fileReader.readLine());
            worldHeight = Integer.parseInt(fileReader.readLine());
        }
        catch (NumberFormatException e) {
            throw new FileFormatException(file.getName() +
                " not loaded" +
                " -> two first rows (width and height of the map) are in the wrong format!"
            );
        }

        String line;

        for (int yCoord = 0; yCoord < worldHeight; yCoord++) {
            line = fileReader.readLine();
            if (line == null) {
                throw new FileFormatException(file.getName() +
                    " not loaded" +
                    " -> world height given at the beginning of this file mismatches the actual number of rows!"
                );
            }
            if (line.length() != worldWidth) {
                throw new FileFormatException(file.getName() +
                    " not loaded" +
                    " -> certain row contains different number of chars than one given at the beginning of this file!"
                );
            }

            for (int xCoord = 0; xCoord < worldWidth; xCoord++) {
                char cellValueInChar = line.charAt(xCoord);
                Cell cell;
                switch (cellValueInChar) {
                    case 'X': {
                        cell = new Cell(true);
                        break;
                    }
                    case '0': {
                        cell = new Cell(false);
                        break;
                    }
                    case '?': {
                        Random generator = new Random();
                        if (generator.nextDouble() > 0.5) {
                            cell = new Cell(true);
                        }
                        else {
                            cell = new Cell(false);
                        }
                        break;
                    }
                    default: {
                        throw new FileFormatException(file.getName() +
                            " not loaded" +
                            " -> world map contains char different from 'X', '0' or '?' !"
                        );
                    }
                }

                worldMap.put(new Position(xCoord, yCoord), cell);
            }
        }

        return new World(worldMap, worldWidth, worldHeight);
    }
}
