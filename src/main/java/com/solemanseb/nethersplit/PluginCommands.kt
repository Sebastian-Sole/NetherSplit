package com.solemanseb.nethersplit

import com.solemanseb.nethersplit.PluginMain
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
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
            // todo: Portal not being built
            buildPortal(commandSender)
            giveStarterItems(commandSender)

        }
        return true
    }

    private fun buildPortal(player: Player) {
        val playerSpawnPoint = player.location
        val portalLocationBlock = Location(main.world,playerSpawnPoint.x+3,playerSpawnPoint.y,playerSpawnPoint.z+3)

        for (i in 0..3) {
            for (j in 0..4) {
                val block = main.world!!.getBlockAt(portalLocationBlock.blockX + i, portalLocationBlock.blockY + j, portalLocationBlock.blockZ)
                if (!(i!=0 && i!=3) || !(j!=0 && j!=4)) {
                    block.type = Material.OBSIDIAN
                }
            }
        }
        main.world!!.getBlockAt(portalLocationBlock.blockX + 1, portalLocationBlock.blockY+1, portalLocationBlock.blockZ).type = Material.FIRE

        Bukkit.broadcastMessage("Portal spawned at: ${portalLocationBlock.x}, ${portalLocationBlock.y}, ${portalLocationBlock.z}")
    }

    private fun giveStarterItems(player: Player) {
        player.inventory.chestplate = (ItemStack(Material.IRON_CHESTPLATE))
        player.inventory.leggings = (ItemStack(Material.IRON_LEGGINGS))
        player.inventory.helmet = (ItemStack(Material.IRON_HELMET))
        player.inventory.boots = (ItemStack(Material.GOLDEN_BOOTS))
        player.inventory.addItem(ItemStack(Material.IRON_AXE))
        player.inventory.addItem(ItemStack(Material.IRON_PICKAXE))
        player.inventory.addItem(ItemStack(Material.COBBLESTONE, 64))
        player.inventory.addItem(ItemStack(Material.BREAD, 64))
        player.inventory.setItemInOffHand(ItemStack(Material.SHIELD))
        player.inventory.addItem(ItemStack(Material.OAK_PLANKS, 64))
        player.inventory.addItem(ItemStack(Material.FLINT_AND_STEEL))
        player.inventory.addItem(ItemStack(Material.GOLD_INGOT, 10))
        player.inventory.addItem(ItemStack(Material.CRAFTING_TABLE))
        player.inventory.addItem(ItemStack(Material.STONE_SHOVEL))
        player.inventory.addItem(ItemStack(Material.WATER_BUCKET))
        player.inventory.addItem(ItemStack(Material.FURNACE))
        player.inventory.addItem(ItemStack(Material.STICK, 10))
    }

    companion object {
        @JvmField
        var registeredCommands = arrayOf(
            "start",
        )
    }
}