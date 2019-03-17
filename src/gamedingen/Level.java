package gamedingen;

import java.util.ArrayList;

public abstract class Level {
    private Tile[][] tiles;
    private ArrayList<Element> elements;

    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }
}
