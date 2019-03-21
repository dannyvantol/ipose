package enginedingen;

import gamedingen.Element;
import gamedingen.Game;
import gamedingen.Tile;

import java.util.HashMap;

public class GameLoader {

    private HashMap<Integer, Class<? extends Tile>> tileMap;

    private HashMap<Integer, Class<? extends Element>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    public Game load() {
        return null;
    }

    public void addLevel(int level, String levelTilesPath, String levelElementsPath) {
        if (levelExists(level)) return;
        levelTilesPaths.put(level, levelTilesPath);
        levelElementsPaths.put(level, levelElementsPath);
    }

    public void addTileConfiguration(HashMap<Integer, Class<? extends Tile>> tileMap) {
        this.tileMap = tileMap;
    }

    public void addElementsConfiguration(HashMap<Integer, Class<? extends Element>> elementMap) {
        this.elementMap = elementMap;
    }

    private boolean levelExists(int level) {
        return levelElementsPaths.get(level) != null && levelTilesPaths.get(level) != null;
    }
}
