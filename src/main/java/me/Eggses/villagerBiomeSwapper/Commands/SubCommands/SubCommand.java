package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import org.bukkit.command.CommandSender;

import java.util.Map;

@FunctionalInterface
public interface SubCommand {
    void execute(CommandSender sender, String[] args, Map<String, String> placeHolders);
}
