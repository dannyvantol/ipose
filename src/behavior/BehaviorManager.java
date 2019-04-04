package behavior;

import game.Element;

/**
 * Een interface die alle Behavior managers moeten implementeren.
 * Hierdoor kunnen ze geregistreerd worden bij het configureren.
 * */
public interface BehaviorManager {
    void handle(Element element);
}
