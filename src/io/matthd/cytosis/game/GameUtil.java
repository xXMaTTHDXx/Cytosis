package io.matthd.cytosis.game;

import io.matthd.cytosis.Cytosis;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Created by Matt on 2016-03-13.
 */
public class GameUtil {

    public static Location getLobby() {
        ConfigurationSection section = Cytosis.getInstance().getConfig().getConfigurationSection("lobby");
        Location location = new Location(Bukkit.getWorld(section.getString("world")), section.getInt("x"), section.getDouble("y"), section.getDouble("z"));
        return location;
    }

    public static Location getSpawn(String team) {
        ConfigurationSection section = Cytosis.getInstance().getConfig().getConfigurationSection(team);
        Location location = new Location(Bukkit.getWorld(section.getString("world")), section.getInt("x"), section.getDouble("y"), section.getDouble("z"));
        return location;
    }
}
