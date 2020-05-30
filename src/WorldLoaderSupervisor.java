import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldLoaderSupervisor {

    private final WorldLoader worldLoader = new WorldLoader();

    public List<World> loadWorldsFromFile(String fileName) throws IOException, NoValidMapException{
        List<File> filesInFolder = Files.walk(Paths.get(fileName))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        List<World> worlds = new ArrayList<>();
        for (File file : filesInFolder) {
            try {
                worlds.add(worldLoader.loadWorld(file));
            }
            catch (FileFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        if (worlds.size() == 0) {
            throw new NoValidMapException(fileName +
                    " contains no valid world maps. Shutting down the app..."
            );
        }

        return worlds;
    }
}
