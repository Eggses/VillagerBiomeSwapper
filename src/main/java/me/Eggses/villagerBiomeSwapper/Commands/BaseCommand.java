package me.Eggses.villagerBiomeSwapper.Commands;

import me.Eggses.villagerBiomeSwapper.Commands.SubCommands.*;
import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.Commands;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.Utility.PlaceHolder;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BaseCommand implements CommandExecutor {

    private final VillagerBiomeSwapper plugin;
    private final CustomConfigurationFile biomeSwapperItemFile;
    private final CustomConfigurationFile guiFile;
    private final CustomConfigurationFile messagesFile;
    private final MessageCreation messageCreation;
    private final SwapperItem swapperItem;

    Map<String, SubCommand> subCommandMap = new HashMap<>();

    public BaseCommand(VillagerBiomeSwapper plugin, CustomConfigurationFile biomeSwapperItemFile,
                       CustomConfigurationFile guiFile, CustomConfigurationFile messagesFile,
                       MessageCreation messageCreation, SwapperItem swapperItem) {

        this.plugin = plugin;
        this.biomeSwapperItemFile = biomeSwapperItemFile;
        this.guiFile = guiFile;
        this.messagesFile = messagesFile;
        this.messageCreation = messageCreation;
        this.swapperItem = swapperItem;

        registerSubCommands();
    }

    private void registerSubCommands() {

        subCommandMap.put(Commands.GIVE.getCommand(), new GiveItemCommand(swapperItem, messageCreation));

        subCommandMap.put(Commands.GIVE_ALL.getCommand(), new GiveAllItemCommand(swapperItem, messageCreation));

        subCommandMap.put(Commands.HELP.getCommand(), new HelpCommand(messageCreation));

        subCommandMap.put(Commands.RELOAD.getCommand(),
                new ReloadCommand(plugin, biomeSwapperItemFile, guiFile, messagesFile, messageCreation));
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command,
                                @NotNull String s, @NotNull String @NotNull [] args) {

        Map<String, String> placeHolders = new HashMap<>();

        if (sender instanceof Player player) {
            placeHolders.put(PlaceHolder.PLAYER.getPlaceHolder(), player.getName());
        }

        // No Permission
        if (!sender.hasPermission(Permission.BASE.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return true;
        }

        // No Arguments
        if (args.length == 0) {
            sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_COMMAND.getMessage(), placeHolders));
            return true;
        }

        // Run SubCommand
        SubCommand subCommand = subCommandMap.get(args[0].toLowerCase());

        if (subCommand != null) {
            subCommand.execute(sender, args, placeHolders);
        }
        else {
            sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_COMMAND.getMessage(), placeHolders));
            return true;
        }

        return true;
    }
}