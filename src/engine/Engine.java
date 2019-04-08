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

/**
 * De engine is het controlle mechanisme van het spel.
 */
public class Engine {
    private static Game game;
    private Renderer renderer;
    private HashMap<Class<? extends Behavior>, BehaviorManager> behaviors;
    private Stage stage;


    public Engine(Game game) {
        this.game = game;
        this.behaviors = new HashMap<>();
    }

    /**
     * Dit is een methode om het game object overal te kunnen verkrijgen in het spel.
     * Gebruik deze methode voor het toevoegen van elementen aan het spel tijdens het draaien van het spel.
     * @return de game
     * */
    public static Game getGameGlobaly() {
        return game;
    }

    /**
     * Dit is een methode om het spel te starten.
     *
     * @param primaryStage die meegegeven word vanuit bij het opstarten van een javafx applicatie.
     * */
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
        CollisionManager collisionManager = new CollisionManager(game);
        addBehavior(Collidable.class,collisionManager);
        addBehavior(KeyBehavior.class, keyBehaviorManager);

    }

    public void resetRenderer(){
        this.renderer.resetRenderer();
    }

    /**
     * Hiermee kan er een gedrag aan de game toegevoegd worden.
     *
     * @param behavior het gedrag dat in de game aanwezig is.
     * @param behaviorManager de manager die het gedrag kan afhandelen.
     * */
    public void addBehavior(Class<? extends Behavior> behavior, BehaviorManager behaviorManager) {
        this.behaviors.put(behavior, behaviorManager);
    }

    /**
     * Hiermee kan een focus van de camera op een element gezet worden. Bijvoorbeeld de character die je bestuurd.
     *
     * @param element de character waarop je de camera op kan laten focussen.
     * */
    public void focusOnElement(Element element) {
        this.renderer.getCamera().focus(element);
    }

    /**
     * Hiermee kan de instantie van de game opgevraagd worden, maar alleen als je een instantie van de engine hebt.
     *
     * @return de game
     * */
    public Game getGame() {
        return this.game;
    }

    /**
     * Hiermee kan je al het gedrag dat in de game aanwezig is opvragen.
     *
     * @return een HashMap met alle soorten gedrag.
     * Deze bevatten .class objecten geen daadwerkelijke instanties van het gedrag.
     * */
    public HashMap<Class<? extends Behavior>, BehaviorManager> getBehaviors() {
        return this.behaviors;
    }
}
