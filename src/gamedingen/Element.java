package gamedingen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Element extends ImageView {

    public Element(String imagePath){
        super();
        super.setImage(new Image(imagePath));
    }
}
