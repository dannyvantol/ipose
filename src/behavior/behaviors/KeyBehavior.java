package behavior.behaviors;

import behavior.Behavior;

import java.util.ArrayList;

public interface KeyBehavior extends Behavior {

    public void handleKeyPresses(ArrayList<String> keyCodes);
}
