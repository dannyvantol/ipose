package behaviourdingen.behaviors;

import behaviourdingen.Behavior;

public interface Collidable extends Behavior {

    void handleCollision(Collidable collidable1);
}
