package engine;

import game.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Deze class zorgd voor het laden van de game
 * */
public class GameLoader {

    private HashMap<Integer, Class<? extends Tile>> tileMap;

    private HashMap<Integer, Class<? extends Element>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    public GameLoader() {
        this.levelTilesPaths = new HashMap<>();
        this.levelElementsPaths = new HashMap<>();
    }

    /**
     * Deze methode zorgd ervoor dat het spel geladen wordt.
     *
     * @return de game
     * */
    public Game load() {
        ArrayList<Level> levels = new ArrayList<>();
        int numberOfLevels = levelTilesPaths.size();

        for (int levelNumber = 1; levelNumber <= numberOfLevels; levelNumber++) {
            InputStream tilesData = getLevelTilesData(levelNumber);
            InputStream elementsData = getLevelElementsData(levelNumber);
            Level level = new Level();
            loadTilesInLevel(tilesData, level);
            loadElementsInLevel(elementsData, level);
            levels.add(level);
        }

        Game game = new Game();
        game.setLevels(levels);

        return game;
    }

    /**
     * Hiermee voeg je een level toe aan het spel.
     * */
    public void addLevel(int level, String levelTilesPath, String levelElementsPath) {
        if (levelExists(level)) return;
        levelTilesPaths.put(level, levelTilesPath);
        levelElementsPaths.put(level, levelElementsPath);
    }

    /**
     * Hier kun je een configuratie voor een tile toevoegen.
     * Deze is dan te herkennen aan een bepaald nummer.
     *
     * @param tileMap een HashMap met een configuratie voor een tile.
     * Bijvoorbeeld: 1,GrassTile
     * */
    public void addTileConfiguration(HashMap<Integer, Class<? extends Tile>> tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * Hier kun je een configuratie voor een element toevoegen.
     * Deze is dan te herkennen aan een bepaald nummer.
     *
     * @param elementMap een HashMap met een configuratie voor een element.
     * Bijvoorbeeld: 1,Character
     * */
    public void addElementsConfiguration(HashMap<Integer, Class<? extends Element>> elementMap) {
        this.elementMap = elementMap;
    }

    private Level loadTilesInLevel(InputStream stream, Level level) {
        Scanner scanner = new Scanner(stream);
        int levelHeight = scanner.nextInt();
        int levelWidth = scanner.nextInt();
        Tile[][] tiles = new Tile[levelHeight][levelWidth];

        for (int y = 0; y < levelHeight; y++) {
            for (int x = 0; x < levelWidth; x++) {
                try {
                    int id = scanner.nextInt();
                    if (tileMap.get(id) == null) continue;
                    Tile tile = tileMap.get(id).newInstance();
                    tile.setX(x*80);
                    tile.setY(y*80);
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
        int levelHeight = scanner.nextInt();
        int levelWidth = scanner.nextInt();
        for (int y = 0; y < levelHeight; y++) {
            for (int x = 0; x < levelWidth; x++) {
                try {
                    int id = scanner.nextInt();
                    if (elementMap.get(id) == null) continue;
                    Element element = elementMap.get(id).newInstance();
                    element.setX(x*80);
                    element.setY(y*80);
                    elements.add(element);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setElements(elements);
    }

    private InputStream getLevelElementsData(int level) {
        String filePath = levelElementsPaths.get(level);
        return readFile(filePath);
    }

    private InputStream getLevelTilesData(int level) {
        String filePath = levelTilesPaths.get(level);
        return readFile(filePath);
    }

    private InputStream readFile(String filePath){
        return this.getClass().getResourceAsStream(filePath);
    }


    private boolean levelExists(int level) {
        return levelElementsPaths.get(level) != null && levelTilesPaths.get(level) != null;
    }
}
