package behaviourdingen.behaviors;

import behaviourdingen.Behavior;

import java.util.ArrayList;

public interface KeyPressed extends Behavior {

    public void handleKeyPresses(ArrayList<String> keyCodes);
}
