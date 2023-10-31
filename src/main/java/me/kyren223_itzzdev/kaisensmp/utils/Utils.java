package me.kyren223_itzzdev.kaisensmp.utils;

import org.bukkit.ChatColor;

public class Utils {
    
    public static final int TICKS_PER_SECOND = 20;
    
    /**
     * Translates color codes
     * @param s String to translate
     * @return Translated string
     */
    public static String col(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
