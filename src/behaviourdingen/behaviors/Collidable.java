package behaviourdingen.behaviors;

import behaviourdingen.Behavior;

public interface Collidable extends Behavior {

    void handleCollide(Collidable collidable1, Collidable collidable2);
}
