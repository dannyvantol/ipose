package enginedingen;

import behaviourdingen.Behavior;
import behaviourdingen.BehaviorManager;
import behaviourdingen.CollisionManager;
import behaviourdingen.KeyPressManager;
import behaviourdingen.behaviors.Collidable;
import behaviourdingen.behaviors.KeyPressed;
import gamedingen.Element;
import gamedingen.Game;
import gamedingen.Level;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Engine extends Application {
    private Game game;
    private Renderer renderer;
    private HashMap<Class<? extends Behavior>, BehaviorManager> behaviors;
    private Stage stage;


    public Engine(Game game) {
        this.game = game;
        this.behaviors = new HashMap<>();
    }




    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        setupInitialBehaviorsAndRenderer();



        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Set<Class<? extends Behavior>> behaviorsKeySet = getBehaviors().keySet();

                for (Class<? extends Behavior> behavior : behaviorsKeySet) {
                    BehaviorManager behaviorManager = getBehaviors().get(behavior);

                    Level level = getGame().getActiveLevel();
                    ArrayList<Element> elements = level.getElements();

                    for (Element element : elements) {
                        if (behavior.isInstance(element))
                            behaviorManager.handle(element);
                    }
                }
                renderer.render();

            }
        }.start();
    }

    private void setupInitialBehaviorsAndRenderer() {
        this.renderer = new Renderer(game,stage);
        focusOnElement(getGame().getActiveLevel().getElements().get(0));
        KeyPressManager keyPressManager = new KeyPressManager(stage);
        CollisionManager collisionManager = new CollisionManager(game.getActiveLevel().getElements());
        addBehavior(Collidable.class,collisionManager);
        addBehavior(KeyPressed.class,keyPressManager);

    }

    public void addBehavior(Class<? extends Behavior> behavior, BehaviorManager behaviorManager) {
        this.behaviors.put(behavior, behaviorManager);
    }

    public void focusOnElement(Element element) {
        this.renderer.getCamera().focus(element);
    }

//    public void loop() {
//        Thread keyInputThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        Thread behaviorThread = new Thread(new BehaviorRunnable(this));
//
//        keyInputThread.start();
//        behaviorThread.start();
//    }

    public Game getGame() {
        return this.game;
    }

    public HashMap<Class<? extends Behavior>, BehaviorManager> getBehaviors() {
        return this.behaviors;
    }
}
