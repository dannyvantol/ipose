package enginedingen;

import gamedingen.Element;
import gamedingen.Game;
import gamedingen.Tile;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Renderer {
    private Game game;
    private Camera camera;
    private Stage stage;

    public Renderer(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        this.camera = new Camera(game);
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
        this.stage.getScene().setCamera(camera);
        renderTiles();
        renderElements();

    }


    private void renderTiles() {
        Rectangle rectangle = new Rectangle(1024+80,768+80);
        rectangle.setX(camera.getX()-80);
        rectangle.setY(camera.getY()-80);
        for(int i = 0; i<this.game.getActiveLevel().getTiles().length; i++){
            for(int j = 0; j<this.game.getActiveLevel().getTiles()[i].length; j++){
                Tile tile = this.game.getActiveLevel().getTiles()[i][j];
                if(tile.intersects(rectangle.getLayoutBounds()) &&!getRootGroup().getChildren().contains(tile) ){
                    getRootGroup().getChildren().add(tile);
                }
                else if(!tile.intersects(rectangle.getLayoutBounds()) && getRootGroup().getChildren().contains(tile) ){
                    getRootGroup().getChildren().remove(tile);
                }
            }
        }
    }

    private void renderElements() {

        Rectangle rectangle = new Rectangle(1024+80,768+80);
        rectangle.setX(camera.getX()-80);
        rectangle.setY(camera.getY()-80);
        for(Element element :this.game.getActiveLevel().getElements()){
            if(element.intersects(rectangle.getLayoutBounds())){
                if(getRootGroup().getChildren().contains(element)){
                    getRootGroup().getChildren().remove(element);
                }
                getRootGroup().getChildren().add(element);
            }
            else if(!element.intersects(rectangle.getLayoutBounds()) && getRootGroup().getChildren().contains(element) ){
                getRootGroup().getChildren().remove(element);
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
