package behavior;

import behavior.behaviors.Collidable;
import game.Element;
import game.Game;
import game.Tile;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.Arrays;

public class CollisionManager implements BehaviorManager {

    private Game game;

    public CollisionManager(Game game)
    {
        this.game = game;
    }

    /**
     * Geeft aan dat er een Collision event afgehandel moet worden door een element met een Collidable uit het spel.
     * @param element met Collidable gedrag.
     * */
    @Override
    public void handle(Element element) {
        for (Element object : game.getActiveLevel().getElements()) {
            if (object instanceof Collidable) {
                Bounds bounds = object.getLayoutBounds();
                if (element.intersects(bounds))
                    ((Collidable) element).handleCollision((Collidable) object);
            }
        }
        Tile[][] tiles = game.getActiveLevel().getTiles();
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
