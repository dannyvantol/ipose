package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public abstract class Element extends ImageView {

    public Element(String imagePath){
        super();
        InputStream inputStream = this.getClass().getResourceAsStream(imagePath);
        super.setImage(new Image(inputStream));
    }
}
