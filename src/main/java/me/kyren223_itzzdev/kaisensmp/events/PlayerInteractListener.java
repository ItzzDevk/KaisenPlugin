package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.data.JujutsuPlayer;
import me.kyren223_itzzdev.kaisensmp.techniques.TechniqueAbilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.OFF_HAND) return;
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        JujutsuPlayer player = JujutsuPlayer.get(e.getPlayer());
        if (!player.hasTechnique()) return;
        TechniqueAbilities.activate(player.getTechnique(), e);
    }
    
    @EventHandler
    public void onPlayerSwapHandsEvent(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
        JujutsuPlayer player = JujutsuPlayer.get(e.getPlayer());
        // toggle the technique
    }
}
