package behaviourdingen;

import behaviourdingen.behaviors.Collidable;
import behaviourdingen.behaviors.KeyPressed;
import gamedingen.Element;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EventManager implements BehaviourManager {

    private Stage stage;
    private ArrayList<String> keyCodes;

    public EventManager(Stage stage) {
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



    @Override
    public void handleBehaviour(Element element) {
        KeyPressed keyPressed = (KeyPressed) element;
        keyPressed.handleKeyPresses(keyCodes);
    }
}

