import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Displayer extends Application {

    private final static int FPS = 30;
    private final static int nanosInSecond = 1_000_000_000;
    private final static int refreshRate = nanosInSecond / FPS;

    private AnimationTimer simulationTimer;
    private Simulator simulator;

    @Override
    public void start(Stage stage) {
        StackPane stackPane = new StackPane();
        Label label = new Label("Hello World!");
        stackPane.getChildren().add(label);

        Scene scene = new Scene(stackPane, 640, 480);
        stage.setScene(scene);
        stage.show();

        simulator = new Simulator();

        createSimulationLoop();
    }

    private void createSimulationLoop() {
        simulationTimer = new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= refreshRate) {
                    simulator.simulateOneStep();
                    lastUpdate = now;
                }
            }
        };

        simulationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
