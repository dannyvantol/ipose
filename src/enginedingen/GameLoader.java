package enginedingen;

import gamedingen.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class GameLoader {

    private static final int LEVEL_HEIGHT = 100;
    private static final int LEVEL_WIDTH = 100;
    private static final String LEVEL_DIR = "levels/";
    private static final String LEVEL_PREFIX = "level";
    private static final String LEVEL_TILES_PREFIX = "tiles/";
    private static final String LEVEL_ELEMENTS_PREFIX = "elements/";
    private static final String LEVEL_EXT = ".txt";

    private HashMap<Integer, Class<? extends Tile>> tileMap;

    private HashMap<Integer, Class<? extends Element>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    public Game load() {
        ArrayList<Level> levels = new ArrayList<>();
        int numberOfLevels = Objects.requireNonNull(new File(LEVEL_DIR).listFiles()).length;

        for (int levelNumber = 1; levelNumber <= numberOfLevels; levelNumber++) {
            InputStream tilesData = getLevelTilesData(levelNumber);
            InputStream elementsData = getLevelElementsData(levelNumber);
            Level level = new LevelImpl();
            loadLevel(tilesData, level);
            loadElementsInLevel(elementsData, level);
            levels.add(level);
        }

        Game game = new GameImpl();
        game.setLevels(levels);

        return game;
    }

    private Level loadLevel(InputStream stream, Level level) {
        Scanner scanner = new Scanner(stream);
        Tile[][] tiles = new Tile[LEVEL_HEIGHT][LEVEL_WIDTH];

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                try {
                    int id = scanner.nextInt();
                    if (tileMap.get(id) == null) continue;
                    Tile tile = tileMap.get(id).newInstance();
                    tile.setX(x);
                    tile.setY(y);
                    tiles[x][y] = tile;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setTiles(tiles);

        return level;
    }

    private void loadElementsInLevel(InputStream stream, Level level) {
        ArrayList<Element> elements = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                try {
                    int id = scanner.nextInt();
                    if (elementMap.get(id) == null) continue;
                    Element element = elementMap.get(id).newInstance();
                    element.setX(x);
                    element.setY(y);
                    elements.add(element);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setElements(elements);
    }

    private InputStream getLevelElementsData(int level) {
        String fileName = LEVEL_DIR + LEVEL_PREFIX + LEVEL_ELEMENTS_PREFIX + level + LEVEL_EXT;
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

    private InputStream getLevelTilesData(int level) {
        String fileName = LEVEL_DIR + LEVEL_PREFIX + LEVEL_TILES_PREFIX + level + LEVEL_EXT;
        return getClass().getClassLoader().getResourceAsStream(fileName);
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
