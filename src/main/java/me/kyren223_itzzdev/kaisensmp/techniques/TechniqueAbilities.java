package me.kyren223_itzzdev.kaisensmp.techniques;

import me.kyren223_itzzdev.kaisensmp.events.EntityDamageListener;
import me.kyren223_itzzdev.kaisensmp.events.PlayerMovementListener;
import me.kyren223_itzzdev.kaisensmp.utils.CursedEnergy;
import me.kyren223_itzzdev.kaisensmp.KaisenSMP;
import me.kyren223_itzzdev.kaisensmp.utils.ParticleHelper;
import me.kyren223_itzzdev.kaisensmp.utils.Utils;
import me.kyren223_itzzdev.kaisensmp.data.TechniqueType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class TechniqueAbilities {
    
    /**
     * Activates the technique for the player
     * @param technique The type of technique to activate
     * @param e The event that triggered this method
     */
    public static void activate(TechniqueType technique, PlayerInteractEvent e) {
        switch (technique) {
            case PURPLE -> activatePurple(e);
            case BLUE -> activateBlue(e);
            case INFINITY -> activateInfinity(e);
            case FIRE_ARROW -> activateFireArrow(e);
            case MAXIMUM_METEOR -> activateMaximumMeteor(e);
            case EMBER_INSECTS -> activateEmberInsects(e);
            case RESERVE_CURSED_TECHNIQUE -> activateReverseCursedTechnique(e);
            case CLEAVE -> activateCleave(e);
            case DISASTER_FLAMES -> activateDisasterFlames(e);
        }
    }
    
    /**
     * Activates the purple technique for the player
     * @param e The event that triggered this method
     */
    private static void activatePurple(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&dHollow Technique: Purple | USED!"));
    }
    
    /**
     * Activates the blue technique for the player
     * @param e The event that triggered this method
     */
    public static void activateBlue(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&bCursed Technique Lapse: Blue | USED!"));
    }

    /**
     * Activates the infinity technique for the player
     * @param e The event that triggered this method
     */
    public static void activateInfinity(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        UUID playerUUID = player.getUniqueId();

        player.sendMessage(("§1Infinity Technique | USED!"));
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2F, 1F);
        EntityDamageListener.inInfinity.put(playerUUID, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 30, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 30, 1));
        // gotta add Glass Sphere animation

        Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
            EntityDamageListener.inInfinity.remove(playerUUID);
            // gotta add Glass Sphere animation
        }, 30 * 20);
    }
    
    /**
     * Activates the fire arrow technique for the player
     * @param e The event that triggered this method
     */
    public static void activateFireArrow(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&cFire Arrow Technique | USED!"));
    }
    
    /**
     * Activates the maximum meteor technique for the player
     * @param e The event that triggered this method
     */
    public static void activateMaximumMeteor(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&4Maximum Meteor Technique | USED!"));
    }
    
    /**
     * Activates the ember insects technique for the player
     * @param e The event that triggered this method
     */
    public static void activateEmberInsects(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&6Ember Insects Technique | USED!"));
    }
    
    /**
     * Activates the reverse cursed technique for the player
     * @param e The event that triggered this method
     */
    public static void activateReverseCursedTechnique(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(("§1Reverse Cursed Technique | USED!"));
        UUID playerUUID = player.getUniqueId();

        PlayerMovementListener.inCharge.put(playerUUID, 1);
        for (int i = 0; i < 35; i++) {
            Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
                Vector v = player.getEyeLocation().getDirection().multiply(2);
                Location loc = player.getEyeLocation().add(v);
                player.getWorld().spawnParticle(Particle.HEART, loc, 1, 0, 0, 0, 0, null);
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1F, 1F);
            }, i * 3L);
        }

        Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
            PlayerMovementListener.inCharge.remove(playerUUID);
        }, 105L);
        
        for (int i = 0; i < 3; i++) {
            Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
                if (!player.isOnline()) return;
                ParticleHelper.heartsParticles(player);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 2F, 1F);
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 300, 9));
            }, (i * 20L) + (5 * 20));
        }
    }
    
    /**
     * Activates the cleave technique for the player
     * @param e The event that triggered this method
     */
    public static void activateCleave(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&c7Cleave Technique | USED!"));
        
        for (int i = 0; i < 20; i++) {
            Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
                        for (Entity entity : player.getNearbyEntities(4, 4, 4)) {
                            if (entity instanceof Player) {
                                ((Player) entity).damage(4);
                                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40 * 20, 0));
                                
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 2F, 1F);
                                ParticleHelper.bloodParticles((Player) entity);
                            }
                        }
            }, i * 10L);
        }
        
        for (int in = 0; in < 100; in++) {
            Bukkit.getScheduler().runTaskLater(KaisenSMP.plugin, () -> {
                ParticleHelper.createParticleCircle3(e.getPlayer());
            }, in * 2L);
        }
    }
    
    /**
     * Activates the disasterflames technique for the player
     * @param e The event that triggered this method
     */
    public static void activateDisasterFlames(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!CursedEnergy.use(player, 100)) return;
        player.sendMessage(Utils.col("&cDisaster Flames Technique | USED!"));
    }
}
