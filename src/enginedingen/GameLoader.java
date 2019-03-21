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
    private static final String LEVEL_EXT = ".txt";

    private HashMap<Integer, Class<? extends Tile>> tileMap;

    private HashMap<Integer, Class<? extends Element>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    public Game load() {
        ArrayList<Level> levels = new ArrayList<>();
        int numberOfLevels = Objects.requireNonNull(new File(LEVEL_DIR).listFiles()).length;

        for (int level = 1; level <= numberOfLevels; level++) {
            InputStream stream = getLevelData(level);
            levels.add(loadLevel(stream));
        }

        Game game = new GameImpl();
        game.setLevels(levels);

        return game;
    }

    private Level loadLevel(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        Level level = new LevelImpl();
        Tile[][] tiles = new Tile[LEVEL_HEIGHT][LEVEL_WIDTH];

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                int id = scanner.nextInt();

                try {
                    tiles[x][y] = tileMap.get(id).newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setTiles(tiles);

        return level;
    }

    private InputStream getLevelData(int level) {
        String fileName = LEVEL_DIR + LEVEL_PREFIX + level + LEVEL_EXT;
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
