package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.techniques.TechniqueAbilities;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.UUID;

public class EntityDamageListener implements org.bukkit.event.Listener {

    public static HashMap<UUID, Integer> inInfinity = new HashMap<>();

    @EventHandler
    public void onPlayerHit(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        UUID playerUUID = entity.getUniqueId();

        if (entity instanceof Player) {
            if (inInfinity.containsKey(playerUUID)) {
                event.setCancelled(true);
                // gotta add Glass Sphere animation
                entity.getWorld().playSound(entity.getLocation(), Sound.ITEM_SHIELD_BLOCK, 2F, 1F);
            }
        }
    }
}
