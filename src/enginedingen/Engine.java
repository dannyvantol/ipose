package enginedingen;

import behaviourdingen.Behavior;
import behaviourdingen.BehaviorManager;
import behaviourdingen.CollisionManager;
import behaviourdingen.KeyPressManager;
import behaviourdingen.behaviors.Collidable;
import behaviourdingen.behaviors.KeyPressed;
import gamedingen.AbstractElement;
import gamedingen.AbstractGame;
import gamedingen.AbstractLevel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Engine extends Application {
    private AbstractGame abstractGame;
    private Renderer renderer;
    private HashMap<Class<? extends Behavior>, BehaviorManager> behaviors;
    private Stage stage;


    public Engine(AbstractGame abstractGame) {
        this.abstractGame = abstractGame;
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

                    AbstractLevel abstractLevel = getAbstractGame().getActiveAbstractLevel();
                    ArrayList<AbstractElement> abstractElements = abstractLevel.getAbstractElements();

                    for (AbstractElement abstractElement : abstractElements) {
                        if (behavior.isInstance(abstractElement))
                            behaviorManager.handle(abstractElement);
                    }
                }
                renderer.render();

            }
        }.start();
    }

    private void setupInitialBehaviorsAndRenderer() {
        this.renderer = new Renderer(abstractGame,stage);
        KeyPressManager keyPressManager = new KeyPressManager(stage);
        CollisionManager collisionManager = new CollisionManager(abstractGame.getActiveAbstractLevel().getAbstractElements());
        addBehavior(Collidable.class,collisionManager);
        addBehavior(KeyPressed.class,keyPressManager);

    }

    public void addBehavior(Class<? extends Behavior> behavior, BehaviorManager behaviorManager) {
        this.behaviors.put(behavior, behaviorManager);
    }

    public void focusOnElement(AbstractElement abstractElement) {
        this.renderer.getCamera().focus(abstractElement);
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

    public AbstractGame getAbstractGame() {
        return this.abstractGame;
    }

    public HashMap<Class<? extends Behavior>, BehaviorManager> getBehaviors() {
        return this.behaviors;
    }
}
