package me.Eggses.villagerBiomeSwapper.Utility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Map;

public class MessageCreation {

    private final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();
    private final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public Component createMessage(String text) {

        if (text == null) {
            text = "Missing text or message value";
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