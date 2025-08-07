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
     * Activates the Ultra Mining mode for the given player.
     * <p>
     * This method sets the activation status of Ultra Mining mode to true for the
     * provided player, indicating that the player is now in Ultra Mining mode.
     *
     * @param player the player whose Ultra Mining mode is to be activated
     * @return an integer value indicating the operation was performed
     */
    public static int activatePlayer(Player player) {
        PLAYERS.put(player.getUUID(), true);
        GenericHelper.showActivationMessage(player);
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
    public static int deactivatePlayer(Player player) {
        PLAYERS.put(player.getUUID(), false);
        GenericHelper.showDeactivationMessage(player);
        return 1;
    }
}
