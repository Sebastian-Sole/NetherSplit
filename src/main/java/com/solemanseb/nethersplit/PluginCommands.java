package com.solemanseb.nethersplit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class PluginCommands implements CommandExecutor {
    public static String[] registeredCommands = {
            "reset",
            "startTimer",
            "end"
    };
    private final PluginMain main;

    public PluginCommands(PluginMain main) {
        this.main = main;
    }

    public List<String> getCompletions(String[] args, List<String> existingCompletions){
        switch (args[0]){
            case "/blueteam":
            case "/redteam":
            case "clearteams":
                return new ArrayList<String>();
            case "/start":
            case "/end":
            case "/top":
            case "/tp":
            default:
                return existingCompletions;
        }
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (label.equals("startTimer")){
            if (args.length > 1){
                commandSender.sendMessage("Invalid format.");
                return true;
            }
        }
        return true;
    }
}
