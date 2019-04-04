package behavior.behaviors;

import behavior.Behavior;

/**
 * Een behavior specifiek voor het afhandelen van collisions
 * */
public interface Collidable extends Behavior {

    /**
     * Deze methode handeld een collision event af.
     *
     * @param collidable1 het collidable element.
     * */
    void handleCollision(Collidable collidable1);
}
