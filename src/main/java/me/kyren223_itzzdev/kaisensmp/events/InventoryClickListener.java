package me.kyren223_itzzdev.kaisensmp.events;

import me.kyren223_itzzdev.kaisensmp.items.CursedOrbItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryClickListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        // Check if offhand was touched, and if so, cancel the event
        if (e.getClick() == ClickType.SWAP_OFFHAND) e.setCancelled(true);
        boolean isCurrentOffhand = CursedOrbItem.isItem(e.getCurrentItem());
        boolean isCursorOffhand = CursedOrbItem.isItem(e.getCursor());
        boolean isHotbarOffhand = e.getHotbarButton() != -1;
        isHotbarOffhand = isHotbarOffhand && CursedOrbItem.isItem(e.getWhoClicked().getInventory().getItem(e.getHotbarButton()));
        if (isCurrentOffhand || isCursorOffhand || isHotbarOffhand) e.setCancelled(true);
    }
    
    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent e) {
        if (CursedOrbItem.isItem(e.getItemDrop().getItemStack())) e.setCancelled(true);
    }
}
