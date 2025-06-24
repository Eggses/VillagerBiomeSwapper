package me.Eggses.villagerBiomeSwapper.Utility;

import me.Eggses.villagerBiomeSwapper.Config.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.HashMap;
import java.util.Map;

public class MessageCreation {

    private final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();
    private final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public Component createMessage(String text, Map<String, String> placeHolders) {

        if (text == null) {
            text = "Missing Text Value!";
        }

        if (placeHolders == null) placeHolders = new HashMap<>();

        placeHolders.put(PlaceHolder.PREFIX.getPlaceHolder(), Messages.PREFIX.getMessage());

        for (Map.Entry<String, String> entry : placeHolders.entrySet()) {
            String placeHolder = entry.getKey();
            String value = entry.getValue();

            text = text.replace(placeHolder, value);
        }

        return applyColour(text);
    }

    private Component applyColour(String text) {

        Component message;

        if (text.matches(".*[§&][0-9a-fk-or].*")) {
            text = text.replace("§", "&");
            message = LEGACY_SERIALIZER.deserialize(text);
        }
        else {
            message = MINI_MESSAGE.deserialize(text);
        }

        return message;
    }
}