package io.matthd.cytosis;

/**
 * Created by Matt on 2016-03-11.
 */

import io.matthd.cytosis.events.EventManager;
import io.matthd.cytosis.events.PlayerDeath;
import io.matthd.cytosis.events.PlayerJoin;
import io.matthd.cytosis.game.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Cytosis extends JavaPlugin {

    private static Cytosis instance;
    private EventManager eventManager;
    private GameManager gameManager;

    public void onEnable(){
        instance = this;
        eventManager = new EventManager();
        gameManager = new GameManager();

        eventManager.add(new PlayerDeath());
        eventManager.add(new PlayerJoin());
        eventManager.register();
    }

    public void onDisable(){
        instance = null;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static Cytosis getInstance() {
        return instance;
    }
}
