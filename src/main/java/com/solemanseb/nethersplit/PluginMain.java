package com.solemanseb.nethersplit;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.*;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class PluginMain extends JavaPlugin {

    private World world;
    private Logger logger;
    private boolean debugMode;
    private PluginCommands commands;

    @Override
    public void onDisable() {
        logger.info("BattleDome plugin disabled");
    }

    @Override
    public void onEnable() {
        logger = Logger.getLogger("com.solemanseb.battledome.PluginMain");
        logger = getLogger();
        logger.info("BattleDome Plugin Enabled!");
        saveDefaultConfig();
        debugMode = getConfig().getBoolean("debugMode", false);
        getServer().getPluginManager().registerEvents(new PluginListener(this), this);

        commands = new PluginCommands(this);
        for (String command : PluginCommands.registeredCommands) {
            this.getCommand(command).setExecutor(commands);
        }

        List<World> worlds = Bukkit.getWorlds();
        if (worlds.size() < 1) {
            logger.warning("Could not detect main world! Plugin will not work.");
        }
        world = worlds.get(0);

    }

    public World getWorld() {
        return world;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public PluginCommands getCommands() {
        return commands;
    }
}

