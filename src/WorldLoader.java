import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldLoader {

    public World loadWorld(File file) throws IOException {
        Map<Position, Cell> worldMap = new HashMap<>();

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        int worldWidth = Integer.parseInt(fileReader.readLine());
        int worldHeight = Integer.parseInt(fileReader.readLine());
        String line;

        System.out.println(file.getAbsolutePath());
        System.out.println(worldWidth);
        System.out.println(worldHeight);

        for (int yCoord = 0; yCoord < worldHeight; yCoord++) {
            line = fileReader.readLine();
            System.out.println(line);
            if (line == null) {
                //TODO: Exception ktory mowi, ze zly format pliku!
            }
            if (line.length() != worldWidth) {
                //TODO: Exception ktory mowi, ze zly format pliku!
            }

            for (int xCoord = 0; xCoord < worldWidth; xCoord++) {
                char cellValueInChar = line.charAt(xCoord);
                Cell cell = new Cell(true);
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
                        //TODO: Exception, ze znaleziono nieprawidlowy znak w char mapie
                    }
                }

                worldMap.put(new Position(xCoord, yCoord), cell);
            }
        }

        return new World(worldMap, worldWidth, worldHeight);
    }
}
