package io.matthd.cytosis.events;

import io.matthd.cytosis.Cytosis;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 2016-03-13.
 */
public class EventManager {

    private List<Listener> allListeners;

    public void register(){
        for(Listener listener : allListeners){
            Bukkit.getPluginManager().registerEvents(listener, Cytosis.getInstance());
        }
    }

    public void add(Listener listener){
        if(allListeners == null){
            allListeners = new ArrayList<>();
        }
        allListeners.add(listener);
    }
}
