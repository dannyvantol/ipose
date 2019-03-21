package enginedingen;

import behaviourdingen.Behavior;
import behaviourdingen.BehaviorManager;
import behaviourdingen.thread.BehaviorRunnable;
import gamedingen.Element;
import gamedingen.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Engine extends Application {
    private Game game;
    private Renderer renderer;

    public Engine(Game game) {
        this.game = game;
    }

    public HashMap<Class<? extends Behavior>, BehaviorManager> behaviors = new HashMap();

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

    public void addBehavior(Class<? extends Behavior> behavior, BehaviorManager behaviorManager) {
        this.behaviors.put(behavior, behaviorManager);
    }

    public void focusOnElement(Element element) {
        // Camera focus on element
    }

    public void loop() {
        Thread keyInputThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread behaviorThread = new Thread(new BehaviorRunnable(this));

        keyInputThread.start();
        behaviorThread.start();
    }

    public Game getGame() {
        return this.game;
    }

    public HashMap<Class<? extends Behavior>, BehaviorManager> getBehaviors() {
        return this.behaviors;
    }
}
