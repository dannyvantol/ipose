package behavior.behaviors;

import behavior.Behavior;

public interface Collidable extends Behavior {

    void handleCollision(Collidable collidable1);
}
