package behaviourdingen.thread;

import enginedingen.Engine;
import gamedingen.Element;
import gamedingen.Level;

import java.util.ArrayList;

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
