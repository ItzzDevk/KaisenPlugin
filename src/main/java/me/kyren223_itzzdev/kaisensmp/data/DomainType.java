package me.kyren223_itzzdev.kaisensmp.data;

import org.bukkit.boss.BarColor;

public enum DomainType {
    MELAVOLANT_SHRINE,
    ULTIMATE_VOID,
    COFFIN_OF_THE_IRON_MOUNTAIN,
    ;

    public static DomainType random() {
        return values()[(int) (Math.random() * values().length)];
    }

    public String getDisplayName() {
        return switch (this) {
            case MELAVOLANT_SHRINE -> "&d&lMelavolant &5&lShrine";
            case ULTIMATE_VOID -> "&f&lUltimate &b&lVoid";
            case COFFIN_OF_THE_IRON_MOUNTAIN -> "&e&lCoffin of the &c&lIron Mountain";
        };
    }

    public BarColor getColor() {
        return switch (this) {
            case MELAVOLANT_SHRINE -> BarColor.PINK;
            case ULTIMATE_VOID -> BarColor.BLUE;
            case COFFIN_OF_THE_IRON_MOUNTAIN -> BarColor.RED;
        };
    }
}