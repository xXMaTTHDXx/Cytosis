package io.matthd.cytosis.team;

import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

/**
 * Created by Matt on 2016-03-13.
 */
public abstract class Team {

    public String name;

    public List<UUID> players;
    public Location spawn;

    public Team(String name){
        this.name = name;
    }

    public Team(String name, Location spawn){
        this.name = name;
        this.spawn = spawn;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public abstract Location getSpawn();
    public abstract void setSpawn(Location spawn);

    public abstract void add(UUID uuid);
    public abstract void remove(UUID uuid);
    public abstract void start();
    public abstract void stop();
}
