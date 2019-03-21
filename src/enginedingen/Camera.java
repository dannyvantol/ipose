package enginedingen;

import gamedingen.Element;
import gamedingen.Game;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Camera {

    private double posX;
    private double posY;
    private Element focusElement;
    private Scene scene;
    private Game game;

    private Parent sceneRoot;

    public Camera(Stage stage, Game game) {
        this.scene = stage.getScene();
        this.sceneRoot = stage.getScene().getRoot();
        this.game = game;
        bind(game.getActiveLevel().getElements().get(0));
    }

    public void calculatePosistion() {
        calculatePosistionX();
        calculatePosistionY();
    }

    private void calculatePosistionX() {
        double offset = scene.getWidth() * 1.0 / 3.0;
        if (focusElement.getX() < offset) {
            return;
        }
        sceneRoot.setLayoutX(-1 * focusElement.getX() + offset);
        this.posX = -1 * sceneRoot.getLayoutX();
        double minLayout = -1 * game.getActiveLevel().getWidth() + scene.getWidth();
        if (sceneRoot.getLayoutX() < minLayout) {
            sceneRoot.setLayoutX(minLayout);
        }
    }

    private void calculatePosistionY() {
        double offset = scene.getHeight() * 1.0 / 3.0;
        if (focusElement.getY() < offset) {
            return;
        }
        sceneRoot.setLayoutY(-1 * focusElement.getY() + offset);
        this.posY = -1 * sceneRoot.getLayoutY();
        double minLayout = -1 * game.getActiveLevel().getHeigt() + scene.getHeight();
        if (sceneRoot.getLayoutY() < minLayout) {
            sceneRoot.setLayoutY(minLayout);
        }
    }

    public void bind(Element element) {
        this.focusElement = element;
    }
}
