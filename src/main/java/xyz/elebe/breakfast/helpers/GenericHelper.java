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

    public static void showActivationMessage(Player player) {
        player.sendSystemMessage(Component.literal(CommandConstants.PLAYER_OUTPUT_COMMAND_ACTIVATION));
    }

    public static void showDeactivationMessage(Player player) {
        player.sendSystemMessage(Component.literal(CommandConstants.PLAYER_OUTPUT_COMMAND_DEACTIVATION));
    }
}
