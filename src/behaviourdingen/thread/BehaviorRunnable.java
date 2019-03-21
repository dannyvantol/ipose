package behaviourdingen.thread;

import enginedingen.Engine;
import behaviourdingen.Behavior;
import behaviourdingen.BehaviorManager;
import gamedingen.Element;
import gamedingen.Level;

import java.util.ArrayList;
import java.util.Set;

public class BehaviorRunnable implements Runnable {
    private Engine engine;

    public BehaviorRunnable(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Set<Class<? extends Behavior>> behaviors = this.engine.getBehaviors().keySet();

                for (Class<? extends Behavior> behavior : behaviors) {
                    BehaviorManager behaviorManager = this.engine.getBehaviors().get(behavior);

                    Level level = this.engine.getGame().getActiveLevel();
                    ArrayList<Element> elements = level.getElements();

                    for (Element element : elements) {
                        if (behavior.isInstance(element))
                            behaviorManager.handle(element, behavior.cast(element));
                    }
                }

                Thread.sleep( 1000 / 60);
            }
        } catch (InterruptedException e) {

        }
    }
}
