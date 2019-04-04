package behavior;

import behavior.behaviors.Collidable;
import game.Element;
import game.Tile;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.Arrays;

public class CollisionManager implements BehaviorManager {

    private ArrayList<Element> elements;
    private Tile[][] tiles;

    public CollisionManager(ArrayList<Element> elements, Tile[][] tiles)
    {
        this.elements = elements;
        this.tiles = tiles;

    }

    /**
     * Geeft aan dat er een Collision event afgehandel moet worden door een element met een Collidable uit het spel.
     * @param element met Collidable gedrag.
     * */
    @Override
    public void handle(Element element) {
        for (Element object : elements) {
            if (object instanceof Collidable) {
                Bounds bounds = object.getLayoutBounds();
                if (element.intersects(bounds))
                    ((Collidable) element).handleCollision((Collidable) object);
            }
        }
        for(int i = 0; i<tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] instanceof Collidable) {
                    Bounds bounds = tiles[i][j].getLayoutBounds();
                    if (element.intersects(bounds))
                        ((Collidable) element).handleCollision((Collidable) tiles[i][j]);
                }
            }
        }
    }
}
