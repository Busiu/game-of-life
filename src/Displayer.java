import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Displayer extends Application {

    private final static int FPS = 30;
    private final static int nanosInSecond = 1_000_000_000;
    private final static int refreshRate = nanosInSecond / FPS;

    private final static int windowHeight = 800;
    private final static int windowWidth = 800;

    private Stage stage;

    private AnimationTimer simulationTimer;
    private Simulator simulator;

    private Map<Position, Rectangle> cells;

    @Override
    public void start(Stage stage) {
        simulator = new Simulator();
        this.stage = stage;

        initWindowForSimulator();
        createSimulationLoop();
    }

    private void initWindowForSimulator() {
        int currentWorldHeight = simulator.getHeightOfCurrentWorld();
        int currentWorldWidth = simulator.getWidthOfCurrentWorld();

        int cellWidth = windowWidth / currentWorldWidth;
        int cellHeight = windowHeight / currentWorldHeight;

        Map<Position, Cell> stateOfCurrentWorld = simulator.getStateOfCurrentWorld();
        cells = new HashMap<>();
        GridPane gridPane = new GridPane();

        for (int i = 0; i < currentWorldWidth; i++) {
            for (int j = 0; j < currentWorldHeight; j++) {
                Rectangle cell = new Rectangle(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                if (stateOfCurrentWorld.get(new Position(i, j)).isAlive()) {
                    cell.setFill(Color.AQUAMARINE);
                }
                else {
                    cell.setFill(Color.BROWN);
                }
                cells.put(new Position(i, j), cell);
                gridPane.add(cell, i, j);
            }
        }

        Scene scene = new Scene(gridPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    private void createSimulationLoop() {
        simulationTimer = new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= refreshRate) {
                    lastUpdate = now;
                    Map<Position, Cell> currentWorldMap = simulator.simulateOneStep();
                    update(currentWorldMap);
                }
            }
        };

        simulationTimer.start();
    }

    private void update(Map<Position, Cell> currentWorldMap) {
        for (Position position : currentWorldMap.keySet()) {
            if (currentWorldMap.get(position).isAlive()) {
                cells.get(position).setFill(Color.BROWN);
            }
            else {
                cells.get(position).setFill(Color.AQUAMARINE);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
