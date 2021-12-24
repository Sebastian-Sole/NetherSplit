package com.solemanseb.nethersplit

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class TaskManager(private val main: PluginMain) {

    fun startTimer(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, {
            main.ticks++
        },0L,1L)

    }

    fun updateActionBar(player: Player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(""" ${ChatColor.BOLD} ${ChatColor.RED} ${getTime()} """))
            Bukkit.broadcastMessage("${main.ticks}")
            Bukkit.broadcastMessage("${main.ticks/20}")
        },0L,20L)
    }

    private fun getTime(): String {
        val hours: Int = (main.ticks/20 / 3600)
        val minutes: Int = ((main.ticks/20 % 3600) / 60)
        val seconds: Int = main.ticks/20 % 60
        return "${hours}:${minutes}:${seconds}"
    }
}