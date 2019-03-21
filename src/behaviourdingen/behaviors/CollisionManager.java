package behaviourdingen.behaviors;

import behaviourdingen.BehaviorManager;
import gamedingen.Element;

public class CollisionManager implements BehaviorManager {
    @Override
    public void handle(Element element) {
        System.out.println("Collide");
    }
}
