package io.matthd.cytosis.game;

/**
 * Created by Matt on 2016-03-13.
 */
public abstract class GameSettings {

    private boolean isPvP = true;
    private boolean isPvE = false;
    private boolean isFFA = false;

    public GameSettings(boolean isPvP, boolean isPvE, boolean isFFA){
        this.isPvE = isPvP;
        this.isPvE = isPvE;
        this.isFFA = isFFA;
    }

    public boolean isPvP() {
        return isPvP;
    }

    public void setPvP(boolean pvP) {
        isPvP = pvP;
    }

    public boolean isPvE() {
        return isPvE;
    }

    public void setPvE(boolean pvE) {
        isPvE = pvE;
    }

    public boolean isFFA() {
        return isFFA;
    }

    public void setFFA(boolean FFA) {
        isFFA = FFA;
    }
}
