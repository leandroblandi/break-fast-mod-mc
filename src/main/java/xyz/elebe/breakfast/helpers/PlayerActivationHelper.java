package xyz.elebe.breakfast.helpers;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerActivationHelper {
    private static final Map<UUID, Boolean> PLAYERS = new HashMap<>();

    public static boolean isPlayerActivated(Player player) {
        return PLAYERS.getOrDefault(player.getUUID(), false);
    }

    public static int togglePlayerActivation(Player player) {
        boolean value  = isPlayerActivated(player);
        PLAYERS.put(player.getUUID(), !value);
        GenericHelper.showToggleOutput(player, value);
        return 1;
    }

    public static void deactivatePlayer(Player player) {
        PLAYERS.put(player.getUUID(), false);
    }
}
