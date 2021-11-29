package com.solemanseb.nethersplit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PluginListener implements Listener {
    private final PluginMain main;

    public PluginListener(PluginMain main) {
        this.main = main;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        giveStarterItems(player);
        buildPortal(player);
    }

    //todo; Generate this above all terrains.
    private void buildPortal(Player player) {
        Location playerSpawnPoint = player.getLocation();
        Location portalLocation =
                (Location) main.getWorld().getBlockAt(playerSpawnPoint.getBlockX() + 5,
                        main.getWorld().getHighestBlockYAt(playerSpawnPoint.getBlockX() + 5, playerSpawnPoint.getBlockY()+3),
                        playerSpawnPoint.getBlockY() + 3);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                Block block = main.getWorld().getBlockAt(
                        portalLocation.getBlockX() + i,
                        portalLocation.getBlockY() + j,
                        portalLocation.getBlockZ());
                if (i != 1 && (i != 2 || j != 1) && j != 2 && j != 3) {
                            block.setType(Material.OBSIDIAN);
                        }
                else {
                    block.setType(Material.NETHER_PORTAL);
                }
            }
        }
    }

    private void giveStarterItems(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_BOOTS));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.BREAD,64));
        player.getInventory().addItem(new ItemStack(Material.COBBLESTONE,64));
        player.getInventory().addItem(new ItemStack(Material.SHIELD));
        player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS,64));
        player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
        player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT,10));
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
        player.getInventory().addItem(new ItemStack(Material.STONE_SHOVEL));
        player.getInventory().addItem(new ItemStack(Material.CRAFTING_TABLE));
        player.getInventory().addItem(new ItemStack(Material.FURNACE));
        player.getInventory().addItem(new ItemStack(Material.STICK,10));

    }

    @EventHandler
    public void onAutocomplete(TabCompleteEvent event){
        String buffer = event.getBuffer();
        if(!buffer.startsWith("/")) return;
        String[] args = buffer.split(" ");

        List<String> completions = main.getCommands().getCompletions(args, event.getCompletions());

        event.setCompletions(completions);
    }

}
