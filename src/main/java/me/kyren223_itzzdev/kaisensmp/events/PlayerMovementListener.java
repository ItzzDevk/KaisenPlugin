package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.data.JujutsuPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMovementListener implements org.bukkit.event.Listener {

    public static HashMap<UUID, Integer> inCharge = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (inCharge.containsKey(playerUUID)) {
            event.setCancelled(true);
        }
    }
}
