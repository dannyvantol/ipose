package behavior;


import behavior.behaviors.KeyBehavior;
import game.Element;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Deze class is verantwoordelijk voor het afhandelen van KeyBehavoir.
 */
public class KeyBehaviorManager implements BehaviorManager {

    private Scene scene;
    private Set<String> keyCodes;

    public KeyBehaviorManager(Stage stage) {
        this.scene = stage.getScene();
        keyCodes = new HashSet<>();
        setupKeyConfig();
    }

    private void setupKeyConfig() {
        scene.setOnKeyPressed(event -> keyCodes.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> keyCodes.remove(event.getCode().toString()));
    }


    /**
     * Geeft aan dat er een Key event afgehandel moet worden door een element met een KeyBehavior uit het spel.
     *
     * @param element met KeyBehavior.
     */
    @Override
    public void handle(Element element) {
        KeyBehavior keyBehavior = (KeyBehavior) element;
        keyBehavior.handleKeyPresses(keyCodes);
    }

}

