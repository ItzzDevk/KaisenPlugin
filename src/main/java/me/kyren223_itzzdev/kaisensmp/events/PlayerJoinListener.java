package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.data.JujutsuPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        JujutsuPlayer.get(e.getPlayer());
    }
    
    @EventHandler
    public void onQuit(PlayerJoinEvent e) {
        JujutsuPlayer.get(e.getPlayer()).sync();
    }
}
