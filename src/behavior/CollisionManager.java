package behavior;

import behavior.behaviors.Collidable;
import game.Element;
import game.Tile;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.Arrays;

public class CollisionManager implements BehaviorManager {

    private ArrayList<Element> objects;

    public CollisionManager(ArrayList<Element> elements, Tile[][] tiles)
    {
        this.objects = new ArrayList<Element>();
        objects.addAll(elements);
        for (Tile[] tile : tiles) {
            this.objects.addAll(Arrays.asList(tile));
        }

    }

    @Override
    public void handle(Element element) {
        for (Element object : objects) {
            if (object instanceof Collidable) {
                Bounds bounds = object.getLayoutBounds();
                if (element.intersects(bounds))
                    ((Collidable) element).handleCollision((Collidable) object);
            }
        }
    }
}
