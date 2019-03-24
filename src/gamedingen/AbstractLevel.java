package gamedingen;

import java.util.ArrayList;

public abstract class AbstractLevel {
    private AbstractTile[][] abstractTiles;
    private ArrayList<AbstractElement> abstractElements;

    public AbstractTile[][] getAbstractTiles() {
        return abstractTiles;
    }

    public ArrayList<AbstractElement> getAbstractElements() {
        return abstractElements;
    }

    public int getWidth() {
        return abstractTiles.length * 80;
    }

    public int getHeigt() {
        return abstractTiles[0].length * 80;
    }

    public void setAbstractTiles(AbstractTile[][] abstractTiles) {
        this.abstractTiles = abstractTiles;
    }

    public void setAbstractElements(ArrayList<AbstractElement> abstractElements) {
        this.abstractElements = abstractElements;
    }
}
