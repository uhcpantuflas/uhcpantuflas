package com.github.uhcpantuflas.uhcpantuflas;

import com.github.uhcpantuflas.uhcpantuflas.config.ConfigurationManager;
import com.github.uhcpantuflas.uhcpantuflas.config.UHCConfigCommandExecutor;
import com.github.uhcpantuflas.uhcpantuflas.config.UHCConfigTabCompleter;
import com.github.uhcpantuflas.uhcpantuflas.gameplay.UHCStartCommandExecutor;
import com.github.uhcpantuflas.uhcpantuflas.player.*;
import com.github.uhcpantuflas.uhcpantuflas.teams.TeamManager;
import com.github.uhcpantuflas.uhcpantuflas.teams.UHCTeamCommandExecutor;
import com.github.uhcpantuflas.uhcpantuflas.teams.UHCTeamTabCompleter;
import com.github.uhcpantuflas.uhcpantuflas.world.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * UHCPantuflas is a Minecraft plugin that allows players to create and
 * customize
 * "Ultra Hardcore" (UHC) game mode matches on a Minecraft server.
 * <p>
 * UHC is a challenging game mode where players must survive in a world where
 * natural health regeneration is disabled. Players have various methods to
 * regain
 * health artificially, including consuming golden apples or instant health
 * potions.
 * Furthermore, once a player dies, they are eliminated from the game with no
 * possibility of respawning.
 * <p>
 * This plugin is designed to handle the core functionality of the UHC game mode
 * within the Minecraft server environment. It extends the JavaPlugin class,
 * allowing it to integrate seamlessly with the Bukkit/Spigot API.
 *
 */
@SuppressWarnings("unused")
public final class UHCPantuflas extends JavaPlugin {

    /*
     * TODO 05/10/2023 add javadoc
     * Called when the plugin is enabled.
     * <p>
     * This method is automatically invoked when the plugin is loaded and enabled
     * by the server. It should be used for any setup, initialization, and
     * registration
     * of listeners and commands required by the plugin.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load current configuration
        saveDefaultConfig();
        ConfigurationManager.get().init(getConfig());

        // Register all listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PortalCreateListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new PrepareItemCraftListener(), this);

        // Register all commands
        Objects.requireNonNull(getCommand(UHCConfigCommandExecutor.NAME)).setExecutor(new UHCConfigCommandExecutor());
        Objects.requireNonNull(getCommand(UHCConfigCommandExecutor.NAME)).setTabCompleter(new UHCConfigTabCompleter());

        Objects.requireNonNull(getCommand(UHCTeamCommandExecutor.NAME)).setExecutor(new UHCTeamCommandExecutor());
        Objects.requireNonNull(getCommand(UHCTeamCommandExecutor.NAME)).setTabCompleter(new UHCTeamTabCompleter());

        Objects.requireNonNull(getCommand(UHCStartCommandExecutor.NAME)).setExecutor(new UHCStartCommandExecutor());

        Objects.requireNonNull(getCommand(UHCSpectateCommandExecutor.NAME))
                .setExecutor(new UHCSpectateCommandExecutor());

        Objects.requireNonNull(getCommand(UHCLobbyCommandExecutor.NAME)).setExecutor(new UHCLobbyCommandExecutor());

        // Initialization
        WorldManager.get().init(this);
        TimeManager.get().init(this);

        System.out.println("Plugin UHC Pantuflas-0.0.0-INITIAL successfully charged");
    }

    /*
     * TODO 05/10/2023 add javadoc
     * Called when the plugin is disabled.
     * <p>
     * This method is automatically invoked when the plugin is unloaded and disabled
     * by the server. It should be used for any cleanup, saving configuration, and
     * releasing resources held by the plugin.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();

        // Remove all data from managers
        TeamManager.get().wipe();
        WorldManager.get().wipe();
    }
}
