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
        },0L,20L)
    }

    private fun getTime(): String {
        val hours: Int = (main.time / 3600).toInt()
        val minutes: Int = (main.time % 3600 / 60).toInt()
        val seconds: Int = (main.time % 3600 % 60).toInt()
        return "${hours}:${minutes}:${seconds}"
    }
}