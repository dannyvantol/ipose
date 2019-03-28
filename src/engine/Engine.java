package engine;

import behavior.Behavior;
import behavior.BehaviorManager;
import behavior.CollisionManager;
import behavior.KeyBehaviorManager;
import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import game.Game;
import game.Level;
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

        renderer.render();

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
        if(getGame().getActiveLevel().getFocusedElement() != null){
            focusOnElement(getGame().getActiveLevel().getFocusedElement());
        }
        KeyBehaviorManager keyBehaviorManager = new KeyBehaviorManager(stage);
        CollisionManager collisionManager = new CollisionManager(game.getActiveLevel().getElements(),game.getActiveLevel().getTiles());
        addBehavior(Collidable.class,collisionManager);
        addBehavior(KeyBehavior.class, keyBehaviorManager);

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
