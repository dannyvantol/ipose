package engine;

import game.Element;
import game.Game;
import javafx.scene.PerspectiveCamera;

/**
 * Dit is de camera class die is verantwoordelijk voor welke onderdelen er in het spel komen en welke niet.
 * */
public class Camera extends PerspectiveCamera {
    
    private Element focusElement;
    private Game game;


    public Camera(Game game) {
        this.game = game;
    }

    /**
     * Met deze methode reken je de x en y positie van de camera uit.
     * */
    public void calculatePosition() {
        calculatePositionX();
        calculatePositionY();
    }

    /**
     * Met deze methode laat je de camera zich op een bepaald element focussen.
     * @param element waar de camera zich op moet focussen.
     * */
    public void focus(Element element) {
        this.focusElement = element;
    }

    /**
     * @return de x waarde van de camera.
     * */
    public double getX() {
        return super.getLayoutX();
    }

    /**
     * @return de y waarde van de camera.
     * */
    public double getY() {
        return super.getLayoutY();
    }

    private void calculatePositionX() {
        if (focusElement != null) {
            double elementX = focusElement.getX();
            if (elementX > super.getLayoutX() + (1024 / 3 * 2) && super.getLayoutX() +1024 <= game.getActiveLevel().getWidth()) {
                double newCameraPosX = super.getLayoutX() + elementX -(super.getLayoutX() + (1024 / 3 * 2));
                if(newCameraPosX +1024 <= game.getActiveLevel().getWidth()){
                    super.setLayoutX(newCameraPosX) ;
                } else{
                    super.setLayoutX(game.getActiveLevel().getWidth()-1024);
                }

            } else if (elementX < super.getLayoutX() +( 1024 / 3)) {
                double newCameraPosX = super.getLayoutX()- ((super.getLayoutX() +( 1024 / 3)) - elementX );
                if(newCameraPosX >= 0){
                    super.setLayoutX(newCameraPosX);
                }
                else{
                    super.setLayoutX(0);
                }

            }
        }
    }

    private void calculatePositionY() {
        if (focusElement != null) {
            double elementY = focusElement.getY();
            if (elementY > super.getLayoutY() +( 768 / 3 * 2) && super.getLayoutY() + 768 <= game.getActiveLevel().getHeigt()) {
                double newCameraPosY = super.getLayoutY() + elementY - ( super.getLayoutY() +( 768 / 3 * 2));
                if(newCameraPosY + 768 <= game.getActiveLevel().getHeigt()){
                    super.setLayoutY(newCameraPosY);
                }
                else{
                    super.setLayoutY(game.getActiveLevel().getHeigt() -768);
                }

            } else if (elementY < super.getLayoutY() +( 768 / 3) && super.getLayoutY() >= 0) {
                double newCameraPosY = super.getLayoutY() - ((super.getLayoutY() +( 768 / 3)) - elementY);
                if(newCameraPosY >= 0){
                    super.setLayoutY(super.getLayoutY() - ((super.getLayoutY() +( 768 / 3)) - elementY));
                }
                else{
                    super.setLayoutY(0);
                }

            }
        }
    }
}
