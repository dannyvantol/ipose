package enginedingen;

import gamedingen.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Engine extends Application {
    private Game game;
    private Renderer renderer;

    public Engine(Game game) {
        this.game = game;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.renderer = new Renderer(game,primaryStage);


        new AnimationTimer() {
            @Override
            public void handle(long now) {

                //behaviors.behavexD();
                renderer.render();

            }
        }.start();
    }
}
