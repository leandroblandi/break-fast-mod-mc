package xyz.elebe.breakfast.helpers;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerActivationHelper {
    private static final Map<UUID, Boolean> PLAYERS = new HashMap<>();

    /**
     * Checks if the given player has Ultra Mining mode activated.
     *
     * @param player the player to check
     * @return true if the player has Ultra Mining mode activated, false otherwise
     */
    public static boolean isPlayerActivated(Player player) {
        return PLAYERS.getOrDefault(player.getUUID(), false);
    }

    /**
     * Toggles the Ultra Mining mode activation status for the given player.
     * <p>
     * If the player currently has Ultra Mining mode activated, it will be
     * deactivated, and vice versa. A message indicating the new status will
     * be sent to the player and logged to the server.
     *
     * @param player the player whose Ultra Mining mode status is to be toggled
     * @return an integer value indicating the operation was performed
     */
    public static int togglePlayerActivation(Player player) {
        boolean value  = isPlayerActivated(player);
        boolean newValue = !value;
        PLAYERS.put(player.getUUID(), newValue);
        GenericHelper.showToggleOutput(player, newValue);
        return 1;
    }

    /**
     * Deactivates Ultra Mining mode for the specified player.
     * <p>
     * This method sets the activation status of Ultra Mining mode to false for the
     * provided player, ensuring that the player is no longer in Ultra Mining mode.
     *
     * @param player the player whose Ultra Mining mode is to be deactivated
     */
    public static void deactivatePlayer(Player player) {
        PLAYERS.put(player.getUUID(), false);
    }
}
