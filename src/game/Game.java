package game;

import java.util.ArrayList;

/**
 * Dit is de game. Hier kun je een aantal methoden aanroepen om informatie over het spel te krijgen,
 * of om levels toe te voegen aan het spel.
 * */
public class Game {
    private ArrayList<Level> levels;
    private Level activeLevel;

    /**
     * @return het level dat momenteel actief is.
     * */
    public Level getActiveLevel() {
        return activeLevel;
    }

    /**
     * @return alle levels van het spel.
     * */
    public ArrayList<Level> getLevels() {
        return levels;
    }

    /**
     * Set de levels in het spel.
     * @param levels de levels die in het spel gespeeld dienen te worden.
     * */
    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    /**
     * Geef aan welk level actief is.
     * @param level het level dat actief gezet moet worden.
     * */
    public void setActiveLevel(Level level){
        this.activeLevel = level;
    }
}
