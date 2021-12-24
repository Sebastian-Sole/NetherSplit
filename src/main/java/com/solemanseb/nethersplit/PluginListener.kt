package com.solemanseb.nethersplit

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.server.TabCompleteEvent
import org.bukkit.inventory.ItemStack

class PluginListener(private val main: PluginMain) : Listener {

    @EventHandler
    fun onAutocomplete(event: TabCompleteEvent) {
        val buffer = event.buffer
        if (!buffer.startsWith("/")) return
        val args: Array<String?> = buffer.split(" ").toTypedArray()
        val completions = main.commands.getCompletions(args, event.completions)
        event.completions = completions
    }
}