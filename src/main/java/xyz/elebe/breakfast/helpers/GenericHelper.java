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

    public static boolean isClientSide(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;
        return level.isClientSide;
    }

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
