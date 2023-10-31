package me.kyren223_itzzdev.kaisensmp.utils;

import me.kyren223_itzzdev.kaisensmp.KaisenSMP;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CursedEnergy {
    
    private static final HashMap<String, Integer> cursedEnergies = new HashMap<>();
    public static final int MAX_CURSED_ENERGY = 1000;
    
    public static void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(KaisenSMP.plugin, CursedEnergy::tick, 0, 10);
    }
    
    private static void tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (getCursedEnergy(player) < MAX_CURSED_ENERGY) {
                cursedEnergies.put(player.getUniqueId().toString(), getCursedEnergy(player) + 1);
            }
            
            // Display in action bar
            String s = Utils.col(String.format("&5☠ &d%d &5/ &d%d &5☠", getCursedEnergy(player), MAX_CURSED_ENERGY));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
        }
    }
    
    public static boolean use(Player player, int amount) {
        int cursedEnergy = getCursedEnergy(player);
        if (cursedEnergy < amount) {
            player.sendMessage(Utils.col("&cYou do not have enough cursed energy!"));
            return false;
        }
        cursedEnergies.put(player.getUniqueId().toString(), cursedEnergy - amount);
        return true;
    }
    
    /**
     * Gets the remaining cursed energy for a player
     * @param player The player to get the cooldown for
     * @return The time left for the cooldown in milliseconds
     */
    public static int getCursedEnergy(Player player) {
        if (!cursedEnergies.containsKey(player.getUniqueId().toString())) {
            cursedEnergies.put(player.getUniqueId().toString(), 0);
        }
        return cursedEnergies.get(player.getUniqueId().toString());
    }
    
    /**
     * Sets the cursed energy for a player
     * @param player The player to set the cursed energy for
     * @param amount The amount of cursed energy to set
     */
    public static void set(Player player, int amount) {
        cursedEnergies.put(player.getUniqueId().toString(), amount);
    }
}
