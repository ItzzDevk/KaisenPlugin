package me.kyren223_itzzdev.kaisensmp;

import me.kyren223_itzzdev.kaisensmp.commands.CursedEnergyCommand;
import me.kyren223_itzzdev.kaisensmp.data.JujutsuPlayer;
import me.kyren223_itzzdev.kaisensmp.events.*;
import me.kyren223_itzzdev.kaisensmp.utils.CursedEnergy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class KaisenSMP extends JavaPlugin {
    
    public static KaisenSMP plugin;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        
        CursedEnergy.init();
        
        registerEvents();
        registerCommands();
        
        for (Player player : Bukkit.getOnlinePlayers()) {
            JujutsuPlayer.get(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            JujutsuPlayer.get(player).sync();
        }
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
    }
    
    private void registerCommands() {
        getCommand("energy").setExecutor(new CursedEnergyCommand());
    }
}
