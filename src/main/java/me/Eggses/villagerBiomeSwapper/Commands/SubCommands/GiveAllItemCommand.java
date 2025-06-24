package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class GiveAllItemCommand implements SubCommand {

    private final SwapperItem swapperItem;
    private final MessageCreation messageCreation;

    public GiveAllItemCommand(SwapperItem swapperItem, MessageCreation messageCreation) {
        this.swapperItem = swapperItem;
        this.messageCreation = messageCreation;
    }

    @Override
    public void execute(CommandSender sender, String[] args, Map<String, String> placeHolders) {

        if (!sender.hasPermission(Permission.GIVE_ITEM.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return;
        }

        if (args.length > 1) {
            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ALL_ITEM_SYNTAX_ERROR.getMessage(), placeHolders));
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            swapperItem.giveSwapperItemToPlayer(player, placeHolders);
        }

        sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ALL_ITEM.getMessage(), placeHolders));
    }
}
