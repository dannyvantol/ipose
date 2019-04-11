package behavior;


import behavior.behaviors.KeyBehavior;
import game.Element;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Deze class is verantwoordelijk voor het afhandelen van KeyBehavoir.
 * */
public class KeyBehaviorManager implements BehaviorManager {

    private Stage stage;
    private Set<String> keyCodes;

    public KeyBehaviorManager(Stage stage) {
        this.stage = stage;
        keyCodes = new HashSet<>();
        setupKeyConfig();
    }

    private void setupKeyConfig() {
        this.stage.getScene().setOnKeyPressed(event -> {
            keyCodes.add(event.getCode().toString());
        });
        stage.getScene().setOnKeyReleased(event -> keyCodes.remove(event.getCode().toString()));
    }


    /**
     * Geeft aan dat er een Key event afgehandel moet worden door een element met een KeyBehavior uit het spel.
     * @param element met KeyBehavior.
     * */
    @Override
    public void handle(Element element) {
        KeyBehavior keyBehavior = (KeyBehavior) element;
        keyBehavior.handleKeyPresses(keyCodes);
    }

}

