package enginedingen.behavior;

import gamedingen.Element;

public interface BehaviorManager<T extends Behavior> {
    void handle(Element element, T behavior);
}
