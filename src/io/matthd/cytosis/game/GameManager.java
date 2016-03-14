package io.matthd.cytosis.game;

import io.matthd.cytosis.Cytosis;
import io.matthd.cytosis.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Matt on 2016-03-13.
 */
public class GameManager {

    private Game current = null;

    public Game getCurrent(){
        return current;
    }

    public void start(Game game){
        if(current != null){
            return;
        }

        if(game.getState() != Game.State.LOBBY){
            return;
        }

        if(Bukkit.getOnlinePlayers().size() < game.getMinPlayers()){
            ChatUtils.messageToAll(game, "Not enough players to start the game! Trying again in 30 seconds");
            new BukkitRunnable(){
                @Override
                public void run() {
                    this.cancel();
                    start(game);
                }
            }.runTaskLater(Cytosis.getInstance(), 20*30L);
        }
        else {
            game.start();
            current = game;
        }
    }

    public void stop(Game game){
        if(current == null){
            return;
        }
        else {
            if(current == game){
                game.stop(true);
            }
        }
    }
}
