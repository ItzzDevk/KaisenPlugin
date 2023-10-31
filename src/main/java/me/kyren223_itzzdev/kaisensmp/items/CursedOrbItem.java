package me.kyren223_itzzdev.kaisensmp.items;

import me.kyren223_itzzdev.kaisensmp.KaisenSMP;
import me.kyren223_itzzdev.kaisensmp.utils.Utils;
import me.kyren223_itzzdev.kaisensmp.data.TechniqueType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class CursedOrbItem {
    
    public static final String DISPLAY_NAME = Utils.col("&d&lCursed Orb");
    
    public static final NamespacedKey TECHNIQUE_KEY =
            new NamespacedKey(KaisenSMP.plugin, "technique");
    

    public static void giveItem(Player player, TechniqueType technique) {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta meta = item.getItemMeta();
        assert meta != null; // Avoids null checks
        
        // Visual
        meta.setDisplayName(DISPLAY_NAME);
        List<String> lore = new ArrayList<>();
        lore.add(Utils.col("&7Cursed Technique: &8" + technique.getDisplayName()));
        meta.setLore(lore);
        meta.setCustomModelData(223);
        
        // Data
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(TECHNIQUE_KEY, PersistentDataType.STRING, technique.name());
        
        item.setItemMeta(meta);
        player.getInventory().setItemInOffHand(item);
    }
    
    public static boolean isItem(ItemStack item) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        return meta.getCustomModelData() == 223;
    }
}
