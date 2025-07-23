package xyz.elebe.breakfast.helpers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.elebe.breakfast.commands.BreakFastCommand;
import xyz.elebe.breakfast.handlers.BreakFastEventsHandler;
import xyz.elebe.breakfast.utils.LogUtil;

public class GenericHelper {
    public static final Logger LOGGER = LoggerFactory.getLogger(BreakFastEventsHandler.MOD_ID);

    public static boolean isClientSide(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;
        return level.isClientSide;
    }

    public static void showToggleOutput(Player player, boolean value) {
        if (value) {
            player.sendSystemMessage(BreakFastCommand.COMMAND_ACTIVATION_MESSAGE);
            LOGGER.info(LogUtil.SERVER_LOG_ACTIVATED_MESSAGE, player.getDisplayName());
        } else {
            player.sendSystemMessage(BreakFastCommand.COMMAND_DEACTIVATION_MESSAGE);
            LOGGER.info(LogUtil.SERVER_LOG_DEACTIVATED_MESSAGE, player.getDisplayName());
        }
    }
}
