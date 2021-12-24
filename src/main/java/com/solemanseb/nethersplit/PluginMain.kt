package com.solemanseb.nethersplit

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class PluginMain : JavaPlugin() {
    var world: World? = null
        private set
    var mainLogger: Logger = Logger.getLogger("com.solemanseb.nethersplit.PluginMain")
    var isDebugMode = false
    var commands: PluginCommands = PluginCommands(this)
    var ticks: Int = -1
    var taskManager: TaskManager = TaskManager(this)


    override fun onDisable() {
        logger.info("Nethersplit plugin disabled")
    }

    override fun onEnable() {
        mainLogger = Logger.getLogger("com.solemanseb.nethersplit.PluginMain")
        mainLogger = logger
        mainLogger.info("Nethersplit Plugin Enabled!")
        saveDefaultConfig()
        isDebugMode = config.getBoolean("debugMode", false)
        server.pluginManager.registerEvents(PluginListener(this), this)
        commands = PluginCommands(this)
        for (command in PluginCommands.registeredCommands) {
            getCommand(command)!!.setExecutor(commands)
        }
        val worlds = Bukkit.getWorlds()
        if (worlds.size < 1) {
            mainLogger.warning("Could not detect main world! Plugin will not work.")
        }
        world = worlds[0]
    }

    override fun getLogger(): Logger {
        return mainLogger
    }
}