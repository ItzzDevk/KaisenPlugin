package me.kyren223_itzzdev.kaisensmp.data;

public enum TechniqueType {
    PURPLE,
    FIRE_ARROW,
    BLUE,
    INFINITY,
    MAXIMUM_METEOR,
    EMBER_INSECTS,
    RESERVE_CURSED_TECHNIQUE,
    CLEAVE,
    DISASTER_FLAMES,
    NONE,
    ;
    
    public String getDisplayName() {
        return switch (this) {
            case PURPLE -> "&5Purple";
            case FIRE_ARROW -> "&6Fire Arrow";
            case BLUE -> "&9Blue";
            case INFINITY -> "&bInfinity";
            case MAXIMUM_METEOR -> "&7Maximum Meteor";
            case EMBER_INSECTS -> "&cEmber Insects";
            case RESERVE_CURSED_TECHNIQUE -> "&aReserve Cursed Technique";
            case CLEAVE -> "&eCleave";
            case DISASTER_FLAMES -> "&4Disaster Flames";
            default -> "None";
        };
    }
}
