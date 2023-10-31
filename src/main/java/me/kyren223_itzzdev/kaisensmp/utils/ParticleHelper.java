package me.kyren223_itzzdev.kaisensmp.utils;

import me.kyren223_itzzdev.kaisensmp.KaisenSMP;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleHelper {
    
    public static void heartsParticles(Player player) {
        player.spawnParticle(Particle.HEART, player.getLocation().add(0.0D, 0.5D, 0.0D), 30, 0.4D, 1.0D, 0.4D, 0.0D);
    }
    
    public static void bloodParticles(Player player) {
        player.spawnParticle(Particle.REDSTONE, player.getLocation().add(0.0D, 0.4D, 0.0D), 10, 0.3D, 1.1D, 0.5D, 0.0D);
    }
    
    public static void createParticleCircle3(Player players) {
        final int radius = 4;
        final int particleCount = 150;
        (new BukkitRunnable() {
            int ticksPassed = 0;
            
            int particlesDuration = 140;
            
            Location center = players.getLocation();
            
            double pullRadius = 6.0D;
            
            double speed = 0.5D;
            
            public void run() {
                int i;
                for (i = 0; i < particleCount; i++) {
                    double angle = 6.283185307179586D * i / particleCount;
                    double x = this.center.getX() + radius * Math.cos(angle);
                    double z = this.center.getZ() + radius * Math.sin(angle);
                    Location particleLoc = new Location(this.center.getWorld(), x, this.center.getY(), z);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GRAY, 1.0F);
                    particleLoc.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, dustOptions);
                }
                this.ticksPassed += 5;
                if (this.ticksPassed >= this.particlesDuration) {
                    cancel();
                    for (i = 0; i < particleCount; i++) {
                        double angle = 6.283185307179586D * i / particleCount;
                        double x = this.center.getX() + radius * Math.cos(angle);
                        double z = this.center.getZ() + radius * Math.sin(angle);
                        Location particleLoc = new Location(this.center.getWorld(), x, this.center.getY(), z);
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.WHITE, 1.0F);
                        particleLoc.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 0, dustOptions);
                    }
                }
            }
        }).runTaskTimer(KaisenSMP.plugin, 0L, 5L);
    }
}
