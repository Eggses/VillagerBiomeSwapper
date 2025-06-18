package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class HelpCommand implements SubCommand {

    private final MessageCreation messageCreation;

    public HelpCommand(MessageCreation messageCreation) {
        this.messageCreation = messageCreation;
    }

    @Override
    public void execute(CommandSender sender, String[] args, Map<String, String> placeHolders) {

        if (!sender.hasPermission(Permission.HELP.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return;
        }

        if (args.length > 1) {
            sender.sendMessage(messageCreation.createMessage(Messages.HELP_SYNTAX_ERROR.getMessage(), placeHolders));
            return;
        }

        sender.sendMessage(messageCreation.createMessage(Messages.HELP.getMessage(), placeHolders));
    }
}
