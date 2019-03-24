package enginedingen;

import gamedingen.AbstractElement;
import gamedingen.AbstractGame;

public class Camera {

    private double posX;
    private double posY;
    private AbstractElement focusAbstractElement;
    private AbstractGame abstractGame;


    public Camera(AbstractGame abstractGame) {
        this.abstractGame = abstractGame;
        this.posX = 0;
        this.posY = 0;
    }

    public void calculatePosition() {
        calculatePositionX();
        calculatePositionY();
    }

    private void calculatePositionX() {
        if (focusAbstractElement != null) {
            double elementX = focusAbstractElement.getX();
            if (elementX > 1024 / 3 * 2 && posX <= abstractGame.getActiveAbstractLevel().getWidth()) {
                posX += elementX;
            } else if (elementX < 1024 / 3 && posX >= 0) {
                posX -= elementX;
            }
        }
    }

    private void calculatePositionY() {
        if (focusAbstractElement != null) {
            double elementY = focusAbstractElement.getY();
            if (elementY > 768 / 3 * 2 && posY <= abstractGame.getActiveAbstractLevel().getHeigt()) {
                posY += elementY;
            } else if (elementY < 768 / 3 && posY >= 0) {
                posY -= elementY;
            }
        }
    }

    public void focus(AbstractElement abstractElement) {
        this.focusAbstractElement = abstractElement;
    }

    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }
}
