import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Displayer extends Application {

    private final static int FPS = 30;
    private final static int NANOS_IN_SECOND = 1_000_000_000;
    private final static int REFRESH_RATE = NANOS_IN_SECOND / FPS;

    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH = 800;

    private final static Paint ALIVE_COLOR = Color.BROWN;
    private final static Paint DEAD_COLOR = Color.AQUAMARINE;

    private Stage stage;

    private Simulator simulator;

    private Map<Position, Rectangle> worldCells;

    @Override
    public void start(Stage stage) {
        simulator = new Simulator();
        this.stage = stage;

        initDisplayForSimulationOfNewWorld();
        createSimulationLoop();
    }

    private void initDisplayForSimulationOfNewWorld() {
        int currentWorldMapHeight = simulator.getHeightOfCurrentWorldMap();
        int currentWorldMapWidth = simulator.getWidthOfCurrentWorldMap();

        int cellWidth = WINDOW_WIDTH / currentWorldMapWidth;
        int cellHeight = WINDOW_HEIGHT / currentWorldMapHeight;

        Map<Position, Cell> stateOfCurrentWorldMap = simulator.getStateOfCurrentWorldMap();
        worldCells = new HashMap<>();
        GridPane worldMapGridPane = new GridPane();

        for (int i = 0; i < currentWorldMapWidth; i++) {
            for (int j = 0; j < currentWorldMapHeight; j++) {
                Rectangle cell = new Rectangle(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                if (stateOfCurrentWorldMap.get(new Position(i, j)).isAlive()) {
                    cell.setFill(ALIVE_COLOR);
                }
                else {
                    cell.setFill(DEAD_COLOR);
                }
                worldCells.put(new Position(i, j), cell);
                worldMapGridPane.add(cell, i, j);
            }
        }

        Scene scene = new Scene(worldMapGridPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

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

    private void updateDisplay(Map<Position, Cell> currentWorldMapState) {
        for (Position position : currentWorldMapState.keySet()) {
            if (currentWorldMapState.get(position).isAlive()) {
                worldCells.get(position).setFill(ALIVE_COLOR);
            }
            else {
                worldCells.get(position).setFill(DEAD_COLOR);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
