package enginedingen;

import gamedingen.AbstractElement;
import gamedingen.AbstractGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Renderer {
    private AbstractGame abstractGame;
    private Camera camera;
    private Stage stage;

    public Renderer(AbstractGame abstractGame, Stage stage) {
        this.abstractGame = abstractGame;
        this.stage = stage;
        this.camera = new Camera(abstractGame);
        initialSetup();
    }

    private void initialSetup(){
        Scene scene = new Scene(new Group(),1024,768);
        this.stage.setScene(scene);
        this.stage.show();
    }

    //WORK IN PROGRESS IK WEET DAT DEZE MOET SAMENWERKEN MET DE CAMERA MAAR DAT MOET IK NOG EVEN UITDENKEN NO FLAME PLEASE!!!!!!!!!!!!!
    public void render(){
        camera.calculatePosition();
        getRootGroup().relocate(camera.getX(),camera.getY());
        renderElements();
        this.getRootGroup().getChildren().addAll(this.abstractGame.getActiveAbstractLevel().getAbstractElements());
    }

    private void renderTiles() {
        for(int i = 0; i<this.abstractGame.getActiveAbstractLevel().getAbstractTiles().length; i++){
            for(int j = 0; j<this.abstractGame.getActiveAbstractLevel().getAbstractTiles()[i].length; j++){
                this.getRootGroup().getChildren().add(this.abstractGame.getActiveAbstractLevel().getAbstractTiles()[i][j]);
            }
        }
    }

    private void renderElements() {

        Rectangle rectangle = new Rectangle(1024+80,768+80);
        rectangle.setX(camera.getX()-80);
        rectangle.setY(camera.getY()-80);
        for(AbstractElement abstractElement :this.abstractGame.getActiveAbstractLevel().getAbstractElements()){
            if(abstractElement.intersects(rectangle.getLayoutBounds())){
                if(!getRootGroup().getChildren().contains(abstractElement)){
                    getRootGroup().getChildren().add(abstractElement);
                }
            }
        }

    }

    public Camera getCamera() {
        return camera;
    }

    private Group getRootGroup(){
        return (Group) this.stage.getScene().getRoot();
    }
}
