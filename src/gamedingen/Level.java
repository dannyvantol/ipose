package gamedingen;

import java.util.ArrayList;

public class Level {
    private Tile[][] tiles;
    private ArrayList<Element> elements;

    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public int getWidth() {
        return tiles.length * 80;
    }

    public int getHeigt() {
        return tiles[0].length * 80;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
