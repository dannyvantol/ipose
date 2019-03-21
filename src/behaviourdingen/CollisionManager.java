package behaviourdingen;

import behaviourdingen.behaviors.Collidable;
import gamedingen.Element;
import javafx.geometry.Bounds;

import java.util.ArrayList;

public class CollisionManager implements BehaviourManager {

    private ArrayList<Element> objects;

    public CollisionManager(ArrayList<Element> elements) {
        this.objects = elements;
    }

    @Override
    public void handleBehaviour(Element element) {
        for (Element object : objects) {
            Bounds bounds = object.getLayoutBounds();
            if (element.intersects(bounds))
                ((Collidable) element).handleCollide((Collidable) element, (Collidable) object);
        }
    }
}
