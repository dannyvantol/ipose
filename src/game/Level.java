package game;

import java.util.ArrayList;

/**
 * Dit is een level. Hier kun je een aantal methoden aanroepen om informatie over het level te krijgen,
 * of om tiles en/of elementen toe te voegen aan het spel.
 * */
public class Level {
    private Tile[][] tiles;
    private ArrayList<Element> elements;
    private Element focusedElement;

    /**
     * @return het element dat door de camera gefocussed wordt.
     * */
    public Element getFocusedElement() {
        return focusedElement;
    }

    /**
     * Set het element dat door de camera gefocussed moet worden.
     * @param focusedElement het element waarop gefocussed moet worden.
     * */
    public void setFocusedElement(Element focusedElement) {
        this.focusedElement = focusedElement;
    }

    /**
     * Met deze methode kun je de tiles van dit level opvragen.
     * @return de tiles van dit level.
     * */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Met deze methode kun je de elementen van dit level opvragen.
     * @return de elementen van dit level.
     * */
    public ArrayList<Element> getElements() {
        return elements;
    }

    /**
     * @return de breedte van het level.
     * */
    public int getWidth() {
        return tiles.length * 80;
    }

    /**
     * @return de hoogte van het level.
     * */
    public int getHeigt() {
        return tiles[0].length * 80;
    }

    /**
     * Plaats de tiles in dit level.
     * @param tiles de tiles die in dit level moeten.
     * */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Plaats de elements in dit level.
     * @param elements de tiles die in dit level moeten.
     * */
    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
