package gamedingen.behaviors;

import enginedingen.behavior.Behavior;

public interface Collidable extends Behavior {

    void handleCollide(Collidable collidable1, Collidable collidable2);
}
