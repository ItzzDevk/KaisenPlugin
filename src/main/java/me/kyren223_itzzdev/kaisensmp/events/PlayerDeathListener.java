package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.data.JujutsuPlayer;
import me.kyren223_itzzdev.kaisensmp.items.CursedOrbItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getDrops().removeIf(CursedOrbItem::isItem);
    }
    
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        JujutsuPlayer player = JujutsuPlayer.get(e.getPlayer());
        player.sync();
    }
}
