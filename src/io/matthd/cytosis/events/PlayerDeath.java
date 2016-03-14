package io.matthd.cytosis.events;

import io.matthd.cytosis.Cytosis;
import io.matthd.cytosis.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Matt on 2016-03-13.
 */
public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Game game = Cytosis.getInstance().getGameManager().getCurrent();
        game.onDeath(e.getEntity(), e);
    }
}
