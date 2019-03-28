package game;

import java.util.ArrayList;

public class Game {
    private ArrayList<Level> levels;
    private Level activeLevel;


    public Level getActiveLevel() {
        return activeLevel;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }


    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public void setActiveLevel(Level level){
        this.activeLevel = level;
    }
}
