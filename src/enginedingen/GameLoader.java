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

    private HashMap<Integer, Class<? extends AbstractTile>> tileMap;

    private HashMap<Integer, Class<? extends AbstractElement>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    public AbstractGame load() {
        ArrayList<AbstractLevel> abstractLevels = new ArrayList<>();
        int numberOfLevels = Objects.requireNonNull(new File(LEVEL_DIR).listFiles()).length;

        for (int levelNumber = 1; levelNumber <= numberOfLevels; levelNumber++) {
            InputStream tilesData = getLevelTilesData(levelNumber);
            InputStream elementsData = getLevelElementsData(levelNumber);
            AbstractLevel abstractLevel = loadLevel(tilesData);
            abstractLevels.add(loadLevel(tilesData));
        }

        AbstractGame abstractGame = new AbstractGameImpl();
        abstractGame.setAbstractLevels(abstractLevels);

        return abstractGame;
    }

    private AbstractLevel loadLevel(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        AbstractLevel abstractLevel = new AbstractLevelImpl();
        AbstractTile[][] abstractTiles = new AbstractTile[LEVEL_HEIGHT][LEVEL_WIDTH];

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                int id = scanner.nextInt();

                try {
                    abstractTiles[x][y] = tileMap.get(id).newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        abstractLevel.setAbstractTiles(abstractTiles);

        return abstractLevel;
    }

    private void loadElementsInLevel(InputStream stream, AbstractLevel abstractLevel) {
        ArrayList<AbstractElement> abstractElements = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            int id = scanner.nextInt();

            try {
                abstractElements.add(elementMap.get(id).newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        abstractLevel.setAbstractElements(abstractElements);
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

    public void addTileConfiguration(HashMap<Integer, Class<? extends AbstractTile>> tileMap) {
        this.tileMap = tileMap;
    }

    public void addElementsConfiguration(HashMap<Integer, Class<? extends AbstractElement>> elementMap) {
        this.elementMap = elementMap;
    }

    private boolean levelExists(int level) {
        return levelElementsPaths.get(level) != null && levelTilesPaths.get(level) != null;
    }
}
