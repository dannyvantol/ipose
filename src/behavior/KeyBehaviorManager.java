package behavior;


import behavior.behaviors.KeyBehavior;
import game.Element;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Deze class is verantwoordelijk voor het afhandelen van KeyBehavoir.
 * */
public class KeyBehaviorManager implements BehaviorManager {

    private Stage stage;
    private ArrayList<String> keyCodes;

    public KeyBehaviorManager(Stage stage) {
        this.stage = stage;
        keyCodes = new ArrayList<>();
        setupKeyConfig();
    }

    private void setupKeyConfig() {
        this.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(!keyCodes.contains(event.getCode().toString())){
                    keyCodes.add(event.getCode().toString());
                }

            }
        });
        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyCodes.remove(event.getCode().toString());
            }
        });
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

