package gamedingen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractElement extends ImageView {

    public AbstractElement(String imagePath){
        super();
        super.setImage(new Image(imagePath));
    }
}
