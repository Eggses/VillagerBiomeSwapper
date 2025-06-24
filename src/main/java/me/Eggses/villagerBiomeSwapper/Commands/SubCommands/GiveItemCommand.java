package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.Utility.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class GiveItemCommand implements SubCommand {

    private final SwapperItem swapperItem;
    private final MessageCreation messageCreation;

    public GiveItemCommand(SwapperItem swapperItem, MessageCreation messageCreation) {
        this.swapperItem = swapperItem;
        this.messageCreation = messageCreation;
    }

    @Override
    public void execute(CommandSender sender, String[] args, Map<String, String> placeHolders) {

        if (!sender.hasPermission(Permission.GIVE_ITEM.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return;
        }

        if (args.length > 2) {
            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_SYNTAX_ERROR.getMessage(), placeHolders));
            return;
        }

        if (args.length == 1) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage(messageCreation.createMessage(Messages.NOT_PLAYER.getMessage(), placeHolders));
                return;
            }

            swapperItem.giveSwapperItemToPlayer(player, placeHolders);

            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_SELF.getMessage(), placeHolders));
            return;
        }

        if (args.length == 2) {

            placeHolders.put(PlaceHolder.TARGET_PLAYER.getPlaceHolder(), args[1]);
            Player player = Bukkit.getPlayer(args[1]);

            if (player == null) {
                sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_PLAYER.getMessage(), placeHolders));
                return;
            }

            swapperItem.giveSwapperItemToPlayer(player, placeHolders);
            sender.sendMessage(messageCreation.createMessage(Messages.GIVE_ITEM_TARGET.getMessage(), placeHolders));
        }
    }
}
