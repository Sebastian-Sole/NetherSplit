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
    fun onLogin(event: PlayerJoinEvent) {
        //todo: This doesn't work
        val player = event.player
        giveStarterItems(player)
        buildPortal(player)
    }

    //todo; Generate this above all terrains.
    private fun buildPortal(player: Player) {
        val playerSpawnPoint = player.location
        val portalLocation = main.world?.getBlockAt(
            playerSpawnPoint.blockX + 5,
            main.world!!.getHighestBlockYAt(playerSpawnPoint.blockX + 5, playerSpawnPoint.blockY + 3) ,
            playerSpawnPoint.blockY + 3
        ) as Location
        for (i in 0..3) {
            for (j in 0..4) {
                val block = main.world!!.getBlockAt(
                    portalLocation.blockX + i,
                    portalLocation.blockY + j,
                    portalLocation.blockZ
                )
                if (i != 1 && (i != 2 || j != 1) && j != 2 && j != 3) {
                    block.type = Material.OBSIDIAN
                } else {
                    block.type = Material.NETHER_PORTAL
                }
            }
        }
    }

    private fun giveStarterItems(player: Player) {
        player.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE))
        player.inventory.addItem(ItemStack(Material.IRON_LEGGINGS))
        player.inventory.addItem(ItemStack(Material.IRON_HELMET))
        player.inventory.addItem(ItemStack(Material.GOLDEN_BOOTS))
        player.inventory.addItem(ItemStack(Material.IRON_AXE))
        player.inventory.addItem(ItemStack(Material.IRON_PICKAXE))
        player.inventory.addItem(ItemStack(Material.BREAD, 64))
        player.inventory.addItem(ItemStack(Material.COBBLESTONE, 64))
        player.inventory.addItem(ItemStack(Material.SHIELD))
        player.inventory.addItem(ItemStack(Material.OAK_PLANKS, 64))
        player.inventory.addItem(ItemStack(Material.FLINT_AND_STEEL))
        player.inventory.addItem(ItemStack(Material.GOLD_INGOT, 10))
        player.inventory.addItem(ItemStack(Material.WATER_BUCKET))
        player.inventory.addItem(ItemStack(Material.STONE_SHOVEL))
        player.inventory.addItem(ItemStack(Material.CRAFTING_TABLE))
        player.inventory.addItem(ItemStack(Material.FURNACE))
        player.inventory.addItem(ItemStack(Material.STICK, 10))
    }

    @EventHandler
    fun onAutocomplete(event: TabCompleteEvent) {
        val buffer = event.buffer
        if (!buffer.startsWith("/")) return
        val args: Array<String?> = buffer.split(" ").toTypedArray()
        val completions = main.commands.getCompletions(args, event.completions)
        event.completions = completions
    }
}