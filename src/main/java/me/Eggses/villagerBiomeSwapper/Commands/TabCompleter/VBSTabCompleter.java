package me.Eggses.villagerBiomeSwapper.Commands.TabCompleter;

import me.Eggses.villagerBiomeSwapper.Utility.Commands;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VBSTabCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (args.length == 1) {

            List<String> suggestions = new ArrayList<>();

            Map<Commands, Permission> commandsPermissionMap = new HashMap<>();
            commandsPermissionMap.put(Commands.GIVE, Permission.GIVE_ITEM);
            commandsPermissionMap.put(Commands.GIVE_ALL, Permission.GIVE_ITEM);
            commandsPermissionMap.put(Commands.HELP, Permission.HELP);
            commandsPermissionMap.put(Commands.RELOAD, Permission.RELOAD);

            for (Map.Entry<Commands, Permission> entry : commandsPermissionMap.entrySet()) {
                if (sender.hasPermission(entry.getValue().getPermission())) {
                    suggestions.add(entry.getKey().getCommand());
                }
            }

            return suggestions;
        }

        if (args.length == 2) {

            List<String> suggestions = new ArrayList<>();

            if (args[0].equals(Commands.GIVE.getCommand())) {

                if (!sender.hasPermission(Permission.GIVE_ITEM.getPermission())) {
                    return List.of();
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    suggestions.add(player.getName());
                }
            }

            else if (args[0].equals(Commands.RELOAD.getCommand())) {
                suggestions.add(Commands.RELOAD_GUI.getCommand());
                suggestions.add(Commands.RELOAD_ITEM.getCommand());
                suggestions.add(Commands.RELOAD_MESSAGES.getCommand());
                suggestions.add(Commands.RELOAD_CONFIG.getCommand());
            }

            return suggestions;
        }
        return List.of();
    }
}