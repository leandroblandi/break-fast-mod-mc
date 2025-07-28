package xyz.elebe.breakfast.helpers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.elebe.breakfast.constants.CommandConstants;
import xyz.elebe.breakfast.constants.LogConstants;
import xyz.elebe.breakfast.handlers.BreakFastEventsHandler;

public class GenericHelper {
    public static final Logger LOGGER = LoggerFactory.getLogger(BreakFastEventsHandler.MOD_ID);

    /**
     * Determines whether the block break event occurred on the client side.
     * <p>
     * This method checks if the level associated with the player in the given
     * block break event is client-side.
     *
     * @param event the block break event
     * @return true if the event is on the client side, false otherwise
     */
    public static boolean isClientSide(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;
        return level.isClientSide;
    }

    /**
     * Sends a system message to the player indicating that Ultra Mining mode has been
     * toggled on or off and logs the same information to the server log.
     * <p>
     * If the given value is true, the player receives a message indicating that
     * Ultra Mining mode has been enabled, and the server log will contain a message
     * indicating that the player activated Ultra Mining mode. Otherwise, the player
     * receives a message indicating that Ultra Mining mode has been disabled, and the
     * server log will contain a message indicating that the player deactivated Ultra
     * Mining mode.
     *
     * @param player the player to send the message to
     * @param value true if Ultra Mining mode is being enabled, false otherwise
     */
    public static void showToggleOutput(Player player, boolean value) {
        if (value) {
            player.sendSystemMessage(Component.literal(CommandConstants.PLAYER_OUTPUT_COMMAND_ACTIVATION));
            LOGGER.info(LogConstants.SERVER_LOG_ACTIVATED_MESSAGE, player.getDisplayName());
        } else {
            player.sendSystemMessage(Component.literal(CommandConstants.PLAYER_OUTPUT_COMMAND_DEACTIVATION));
            LOGGER.info(LogConstants.SERVER_LOG_DEACTIVATED_MESSAGE, player.getDisplayName());
        }
    }
}
