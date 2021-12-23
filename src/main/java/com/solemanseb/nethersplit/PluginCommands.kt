package com.solemanseb.nethersplit

import com.solemanseb.nethersplit.PluginMain
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.ArrayList

class PluginCommands(private val main: PluginMain) : CommandExecutor {
    fun getCompletions(args: Array<String?>, existingCompletions: List<String>): List<String> {
        return when (args[0]) {
            "/start" -> existingCompletions
            else -> existingCompletions
        }
    }

    override fun onCommand(commandSender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (label == "start") {
            if (args.size > 1) {
                commandSender.sendMessage("Invalid format.")
                return true
            }
            main.taskManager.startTimer()
            main.taskManager.updateActionBar(commandSender as Player)
        }
        return true
    }

    companion object {
        @JvmField
        var registeredCommands = arrayOf(
            "start",
        )
    }
}