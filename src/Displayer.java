import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Displayer extends Application {

    private final static int FPS = 30;
    private final static int nanosInSecond = 1_000_000_000;
    private final static int refreshRate = nanosInSecond / FPS;

    private final static int windowHeight = 800;
    private final static int windowWidth = 800;

    private Stage stage;

    private AnimationTimer simulationTimer;
    private Simulator simulator;

    private List<Rectangle> cells;

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

        cells = new ArrayList<>();
        GridPane gridPane = new GridPane();

        for (int i = 0; i < currentWorldWidth; i++) {
            for (int j = 0; j < currentWorldHeight; j++) {
                Rectangle cell = new Rectangle(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                cell.setFill(Color.AQUAMARINE);
                cells.add(cell);
                gridPane.add(cell, j, i);
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
                    simulator.simulateOneStep();
                }
            }
        };

        simulationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
