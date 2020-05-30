package gameoflife;

import gameoflife.model.Cell;
import gameoflife.model.Position;
import gameoflife.model.World;
import gameoflife.worldsloader.NoValidMapException;
import gameoflife.worldsloader.WorldLoaderSupervisor;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This class is a main class in this project. Its main responsibility is to display another generations of our game
    of life and to communicate with Simulator instance.
 */
public class Displayer extends Application {

    /*
        Frequency of the game loop constants. To change number of FPS, just change value of FPS constant.
     */
    private final static int FPS = 30;
    private final static int NANOS_IN_SECOND = 1_000_000_000;
    private final static int REFRESH_RATE = NANOS_IN_SECOND / FPS;

    /*
        Application window size in pixels.
     */
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH = 800;

    /*
        Folder from which we will receive initial patterns to our simulation.
     */
    private final static String WORLD_MAP_FOLDER = "WorldMaps";

    /*
        Predefined colors of cells.
     */
    private final static Paint ALIVE_COLOR = Color.BLACK;
    private final static Paint DEAD_COLOR = Color.GREEN;

    /*
        JavaFx thing. Stage is necessary to display our simulation.
     */
    private Stage simulationStage;

    /*
        Contains and works with all logic of a simulation.
     */
    private Simulator simulator;

    /*
        World map/grid representation.
     */
    private Map<Position, Rectangle> worldCells;

    /*
        This method gets initial patterns from given folder and then starts a simulation.
     */
    @Override
    public void start(Stage stage) {
        WorldLoaderSupervisor worldLoaderSupervisor = new WorldLoaderSupervisor();
        try {
            List<World> worlds = worldLoaderSupervisor.loadWorldsFromFile(WORLD_MAP_FOLDER);
            simulator = new Simulator(worlds);

            this.simulationStage = stage;

            createDisplayForSimulationOfNewWorld();
            createSimulationLoop();
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + " -> probably this folder does not exist!");
            Platform.exit();
        }
        catch (NoValidMapException e) {
            System.out.println(e.getMessage());
            Platform.exit();
        }
    }

    /*
        This method translates our world's model into graphical representation.
     */
    private void createDisplayForSimulationOfNewWorld() {
        int currentWorldMapHeight = simulator.getHeightOfCurrentWorldMap();
        int currentWorldMapWidth = simulator.getWidthOfCurrentWorldMap();

        int cellWidth = WINDOW_WIDTH / currentWorldMapWidth;
        int cellHeight = WINDOW_HEIGHT / currentWorldMapHeight;

        Map<Position, Cell> stateOfCurrentWorldMap = simulator.getStateOfCurrentWorldMap();
        worldCells = new HashMap<>();
        GridPane worldMapGridPane = new GridPane();

        for (int xCoord = 0; xCoord < currentWorldMapWidth; xCoord++) {
            for (int yCoord = 0; yCoord < currentWorldMapHeight; yCoord++) {
                Rectangle cell = new Rectangle(xCoord * cellWidth, yCoord * cellHeight, cellWidth, cellHeight);
                if (stateOfCurrentWorldMap.get(new Position(xCoord, yCoord)).isAlive()) {
                    cell.setFill(ALIVE_COLOR);
                }
                else {
                    cell.setFill(DEAD_COLOR);
                }
                worldCells.put(new Position(xCoord, yCoord), cell);
                worldMapGridPane.add(cell, xCoord, yCoord);
            }
        }

        Scene simulationScene = new Scene(worldMapGridPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        createKeyListeners(simulationScene);

        simulationStage.setScene(simulationScene);
        simulationStage.show();
    }

    /*
        This method creates listeners for our simulation.
     */
    private void createKeyListeners(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                setEnterListener();
            }
        });
    }

    /*
        This method defines enter listener behaviour. Clicking enter causes moving to the next world/grid if we have one
        or shutting down the app.
     */
    private void setEnterListener() {
        if (simulator.moveToTheNextWorld()) {
            createDisplayForSimulationOfNewWorld();
        }
        else {
            Platform.exit();
        }
    }

    /*
        This method creates an animation of our simulation and set frequency of the game loop into predefined
        FPS number.
     */
    private void createSimulationLoop() {
        AnimationTimer simulationTimer = new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= REFRESH_RATE) {
                    lastUpdate = now;
                    Map<Position, Cell> currentWorldMapState = simulator.simulateOneStep();
                    updateDisplay(currentWorldMapState);
                }
            }
        };

        simulationTimer.start();
    }

    /*
        This method updates display to the newest world map state (which is passed by an argument).
     */
    private void updateDisplay(Map<Position, Cell> currentWorldMapState) {
        for (Position position : currentWorldMapState.keySet()) {
            Paint cellColor;
            if (currentWorldMapState.get(position).isAlive()) {
                cellColor = ALIVE_COLOR;
            }
            else {
                cellColor = DEAD_COLOR;
            }
            worldCells.get(position).setFill(cellColor);
        }
    }

    /*
        Main method runs start() method in a nutshell.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
