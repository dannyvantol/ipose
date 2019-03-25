package enginedingen;

import gamedingen.Element;
import gamedingen.Game;

public class Camera {

    private double posX;
    private double posY;
    private Element focusElement;
    private Game game;


    public Camera(Game game) {
        this.game = game;
        this.posX = 0;
        this.posY = 0;
    }

    public void calculatePosition() {
        calculatePositionX();
        calculatePositionY();
    }

    private void calculatePositionX() {
        if (focusElement != null) {
            double elementX = focusElement.getX();
            if (elementX > 1024 / 3 * 2 && posX <= game.getActiveLevel().getWidth()) {
                posX += elementX;
            } else if (elementX < 1024 / 3 && posX >= 0) {
                posX -= elementX;
            }
        }
    }

    private void calculatePositionY() {
        if (focusElement != null) {
            double elementY = focusElement.getY();
            if (elementY > 768 / 3 * 2 && posY <= game.getActiveLevel().getHeigt()) {
                posY += elementY;
            } else if (elementY < 768 / 3 && posY >= 0) {
                posY -= elementY;
            }
        }
    }

    public void focus(Element element) {
        this.focusElement = element;
    }

    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }
}
