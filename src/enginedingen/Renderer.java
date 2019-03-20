package enginedingen;

import gamedingen.Element;
import gamedingen.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Renderer {
    private Game game;
    private Camera camera;
    private Stage stage;

    public Renderer(Game game,Stage stage) {
        this.game = game;
        this.stage = stage;
        this.camera = new Camera(stage, game);

    }

    private void initialSetup(){
        Scene scene = new Scene(new Group(),1024,768);
        this.stage.setScene(scene);
    }

    //WORK IN PROGRESS IK WEET DAT DEZE MOET SAMENWERKEN MET DE CAMERA MAAR DAT MOET IK NOG EVEN UITDENKEN NO FLAME PLEASE!!!!!!!!!!!!!
    public void render(){
        renderTiles();
        renderElements();
        this.getRootGroup().getChildren().addAll(this.game.getActiveLevel().getElements());
    }

    private void renderTiles() {
        for(int i = 0;i<this.game.getActiveLevel().getTiles().length;i++){
            for(int j = 0;j<this.game.getActiveLevel().getTiles()[i].length;j++){
                this.getRootGroup().getChildren().add(this.game.getActiveLevel().getTiles()[i][j]);
            }
        }
    }

    private void renderElements() {
        this.getRootGroup().getChildren().addAll(this.game.getActiveLevel().getElements());
    }

    private Group getRootGroup(){
        return (Group) this.stage.getScene().getRoot();
    }
}
