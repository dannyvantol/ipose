package gamedingen;

import java.util.ArrayList;

public abstract class AbstractGame {
    private ArrayList<AbstractLevel> abstractLevels;
    private AbstractLevel activeAbstractLevel;

    public ArrayList<AbstractLevel> getAbstractLevels() {
        return abstractLevels;
    }

    public AbstractLevel getActiveAbstractLevel() {
        return activeAbstractLevel;
    }

    public void setAbstractLevels(ArrayList<AbstractLevel> abstractLevels) {
        this.abstractLevels = abstractLevels;
    }

    public void setActiveAbstractLevel(AbstractLevel abstractLevel){
        this.activeAbstractLevel = abstractLevel;
    }
}
