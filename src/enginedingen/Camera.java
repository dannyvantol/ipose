package enginedingen;

import gamedingen.Element;
import gamedingen.Game;
import javafx.scene.PerspectiveCamera;

public class Camera extends PerspectiveCamera {
    
    private Element focusElement;
    private Game game;


    public Camera(Game game) {
        this.game = game;
    }

    public void calculatePosition() {
        calculatePositionX();
        calculatePositionY();
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

    public void focus(Element element) {
        this.focusElement = element;
    }

    public double getX() {
        return super.getLayoutX();
    }

    public double getY() {
        return super.getLayoutY();
    }
}
