package gamedingen.behaviors;

import enginedingen.behavior.BehaviorManager;
import gamedingen.Element;

public class CollisionManager implements BehaviorManager<Collidable> {
    @Override
    public void handle(Element element, Collidable behavior) {
        System.out.println("Collide");
    }
}
