package enginedingen.thread;

import enginedingen.Engine;
import enginedingen.behavior.Behavior;
import enginedingen.behavior.BehaviorManager;
import gamedingen.Element;
import gamedingen.Level;

import java.util.ArrayList;
import java.util.Set;

public class KeyEventRunnable implements Runnable {

    private Engine engine;

    public KeyEventRunnable(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Level level = this.engine.getGame().getActiveLevel();
                ArrayList<Element> elements = level.getElements();

                for (Element element : elements) {
                    if (element instanceof EventKey)
                        behaviorManager.handle(element, behavior.cast(element));
                }

                Thread.sleep( 1000 / 60);
            }
        } catch (InterruptedException e) {

        }
    }
}
