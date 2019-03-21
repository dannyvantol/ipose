package gamedingen;

import java.util.ArrayList;

public abstract class Game {
    private ArrayList<Level> levels;
    private Level activeLevel;

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public Level getActiveLevel() {
        return activeLevel;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
}
