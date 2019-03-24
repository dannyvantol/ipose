package behaviourdingen;

import behaviourdingen.behaviors.Collidable;
import gamedingen.AbstractElement;
import javafx.geometry.Bounds;

import java.util.ArrayList;

public class CollisionManager implements BehaviorManager {

    private ArrayList<AbstractElement> objects;

    public CollisionManager(ArrayList<AbstractElement> abstractElements) {
        this.objects = abstractElements;
    }

    @Override
    public void handle(AbstractElement abstractElement) {
        for (AbstractElement object : objects) {
            Bounds bounds = object.getLayoutBounds();
            if (abstractElement.intersects(bounds))
                ((Collidable) abstractElement).handleCollision((Collidable) object);
        }
    }
}
