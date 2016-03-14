package io.matthd.cytosis.events;

import io.matthd.cytosis.Cytosis;
import io.matthd.cytosis.game.Game;
import io.matthd.cytosis.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Matt on 2016-03-13.
 */
public class PlayerJoin implements Listener {

    private Cytosis instance = Cytosis.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Game game = instance.getGameManager().getCurrent();
        if(game.getState() != Game.State.LOBBY){
            game.addPlayer(e.getPlayer().getUniqueId(), game.getRandomTeam());
            e.getPlayer().teleport(game.getLobby());
            Bukkit.broadcastMessage(game.getPrefix() + e.getPlayer().getName() + " has joined (" + game.getPlayers().size() + "/" + game.getMaxPlayers() + ")");
        }
    }
}
