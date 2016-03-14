package io.matthd.cytosis.chat;

import io.matthd.cytosis.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Matt on 2016-03-13.
 */
public class ChatUtils {
    public static void messageToAll(Game game, String msg){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(game.getPrefix() + " " + msg);
        }
    }

    public static void messageToOne(Player player, Game game, String msg){
        player.sendMessage(game.getPrefix() + " " + msg);
    }
}
