package me.kyren223_itzzdev.kaisensmp.data;

import me.kyren223_itzzdev.kaisensmp.items.CursedOrbItem;
import me.kyren223_itzzdev.kaisensmp.utils.CursedEnergy;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class JujutsuPlayer {

    private final Player player;
    private TechniqueType technique;
    private DomainType domainType;
    
    
    public JujutsuPlayer(Player player) {
        this.player = player;
        this.domainType = DomainType.random();
        ItemStack item = player.getInventory().getItemInOffHand();
        this.technique = loadTechnique(item);
        sync();
    }
    
    // Private
    
    private TechniqueType loadTechnique(ItemStack item) {
        if (item == null) return TechniqueType.NONE;
        if (!item.hasItemMeta()) return TechniqueType.NONE;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return TechniqueType.NONE;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        if (!pdc.has(CursedOrbItem.TECHNIQUE_KEY, PersistentDataType.STRING)) return TechniqueType.NONE;
        String technique = pdc.get(CursedOrbItem.TECHNIQUE_KEY, PersistentDataType.STRING);
        return TechniqueType.valueOf(technique);
    }

    private void loadFromItem(ItemStack item) {
        if (!CursedOrbItem.isItem(item)) return;
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        String type = pdc.getOrDefault(CursedOrbItem.DOMAIN_KEY, PersistentDataType.STRING, "NONE");
        try {
            this.domainType = DomainType.valueOf(type);
        } catch (IllegalArgumentException e) {
            this.domainType = DomainType.random();
        }
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
        sync();
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public TechniqueType getTechnique() {
        return this.technique;
    }
    
    public void setTechnique(TechniqueType technique) {
        this.technique = technique;
        sync();
    }
    
    public boolean hasTechnique() {
        return this.technique != TechniqueType.NONE;
    }
    
    public void sync() {
        CursedOrbItem.giveItem(player, technique, domainType);
    }
    
    // Static
    
    private static final Map<Player, JujutsuPlayer> players = new HashMap<>();
    
    public static JujutsuPlayer get(Player player) {
        if (!players.containsKey(player)) players.put(player, new JujutsuPlayer(player));
        return players.get(player);
    }
}
