package behavior.behaviors;

import behavior.Behavior;

import java.util.ArrayList;

/**
 * Een behavoir specifiek voor key gerelateerde events.
 * */
public interface KeyBehavior extends Behavior {

    /**
     * Deze methode handeld een key event af.
     * @param keyCodes de keycodes die gebruikt zijn.
     * */
    void handleKeyPresses(ArrayList<String> keyCodes);
}
