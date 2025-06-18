package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class GiveItemCommand implements SubCommand {

    private final MessageCreation messageCreation;

    public GiveItemCommand(MessageCreation messageCreation) {
        this.messageCreation = messageCreation;
    }

    @Override
    public void execute(CommandSender sender, String[] args, Map<String, String> placeHolders) {

        if (!sender.hasPermission(Permission.GIVE_ITEM.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(messageCreation.createMessage(Messages.NOT_PLAYER.getMessage(), placeHolders));
            return;
        }

        if (args.length > 2) {
            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_SYNTAX_ERROR.getMessage(), placeHolders));
            return;
        }

        if (args.length == 1) {

            // Give Item to yourself
            // TODO
            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_SELF.getMessage(), placeHolders));
            return;
        }

        if (args.length == 2) {

            Player player = Bukkit.getPlayer(args[1]);

            if (player == null) {
                sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_PLAYER.getMessage(), placeHolders));
                return;
            }

            if (player == sender) {
                // Give Item to yourself
                // TODO
                sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_SELF.getMessage(), placeHolders));
            }

            // TODO
            // give the other player the item.

            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_TARGET.getMessage(), placeHolders));
        }
    }
}
